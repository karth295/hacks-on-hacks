import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ChicagoLocatAffordParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// parse location affordability index data
	public static Map<String, Double[]> parseLAIData() throws FileNotFoundException {
		Map<String, Double[]> res = new HashMap<String, Double[]>();
		Scanner in = new Scanner(new File("NewMedianIncomeChange.txt")); 
		String line;
		String[] s;
		line = in.nextLine();
		while (in.hasNext()) {			
			line = in.nextLine();
			s = line.split(",");
			Double[] data = {Double.parseDouble(s[1]),Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4])};
			
			res.put(s[0].substring(0,12), data);
			
		}
		
		
		return res;
		
	}

}
