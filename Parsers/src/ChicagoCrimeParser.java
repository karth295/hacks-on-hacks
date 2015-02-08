import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ChicagoCrimeParser {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		parseCrimeData();
	}
	
	// parse location affordability index data
	public static Map<String, Double> parseCrimeData() throws FileNotFoundException {
		Map<String, Double> res = new HashMap<String, Double>();
		Scanner in = new Scanner(new File("NewCrime.txt")); 
		String line;
		String[] s;
		line = in.nextLine();
		//System.out.println("sdas");
		while (in.hasNext()) {			
			line = in.nextLine();
			//System.out.println(line);
			s = line.split(",");
			String fib = s[2].substring(0,12);
		
			//System.out.println(cs);
			if (res.containsKey(fib)) {
				Double combine = res.get(fib) + 1;
				res.put(fib, combine);
			} else {
				res.put(fib, 1.0);
			}
		
		}
		//System.out.println(res);
		
		
		return res;
		
	}

}
