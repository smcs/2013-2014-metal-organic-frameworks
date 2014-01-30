import java.util.HashMap;


public class NodeMap {

	public static void main(String[] args) {
		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
		
		/* magically read in a node */
		Node n = new Node();
		n.id = 42;
		
		/* store that node for future reference */
		nodes.put(n.id, n);
		
		/* heck, let's add another node! */
		n = new Node();
		n.id = 17;
		nodes.put(n.id, n);
		
		/* let's read a bond! */
		Bond b = new Bond();
		b.B = 42;
		b.E = 17;
		
		/* add the bond to the affected notes */
		nodes.get(b.B).addBond(b);
		nodes.get(b.E).addBond(b);
		
		nodes.get(b.Order).addOrder(b);
		
		/* how many bonds does any particular node have? */
		nodes.get(42).getBondCount();
	}

	public void put(int parseInt, Node currNode) {
		// TODO Auto-generated method stub
		
	}

}
