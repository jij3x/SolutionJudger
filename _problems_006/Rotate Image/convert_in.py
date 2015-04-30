import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split()
    n = int(parts[0])
    m = int(parts[0])
    idx = 1
    print(n, end="")
    for i in range(0, n):
        print(" {}".format(m), end="")
        for j in range(0, m):
            print(" {}".format(parts[idx]), end="")
            idx += 1
    print()
