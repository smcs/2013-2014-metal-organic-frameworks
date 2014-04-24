package objloader;

import java.awt.Paint;

public class JUNGbond {
	private JUNGatom start, end;
	private double weight;
	private Paint p; 

	public JUNGbond(JUNGatom start, JUNGatom end) {
		this(start, end, 1);
	}

	public JUNGbond(JUNGatom start, JUNGatom end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	public JUNGbond(JUNGatom start, JUNGatom end, double weight, Paint p) {
		this.start = start;
		this.end = end;
		this.weight = weight;
		this.p = p; 
	}

	public JUNGbond(JUNGbond other) {
		this.start = other.start;
		this.end = other.end;
		this.weight = other.weight;
	}

	public JUNGatom getStart() {
		return start;
	}

	public JUNGatom getEnd() {
		return end;
	}

	public double getWeight() {
		return weight;
	}

	public String toString() {
		return Double.toString(weight);
	}
	public Paint getP(){
		return p; 
	}
}
