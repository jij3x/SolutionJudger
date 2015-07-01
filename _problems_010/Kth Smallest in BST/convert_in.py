import sys

lines = sys.stdin.read().replace("null", "#").splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] == "[":
        tree = line[1:-1]
        if tree == "":
            print("0")
        else:
            nodes = tree.split(",")
            print(len(nodes), end="")
            for node in nodes:
                print(" {}".format(node), end="")
            print()
    else:
        print(line)
