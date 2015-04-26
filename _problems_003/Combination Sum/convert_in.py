import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.split()
    print(" ".join(parts[:-1]))
    print(parts[-1])
