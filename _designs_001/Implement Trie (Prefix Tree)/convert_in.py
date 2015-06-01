import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 4)
for i in range(len(lines) // 4):
    options = lines[i * 4 + 0][1:-1].split(",")
    inserts = lines[i * 4 + 1][1:-1].split(",")
    searches = lines[i * 4 + 2][1:-1].split(",")
    prefixes = lines[i * 4 + 3][1:-1].split(",")
    stream = []
    for op in options:
        stream.append(op)
        if op == "0":
            stream.append(inserts.pop(0))
        elif op == "1":
            stream.append(searches.pop(0))
        else:
            stream.append(prefixes.pop(0))
    print("{} {}".format(len(options), " ".join(stream)))
