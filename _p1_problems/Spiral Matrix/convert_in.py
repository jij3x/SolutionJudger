import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split(" ")
    if parts[0] == 0:
        print("0 0")
    else:
        n = int(parts[0])
        m = int(parts[1])
        idx = 2
        print(n, end="")
        for i in range(0, n):
            print(" {}".format(m), end="")
            for j in range(0, m):
                print(" {}".format(parts[idx]), end="")
                idx += 1
        print()
