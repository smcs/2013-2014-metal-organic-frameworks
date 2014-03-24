package objloader;
import java.util.Vector;

public class Node {
	/**
	 * ID, Element
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
