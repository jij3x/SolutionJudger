import sys

lines = sys.stdin.readlines()
for line in lines:
    print("[" + line[1:-2] + "]")
