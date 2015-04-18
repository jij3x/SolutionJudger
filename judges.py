import os
import sys
import json


lib_path = os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), '_commons'))
sys.path.append(lib_path)
import probdesc as m
import metatypes as t


tim = t.type_map


def noFilter(line):
    return line


def udgraphFilter(line):
    graph = json.loads(line)
    for node in graph:
        node[0:] = node[1::2]

    return json.dumps(graph, separators=(",", ":"))


def general(user_ans, answer, problem_md):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    imvar_cnt = 0
    addout_cnt = len(problem_md[m.ADO]) if m.ADO in problem_md else 0

    i = 0
    j = 0
    while i < len(user_ans["user_out"]):
        i += imvar_cnt
        result["execTime"] += float(user_ans["user_out"][i])
        i += 1

        output_filter = tim[m.get_prop(problem_md, problem_md[m.OUT])[m.TYP]][t.P_OFLTR]
        result["finalOut"].append(globals()[output_filter](user_ans["user_out"][i]))
        if result["finalOut"][j] != answer[j]:
            return result
        i += 1
        i += addout_cnt
        i += imvar_cnt
        j += 1

    result["rc"] = 0
    return result


def clonegraph(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    while i < len(user_out):
        i += imvar_num
        i += 1

        graph = json.loads(user_out[i])
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

        user_out[i] = json.dumps(graph, separators=(",", ":"))

        i += 1
        i += addout_num
        i += imvar_num

    return general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md)


def wordladders(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md):
    i = 0
    while i < len(user_out):
        i += imvar_num
        i += 1

        arr = json.loads(user_out[i])
        arr.sort()
        user_out[i] = json.dumps(arr, separators=(",", ":"))

        i += 1
        i += addout_num
        i += imvar_num

    return general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md)


def sizedintarray(user_ans, answer, problem_md):
    i = 0
    while i < len(user_ans["out"]):
        i += imvar_num
        i += 1

        arr = json.loads(user_out[i])
        arr = arr[:int(user_out[i + 1])]
        user_out[i] = json.dumps(arr, separators=(",", ":"))

        i += 1
        i += addout_num
        i += imvar_num

    return general(user_ans, answer, problem_md)
