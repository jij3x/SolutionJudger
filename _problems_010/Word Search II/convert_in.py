import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for i in range(len(lines) // 2):
    line1 = lines[i * 2][1:-1]
    print(0) if not line1 else print("{} {}".format(len(line1.split(",")), " ".join([x[1:-1] for x in line1.split(",")])))
    line2 = lines[i * 2 + 1][1:-1]
    print(0) if not line2 else print("{} {}".format(len(line2.split(",")), " ".join(line2.split(","))))
