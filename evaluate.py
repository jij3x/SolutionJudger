import subprocess
import sys
import os
import re
import os.path
import shutil
import json
import functools

lib_path = os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), '_commons'))
sys.path.append(lib_path)
import probdesc as m
import metatypes as t
from environment import *

tim = t.type_map


def no_filter(line):
    return line


def sllist_filter(line):
    sllist = json.loads(line)
    sllist[0:] = sllist[1::2]

    return json.dumps(sllist, separators=(",", ":"))


def sllistvector_filter(line):
    vector = json.loads(line)
    for sllist in vector:
        sllist[0:] = sllist[1::2]

    return json.dumps(vector, separators=(",", ":"))


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


def pair_filter(line):
    pair = json.loads(line)
    return json.dumps(pair[1:], separators=(",", ":"))


def pairvector_filter(line):
    pairs = json.loads(line)
    for pair in pairs:
        pair[0:] = pair[1:]

    return json.dumps(pairs, separators=(",", ":"))


R_RC = "return_code"
R_ERR = "error"
R_PROBIN = "problematic_in"
R_EXPANS = "expecting_ans"
R_WANS = "wrong_answer"
R_USEROUT = "user_out"
R_EXECTIME = "exec_time"

AC = "Accepted"
WA = "Wrong Answer"
TLE = "Time Limit Exceeded"
MLE = "Memory Limit Exceeded"
OLE = "Output Limit Exceeded"
RE = "Runtime Error"
CE = "Compile Error"
IC = "Input has been changed"
OT = "Return object has been tampered"
ML = "Memory Leaked"


def general_grader(user_ans, formatted_in, answer):
    result = {R_USEROUT: [], R_EXECTIME: 0}

    if user_ans[UA_ERR]:
        result[R_RC] = RE
        result[R_ERR] = user_ans[UA_ERR]
        result[R_PROBIN] = formatted_in[len(user_ans[UA_OUT]) - 1]
        return result

    for line in user_ans[UA_OUT]:
        result[R_USEROUT].append(globals()[user_ans[UA_OUTF]](line))

    for i in range(len(result[R_USEROUT])):
        if result[R_USEROUT][i] != answer[i]:
            result[R_RC] = WA
            result[R_EXPANS] = answer[i]
            result[R_WANS] = result[R_USEROUT][i]
            result[R_PROBIN] = formatted_in[i]
            return result

    if user_ans[UA_IMVARCNT] > 0:
        for i in range(len(user_ans[UA_PREIMVAR])):
            if user_ans[UA_PREIMVAR][i] != user_ans[UA_POSTIMVAR][i]:
                result[R_RC] = IC
                return result

    result[R_EXECTIME] = [float(t) for t in functools.reduce(
        lambda x, y: " ".join([str(sum(map(float, t))) for t in zip(x.split(), y.split())]),
        user_ans[UA_EXECTIME], "0 " * len(user_ans[UA_EXECTIME][0])).split()]

    result[R_RC] = AC
    return result


def clonegraph_grader(user_ans, formatted_in, answer):
    result = {}

    for i in range(len(user_ans[UA_OUT])):
        graph = json.loads(user_ans[UA_OUT][i])
        for node in graph:
            seq_arr = node[0::2]
            filtered_node = node[1::2]
            if not (seq_arr[1:] == seq_arr[:-1] and seq_arr[0] == -1):
                result[R_RC] = OT
                return result
            node[0:] = filtered_node[0:1] + sorted(filtered_node[1:])

        graph.sort()
        for node in graph:
            seq_arr = [-1] * len(node)
            node[0:] = [x for t in zip(seq_arr, node) for x in t]

        user_ans[UA_OUT][i] = json.dumps(graph, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


def copyrandomlist_grader(user_ans, formatted_in, answer):
    result = {}

    for i in range(len(user_ans[UA_OUT])):
        line = user_ans[UA_OUT][i][1:-1]
        if line:
            slrlist = line.split(",")
            nodes = slrlist[:- len(slrlist) // 3]
            seq_arr = nodes[0::2]
            if not (seq_arr[1:] == seq_arr[:-1] and seq_arr[0] == "-1"):
                result[R_RC] = OT
                return result

    return general_grader(user_ans, formatted_in, answer)


def unsorted_vec_grader(user_ans, formatted_in, answer):
    for i in range(len(user_ans[UA_OUT])):
        arr = json.loads(user_ans[UA_OUT][i])
        arr.sort()
        user_ans[UA_OUT][i] = json.dumps(arr, separators=(",", ":"))
        arr = json.loads(answer[i])
        arr.sort()
        answer[i] = json.dumps(arr, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


def sizedintarray_grader(user_ans, formatted_in, answer):
    for i in range(len(user_ans[UA_OUT])):
        arr = json.loads(user_ans[UA_OUT][i])
        arr = arr[:int(user_ans[UA_ADDOUT][i])]
        user_ans[UA_OUT][i] = json.dumps(arr, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


def balancedbst_grader(user_ans, formatted_in, answer):
    class BinaryTreeNode:
        def __init__(self, val):
            self.val, self.left, self.right = val, None, None

    def isvalidbst(root):
        prev, stack = None, []
        while root is not None or stack:
            if root is None:
                root = stack.pop()
                if prev is not None and prev >= int(root.val):
                    return False
                prev = int(root.val)
                root = root.right
            else:
                while root is not None:
                    stack.append(root)
                    root = root.left
        return True

    def isbalanced(root):
        prev, stack, heights = None, [], {}
        while root is not None or stack:
            if root is not None:
                stack.append(root)
                root = root.left
            elif stack[-1].right is None or stack[-1].right == prev:
                prev = stack.pop()
                lh = 0 if prev.left is None else heights[prev.left]
                rh = 0 if prev.right is None else heights[prev.right]
                if abs(lh - rh) > 1:
                    return False
                heights[prev] = max(lh, rh) + 1
            else:
                root = stack[-1].right
        return True

    def deserbst(line):
        if not line:
            return None

        parts, buff, curr, flag = line.split(","), [BinaryTreeNode(0)], 0, 1
        while parts:
            newnode = None if parts.pop(0) == "#" else BinaryTreeNode(parts.pop(0))
            buff.extend([] if newnode is None else [newnode])

            buff[curr].right = newnode if flag == 1 else buff[curr].right
            buff[curr].left = newnode if flag == 0 else buff[curr].left
            curr += flag
            flag ^= 1
        return buff[1]

    def addseqno(line):
        if line == "[]":
            return line

        r, nodes = [], line[1:-1].split(",")
        for node in nodes:
            r.extend(["-1", node] if node != "#" else ["#"])
        return "[{}]".format(",".join(r))

    for i in range(len(user_ans[UA_OUT])):
        tree = deserbst(user_ans[UA_OUT][i][1:-1])
        if isvalidbst(tree) and isbalanced(tree):
            user_ans[UA_OUT][i] = addseqno(answer[i])

    return general_grader(user_ans, formatted_in, answer)


def metrics_reorg_grader(user_ans, formatted_in, answer):
    for i in range(len(user_ans[UA_ADDOUT])):
        exec_dur = float(user_ans[UA_ADDOUT][i][1:-1].split(",")[0])
        metrics = user_ans[UA_EXECTIME][i].split()
        metrics[0] = str(float(metrics[0]) + (float(metrics[3]) - exec_dur))
        metrics[3] = str(exec_dur)
        user_ans[UA_EXECTIME][i] = " ".join(metrics)

    return general_grader(user_ans, formatted_in, answer)


UA_OUT = "user_out"
UA_ERR = "user_err"
UA_IMVARCNT = "immutable_var_cnt"
UA_ADDOUTCNT = "additional_out_cnt"
UA_ADDOUT = "additional_out"
UA_OUTF = "out_filter"
UA_EXECTIME = "execution_time"
UA_PREIMVAR = "immutable_var_before_exec"
UA_POSTIMVAR = "immutable_var_after_exec"


def get_user_ans(sol_out, metadata):
    out_type = m.get_prop(metadata, metadata[m.OUT])[m.TYPE]
    user_ans = {UA_IMVARCNT: m.unchangeable_param_cnt(metadata),
                UA_ADDOUTCNT: len(metadata[m.ADO]) if m.ADO in metadata else 0,
                UA_OUTF: tim[out_type][t.P_OFLTR] if t.P_OFLTR in tim[out_type] else "no_filter",
                UA_PREIMVAR: [],
                UA_EXECTIME: [],
                UA_OUT: [],
                UA_ADDOUT: [],
                UA_POSTIMVAR: []}

    iv_cnt = user_ans[UA_IMVARCNT]
    ao_cnt = user_ans[UA_ADDOUTCNT]
    segment = iv_cnt * 2 + 2 + ao_cnt
    if len(sol_out) % segment != 0:
        sol_out += [""] * (segment - len(sol_out) % segment)
    for i in range(0, len(sol_out), segment):
        user_ans[UA_PREIMVAR].extend(sol_out[i: i + iv_cnt])
        user_ans[UA_OUT].extend(sol_out[i + iv_cnt: i + iv_cnt + 1])
        user_ans[UA_ADDOUT].extend(sol_out[i + iv_cnt + 1: i + iv_cnt + 1 + ao_cnt])
        user_ans[UA_POSTIMVAR].extend(sol_out[i + iv_cnt + 1 + ao_cnt: i + segment - 1])
        user_ans[UA_EXECTIME].extend(sol_out[i + segment - 1: i + segment])

    return user_ans


def get_metadata_fname(problem_path):
    metadata_fname = ""
    for file in os.listdir(problem_path):
        if file.endswith(".json"):
            metadata_fname = os.path.join(problem_path, file)
            return metadata_fname


def get_grade(metadata, sol_out, sol_err, answer):
    user_ans = get_user_ans(sol_out.decode("utf-8").splitlines(), metadata)
    user_ans[UA_ERR] = sol_err.decode("utf-8")

    formatted_in = len(user_ans[UA_OUT]) * [""]
    return globals()[metadata[m.GRAD]](user_ans, formatted_in, answer.read().splitlines())


def eval_java_sol(problem_path):
    metadata_fname = get_metadata_fname(problem_path)

    # Generate Driver.java
    with open("Driver.java", "w") as driver, open(metadata_fname, "r") as metadata_file:
        subprocess.call([PYCMD, os.path.join(GENR_PATH["java"], "gen_java_driver.py")],
                        stdin=metadata_file, stdout=driver)
        metadata_file.seek(0, 0)
        metadata = json.load(metadata_file)
        m.complete_metadata(metadata)

    # Compose class files and compile
    classes = [metadata[m.SOL][m.NAME] + ".java"]
    if m.CLS in metadata:
        for cls in metadata[m.CLS]:
            classes.append(cls[m.NAME] + ".java")
    for fname in classes:
        with open(os.path.join(GENR_PATH["java"], "java.imports"), "r") as java_imports, \
                open(os.path.join(problem_path, fname), "r") as class_code, open(fname, "w") as final_class:
            final_class.write(java_imports.read())
            final_class.write(class_code.read())

    # Compile Driver, then execute
    subprocess.call(["javac", "Solution.java"])  # compile the new Solution.java
    subprocess.call(["javac", "Driver.java"])
    with open(os.path.join(problem_path, "user.in"), "r") as test_data, \
            open(os.path.join(problem_path, "user.out"), "r") as answer:
        sp = subprocess.Popen(["java", "Driver"], stdin=test_data, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sol_out, sol_err = sp.communicate()
        return get_grade(metadata, sol_out, sol_err, answer)


def eval_ruby_sol(problem_path):
    metadata_fname = get_metadata_fname(problem_path)

    # Generate driver.rb
    with open("driver.rb", "w") as driver, open(metadata_fname, "r") as metadata_file:
        sp = subprocess.Popen([PYCMD, os.path.join(GENR_PATH["ruby"], "gen_ruby_driver.py")],
                              stdin=metadata_file, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        drv_out, drv_err = sp.communicate()
        metadata_file.seek(0, 0)
        metadata = json.load(metadata_file)
        m.complete_metadata(metadata)

        classes = [metadata[m.SOL][m.NAME]]
        if m.CLS in metadata:
            for cls in metadata[m.CLS]:
                classes.append(cls[m.NAME])

        classes_pos = "# class requires inject here"
        class_requires = ""
        for clazz in classes:
            class_requires += "require '{}'\n".format(clazz)
            shutil.copy(os.path.join(problem_path, clazz + ".rb"), ".")
        driver.write(re.sub(re.escape(classes_pos) + r".*?\n", class_requires, drv_out.decode("utf-8")))

    # Run driver.rb
    with open(os.path.join(problem_path, "user.in"), "r") as test_data, \
            open(os.path.join(problem_path, "user.out"), "r") as answer:
        sp = subprocess.Popen(["ruby", "driver.rb"], stdin=test_data, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sol_out, sol_err = sp.communicate()
        return get_grade(metadata, sol_out, sol_err, answer)


eval_sol = {"java": eval_java_sol, "ruby": eval_ruby_sol}
