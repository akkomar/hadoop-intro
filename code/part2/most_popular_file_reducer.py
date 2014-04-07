#!/usr/bin/python

import sys

prev_file = None
prev_number_of_hits = 0
top_file = None
top_hits = 0

for line in sys.stdin:
    data_mapped = line.strip().split("\t")
    if len(data_mapped) != 2:
        # Something has gone wrong. Skip this line.
        continue

    current_file = data_mapped[0]

    if prev_file and prev_file != current_file:
        if top_file is None or top_hits < prev_number_of_hits:
            top_file = prev_file
            top_hits = prev_number_of_hits
        prev_file = current_file
        prev_number_of_hits = 0
    
    prev_file = current_file
    prev_number_of_hits += 1



if top_file != None:
    print top_file, "\t", top_hits

