import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    parts = line.split()
    if parts[0] == "0":
        print(0)
    else:
        row = int(parts[0])
        col = int(parts[1])
        print(row, end="")
        for i in range(row):
            print(" {} {}".format(col, " ".join(parts[2 + i * col: 2 + i * col + col])), end="")
        print()
