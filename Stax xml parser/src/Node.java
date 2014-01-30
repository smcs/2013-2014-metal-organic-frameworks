import java.util.Vector;

public class Node {

	int id;
	int p;
	int Z;
	int Element;
	Vector<Bond> bonds;

	public Node() {
		bonds = new Vector<Bond>();
	}
	
	public String toString() {
		return id + " " + Element;
	}

	public void addBond(Bond b) {
		bonds.add(b);
	}
	
	public int getBondCount() {
		/* NOTA BENE: we are completely ignoring order here */
		return bonds.size();
	}

	public void addOrder(Bond b) {
		
		
	}
}
