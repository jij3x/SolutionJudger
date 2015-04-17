import os
import sys
import json


lib_path = os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), '_commons'))
sys.path.append(lib_path)
import probdesc as m
import metatypes as t


tim = t.type_map


def udgraphFilter(line):
    return line[1:][::2]


def general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md):
    result = {"rc": -1, "msg": "", "execTime": 0, "finalOut": []}

    i = 0
    j = 0
    while i < len(user_out):
        i += imvar_num
        result["execTime"] += float(user_out[i])
        i += 1

        output_filter = tim[m.get_prop(problem_md, problem_md[m.OUT])[m.TYP]][t.P_OFLTR]
        result["finalOut"].append(eval("{}({})".format(output_filter, user_out[i])))
        if result["finalOut"][j] != answer[j]:
            return result
        i += 1
        i += addout_num
        i += imvar_num
        j += 1

    result["rc"] = 0
    return result


def clonegraph(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md):
    i = 0
    while i < len(user_out):
        i += imvar_num
        i += 1

        arr = json.loads(user_out[i])
        for node in arr:
            node[1:] = sorted(node[1:])
        arr.sort()
        user_out[i] = json.dumps(arr, separators=(",", ":"))

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


def sizedintarray(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md):
    i = 0
    while i < len(user_out):
        i += imvar_num
        i += 1

        arr = json.loads(user_out[i])
        arr = arr[:int(user_out[i + 1])]
        user_out[i] = json.dumps(arr, separators=(",", ":"))

        i += 1
        i += addout_num
        i += imvar_num

    return general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in, problem_md)
