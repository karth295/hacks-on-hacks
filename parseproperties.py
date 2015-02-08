import urllib2
import csv
import json
import sys

if not len(sys.argv) == 2:
  print "Specify CSV file"
  sys.exit(1)

properties = []
counter = 0
with open(sys.argv[1], 'rb') as csvfile:
  reader = csv.reader(csvfile)
  for line in reader:
    if counter % 1000 == 0:
      print counter
    counter += 1
    latitude = line[0].strip()
    longitude = line[1].strip()
    try:
      fccdata = urllib2.urlopen('http://data.fcc.gov/api/block/2010/find?format=json&latitude=' + latitude + '&longitude=' + longitude + '&format=json').read()
      fccdata = json.loads(fccdata)
      properties.append([latitude, longitude, fccdata["Block"]["FIPS"]])
    except Exception as e:
      print "FAIL", line, str(e)
      properties.append([latitude, longitude, "FAIL"])

with open('3_' + sys.argv[1], 'wb') as writefile:
  csv.writer(writefile).writerows(properties)
