import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    line = line[1:-1]
    if not line:
        print(0)
    else:
        nodes = line.split(",")
        print(len(nodes), end="")
        print(" " + " ".join(nodes))
