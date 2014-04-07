#!/usr/bin/python

import sys

salesTotal = 0
numberOfSales = 0
oldKey = None

# Loop around the data
# It will be in the format key\tval


for line in sys.stdin:
    data_mapped = line.strip().split("\t")
    if len(data_mapped) != 2:
        # Something has gone wrong. Skip this line.
        continue

    thisKey, thisSale = data_mapped

    salesTotal += float(thisSale)
    numberOfSales += 1

print salesTotal, "\t", numberOfSales

