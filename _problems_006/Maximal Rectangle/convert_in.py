import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split(" ")
    print(parts[0], end="")
    for i in range(1, len(parts)):
        print(' "{}"'.format(parts[i]), end="")
    print()
