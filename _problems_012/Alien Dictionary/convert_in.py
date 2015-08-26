import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    arr = line[1:-1].replace(" ", "").split(",")
    print("{} {}".format(len(arr), " ".join(arr)))
