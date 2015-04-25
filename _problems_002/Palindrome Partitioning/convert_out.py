import sys
import json

lines = sys.stdin.readlines()
for line in lines:
    ladders = json.loads(line)
    ladders.sort()
    output = "["
    for i, ladder in enumerate(ladders):
        output += "," if i > 0 else ""
        output += "[{}]".format(",".join(map(lambda x: '"{}"'.format(x), ladder)))
    print(output + "]")
