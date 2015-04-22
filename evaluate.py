import subprocess
import sys
import os
import os.path
import json
import functools

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


R_RC = "return_code"
R_ERR = "error"
R_CI = "current_input"
R_EXP = "expecting_ans"
R_WA = "wrong_ans"
R_ANS = "answer"
R_ET = "exec_time"

RC_AC = "Accepted"
RC_WA = "Wrong Answer"
RC_TLE = "Time Limit Exceeded"
RC_MLE = "Memory Limit Exceeded"
RC_OLE = "Output Limit Exceeded"
RC_RE = "Runtime Error"
RC_CE = "Compile Error"
RC_IC = "Input has been changed"
RC_OT = "Return object has been tampered"


def general_grader(user_ans, formatted_in, answer):
    result = {R_RC: RC_AC, R_ERR: "", R_CI: "", R_EXP: "", R_WA: "", R_ANS: [],  R_ET: 0}

    if user_ans[ANS_ERR]:
        result[R_RC] = RC_RE
        result[R_CI] = formatted_in[len(user_ans[ANS_OUT])]
        return result

    for i in range(len(user_ans[ANS_OUT])):
        if user_ans[ANS_OUT][i] != answer[i]:
            result[R_RC] = RC_WA
            result[R_EXP] = answer[i]
            result[R_WA] = user_ans[ANS_OUT][i]
            result[R_CI] = formatted_in[i]
            return result

    if user_ans[ANS_IVC] > 0:
        for i in range(len(user_ans[ANS_BIV])):
            if user_ans[ANS_BIV][i] != user_ans[ANS_AIV][i]:
                result[R_RC] = RC_IC
                return result

    result[R_ET] = functools.reduce(lambda x, y: x + int(y), user_ans[ANS_ET], 0)

    for line in user_ans[ANS_OUT]:
        result[R_ANS].append(globals()[user_ans[ANS_OF]](line))

    result[R_RC] = RC_AC
    return result


def clonegraph_grader(user_ans, formatted_in, answer):
    result = {R_RC: RC_AC, R_ERR: "", R_CI: "", R_EXP: "", R_WA: "", R_ANS: [],  R_ET: 0}

    for i in range(user_ans[ANS_OUT]):
        graph = json.loads(user_ans[ANS_OUT][i])
        for node in graph:
            seq_arr = node[0::2]
            filtered_node = node[1::2]
            if not (seq_arr[1:] == seq_arr[:-1] and seq_arr[0] == -1):
                result[R_RC] = RC_OT
                return result
            node[0:] = filtered_node[0:1] + sorted(filtered_node[1:])

        graph.sort()
        for node in graph:
            seq_arr = [-1] * len(node)
            node[0:] = [x for t in zip(seq_arr, node) for x in t]

        user_ans[ANS_OUT][i] = json.dumps(graph, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


def copyrandomlist_grader(user_ans, formatted_in, answer):
    result = {R_RC: RC_AC, R_ERR: "", R_CI: "", R_EXP: "", R_WA: "", R_ANS: [],  R_ET: 0}

    for i in range(user_ans[ANS_OUT]):
        line = user_ans[ANS_OUT][i][1:-1]
        if line:
            slrlist = line.split(",")
            nodes = slrlist[:- len(slrlist) // 3]
            seq_arr = nodes[0::2]
            if not (seq_arr[1:] == seq_arr[:-1] and seq_arr[0] == "-1"):
                result[R_RC] = RC_OT
                return result

    return general_grader(user_ans, formatted_in, answer)


def wordladders_grader(user_ans, formatted_in, answer):
    for i in range(user_ans[ANS_OUT]):
        arr = json.loads(user_ans[ANS_OUT][i])
        arr.sort()
        user_ans[ANS_OUT][i] = json.dumps(arr, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


def sizedintarray_grader(user_ans, formatted_in, answer):
    for i in range(user_ans[ANS_OUT]):
        arr = json.loads(user_ans[ANS_OUT][i])
        arr = arr[:int(user_ans[ANS_AO][i])]
        user_ans[ANS_OUT][i] = json.dumps(arr, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


ANS_RC = "return_code"
ANS_OUT = "user_out"
ANS_ERR = "user_err"
ANS_IVC = "immutable_var_cnt"
ANS_AOC = "additional_out_cnt"
ANS_AO = "additional_out"
ANS_OF = "out_filter"
ANS_ET = "execution_time"
ANS_BIV = "immutable_var_before_exec"
ANS_AIV = "immutable_var_after_exec"


def get_user_ans(sol_out, metadata):
    user_ans = {ANS_RC: 0,
                ANS_IVC: m.unchangeable_param_cnt(metadata),
                ANS_AOC: len(metadata[m.ADO]) if m.ADO in metadata else 0,
                ANS_OF: tim[m.get_prop(metadata, metadata[m.OUT])[m.TYP]][t.P_OFLTR],
                ANS_BIV: [],
                ANS_ET: [],
                ANS_OUT: [],
                ANS_AO: [],
                ANS_AIV: []}

    segment = user_ans[ANS_IVC] * 2 + 2 + user_ans[ANS_AOC]
    if len(sol_out) % segment != 0:
        sol_out += [""] * (segment - len(sol_out) % segment)
    for i in range(0, len(sol_out), segment):
        user_ans[ANS_BIV].append(sol_out[i: i + user_ans[ANS_IVC]])
        user_ans[ANS_ET].append(sol_out[i + user_ans[ANS_IVC]: i + user_ans[ANS_IVC] + 1])
        user_ans[ANS_OUT].append(sol_out[i + user_ans[ANS_IVC] + 1: i + user_ans[ANS_IVC] + 2])
        user_ans[ANS_AO].append(sol_out[i + user_ans[ANS_IVC] + 2: i + user_ans[ANS_IVC] + 2 + user_ans[ANS_AOC]])
        user_ans[ANS_AIV].append(sol_out[i + user_ans[ANS_IVC] + 2 + user_ans[ANS_AOC]:])

    return user_ans


def eval_java_sol(problem_path):
    # Find problem metadata
    metadata_fname = ""
    for file in os.listdir(problem_path):
        if file.endswith(".json"):
            metadata_fname = os.path.join(problem_path, file)
            break

    # Generate Driver.java, and load the problem metadata
    with open("Driver.java", "w") as driver, open(metadata_fname, "r") as metadata_file:
        subprocess.call([PYCMD, os.path.join(GENR_PATH, "gen_java_driver.py")], stdin=metadata_file, stdout=driver)
        metadata_file.seek(0, 0)
        metadata = json.load(metadata_file)
        m.complete_metadata(metadata)

    # Compose Solution.java
    with open(os.path.join(GENR_PATH, "java.solution.imports"), "r") as sol_imports, \
            open(os.path.join(problem_path, "Solution.java"), "r") as sol_code, \
            open("Solution.java", "w") as final_solution:
        final_solution.write(sol_imports.read())
        final_solution.write(sol_code.read())

    # Compile Driver and Solution, then run solution
    subprocess.call(["javac", "Solution.java"])
    subprocess.call(["javac", "Driver.java"])
    with open(os.path.join(problem_path, "user.in"), "r") as test_data, \
            open(os.path.join(problem_path, "user.out"), "r") as answer:
        sp = subprocess.Popen(["java", "Driver"], stdin=test_data, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sol_out, sol_err = sp.communicate()

        user_ans = get_user_ans(sol_out.decode("utf-8").splitlines(), metadata)
        user_ans[ANS_ERR] = sol_err.decode("utf-8")

        formatted_in = ""
        return globals()[metadata[m.GDR]](user_ans, formatted_in, answer)
