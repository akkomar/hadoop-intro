#!/usr/bin/python

import sys

sales_total = float(0)
number_of_sales = 0
old_day = None


for line in sys.stdin:
    data_mapped = line.strip().split("\t")
    if len(data_mapped) != 2:
        # Something has gone wrong. Skip this line.
        continue

    day, sale = data_mapped

    sale = float(sale)

    if old_day and old_day != day:
        print old_day, "\t", sales_total/number_of_sales
        old_day = day
        sales_total = float(0)
        number_of_sales = 0

    old_day = day
    number_of_sales += 1
    sales_total += sale

if old_day != None:
    print old_day, "\t", sales_total/number_of_sales

