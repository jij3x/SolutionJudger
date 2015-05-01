import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    parts = line.split()
    parts = parts[2:]
    print("{} {}".format(len(parts), " ".join(parts)))
