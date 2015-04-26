import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.split()
    m = int(parts[0])
    n = int(parts[1])
    parts2 = parts[2:2+m]
    parts2.extend(["0"] * n)
    print("{} {}".format(m + n, " ".join(parts2)))
    print(m)
    if n == 0:
        print(0)
    else:
        print("{} {}".format(n, " ".join(parts[2 + m:])))
    print(n)
