# hacks-on-hacks
For the Zillow/HUD hackathon in Seattle

This tool takes multiple data sources and combines them to provide census block granularity suggestions for HUD to create new housing 
developments. For the sake of the hackathon we looked at Chicago, because they had many sources of helpful data. We hope that in the future, 
this promotes inter-departmental communication regarding government-owned land. 

Image of app:
http://content.screencast.com/users/palakar/folders/Jing/media/9a24a9d2-9d68-4fe2-aebf-39a6b0265d16/2015-02-08_1323.png

Data sources:
http://egis.hud.gov/ArcGIS/rest/services/cpdmaps/AcsThematicTract/MapServer/8 -census tract median wage growth over past 10 years
http://catalog.data.gov/dataset/city-owned-land-inventory-b8efd - vacant land in Chicago
http://www.fcc.gov/developers/census-block-conversions-api - lat,long to census block FIPS code
https://data.cityofchicago.org/Public-Safety/Crimes-2014/qnmj-8ku6 - Chicago crime 2014
http://www.transitchicago.com/downloads/sch_data/ - Chicago transit
http://zillowhack.hud.opendata.arcgis.com/datasets/27b53ea69f98474eb002ac3b9c6b51eb_0 - HUD Location Affordability Index
