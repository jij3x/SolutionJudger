import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 3)
for i in range(len(lines) // 3):
    options = lines[i * 3 + 0][1:-1].split(",")
    inserts = lines[i * 3 + 1][1:-1].split(",")
    searches = lines[i * 3 + 2][1:-1].split(",")

    stream = []
    for op in options:
        stream.append(op)
        if int(op) == 0:
            stream.append(inserts.pop(0))
        else:
            stream.append(searches.pop(0))
    print("{} {}".format(len(options), " ".join(stream)))
