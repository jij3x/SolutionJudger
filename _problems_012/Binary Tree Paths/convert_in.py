import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    if line == "[]":
        print(0)
    else:
        nodes = line[1:-1].replace("null", "#").split(",")
        print("{} {}".format(len(nodes), " ".join(nodes)))
