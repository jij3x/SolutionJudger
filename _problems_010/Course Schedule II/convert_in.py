import sys
import json

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] == "[":
        pairs = json.loads(line)
        print(len(pairs), end="")
        for pair in pairs:
            print(" {} {}".format(len(pair), " ".join(str(x) for x in pair)), end="")
        print()
    else:
        print(line)
