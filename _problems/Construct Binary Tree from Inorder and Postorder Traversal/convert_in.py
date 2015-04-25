import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    nums = line.split()
    n = int(nums[0])
    if n == 0:
        print(0)
        print(0)
        continue

    print(nums[0] + " " + " ".join(nums[1:n+1]))
    print(nums[0] + " " + " ".join(nums[n+1:]))
