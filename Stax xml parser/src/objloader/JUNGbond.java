package objloader;

public class JUNGbond {
	private JUNGatom start, end;
	private double weight;

	public JUNGbond(JUNGatom start, JUNGatom end) {
		this(start, end, 0.25);
	}

	public JUNGbond(JUNGatom start, JUNGatom end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
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
}
