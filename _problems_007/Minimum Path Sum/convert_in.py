import sys
import json

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    arr = json.loads(line)
    print(len(arr), end="")
    for a in arr:
        print(" {} {}".format(len(a), " ".join(map(str, a))), end="")
    print()
