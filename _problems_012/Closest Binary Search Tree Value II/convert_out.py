import sys
import json

lines = sys.stdin.read().splitlines()
for line in lines:
    if line == "[]":
        print("[]")
    elif line[0] == "[":
        arr = [int(x) for x in line[1:-1].split(",")]
        arr.sort()
        print("[{}]".format(",".join(str(x) for x in arr)))
