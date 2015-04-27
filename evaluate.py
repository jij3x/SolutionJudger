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


TIM = t.type_map
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

    result[R_EXECTIME] = functools.reduce(lambda x, y: x + float(y), user_ans[UA_EXECTIME], 0)

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

    return general_grader(user_ans, formatted_in, answer)


def sizedintarray_grader(user_ans, formatted_in, answer):
    for i in range(len(user_ans[UA_OUT])):
        arr = json.loads(user_ans[UA_OUT][i])
        arr = arr[:int(user_ans[UA_ADDOUT][i])]
        user_ans[UA_OUT][i] = json.dumps(arr, separators=(",", ":"))

    return general_grader(user_ans, formatted_in, answer)


def unnormbst_grader(user_ans, formatted_in, answer):
    class BinaryTreeNode:
        def __init__(self, val):
            self.val = val
            self.left = None
            self.right = None

    def inordertraversal(root):
        if not root:
            return []
        result = inordertraversal(root.left)
        result.extend([root.val])
        result.extend(inordertraversal(root.right))
        return result

    def buildnormbst(nums, left, right):
        if left > right:
            return None
        mid = round((left + right) / 2 + 0.01)
        root = BinaryTreeNode(nums[mid])
        root.left = buildnormbst(nums, left, mid - 1)
        root.right = buildnormbst(nums, mid + 1, right)
        return root

    def serbst(root):
        if not root:
            return "[]"

        q = [root]
        ser = ["-1", root.val]
        while q:
            curr = q.pop(0)
            if not curr.left:
                ser.append("#")
            else:
                q.append(curr.left)
                ser.extend(["-1", curr.left.val])

            if not curr.right:
                ser.append("#")
            else:
                q.append(curr.right)
                ser.extend(["-1", curr.right.val])

        while ser[-1] == "#":
            ser = ser[:-1]
        return "[{}]".format(",".join(ser))

    def deserbst(line):
        if not line:
            return None
        parts = line.split(",")
        buff = [BinaryTreeNode(0)]
        curr = 0
        flag = 1
        while parts:
            if parts.pop(0) == "#":
                newnode = None
            else:
                newnode = BinaryTreeNode(parts.pop(0))
                buff.append(newnode)

            if flag == 1:
                buff[curr].right = newnode
                curr += 1
                flag = 0
            else:
                buff[curr].left = newnode
                flag = 1

        return buff[1]

    for i in range(len(user_ans[UA_OUT])):
        bst = deserbst(user_ans[UA_OUT][i][1:-1])
        vals = inordertraversal(bst)
        user_ans[UA_OUT][i] = serbst(buildnormbst(vals, 0, len(vals) - 1))

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
    user_ans = {UA_IMVARCNT: m.unchangeable_param_cnt(metadata),
                UA_ADDOUTCNT: len(metadata[m.ADO]) if m.ADO in metadata else 0,
                UA_OUTF: TIM[m.get_prop(metadata, metadata[m.OUT])[m.TYP]][t.P_OFLTR],
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
        user_ans[UA_EXECTIME].extend(sol_out[i + iv_cnt: i + iv_cnt + 1])
        user_ans[UA_OUT].extend(sol_out[i + iv_cnt + 1: i + iv_cnt + 2])
        user_ans[UA_ADDOUT].extend(sol_out[i + iv_cnt + 2: i + iv_cnt + 2 + ao_cnt])
        user_ans[UA_POSTIMVAR].extend(sol_out[i + iv_cnt + 2 + ao_cnt: i + segment])

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
        user_ans[UA_ERR] = sol_err.decode("utf-8")

        formatted_in = len(user_ans[UA_OUT]) * [""]
        return globals()[metadata[m.GDR]](user_ans, formatted_in, answer.read().splitlines())
