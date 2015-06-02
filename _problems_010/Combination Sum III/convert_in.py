import sys

lines = sys.stdin.readlines()
print(len(lines) // 2)
for line in lines:
    print(line)
