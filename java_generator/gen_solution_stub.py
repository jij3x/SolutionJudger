import sys
import json

import probdesc as md
import metatypes as tm

tim = tm.type_map

with open(sys.argv[1]) as metadata_file:
    metadata = json.load(metadata_file)
_function = metadata[md.itf][md.fnn]
_params = metadata[md.itf][md.pl]
_interface = metadata[md.itf]

return_type = tim[_interface[md.rt][md.tp]][tm.k_java_t]
inputs = ""
for i, pm in enumerate(_params):
    inputs += (", " if i > 0 else "") + tim[pm[md.tp]][tm.k_java_t] + " " + pm[md.nm]
print("""
public class Solution {{
    public {} {}({}) {{

    }}
}}
""".format(return_type, _function, inputs))
