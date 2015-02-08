import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class CleanMedianIncome {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		PrintWriter writer = new PrintWriter("NewMedianIncomeChange.txt", "UTF-8");
		
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new File("MedianIncomeChange.txt")); 
		String line;
		String p;
		String income = "";

		boolean have = true;
		in.hasNext();
		while (in.hasNext()) {
			line = in.nextLine();
			//System.out.println(line);
			
			if (line.contains("GEOID")) {
				p = line.split(": ")[1];
				while (in.hasNext()) {
					line = in.nextLine();
					if (line.contains("PCT_MEDHH_INC")) {
						String[] a = line.split(": ");
						if (a.length != 1) {
							income = a[1];
							have = true;
						} else {
							have = false;
						}
						break;
					}
				}
				if (have) {
					writer.println(p + "," + income);

				}
			}
			
		}
		
		writer.close();
	}

}
