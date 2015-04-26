import sys

lines = sys.stdin.readlines()
print(len(lines) // 3)
for line in lines:
    print(line[:-1])
