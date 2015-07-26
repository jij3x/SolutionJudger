import sys

lines = sys.stdin.read().splitlines()
print(len(lines) // 2)
for line in lines:
    if line[0] == "[":
        a = eval(line)
        print(len(a), end="")
        for suba in a:
            print(" {}".format(len(suba)), end="")
            if len(suba) > 0:
                print(" {}".format(" ".join(str(x) for x in suba)), end="")
        print()
    else:
        print(line)
