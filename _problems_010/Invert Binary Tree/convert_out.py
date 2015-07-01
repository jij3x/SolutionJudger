import sys

lines = sys.stdin.read().replace("null", "#").splitlines()
for line in lines:
    print("[{}]".format(line[1:-1]))
