import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    nums = line.split()
    n = int(nums[0])
    nums = nums[1:]
    parts = [str(n)]
    s = 0
    for i in range(n):
        parts.append(str(i + 1))
        parts.extend(nums[s: s+i+1])
        s += i + 1
    print(" ".join(parts))
