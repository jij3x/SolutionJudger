import sys

lines = sys.stdin.readlines()
print(len(lines) // 8)
for line in lines:
    print(line)
