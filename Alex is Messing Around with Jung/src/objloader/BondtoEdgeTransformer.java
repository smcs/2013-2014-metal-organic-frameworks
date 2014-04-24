package objloader;

import edu.uci.ics.jung.algorithms.layout3d.SpringLayout.LengthFunction;

public class BondtoEdgeTransformer implements LengthFunction<JUNGbond> {

	public double getLength(JUNGbond b) {
		// TODO Auto-generated method stub
		return b.getWeight();
	}



}
