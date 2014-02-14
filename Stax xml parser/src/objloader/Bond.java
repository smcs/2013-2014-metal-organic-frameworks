package objloader;
import java.util.Vector;

public class Bond {
	
	/**
	 * Beginning Node ID, End Node ID, Length =1,  Order 
	 */
	private int id;
	private int Order=1;
	private int B;
	private int E;

	public void setOrder(Integer NewOrder){
		if (NewOrder !=  null && NewOrder > 0){
		Order = NewOrder;
		}
	}
	
	public int getOrder(){
		return Order; 
	}
	
	public void setID (int New_ID){
		id=New_ID; 
	}
	
	public int getID(){
		return id; 
	}
	
	public int getB(){
		return B;
	}
	
	public int getE(){
		return E; 
	}
	
	public void setB(int New_B){
		B= New_B;
	}

	public void setE(int parseInt) {
		E= parseInt; 
	}
	

}
