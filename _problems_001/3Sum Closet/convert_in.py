import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] == "[":
        nums = line[1:-1].split(",")
        print("{} {}".format(len(nums), " ".join(nums)))
    else:
        print(line)
