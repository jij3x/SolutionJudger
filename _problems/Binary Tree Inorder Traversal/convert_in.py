import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for tree in lines:
    tree = tree[1:-1]
    if tree == "":
        print("0")
    else:
        nodes = tree.split(",")
        print(len(nodes), end="")
        for node in nodes:
            print(" {}".format(node), end="")
        print()
