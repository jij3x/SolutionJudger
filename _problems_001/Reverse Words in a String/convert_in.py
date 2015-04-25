import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    print(line[:-1])
