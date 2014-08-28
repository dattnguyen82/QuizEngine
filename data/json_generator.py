__author__ = '212391398'

with open('list.txt') as f:
    content = f.readlines()

key = 10;
for line in content:
    name = line.strip()
    element = '{\n' + '"text": "' + name + '",\n' + '"key": ' + str(key) + "\n},"
    key += 1
    print element



