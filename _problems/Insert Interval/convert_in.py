import sys
import json

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line == "[]":
        print(0)
    else:
        arr = json.loads(line)
        if isinstance(arr[0], list):
            print(len(arr), end="")
            for interval in arr:
                print(" {} {}".format(interval[0], interval[1]), end="")
            print()
        else:
            print(" ".join(str(x) for x in arr))
