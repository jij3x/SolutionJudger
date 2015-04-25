import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split(", ")
    print(parts[0])
    if parts[1] == "[]":
        print(0)
    else:
        words = parts[1][1:-1].split(",")
        print("{} ".format(len(words)), end="")
        print(" ".join(words))
