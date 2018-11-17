package maxmin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomise {
	
	public static List<Double> makeList(int n,double lower,double upper) {
		double range=upper-lower;
		Random rng = new Random(System.currentTimeMillis());
		List<Double> out = new ArrayList<Double>(n);
		for(int i=0;i<n;i++) out.add((rng.nextDouble()*range)+lower);
		return out;
	}
	public static List<Double> makeSymmetricList(int n,double range) {
		return makeList(n,-0.5*range,0.5*range);
	}

}
