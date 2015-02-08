import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class Ranker {

	public static Map<String, String> coords;
	public static Map<String, Double> MHHIC;
	public static Map<String, Integer> TransitCount;
	public static Map<String, Double> PropertySize;
	public static Map<String, Double[]> LocateAffordIndex;
	public static Map<String, Double> CrimeCount;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// FIPS -> Coords
		coords = ChicagoFIBStoCoordinates.parseBlocks();
		
		// Median House Hold Income Change In 10 years
		MHHIC = ChicagoMedianIncomeParser.getMHHIC();

		// Number of transit area in a FIPS block
		TransitCount = ChicagoStopsParser.getStops();
		
		// Sizes of property available in FIPS block
		PropertySize = ChicagoPropertyParser.getSize();
		
		// FIPS -> HousingAsPercentage, TransitAsPercentage, Median Income, EmploymentAccessability 
		// LocateAffordIndex = ChicagoLocatAffordParser.parseLAIData();
		
		// FIPS -> Crime Count
		CrimeCount = ChicagoCrimeParser.parseCrimeData();
		
		// 
		System.out.println(coords.keySet().size());
		System.out.println(MHHIC.keySet().size());
		System.out.println(TransitCount.keySet().size());
		//System.out.println(PropertySize.keySet().size());
		Set<String> fips = coords.keySet();
		PriorityQueue<CensusBlock> que = new PriorityQueue<CensusBlock>();
		for (String c : fips) {
			//System.out.println(c);
			CensusBlock a = new CensusBlock();
			a.fips = c;
			int match = 0;
			if (coords.containsKey(c)) {
				// System.out.println("Match Coord");
				match++;
				String[] coor = coords.get(c).split(",");
				a.lat = coor[0];
				a.lon = coor[1];
			}
			
			if (MHHIC.containsKey(c.substring(0,11))) {
				//System.out.println("Match MHHIC");

				match++;
				a.perctIncreaseMedianHHIncome = MHHIC.get(c.substring(0,11));
			}
			
			if (TransitCount.containsKey(c)) {
				//System.out.println("Match Transit");
				match++;
				a.transit = TransitCount.get(c);
			}
			if (PropertySize.containsKey(c)) {
				//System.out.println("Match PPS");
				match++;
				a.size = PropertySize.get(c);
			}
			
			if (CrimeCount.containsKey(c)) {
				//System.out.println("Crime Match");
				a.crime = CrimeCount.get(c);
			}
//			Double[] data = LocateAffordIndex.get(c);
//			a.employmentAccessability = data[3];
//			a.medianHHIncome = data[2];
//			a.transitAsPercent = data[1];
//			a.housingAsPercent = data[0];
			
			//a.crime = CrimeCount.get(c);
			//System.out.println(match);
			que.add(a);
		}
		System.out.println(que);
		System.out.println(que.iterator().next().value());
		
		PrintWriter writer = new PrintWriter("Ranks.txt", "UTF-8");

		for (CensusBlock c : que) {
			System.out.println(c.fips + "," + c.lat + "," + c.lon + "," + c.size + "," + c.crime + "," + c.employmentAccessability + "," + c.housingAsPercent + "," + c.medianHHIncome + "," + c.perctIncreaseMedianHHIncome + "," + c.transit + "," + c.transitAsPercent + "," + c.value());
			writer.println(c.fips + "," + c.lat + "," + c.lon + "," + c.size + "," + c.crime + "," + c.employmentAccessability + "," + c.housingAsPercent + "," + c.medianHHIncome + "," + c.perctIncreaseMedianHHIncome + "," + c.transit + "," + c.transitAsPercent + "," + c.value());
		}
		
		writer.close();
		
	}
	
	// return super set of FIPS from all the sets
	public static Set<String> getUniqueFIPS() {
		Set<String> res = new HashSet<String>(MHHIC.keySet());
		for (String c : TransitCount.keySet()) {
			if (!res.contains(c)) {
				res.add(c);
			}
		}
		for (String c : PropertySize.keySet()) {
			if (!res.contains(c)) {
				res.add(c);
			}
		}
		for (String c : LocateAffordIndex.keySet()) {
			if (!res.contains(c)) {
				res.add(c);
			}
		}
		for (String c : CrimeCount.keySet()) {
			if (!res.contains(c)) {
				res.add(c);
			}
		}
		return res;
	}
	
	

}
