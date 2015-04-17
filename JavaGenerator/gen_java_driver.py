import sys
import json
import re
import os

lib_path = os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), '..', '_commons'))
sys.path.append(lib_path)
import intfdesc as m
import typemap as t

tim = t.type_map

DRVTML_FNM = os.path.join(os.path.dirname(os.path.realpath(__file__)), "java.driver.template")

CODE_NAME = "codeName"
SOL_RETURN_CN = "_RETURN_"
IP_RETURN_CN = "_IP_RETURN_{}_"
OP_RETURN_CN = "_OP_RETURN_{}_"
PARAM_CN = "_PARAM_{}_"

PARAM_DS_POS = "// param deserialization code inject here"
INPUT_PROCR_POS = "// input processing code inject here"
SOLVING_POS = "// solution invoking code inject here"
OUTPUT_PROCR_POS = "// output processing code inject here"
OUTPUT_S_POS = "// result serialization code inject here"
ADDOUT_S_POS = "// additional output serialization code inject here"


#
# Read problem description, and compose metadata
#
metadata = json.loads(sys.stdin.read())


def add_changeable_flag(param):
    if param[m.TYP].find(t.NOCHANGE) == 0:
        param[m.TYP] = param[m.TYP].replace("{} ".format(t.NOCHANGE), "")
        param[t.COL_CHANGEABLE] = False
    else:
        param[t.COL_CHANGEABLE] = True

for i in range(len(metadata[m.INP])):
    metadata[m.INP][i][CODE_NAME] = PARAM_CN.format(str(i))
    add_changeable_flag(metadata[m.INP][i])

if m.IPR in metadata:
    for i in range(len(metadata[m.IPR])):
        if m.RT not in metadata[m.IPR][i]:
            metadata[m.IPR][i][m.RT] = {m.TYP: t.VOID}
        metadata[m.IPR][i][m.RT][CODE_NAME] = IP_RETURN_CN.format(str(i))
        add_changeable_flag(metadata[m.INP][i])
else:
    metadata[m.IPR] = []

if m.RT not in metadata[m.SOL]:
    metadata[m.SOL][m.RT] = {m.TYP: t.VOID}
metadata[m.SOL][m.RT][CODE_NAME] = SOL_RETURN_CN

if m.OPR in metadata:
    for i in range(len(metadata[m.OPR])):
        if m.RT not in metadata[m.OPR][i]:
            metadata[m.OPR][i][m.RT] = {m.TYP: t.VOID}
        metadata[m.OPR][i][m.RT][CODE_NAME] = OP_RETURN_CN.format(str(i))
else:
    metadata[m.OPR] = []

if m.ADO not in metadata:
    metadata[m.ADO] = []


def eval_prop(s):
    prop = re.sub(r"\[\[\"(\w+)\"\]\]", "[\g<1>]", re.sub(r"\.", "", re.sub(r"(\w+)", "[\"\g<1>\"]", s)))
    return eval("metadata" + prop)


def gen_solution_invoking_code(fn_ret, fn_name, params, lspc, rt_tmpl, fn_tmpl):
    inputs = ""
    for i, par in enumerate(params):
        inputs += (", " if i > 0 else "") + eval_prop(par)[CODE_NAME]
    return_par = rt_tmpl.format(tim[fn_ret[m.TYP]][t.P_JAVA_T], fn_ret[CODE_NAME]) if fn_ret[m.TYP] != t.VOID else ""
    return " " * lspc + fn_tmpl.format(return_par, fn_name, inputs)


def gen_output_serlizing_code(output, lspc, fn_tmpl):
    inputs = eval_prop(output)[CODE_NAME]
    serializer = tim[eval_prop(output)[m.TYP]][t.P_SER]
    return " " * lspc + fn_tmpl.format(serializer, inputs)


def gen_input_deserlizing_code(param, lspc, fn_tmpl):
    deser = tim[param[m.TYP]][t.P_DES]
    rtype = tim[param[m.TYP]][t.P_JAVA_T]
    return " " * lspc + fn_tmpl.format(rtype, param[CODE_NAME], deser)

#
# Fetch inputs deserialization code
#
param_deser_code = ""
for param in metadata[m.INP]:
    param_deser_code += gen_input_deserlizing_code(param, 12, "{} {} = Serializer.{}(tokenizer);\n")

#
# Compose the code to process input
#
input_proc_code = ""
for procr in metadata[m.IPR]:
    input_proc_code += gen_solution_invoking_code(procr[m.RT], procr[m.FN], procr[m.PAR], 12,
                                                  "{} {} = ", "{} Helper.{}({});\n")

#
# Compose the code to call Solution
#
solving_code = gen_solution_invoking_code(metadata[m.SOL][m.RT], metadata[m.SOL][m.FN], metadata[m.SOL][m.PAR], 12,
                                          "{} {} = ", "{}(new Solution()).{}({});\n")

#
# Compose the code to process output
#
output_proc_code = ""
for procr in metadata[m.OPR]:
    output_proc_code += gen_solution_invoking_code(procr[m.RT], procr[m.FN], procr[m.PAR], 12,
                                                   "{} {} = ", "{} Helper.{}({});\n")

#
# Compose the code to serialize the Solution return
#
result_ser_code = gen_output_serlizing_code(metadata[m.OUT], 12, "printWriter.println(Serializer.{}({}));\n")

#
# Compose the code to serialize additional output, which might be empty
#
addout_ser_code = ""
for addout in metadata[m.ADO]:
    addout_ser_code = gen_output_serlizing_code(addout, 12, "printWriter.println(Serializer.{}({}));\n")

#
# Inject the code into Driver template
#
with open(DRVTML_FNM) as driver_template:
    driver_code = driver_template.read()
    driver_code = re.sub(r"[ \t]*" + re.escape(PARAM_DS_POS) + r".*?\n", param_deser_code, driver_code)
    driver_code = re.sub(r"[ \t]*" + re.escape(INPUT_PROCR_POS) + r".*?\n", input_proc_code, driver_code)
    driver_code = re.sub(r"[ \t]*" + re.escape(SOLVING_POS) + r".*?\n", solving_code, driver_code)
    driver_code = re.sub(r"[ \t]*" + re.escape(OUTPUT_PROCR_POS) + r".*?\n", output_proc_code, driver_code)
    driver_code = re.sub(r"[ \t]*" + re.escape(OUTPUT_S_POS) + r".*?\n", result_ser_code, driver_code)
    if addout_ser_code:
        driver_code = re.sub(r"[ \t]*" + re.escape(ADDOUT_S_POS) + r".*?\n", addout_ser_code, driver_code)

print(driver_code)
