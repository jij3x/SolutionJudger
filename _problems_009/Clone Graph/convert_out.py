import sys

lines = sys.stdin.readlines()
for line in lines:
    line = line[1:-2]
    if len(line) == 0:
        print("[]")
    else:
        parts = line.split("#")
        print("[{}]".format(",".join(map(lambda x: "[{}]".format(x), parts))))
