import urllib2
import csv
import json
import sys

if not len(sys.argv) == 2:
  print "Specify CSV file"
  sys.exit(1)

properties = []
with open(sys.argv[1], 'rb') as csvfile:
  reader = csv.reader(csvfile)
  for line in reader:
    latitude = line[0].trim()
    longitude = line[1].trim()
    try:
      fccdata = urllib2.urlopen('http://data.fcc.gov/api/block/2010/find?format=json&latitude=' + latitude + '&longitude=' + longitude + '&format=json').read()
      fccdata = json.loads(fccdata)
      properties.append([latitude, longitude, fccdata["Block"]["FIPS"]])
    except Exception as e:
      print "FAIL", line, str(e)
      properties.append([latitude, longitude, "FAIL"])

with open('2_' + sys.argv[1], 'wb') as writefile:
  csv.writer(writefile).writerows(properties)