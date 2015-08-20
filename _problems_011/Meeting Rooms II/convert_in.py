import sys
import json

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    arr = json.loads(line)
    print(len(arr), end="")
    for pair in arr:
        print(" {} {}".format(pair[0], pair[1]), end="")
    print()
