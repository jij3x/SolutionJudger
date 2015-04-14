import sys


def print_tree(tree):
    if tree == "":
        print("0")
        return
    nodes = tree.split(",")
    print(len(nodes), end="")
    for node in nodes:
        print(" {}".format(node), end="")
    print()


lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    trees = line.split(" ")
    print_tree(trees[0][1:-1])
    print_tree(trees[1][1:-1])
