import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    parts = line[1:-1].split(",")
    parts.sort()
    print("[{}]".format(",".join(parts)))
