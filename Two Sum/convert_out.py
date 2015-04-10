import sys
import re

lines = sys.stdin.readlines()
regex = re.compile(r"\s+")
target = ""
for line in lines:
    target += "[{}]\n".format(regex.sub("", line))
print(target, end="")
