import urllib2
import re

zipcodes = [60018, 60068, 60176, 60601, 60602, 60603, 60604, 60605, 60606, 60607, 60608, 60609, 60610, 60611, 60612, 60613, 60614, 60615, 60616, 60617, 60618, 60619, 60620, 60621, 60622, 60623, 60624, 60625, 60626, 60628, 60630, 60631, 60632, 60634, 60636, 60637, 60639, 60640, 60641, 60642, 60643, 60644, 60645, 60646, 60647, 60649, 60651, 60652, 60653, 60654, 60655, 60656, 60657, 60659, 60660, 60661, 60706, 60707, 60714]

for zipcode in zipcodes:
  try:
    data = urllib2.urlopen("http://www.zillow.com/chicago-il-" + str(zipcode) + "/home-values/").read()
    data = data.replace("\n", "").replace("\r", "")
    current = re.search('<li class="zsg-lg-1-2">(-?\d+\.\d)% <span class="zsg-fineprint">1-year change<\/span><\/li>', data)
    if current == None:
      raise Exception("No regex match")
    current = current.group(1)
    future = re.search('<li class="zsg-lg-1-2">(-?\d+\.\d)% <span class="zsg-fineprint">1-year forecast<\/span><\/li>', data)
    if future == None:
      raise Exception("No regex match")
    future = future.group(1)
    print "OK", zipcode, current, future
  except Exception as e:
    print "FAIL", zipcode, str(e)