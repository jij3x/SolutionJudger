import re
import metatypes as t

INP = "input"
NAME = "name"
TYPE = "type"
SOL = "solution"
PARM = "params"
FUNC = "actionName"
RET = "return"
OUT = "output"
PREP = "preProcr"
POSP = "postProcr"
ADO = "additionalOutput"
VARN = "variableName"
MTDS = "methods"
MCOL = "mutableCollection"
GRAD = "grader"
CLS = "classes"

tim = t.type_map


def add_changeable_flag(param):
    if param[TYPE].find(t.NOCHANGE) == 0:
        param[TYPE] = param[TYPE].replace("{} ".format(t.NOCHANGE), "")
        param[MCOL] = False
    else:
        param[MCOL] = True


def complete_metadata(metadata):
    if INP not in metadata:
        metadata[INP] = []
    for i in range(len(metadata[INP])):
        metadata[INP][i][VARN] = "_PARAM_{}_".format(i)
        add_changeable_flag(metadata[INP][i])

    if PREP in metadata:
        for i in range(len(metadata[PREP])):
            if RET not in metadata[PREP][i]:
                metadata[PREP][i][RET] = {TYPE: t.VOID}
            metadata[PREP][i][RET][VARN] = "_PREP_RETURN_{}_".format(i)
            add_changeable_flag(metadata[PREP][i][RET])
            if PARM not in metadata[PREP][i]:
                metadata[PREP][i][PARM] = []
    else:
        metadata[PREP] = []

    if RET not in metadata[SOL]:
        metadata[SOL][RET] = {TYPE: t.VOID}
    metadata[SOL][RET][VARN] = "_RETURN_"
    if NAME not in metadata[SOL]:
        metadata[SOL][NAME] = "Solution"
    if MTDS in metadata[SOL]:
        for i in range(len(metadata[SOL][MTDS])):
            method = metadata[SOL][MTDS][i]
            if PARM not in method:
                method[PARM] = []
            if RET not in method:
                method[RET] = {TYPE: t.VOID}
            method[RET][VARN] = "_MTD_{}_RETURN_".format(i)

    if POSP in metadata:
        for i in range(len(metadata[POSP])):
            if RET not in metadata[POSP][i]:
                metadata[POSP][i][RET] = {TYPE: t.VOID}
            metadata[POSP][i][RET][VARN] = "_POSP_RETURN_{}_".format(i)
    else:
        metadata[POSP] = []

    if ADO not in metadata:
        metadata[ADO] = []

    if GRAD not in metadata:
        metadata[GRAD] = "general_grader"


def unchangeable_param_cnt(metadata):
    cnt = 0
    for param in metadata[INP]:
        cnt += 1 if not param[MCOL] else 0
    for param in metadata[PREP]:
        cnt += 1 if not param[RET][MCOL] else 0
    return cnt


def get_prop(metadata, prop):
    prop = re.sub(r"\[\[\"(\w+)\"\]\]", "[\g<1>]", re.sub(r"\.", "", re.sub(r"(\w+)", "[\"\g<1>\"]", prop)))
    return eval(get_prop.__code__.co_varnames[0] + prop)
