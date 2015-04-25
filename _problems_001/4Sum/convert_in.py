import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    nums = line.split()
    print(" ".join(nums[:-1]))
    print(nums[-1])
