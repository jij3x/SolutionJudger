import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    r = 0
    for c in line:
        r = (r << 1) + int(c)
    print(r)