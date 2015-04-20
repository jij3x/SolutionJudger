import json


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


def general(user_ans, ans_desc, answer):
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


def clonegraph(user_ans, ans_desc, answer):
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

    return general(user_ans, ans_desc, answer)


def copyrandomlist(user_ans, ans_desc, answer):
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

    return general(user_ans, ans_desc, answer)


def wordladders(user_ans, ans_desc, answer):
    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"] + 1

        arr = json.loads(user_ans["out"][i])
        arr.sort()
        user_ans["out"][i] = json.dumps(arr, separators=(",", ":"))

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

    return general(user_ans, ans_desc, answer)


def sizedintarray(user_ans, ans_desc, answer):
    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"] + 1

        arr = json.loads(user_ans["out"][i])
        arr = arr[:int(user_ans["out"][i + 1])]
        user_ans["out"][i] = json.dumps(arr, separators=(",", ":"))

        i += 1 + ans_desc["addout_cnt"] + ans_desc["imvar_cnt"]

    return general(user_ans, ans_desc, answer)
