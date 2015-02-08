import urllib2
import json

def httpGet(url):
    return urllib2.urlopen(url).read()

def makeRequest(blocks):
    exq = 'hh_type3_h<0.5&hh_type3_ht<0.75'
    outfields = '*'

    tail = '&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&distance=&units=esriSRUnit_Meter&returnGeometry=true&maxAllowableOffset=5&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&returnExtentOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&resultOffset=&resultRecordCount=&returnZ=false&returnM=false&quantizationParameters=&f=pgeojson&token='
    head = 'http://services.arcgis.com/VTyQ9soqVukalItT/arcgis/rest/services/LocationAffordabilityIndexData/FeatureServer/0/query?where='
    
    groups = ''
    for block in blocks:
        groups = groups + 'blkgrp=' + block[:12] + '+OR+'
    groups = groups[:len(groups) - 3]

    request = head + groups + '&' + exq +'&' + 'outfields=' + outfields + tail 

    print request

    return request

def getJsonData(blocks):
    results = []
    n = 50
    for i in xrange(0, len(blocks), n):        
        res = httpGet(makeRequest(blocks[i:i + n]))
        #print res
        jres = json.loads(res)
        ##print 'jres'
        ##print jres['features']
        #print 'results'
        #print results
 
        results.extend(jres['features'])
    return results

def main():
    inf = open('ChicagoCensusBlockFIPS.txt', 'r')
    #inf = open('test.txt', 'r')
    out = open('block_affordability.txt', 'w')
    
    
    inl = inf.read().splitlines()
    #request = makeRequest(inl)
    
    ##print 'request'
    ##print request
  
    #results = httpGet(request)
 
#    #print 'results'
#    #print results
    
    #jres = json.loads(results)   

#    #print 'jres'
#    #print jres

    entries = getJsonData(inl)

    #entries = jres['features']

    attributes = [u'hh_type3_h_rent', u'hh_type3_t_rent', u'area_median_income', u'employment_access_index']

    sums = {}
        
    for entry in entries:
        props = entry['properties']
        # housing, transit, median income, employment acc
        for a in attributes: 
            print sums.get(a, 0)
            print props.get(a, 0)
            s = sums.get(a, 0)
            p = props.get(a, 0)
            if p == None:
                p = 0
            if s == None:
                s = 0
            sums[a] = s + p

    
    avgs = {}
    for k,v in sums.iteritems():
        avgs[k] = v / len(entries)

    out.write('blkgrp')
    for a in attributes:
        out.write(',' + a)
    out.write('\n')

    for entry in entries:
        props = entry['properties']
        out.write(props[u'blkgrp'])
        for a in attributes:
            try:
                out.write(',' + str(float(props.get(a,0)) / float(avgs.get(a, 0))))
            except TypeError:
                #ignore.
                print 'Error'
        out.write('\n')

    out.close()    

if __name__ == "__main__":
    main()
