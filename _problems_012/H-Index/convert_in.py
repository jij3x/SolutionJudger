import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    line = line.replace(" ", "")
    if line == "[]":
        print(0)
        continue
    parts = line[1:-1].split(",")
    print("{} {}".format(len(parts), " ".join(parts)))
