#!/usr/bin/python

import sys

number_of_hits = 0
old_ip = None

for line in sys.stdin:
    data_mapped = line.strip().split("\t")
    if len(data_mapped) != 2:
        # Something has gone wrong. Skip this line.
        continue

    current_ip = data_mapped[0]
    
    if old_ip and old_ip != current_ip:
        print old_ip, "\t", number_of_hits
        old_ip = current_ip
        number_of_hits = 0

    old_ip = current_ip
    number_of_hits += 1

if old_ip != None:
    print old_ip, "\t", number_of_hits

