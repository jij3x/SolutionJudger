import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.split()
    print(parts[0])
    print("{} {}".format(len(parts) - 1, " ".join(parts[1:])))
