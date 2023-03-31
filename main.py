import csv
import random

f = open('numbers.txt','w')

a = []
for i in range(100):
    a.append(+random.randint(100,10000))
a.sort()
print(a)


for i in a:
    line = ''
    for j in range(i):
        line += str(random.randint(-100000, 100000)) + " "
    f.write(line+'\n')

