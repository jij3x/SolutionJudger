import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split(" ")
    n = int(parts[0])
    print(n, end="")
    i = 1
    while i < len(parts) - 1:
        if parts[i] == "0":
            print(' ""', end="")
        else:
            i += 1
            print(' "{}"'.format(parts[i]), end="")
        i += 1

    print(" {}".format(parts[len(parts) - 1]))
