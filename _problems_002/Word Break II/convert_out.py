import sys
import json

lines = sys.stdin.readlines()
for line in lines:
    words = json.loads(line)
    words.sort()
    print("[{}]".format(",".join(map(lambda x: '"{}"'.format(x), words))))
