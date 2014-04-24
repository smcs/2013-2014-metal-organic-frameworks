package objloader;

public class Node {
	private int id;
	private int element;
	private StringBuilder sb;
	
	public Node(){
		sb = new StringBuilder();
	}
	
	public void setID(int i){
		id = i; 
	}
	public void setElement(int i){
		element = i; 
	}
	public int getID(){
		return id;
	}
	public int getElement(){
		return element;
	}
	public String getName(){
		sb.append(element);
		return sb.toString();
	}
	
}
