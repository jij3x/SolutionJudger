import sys
import json

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    print(line)
