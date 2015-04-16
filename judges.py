import json


def general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in):
    result = {"rc": -1, "msg": "", "execTime": 0}

    i = 0
    j = 0
    while i < len(user_out):
        i += imvar_num
        result["execTime"] += float(user_out[i])
        i += 1
        if user_out[i] != answer[j]:
            return result
        i += 1
        i += addout_num
        i += imvar_num
        j += 1

    result["rc"] = 0
    return result


def clonegraph(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in):
    sorted_out = []
    for line in user_out:
        arr = json.loads(line)
        for node in arr:
            node[1:] = sorted(node[1:])
        arr.sort()
        sorted_out.append(json.dumps(arr, separators=(",", ":")))
    return general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in)


def wordladders(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in):
    sorted_out = []
    for line in user_out:
        arr = json.loads(line)
        arr.sort()
        sorted_out.append(json.dumps(arr, separators=(",", ":")))
    return general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in)


def sizedintarray(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in):
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

    return general(imvar_num, addout_num, user_out, return_code, user_err, answer, user_in)
