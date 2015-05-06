import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    line = line[1:-2]
    if line == "":
        print("0")
    else:
        nodes = line.split(",")
        print(len(nodes), end=" ")
        print(" ".join(nodes))
