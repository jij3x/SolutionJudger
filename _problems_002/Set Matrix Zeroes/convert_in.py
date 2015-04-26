import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    parts = line.split()
    print(parts[0], end="")
    n = parts[1]
    for i in range(2, (len(parts))):
        if (i - 2) % int(n) == 0:
            print(" {}".format(n), end="")
        print(" {}".format(parts[i]), end="")
    print()
