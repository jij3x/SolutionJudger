import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    print(line.replace("true", "1").replace("false", "0"))
