import sys

lines = sys.stdin.readlines()
print(len(lines) // 2)
for line in lines:
    line = line[1:-2]
    nodes = line.split(",")
    print(len(nodes), end=" ")
    print(" ".join(nodes))
