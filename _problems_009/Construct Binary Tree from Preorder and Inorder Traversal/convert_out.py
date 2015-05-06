import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    print("[{}]".format(line[1:-1]))
