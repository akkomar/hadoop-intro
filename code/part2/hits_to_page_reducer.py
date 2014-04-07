#!/usr/bin/python

import sys

number_of_hits = 0
old_file = None

for line in sys.stdin:
    data_mapped = line.strip().split("\t")
    if len(data_mapped) != 2:
        # Something has gone wrong. Skip this line.
        continue

    current_file = data_mapped[0]
    
    if old_file and old_file != current_file:
        print old_file, "\t", number_of_hits
        old_file = current_file
        number_of_hits = 0

    old_file = current_file
    number_of_hits += 1

if old_file != None:
    print old_file, "\t", number_of_hits

