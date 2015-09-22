import sys
import json

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    arr = json.loads(line)
    if len(arr) == 0:
        print(0)
    else:
        print("{} {}".format(str(len(arr)), " ".join(str(x) for x in arr)))
