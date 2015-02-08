import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ChicagoFIBStoCoordinates {
	// return map of FIBS code to Coordinates
		public static Map<String, String> parseBlocks() throws FileNotFoundException {
			Scanner in = new Scanner(new File("NewChicagoPropertyBlocks.txt")); 
			String line;
			int conunt = 0;
			String[] s;
			Map<String, String> map = new HashMap<String, String>();
			while (in.hasNext()) {
				conunt++;
				line = in.nextLine();
				s = line.split(",");
				
				String fib = s[2].substring(0,12);
				if (!map.containsKey(fib)) {
					map.put(fib, s[0] + "," + s[1]);
				}
				
			}
//			System.out.println(conunt);
//			System.out.println(map.keySet().size());
			return map;
		}

		
		public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
			System.out.println(parseBlocks());
//			PrintWriter writer = new PrintWriter("ChicagoCensusBlockFIPS.txt", "UTF-8");
			//
//					for (String s : parseBlocks().keySet()) {
//						System.out.println(s);
//						writer.println(s);
			//
//					}
//					
//					writer.close();
		}
}
