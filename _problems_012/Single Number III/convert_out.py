import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    parts = line.replace(" ", "")[1:-1].split(",")
    parts = list(map(int, parts))
    parts.sort()
    print("[{}]".format(",".join(str(x) for x in parts)))
