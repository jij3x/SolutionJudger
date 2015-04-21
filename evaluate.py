import subprocess
import sys
import os
import os.path
import json


lib_path = os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), '_commons'))
sys.path.append(lib_path)
import probdesc as m
import metatypes as t


tim = t.type_map

PYCMD = "python"
GENR_PATH = "JavaGenerator"


def no_filter(line):
    return line


def sllist_filter(line):
    sllist = json.loads(line)
    sllist[0:] = sllist[1::2]

    return json.dumps(sllist, separators=(",", ":"))


def slrlist_filter(line):
    if line == "[]":
        return line

    nodes = line[1:-1].split(",")
    finallist = nodes[:- len(nodes) // 3][1::2] + nodes[- len(nodes) // 3:]

    return "[{}]".format(",".join(finallist))


def udgraph_filter(line):
    graph = json.loads(line)
    for node in graph:
        node[0:] = node[1::2]

    return json.dumps(graph, separators=(",", ":"))


def binarytree_filter(line):
    tree = line[1:-1].split(",")
    filtered = []
    f = 0
    for n in tree:
        if n != "#":
            if f == 1:
                filtered.append(n)
            f ^= 1
        else:
            f = 0
            filtered.append(n)

    return "[{}]".format(",".join(filtered))


def binarytreevector_filter(line):
    if line == "[]":
        return line

    trees = line[1:-1].split("],[")
    for i in range(len(trees)):
        trees[i] = ("" if trees[i].startswith("[") else "[") + trees[i] + ("" if trees[i].endswith("]") else "]")
        trees[i] = binarytree_filter(trees[i])

    return "[{}]".format(",".join(trees))


def iintervalvec_filter(line):
    intervals = json.loads(line)
    for interval in intervals:
        interval[0:] = interval[1:]

    return json.dumps(intervals, separators=(",", ":"))


def general_grader(user_ans, ans_desc, answer):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    j = 0
    while i < len(user_ans["out"]):
        for k in range(ans_desc["imvar_cnt"]):
            dist = ans_desc["imvar_cnt"] + 2 + ans_desc["addout_cnt"]
            if user_ans["out"][k] != user_ans["out"][k + dist]:
                result["msg"] = "unchangeable input tampered"
                return result
        i += ans_desc["imvar_cnt"]

        result["execTime"] += float(user_ans["out"][i])
        i += 1

        result["finalOut"].append(globals()[ans_desc["out_filter"]](user_ans["out"][i]))
        if result["finalOut"][j] != answer[j]:
            result["msg"] = "answer is wrong"
            return result

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

        j += 1

    if j != len(answer):
        result["msg"] = "solution stopped at case #{}".format(j)
        return result

    result["rc"] = 0
    return result


def clonegraph_grader(user_ans, ans_desc, answer):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"] + 1

        graph = json.loads(user_ans["out"][i])
        for node in graph:
            seq_arr = node[0::2]
            filtered_node = node[1::2]
            if not (seq_arr[1:] == seq_arr[:-1] and seq_arr[0] == -1):
                result["msg"] = "new nodes are tampered"
                return result
            node[0:] = filtered_node[0:1] + sorted(filtered_node[1:])

        graph.sort()
        for node in graph:
            seq_arr = [-1] * len(node)
            node[0:] = [x for t in zip(seq_arr, node) for x in t]

        user_ans["out"][i] = json.dumps(graph, separators=(",", ":"))

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

    return general_grader(user_ans, ans_desc, answer)


def copyrandomlist_grader(user_ans, ans_desc, answer):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"] + 1

        line = user_ans["out"][i][1:-1]
        if line:
            slrlist = line.split(",")
            nodes = slrlist[:- len(slrlist) // 3]
            seq_arr = nodes[0::2]
            if not (seq_arr[1:] == seq_arr[:-1] and seq_arr[0] == "-1"):
                result["msg"] = "new nodes are tampered"
                return result

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

    return general_grader(user_ans, ans_desc, answer)


def wordladders_grader(user_ans, ans_desc, answer):
    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"] + 1

        arr = json.loads(user_ans["out"][i])
        arr.sort()
        user_ans["out"][i] = json.dumps(arr, separators=(",", ":"))

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

    return general_grader(user_ans, ans_desc, answer)


def sizedintarray_grader(user_ans, ans_desc, answer):
    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"] + 1

        arr = json.loads(user_ans["out"][i])
        arr = arr[:int(user_ans["out"][i + 1])]
        user_ans["out"][i] = json.dumps(arr, separators=(",", ":"))

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

    return general_grader(user_ans, ans_desc, answer)


def evaluate(problem_path, debug):
    #
    # Find problem metadata
    #
    metadata_fname = ""
    for file in os.listdir(problem_path):
        if file.endswith(".json"):
            metadata_fname = os.path.join(problem_path, file)
            break

    #
    # Generate Driver.java, and pick grader
    #
    with open("Driver.java", "w") as driver, open(metadata_fname, "r") as metadata_file:
        subprocess.call([PYCMD, os.path.join(GENR_PATH, "gen_java_driver.py")], stdin=metadata_file, stdout=driver)
        metadata_file.seek(0, 0)
        metadata = json.load(metadata_file)
        m.complete_metadata(metadata)

        grader = general_grader
        grader_board = {"clonegraph_judge": clonegraph_grader,
                        "wordladders_judge": wordladders_grader,
                        "sizedintarray_judge": sizedintarray_grader,
                        "copyrandomlist_judge": copyrandomlist_grader}
        if "judge" in metadata and metadata["judge"] in grader_board:
            grader = grader_board[metadata["judge"]]

    #
    # Compose Solution.java
    #
    with open(os.path.join(GENR_PATH, "java.solution.imports"), "r") as sol_imports, \
            open(os.path.join(problem_path, "Solution.java"), "r") as sol_code, \
            open("Solution.java", "w") as final_solution:
        final_solution.write(sol_imports.read())
        final_solution.write(sol_code.read())

    #
    # Compile Driver and Solution, then run solution
    #
    subprocess.call(["javac", "Solution.java"])
    subprocess.call(["javac", "Driver.java"])
    with open(os.path.join(problem_path, "user.in"), "r") as test_data, \
            open(os.path.join(problem_path, "user.out"), "r") as answer:
        sp = subprocess.Popen(["java", "Driver"], stdin=test_data, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sol_out, sol_err = sp.communicate()

        user_ans = {"return_code": 0,
                    "out": sol_out.decode("utf-8").splitlines(),
                    "err": sol_err.decode("utf-8").splitlines()}
        ans_desc = {"imvar_cnt": m.unchangeable_param_cnt(metadata),
                    "addout_cnt": len(metadata[m.ADO]) if m.ADO in metadata else 0,
                    "out_filter": tim[m.get_prop(metadata, metadata[m.OUT])[m.TYP]][t.P_OFLTR]}
        result = grader(user_ans, ans_desc, answer.read().splitlines())

        if debug:
            with open("user.out", "w") as userout_file, open("user.out.unfiltered", "w") as progout_file, \
                    open("user.err", "w") as err_file:
                userout_file.write("\n".join(result["finalOut"]) + "\n")
                progout_file.write("\n".join(user_ans["out"]) + "\n")
                err_file.write("\n".join(user_ans["err"]) + "\n")

    #
    # Print out testing result
    #
    passed = "\033[92m" + "Passed" + "\033[0m"
    failed = "\033[91m" + "Failed" + "\033[0m"
    print(problem_path + " - ", end="")
    print("runtime: %.5fms %s" % (result["execTime"], passed if result["rc"] == 0 else failed))

    return 0 if result["rc"] == 0 else 1

