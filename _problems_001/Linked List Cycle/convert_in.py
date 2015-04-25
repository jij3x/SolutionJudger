import sys

lines = sys.stdin.readlines()
print(len(lines))
for line in lines:
    parts = line.rstrip("\n").split("}, ")
    linkedlist = parts[0].replace("{", "").split(",")
    if linkedlist[0] == "" and len(linkedlist) == 1:
        print(0)
    else:
        print(str(len(linkedlist)) + " " + " ".join(linkedlist))
    print(parts[1])
