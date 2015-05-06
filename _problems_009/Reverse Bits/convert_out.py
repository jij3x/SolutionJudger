import sys

lines = sys.stdin.read().splitlines()
for line in lines:
    print(line.split()[0])
