import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] == "[":
        parts = line[1:-1].split(",")
        print("{} {}".format(len(parts), " ".join(parts)))
    else:
        print(line)
