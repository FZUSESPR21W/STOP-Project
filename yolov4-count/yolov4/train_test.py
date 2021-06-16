# python3
import os
import random

train_percent = 0.8
filepath = "C:/Users/MI/Desktop/pictures"
total = os.listdir(filepath)

num = len(total)
list = range(num)
train = random.sample(list, int(train_percent * num))

ftest = open("test.txt", 'w')
ftrain = open("train.txt", 'w')

for i in range(num):
    name = "/content/drive/MyDrive/electromobile/obj/" + total[i] + "\n"
    if i in train:
        ftrain.write(name)
    else:
        ftest.write(name)

ftrain.close()
ftest.close()
