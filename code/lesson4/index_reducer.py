#!/usr/bin/python

import sys, csv

old_word = None
nodes = []

reader = csv.reader(sys.stdin, delimiter='\t', quotechar='"')
writer = csv.writer(sys.stdout, delimiter='\t', quotechar='"', quoting=csv.QUOTE_ALL)

for line in reader:
    word = line[0]
    node_id = line[1]

    if old_word and old_word != word:
        writer.writerow([old_word, ', '.join(nodes)])
        old_word = word
        nodes = []

    old_word = word
    nodes.append(node_id)

if old_word != None:
    writer.writerow([old_word, ', '.join(nodes)])

