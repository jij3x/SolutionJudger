import sys
import json

lines = sys.stdin.read().splitlines()
for line in lines:
    nums = json.loads(line)
    nums.sort()
    print(json.dumps(nums, separators=(",", ":")))
