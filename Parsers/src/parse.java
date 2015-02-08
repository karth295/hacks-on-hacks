import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class parse {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		PrintWriter writer = new PrintWriter("NewChicagoProperty.txt", "UTF-8");
		
		
		Scanner in = new Scanner(new File("ChicagoProperty.csv")); 
		String line;
		String p;
		String income = "";
		String square;
		String coord;
		boolean have = true;
		in.hasNext();
		while (in.hasNext()) {
			line = in.nextLine();
			System.out.println(line);
			//p = line.split(",");
			
			//line = in.nextLine();
			if (line.charAt(0) == '(') {
				//System.out.println(line);
				coord = line.substring(1, line.length()-2);
				System.out.println(coord);
				//writer.println(coord);
			}
			// HACKS!!
			//writer.println(p[p.length-2].substring(2) + "," + p[p.length-1].substring(0,p[p.length-1].length() - 2));
			//System.out.println();
//			if (line.contains("coordinates")) {
//				String[] a;
//				p = line.substring(52, 73);
//				a = p.split(",");
//				System.out.println(a[1] + "," + a[0].substring(1));
//				writer.println(a[1] + "," + a[0].substring(1));
//
//			}
			//System.out.println();
			//System.out.println(p[p.length-5] + ", " + p[p.length-4]);
			
		}
		
		writer.close();
	}

}
