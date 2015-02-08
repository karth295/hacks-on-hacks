window.onload = function() {
  document.getElementById("p1").innerHTML = "Starting";

  var res = getOptimalLocationAffordabilityGroups();
  
  // var out = printOut(res);
  document.getElementById("p2").innerHTML = res;
}

function printOut(data) {
  console.log(data);
  str = "";
    data.forEach( function(e) {
      console.log(e);
      str += e.properties + "\n\n";
    });
}

var block_groups = [
  "160550006011",
  "160550006022",
  "160550005001",
  "160550005002",
  "160550003022",
  "160550016001",
  "160550016002",
  "160550012003",
  "160550012001",
  "160550009002"
];

function getOptimalLocationAffordabilityGroups() {
  var tail = "&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&distance=&units=esriSRUnit_Meter&returnGeometry=true&maxAllowableOffset=5&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false&returnExtentOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&resultOffset=&resultRecordCount=&returnZ=false&returnM=false&quantizationParameters=&f=pgeojson&token=";

  var head = "http://services.arcgis.com/VTyQ9soqVukalItT/arcgis/rest/services/LocationAffordabilityIndexData/FeatureServer/0/query?where=";

  var exq = "hh_type3_h<0.5&hh_type3_ht<0.75";
  var outfields = "*";  

  data = [];

  groups = "";
  block_groups.forEach( function(group) {
    groups += "blkgrp=" + group + "+OR+";
  });

  groups = groups.substring(0, groups.length - 3);

  var query = head + groups + "&" + exq + "&" + "outFields=" + outfields + tail;
  var res = httpGet(query);
  data += res; 
  
  return data; 
}

function httpGet(theUrl) {
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
}

