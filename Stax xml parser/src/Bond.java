public class Bond {

	private int id;
	private int Order=1;
	private int B;
	private int E;

	public String toString() {
		return id + " " + Order;
	}

	public void setOrder(Integer NewOrder){
		if (NewOrder !=  null && NewOrder > 0){
		Order = NewOrder;
		}
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
