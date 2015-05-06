import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    line = line.replace("{", "[")
    print(line.replace("}", "]"))
