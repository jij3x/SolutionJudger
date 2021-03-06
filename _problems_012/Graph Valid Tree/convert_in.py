import sys
import json

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] != "[":
        print(line)
        continue
    arr = json.loads(line)
    print(len(arr), end="")
    for row in arr:
        if len(row) == 0:
            print(" 0", end="")
        else:
            print(" {} {}".format(str(len(row)), " ".join(str(x) for x in row)), end="")
    print()
