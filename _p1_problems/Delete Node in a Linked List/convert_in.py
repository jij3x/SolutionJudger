import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] == "[":
        line = line[1:-1]
        if line == "":
            print("0")
        else:
            nodes = line.split(",")
            print(len(nodes), end=" ")
            print(" ".join(nodes))
    else:
        print(line)
