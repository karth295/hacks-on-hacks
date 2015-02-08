
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CensusBlock implements Comparable<CensusBlock>{
	public String fips;
	public String lat;
	public String lon;
	public double transit;
	public double size;
	public double crime;
	public double medianHHIncome;
	public double housingAsPercent;
	public double transitAsPercent;
	public double employmentAccessability;
	public double perctIncreaseMedianHHIncome;
	
	
	public static double avg_transit = 1.33;
	public static double avg_size = 5790.21;
	public static double avg_crime = 20;
	public static double avg_perctIncreaseMedianHHIncome = 18.06;


	
	public static double wgt_transit = 1;
	public static double wgt_size = 3;
	public static double wgt_crime = -3;
	public static double wgt_medianHHIncome = 2;
	public static double wgt_housingAsPercent = -2;
	public static double wgt_transitAsPercent = -1;
	public static double wgt_employmentAccessability = 2;
	public static double wgt_perctIncreaseMedianHHIncome = 2;
	
	public CensusBlock() {
		fips = "";
		lat = "";
		lon = "";
		transit = 0;
		size = 0;
		crime = 0;
		medianHHIncome = 0;
		housingAsPercent = 0;
		transitAsPercent = 0;
		employmentAccessability = 0;
		perctIncreaseMedianHHIncome = 0;
	}
	
	public static void main(String[] args) {
		 Gson gson = new GsonBuilder().create();
	        gson.toJson("Hello", System.out);
	        gson.toJson(123, System.out);
	}
	
	public double value() {
		return transit/avg_transit*wgt_transit + size/avg_size*wgt_size + crime/avg_crime*wgt_crime +
				medianHHIncome*wgt_medianHHIncome + housingAsPercent*wgt_housingAsPercent + transitAsPercent*wgt_transitAsPercent
			   + employmentAccessability*wgt_employmentAccessability + perctIncreaseMedianHHIncome/avg_perctIncreaseMedianHHIncome*wgt_perctIncreaseMedianHHIncome;
	}
	
	@Override 
	public int compareTo(CensusBlock aThat) {
		return (int) (aThat.value() - this.value());		
	}
}
