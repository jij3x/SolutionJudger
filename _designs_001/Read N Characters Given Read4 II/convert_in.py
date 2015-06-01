import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.split()
    if parts[0] == "0":
        print('""')
        print(" ".join(parts[1:]))
    else:
        print('"{}"'.format(parts[1]))
        print(" ".join(parts[2:]))
