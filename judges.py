import json


def general(user_out, user_err, answer):
    if len(user_out) != len(answer) or (len(user_err) > 0 and len(user_err) != len(user_out)):
        print("size mismatch")
        return "Failed!"
    for i in range(0, len(user_out)):
        if user_out[i] != answer[i] or (len(user_err) > 0 and user_err[i] != ""):
            return "Failed"
    return "Accepted"


def clonegraph(user_out, user_err, answer):
    sorted_out = []
    for line in user_out:
        arr = json.loads(line)
        for node in arr:
            node[1:] = sorted(node[1:])
        arr.sort()
        sorted_out.append(json.dumps(arr, separators=(",", ":")))
    result = general(sorted_out, user_err, answer)
    if result != "Accept":
        return result


def wordladders(user_out, user_err, answer):
    sorted_out = []
    for line in user_out:
        arr = json.loads(line)
        arr.sort()
        sorted_out.append(json.dumps(arr, separators=(",", ":")))
    result = general(sorted_out, user_err, answer)
    if result != "Accept":
        return result
