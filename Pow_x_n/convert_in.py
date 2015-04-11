import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split(" ")
    print(parts[0])
    print(parts[1])
