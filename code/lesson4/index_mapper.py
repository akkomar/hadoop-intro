#!/usr/bin/python



import sys, re, csv

reader = csv.reader(sys.stdin, delimiter='\t')
writer = csv.writer(sys.stdout, delimiter='\t', quotechar='"', quoting=csv.QUOTE_ALL)

for line in reader:

    #print len(line)
    node_id = line[0]
    body = line[4].strip()
    #print line[4]
    #print line[4].strip()
    words = re.split('\s|\,|\.|\!|\?|\:|\;|\"|\(|\)|\<|\>|\[|\]|\#|\$|\=|\-|\/', body)
    #print words
    for word in words:
        if word:
            writer.writerow([word.lower(), node_id])
    #print "{0}\t{1}".format(store, cost)

