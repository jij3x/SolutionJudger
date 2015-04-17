"""
{
  "input":[
    {
      "name":"numbers",
      "type":":array<:int>"
    },
    {
      "name":"target",
      "type":":int"
    }
  ],
  "solution":{
    "actionName":"twoSum",
    "params":[
      "input[0]",
      "input[1]"
    ],
    "return":{
      "type":":array<:int>"
    }
  },
  "output":[
    "solution.return"
  ]
}
"""

import re
import metatypes as t

INP = "input"
NM = "name"
TYP = "type"
SOL = "solution"
PAR = "params"
FN = "actionName"
RT = "return"
OUT = "output"
IPR = "inProcr"
OPR = "outProcr"
ADO = "additionalOutput"
VNM = "variableName"

tim = t.type_map


def add_changeable_flag(param):
    if param[TYP].find(t.NOCHANGE) == 0:
        param[TYP] = param[TYP].replace("{} ".format(t.NOCHANGE), "")
        param[t.COL_CHANGEABLE] = False
    else:
        param[t.COL_CHANGEABLE] = True


def complete_metadata(metadata):
    for i in range(len(metadata[INP])):
        metadata[INP][i][VNM] = "_PARAM_{}_".format(str(i))
        add_changeable_flag(metadata[INP][i])

    if IPR in metadata:
        for i in range(len(metadata[IPR])):
            if RT not in metadata[IPR][i]:
                metadata[IPR][i][RT] = {TYP: t.VOID}
            metadata[IPR][i][RT][VNM] = "_IP_RETURN_{}_".format(str(i))
            add_changeable_flag(metadata[INP][i])
    else:
        metadata[IPR] = []

    if RT not in metadata[SOL]:
        metadata[SOL][RT] = {TYP: t.VOID}
    metadata[SOL][RT][VNM] = "_RETURN_"

    if OPR in metadata:
        for i in range(len(metadata[OPR])):
            if RT not in metadata[OPR][i]:
                metadata[OPR][i][RT] = {TYP: t.VOID}
            metadata[OPR][i][RT][VNM] = "_OP_RETURN_{}_".format(str(i))
    else:
        metadata[OPR] = []

    if ADO not in metadata:
        metadata[ADO] = []


def get_prop(metadata, prop):
    prop = re.sub(r"\[\[\"(\w+)\"\]\]", "[\g<1>]", re.sub(r"\.", "", re.sub(r"(\w+)", "[\"\g<1>\"]", prop)))
    return eval(get_prop.__code__.co_varnames[0] + prop)
