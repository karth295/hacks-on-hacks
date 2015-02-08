import csv


def delta_growth_by_zipcode(file):
  growth = {}
  with open(file, 'rb') as csvfile:
    reader = csv.reader(csvfile)
    for line in reader:
      growth[float(line[0])] = float(line[2]) - float(line[1])  # delta in growth by zip code
  return growth

def main():
  growth = delta_growth_by_zipcode("growth.csv")
  print growth

if __name__ == '__main__':
  main()