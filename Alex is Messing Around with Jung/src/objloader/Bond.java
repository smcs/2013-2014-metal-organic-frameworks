package objloader;

public class Bond {
	private int b;
	private int e;
	private int id; 
	private int order; 
	
	public Bond(){
		
	}
	public void setB(int i){
		b = i; 
	}
	
	public void setE(int i){
		e = i; 
	}
	public void setID(int i){
		id = i; 
	}
	public void setOrder(int i){
		order = i; 
	}
	public int getID(){
		return id; 
	}
	public int getB(){
		return b;
	}
	public int getE(){
		return e; 
	}
	public int getOrder(){
		return order; 
	}
}
