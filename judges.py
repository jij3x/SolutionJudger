def general(user_out, user_err, answer):
    if len(user_out) != len(answer) or (len(user_err) > 0 and len(user_err) != len(user_out)):
        return "Failed!"
    for i in range(0, len(user_out)):
        if user_out[i] != answer[i] or (len(user_err) > 0 and len[i] != "\n"):
            return "Failed"
    return "Accepted"


def clonegraph(user_out, user_err, answer):
    result = general(user_out, user_err, answer)
    if result != "Accept":
        return result


def wordladders(user_out, user_err, answer):
    result = general(user_out, user_err, answer)
    if result != "Accept":
        return result


