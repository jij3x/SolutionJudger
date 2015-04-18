import json


def no_filter(line):
    return line


def udgraph_filter(line):
    graph = json.loads(line)
    for node in graph:
        node[0:] = node[1::2]

    return json.dumps(graph, separators=(",", ":"))


def general(user_ans, ans_desc, answer):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    j = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"]
        result["execTime"] += float(user_ans["out"][i])
        i += 1

        result["finalOut"].append(globals()[ans_desc["out_filter"]](user_ans["out"][i]))
        if result["finalOut"][j] != answer[j]:
            return result

        i += 1
        i += ans_desc["imvar_cnt"]
        i += ans_desc["addout_cnt"]
        j += 1

    result["rc"] = 0
    return result


def clonegraph(user_ans, ans_desc, answer):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"]
        i += 1

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

        i += 1
        i += ans_desc["imvar_cnt"]
        i += ans_desc["addout_cnt"]

    return general(user_ans, ans_desc, answer)


def wordladders(user_ans, ans_desc, answer):
    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"]
        i += 1

        arr = json.loads(user_ans["out"][i])
        arr.sort()
        user_ans["out"][i] = json.dumps(arr, separators=(",", ":"))

        i += 1
        i += ans_desc["imvar_cnt"]
        i += ans_desc["addout_cnt"]

    return general(user_ans, ans_desc, answer)


def sizedintarray(user_ans, ans_desc, answer):
    i = 0
    while i < len(user_ans["out"]):
        i += ans_desc["imvar_cnt"]
        i += 1

        arr = json.loads(user_ans["out"][i])
        arr = arr[:int(user_ans["out"][i + 1])]
        user_ans["out"][i] = json.dumps(arr, separators=(",", ":"))

        i += 1
        i += ans_desc["imvar_cnt"]
        i += ans_desc["addout_cnt"]

    return general(user_ans, ans_desc, answer)
