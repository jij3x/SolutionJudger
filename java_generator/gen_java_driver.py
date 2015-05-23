import sys
import json
import re
import os

lib_path = os.path.abspath(os.path.join(os.path.dirname(os.path.realpath(__file__)), '..', '_commons'))
sys.path.append(lib_path)
import probdesc as m
import metatypes as t


PARAM_DS_POS = "// param deserialization code inject here"
INPUT_PROCR_POS = "// input processing code inject here"
SOLVING_POS = "// solution invoking code inject here"
OUTPUT_PROCR_POS = "// output processing code inject here"
OUTPUT_S_POS = "// result serialization code inject here"
ADDOUT_S_POS = "// additional output serialization code inject here"
UCVARS_BS_POS = "// unchangeable variables serialization code before execution inject here"
UCVARS_AS_POS = "// unchangeable variables serialization code after execution inject here"

tim = t.type_map
metadata = json.loads(sys.stdin.read())
m.complete_metadata(metadata)


def gen_invoking_code(fn_ret, fn_name, params, rt_tmpl, fn_tmpl):
    inputs = ""
    for i, par in enumerate(params):
        inputs += (", " if i > 0 else "") + m.get_prop(metadata, par)[m.VARN]
    return_par = rt_tmpl.format(tim[fn_ret[m.TYPE]][t.P_JAVA_T], fn_ret[m.VARN]) if fn_ret[m.TYPE] != t.VOID else ""
    return fn_tmpl.format(return_par, fn_name, inputs)


def gen_output_serlizing_code(output, fn_tmpl):
    param = m.get_prop(metadata, output)
    serializer = tim[param[m.TYPE]][t.P_SER]
    return fn_tmpl.format(serializer, param[m.VARN])


def gen_input_serlizing_code(param, fn_tmpl):
    serializer = tim[param[m.TYPE]][t.P_SER]
    return fn_tmpl.format(serializer, param[m.VARN])


def gen_input_deserlizing_code(param, fn_tmpl):
    deser = tim[param[m.TYPE]][t.P_DES]
    rtype = tim[param[m.TYPE]][t.P_JAVA_T]
    return fn_tmpl.format(rtype, param[m.VARN], deser)

# Fetch inputs deserialization code
param_deser_code = ""
for param in metadata[m.INP]:
    param_deser_code += gen_input_deserlizing_code(param, "{} {} = Serializer.{}(tokenizer);\n")

# Compose the code to process input
input_proc_code = ""
for procr in metadata[m.PREP]:
    input_proc_code += gen_invoking_code(procr[m.RET], procr[m.FUNC], procr[m.PARM],
                                         "{} {} = ", "{} Helper.{}({});\n")

# Compose the code to call Solution
solving_code = gen_invoking_code(metadata[m.SOL][m.RET], metadata[m.SOL][m.FUNC], metadata[m.SOL][m.PARM],
                                 "{} {} = ", "{}(new Solution()).{}({});\n")

# Compose the code to process output
output_proc_code = ""
for procr in metadata[m.POSP]:
    output_proc_code += gen_invoking_code(procr[m.RET], procr[m.FUNC], procr[m.PARM],
                                          "{} {} = ", "{} Helper.{}({});\n")

# Compose the code to serialize the Solution return
result_ser_code = gen_output_serlizing_code(metadata[m.OUT], "printWriter.println(Serializer.{}({}));\n")

# Compose the code to serialize additional output, which might be empty
addout_ser_code = ""
for addout in metadata[m.ADO]:
    addout_ser_code += gen_output_serlizing_code(addout, "printWriter.println(Serializer.{}({}));\n")

# Compose the code to serialize unchangeable variables
ucvars_s_code = ""
for param in metadata[m.INP]:
    if not param[m.MC]:
        ucvars_s_code += gen_input_serlizing_code(param, "printWriter.println(Serializer.{}({}));\n")
for param in metadata[m.PREP]:
    if not param[m.RET][m.MC]:
        ucvars_s_code += gen_input_serlizing_code(param[m.RET], "printWriter.println(Serializer.{}({}));\n")

# Inject the code into Driver template
DRVTML_FNM = os.path.join(os.path.dirname(os.path.realpath(__file__)), "java.driver.template")
with open(DRVTML_FNM) as driver_template:
    driver_code = driver_template.read()
    driver_code = re.sub(re.escape(PARAM_DS_POS) + r".*?\n", param_deser_code, driver_code)
    driver_code = re.sub(re.escape(INPUT_PROCR_POS) + r".*?\n", input_proc_code, driver_code)
    driver_code = re.sub(re.escape(UCVARS_BS_POS) + r".*?\n", ucvars_s_code, driver_code)
    driver_code = re.sub(re.escape(SOLVING_POS) + r".*?\n", solving_code, driver_code)
    driver_code = re.sub(re.escape(OUTPUT_PROCR_POS) + r".*?\n", output_proc_code, driver_code)
    driver_code = re.sub(re.escape(OUTPUT_S_POS) + r".*?\n", result_ser_code, driver_code)
    driver_code = re.sub(re.escape(ADDOUT_S_POS) + r".*?\n", addout_ser_code, driver_code)
    driver_code = re.sub(re.escape(UCVARS_AS_POS) + r".*?\n", ucvars_s_code, driver_code)

print(driver_code)
