import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChicagoStopsParser {
	
	// Parse Chicago Property
	// return map of FIBS code to Stops Count
		public static Map<String, Integer> getStops() throws FileNotFoundException {
			Scanner in = new Scanner(new File("new_stops.txt")); 
			String line;
			String[] s;
			
			Map<String, Integer> fipsToStops = new HashMap<String, Integer>();
			while (in.hasNext()) {
				
				line = in.nextLine();
				s = line.split(",");
				
			
				String fib = s[2].substring(0, 12);
				//System.out.println(cs);
				if(fipsToStops.containsKey(fib)) {
					int combine = fipsToStops.get(fib) + 1;
					fipsToStops.put(fib, combine);
				} else {
					fipsToStops.put(fib, 1);
				}
			}
			return fipsToStops;
		}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//		System.out.println(getStops());
		Map<String, Integer> res = getStops();
		double total = 0;
		double count = 0;
		for (String key : res.keySet()) {
			count++;
			total += res.get(key);
		}
		System.out.println(total/count);
//		PrintWriter writer = new PrintWriter("ChicagoCensusBlockFIPS.txt", "UTF-8");
//
//		for (String s : parseBlocks().keySet()) {
//			System.out.println(s);
//			writer.println(s);
//
//		}
//		
//		writer.close();
	}

}
