import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    words = line.split("],")[0][1:]
    arr = words.split(",")
    print("{} {}".format(len(arr), " ".join(arr)))
    ops = ")," + line.split("],")[1][:-1]
    arr = list(map(lambda x: x.replace('"', ""), ops.replace("),distance(", ",")[1:].split(",")))
    arr = list(map(lambda x: '"{}"'.format(x), arr))
    arr = [x for y in (["0"] + arr[i:i+2] for i in range(0, len(arr), 2)) for x in y]
    print("{} {}".format(len(arr) // 2, " ".join(arr)))
