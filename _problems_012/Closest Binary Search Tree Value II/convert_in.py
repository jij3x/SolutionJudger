import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 3)
for line in lines:
    if line == "[]":
        print(0)
    elif line[0] == "[":
        nodes = line[1:-1].replace("null", "#").split(",")
        print("{} {}".format(len(nodes), " ".join(nodes)))
    else:
        print(line)
