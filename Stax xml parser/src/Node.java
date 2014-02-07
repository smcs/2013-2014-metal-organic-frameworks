import java.util.Vector;

public class Node {
	/**
	 * ID, Element
	 */
	
	/*
	 * 1. Calculate hybridization on each atom= number of bonds attached to each atom 
	 * 2. Create a separate hash map for bond 
	 */

	private int id;
	private int Element;
	private Vector<Bond> bonds;

	public Node() {
		bonds = new Vector<Bond>();
	}
	
	public int getID(){
		return id;
	}
	
	public int getElement(){
		return Element; 
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
	
	public void setID(int New_id){
		id = New_id; 
	}
	
	public void setElement(int New_Element){
		Element= New_Element;
	}
}
