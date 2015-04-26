import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    parts = line.split()
    print(" ".join(parts[:-1]))
    print(parts[-1])
