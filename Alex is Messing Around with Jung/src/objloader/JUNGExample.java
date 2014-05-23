package objloader;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Vector;


import javax.swing.JFrame;
import javax.xml.stream.XMLStreamException;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * An example using the JUNG (Java Universal Network Graphs) library. The Grotto
 * tutorials come highly recommended
 * {@link http://www.grotto-networking.com/JUNG/JUNG2-Tutorial.pdf}.
 * 
 * @author SethBattis@stmarksschool.org
 */

public class JUNGExample{
	/* define our basic graph made of JUNGatoms and JUNGbonds */
	public static Graph<JUNGatom, JUNGbond> g = new SparseGraph<JUNGatom, JUNGbond>();
	private Vector<JUNGbond> bonds = new Vector<JUNGbond>(); 
	private HashMap<Integer ,JUNGatom> atoms = new HashMap<Integer,JUNGatom>();
	private parser MyParser = new parser(); 

	
	public JUNGExample() throws XMLStreamException, Exception{
		
		//add nodes from the parser with 
		for (Integer key : MyParser.nodes.keySet()){
			if(MyParser.nodes.get(key).getElement() == 6.0){
				atoms.put(key, new JUNGatom("C","Carbon"
						,-300));
			}
			if(MyParser.nodes.get(key).getElement() == 8.0){
				atoms.put(key, new JUNGatom("O","Oxygen"
						,-300));
			}
		
			g.addVertex(atoms.get(key)); 
			System.out.println("The names are: " + atoms.get(key).getWeight());
			
		}
		
		//add edges of bonds from the parser 
		for (Integer key : MyParser.bonds.keySet()){
			g.addEdge(new JUNGbond(atoms.get(MyParser.bonds.get(key).getB()),
					atoms.get(MyParser.bonds.get(key).getE()), 20)
					, atoms.get(MyParser.bonds.get(key).getB()), atoms.get(MyParser.bonds.get(key).getE()),
					EdgeType.UNDIRECTED); 
		}
		
		for (Integer key : MyParser.bonds.keySet()){
			for (Integer key2 : MyParser.bonds.keySet()){
				//if the two bonds start at the same element 
				if(MyParser.bonds.get(key).getB() == MyParser.bonds.get(key2).getE() &&
						!checkLoop(MyParser.bonds.get(key).getE(),MyParser.bonds.get(key2).getB())){
					g.addEdge(new JUNGbond(atoms.get(MyParser.bonds.get(key).getE()),
							atoms.get(MyParser.bonds.get(key2).getB()), 100)
							, atoms.get(MyParser.bonds.get(key).getE()), atoms.get(MyParser.bonds.get(key2).getB()),
							EdgeType.UNDIRECTED); 
				}
				if(MyParser.bonds.get(key).getE() == MyParser.bonds.get(key2).getE() &&
						!checkLoop(MyParser.bonds.get(key).getB(),MyParser.bonds.get(key2).getB())){
					g.addEdge(new JUNGbond(atoms.get(MyParser.bonds.get(key).getB()),
							atoms.get(MyParser.bonds.get(key2).getB()), 100)
							, atoms.get(MyParser.bonds.get(key).getB()), atoms.get(MyParser.bonds.get(key2).getB()),
							EdgeType.UNDIRECTED); 
				}
				if(MyParser.bonds.get(key).getB() == MyParser.bonds.get(key2).getB() &&
						!checkLoop(MyParser.bonds.get(key).getE(),MyParser.bonds.get(key2).getE())){
					g.addEdge(new JUNGbond(atoms.get(MyParser.bonds.get(key).getE()),
							atoms.get(MyParser.bonds.get(key2).getE()), 100)
							, atoms.get(MyParser.bonds.get(key).getE()), atoms.get(MyParser.bonds.get(key2).getE()),
							EdgeType.UNDIRECTED); 
				}
				if(MyParser.bonds.get(key).getE() == MyParser.bonds.get(key2).getB() &&
						!checkLoop(MyParser.bonds.get(key).getB(),MyParser.bonds.get(key2).getE())){
					g.addEdge(new JUNGbond(atoms.get(MyParser.bonds.get(key).getB()),
							atoms.get(MyParser.bonds.get(key2).getE()), 100)
							, atoms.get(MyParser.bonds.get(key).getB()), atoms.get(MyParser.bonds.get(key2).getE()),
							EdgeType.UNDIRECTED); 
				}
			}
			
		}
		int count = 0; 
		int[][] connectedBonds;
		//for each carbon we get an array of bonds beginning and ending on that carbon. 
		for(int key: atoms.keySet()){
			System.out.println("I'm Working!"); 
			count = 0; 
			connectedBonds = new int[4][2];
			System.out.println(atoms.get(key).getSymbol()); 
			if(atoms.get(key).getWeight() == 6.0){
				System.out.println("I also work here"); 
				for(int key2: MyParser.bonds.keySet()){
					if(MyParser.bonds.get(key2).getB() == key){
						
						connectedBonds[count][0] = key2;
						connectedBonds[count][1] = 0; 
						System.out.println("Printing data " + connectedBonds[count][0]); 
						count++; 
						
						
					}
					if(MyParser.bonds.get(key2).getE() == key){
						
						connectedBonds[count][0] = key2;
						connectedBonds[count][1] = 1; 
						System.out.println("Printing data " + connectedBonds[count][0]); 
						count++; 
						
						
					}
				}
			
				int newCount = 4 - count;
				//for every bond the carbon is "missing" 
				Vector<JUNGatom> newAtoms = new Vector<JUNGatom>(); 
				System.out.println("The count is" + newCount); 
				for(int i = 0; i < newCount; i++){
					System.out.println("I'm adding atoms"); 
					JUNGatom a = new JUNGatom("0","0",1);
					newAtoms.add(a); 
					//g.addVertex(a); 
					//g.addEdge(new JUNGbond(atoms.get(key), a, 100), atoms.get(key),a);
				}
				//add edges between newly placed atoms
				for(int i = 0; i < newAtoms.size(); i++){
					if(i+1 < newAtoms.size()){
						/*g.addEdge(new JUNGbond(newAtoms.get(i),newAtoms.get(i+1),100)
						, newAtoms.get(i),newAtoms.get(i+1));
					}else{
						g.addEdge(new JUNGbond(newAtoms.get(i),newAtoms.get(0),100)
						, newAtoms.get(i),newAtoms.get(0));
						*/
					}
				}
					
			}
			//add extra bonds to the carbons 
		}
		
			
		System.out.println("# bonds is: " + g.getEdges().size());
		
 
	}


	private boolean checkLoop(Integer key, Integer key2) {
		System.out.println("I've been called");
		int[][] connected = new int[40][40];
		
		for (Integer key3 : MyParser.bonds.keySet()){
			connected[MyParser.bonds.get(key3).getB()][MyParser.bonds.get(key3).getE()] = 1; 
			connected[MyParser.bonds.get(key3).getE()][MyParser.bonds.get(key3).getB()] = 1; 
		}
		int count = 0; 
		while(count > 0){
			for (Integer key3 : MyParser.nodes.keySet()){
				for (Integer key4 : MyParser.nodes.keySet()){
					for (Integer key5 : MyParser.nodes.keySet()){
						if(connected[key3][key4] == 1 && connected[key4][key5] == 1){
							if(connected[key3][key5] == 1){
								count++; 
							}
							connected[key3][key5] = 1;
							connected[key5][key3] = 1; 
						}
				}
			}
		}
		}
		boolean result; 
		if(connected[key][key] == 1 && connected[key2][key2] == 1){
			result = true; 
		}else{
			result = false; 
		}
		result = false; 
		for(Integer key3 : MyParser.bonds.keySet()){
			if(key == MyParser.bonds.get(key3).getB()){
				if(MyParser.nodes.get(key).getElement() != 6){
					result = true; 
				}
			}
			if(key2 == MyParser.bonds.get(key3).getB()){
				if(MyParser.nodes.get(key2).getElement() != 6){
					result = true; 
				}
			}
		}
		System.out.println(key);
		System.out.println(key2); 
		System.out.println("The first element is: "+ MyParser.nodes.get(key).getElement());
		System.out.println("The second element is: "+ MyParser.nodes.get(key2).getElement());
		
		if(MyParser.nodes.get(key).getElement() == 6 && MyParser.nodes.get(key2).getElement() == 6){
			System.out.println("The result is true");
			return true;
		}else{
			System.out.println("The result is false");
			return false;
		}
		 
	}
}
