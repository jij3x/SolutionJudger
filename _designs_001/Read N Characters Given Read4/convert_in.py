import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.split()
    if parts[0] == "0":
        print('""')
        print(parts[1])
    else:
        print('"{}"'.format(parts[1]))
        print(parts[2])
