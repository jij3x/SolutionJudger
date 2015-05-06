import sys

lines = sys.stdin.readlines()
target = lines[0]
for i in range(0, int(lines[0]), 1):
    target += lines[1 + i * 3 + 1].rstrip("\n") + " " + lines[1 + i * 3 + 2]
    target += lines[1 + i * 3]
print(target, end="")
