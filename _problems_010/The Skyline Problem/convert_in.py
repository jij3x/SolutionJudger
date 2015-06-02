import sys
import json

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    pairs = json.loads(line)
    print(len(pairs), end="")
    for pair in pairs:
        print(" {} {}".format(len(pair), " ".join(str(x) for x in pair)), end="")
    print()
