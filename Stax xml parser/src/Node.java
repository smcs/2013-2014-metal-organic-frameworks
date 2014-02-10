import java.util.Vector;

public class Node {
	/**
	 * ID, Element
	 */
	
	/*
	 * 1. Calculate hybridization on each atom= number of bonds attached to each atom 
	 */

	private int id;
	private int Element;

	
	public int getID(){
		return id;
	}
	
	public int getElement(){
		return Element; 
	}
	
	public void setID(int New_id){
		id = New_id; 
	}
	
	public void setElement(int New_Element){
		Element= New_Element;
	}
}
