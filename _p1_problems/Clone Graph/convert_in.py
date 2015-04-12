import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    line = line[1:-2]
    if len(line) == 0:
        print(0)
    else:
        parts = line.split("#")
        print(str(len(parts)) + " " + " ".join(map(lambda x: '"{}"'.format(x), parts)))
