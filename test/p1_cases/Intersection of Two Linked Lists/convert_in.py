import sys

lines = sys.stdin.read().splitlines()
print(len(lines))
for line in lines:
    parts = line.split(", ")

    list1 = parts[1][1:-1]
    if not list1:
        print(0)
    else:
        nodes = list1.split(",")
        print("{} {}".format(len(nodes), " ".join(nodes)))

    list2 = parts[2][1:-1]
    if not list2:
        print(0)
    else:
        nodes = list2.split(",")
        print("{} {}".format(len(nodes), " ".join(nodes)))

    print(parts[3])
    print(parts[4])
