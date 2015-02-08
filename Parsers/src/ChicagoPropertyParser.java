import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChicagoPropertyParser {
	
	// Parse Chicago Property
	// return map of FIBS code to Property Sizes
		public static Map<String, Double> getSize() throws FileNotFoundException {
			Scanner in = new Scanner(new File("ChicagoProperty.csv")); 
			String line;
			String[] s;
			line = in.nextLine();
			Map<String, Double> fipsToSize = new HashMap<String, Double>();
			while (in.hasNext()) {
				
				line = in.nextLine();
				// more HACKs
				if (line.charAt(0) == '(') {
				} else if (line.startsWith("CHICAGO")) {
				}else {
					s = line.split(",");
					
					// HACK
					if (s.length > 5) {
						String cs = s[0].replaceAll("-", "");
						cs = cs.substring(0, 12);
						//System.out.println(cs);
						if(fipsToSize.containsKey(cs)) {
							double combine = fipsToSize.get(cs) + Double.parseDouble(s[5]);
							fipsToSize.put(cs, combine);
						} else {
							fipsToSize.put(cs, Double.parseDouble(s[5]));
						}
					}
				}
				

			}
			return fipsToSize;
		}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//		System.out.println(getSize());
		double total = 0;
		Map<String, Double> res = getSize();
		for (String key : res.keySet()) {
			total += res.get(key);
		}
		
		System.out.println(total/res.size());
		
	}

}
