import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 3)
for line in lines:
    if line[0] == "[":
        arr = eval(line)
        print("{} {}".format(len(arr), " ".join(['"{}"'.format(x) for x in arr])))
    else:
        print(line)
