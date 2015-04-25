import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line == "[]":
        print(0)
    else:
        nums = line[1:-1].split(",")
        print("{} {}".format(len(nums), " ".join(nums)))
