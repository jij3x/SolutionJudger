import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    print("[" + line[1:-1] + "]")
