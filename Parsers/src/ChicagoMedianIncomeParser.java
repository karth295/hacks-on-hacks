import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChicagoMedianIncomeParser {
	
	// Parse Chicago Property
	// return map of FIBS code to Property Sizes
		public static Map<String, Double> getMHHIC() throws FileNotFoundException {
			Scanner in = new Scanner(new File("NewMedianIncomeChange.txt")); 
			String line;
			String[] s;
			line = in.nextLine();
			Map<String, Double> fipsToMHHI = new HashMap<String, Double>();
			while (in.hasNext()) {
				
				line = in.nextLine();
				s = line.split(",");
					
				if(fipsToMHHI.containsKey(s[0])) {
					Double combine = fipsToMHHI.get(s[0]) + Double.parseDouble(s[1]);
					fipsToMHHI.put(s[0], combine);
				} else {
					fipsToMHHI.put(s[0], Double.parseDouble(s[1]));
				}
			}
			return fipsToMHHI;
		}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//		System.out.println(getSize());
		double total = 0;
		Map<String, Double> res = getMHHIC();
		for (String key : res.keySet()) {
			total += res.get(key);
		}
		
		System.out.println(total/res.size());
		
	}

}
