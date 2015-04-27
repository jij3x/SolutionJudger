import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    parts = line.split()
    tree = parts[0][1:-1]
    if not tree:
        print(0)
    else:
        nodes = tree.split(",")
        print("{} {}".format(len(nodes), " ".join(nodes)))
    print(parts[1])
