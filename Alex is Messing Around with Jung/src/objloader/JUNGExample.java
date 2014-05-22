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
			atoms.put(key, new JUNGatom(MyParser.nodes.get(key).getName(),MyParser.nodes.get(key).getName()
					,MyParser.nodes.get(key).getElement()));
			g.addVertex(atoms.get(key)); 
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
		int[] keyBonds = new int[4];
		int[] key2Bonds = new int[4];
		for (Integer key : atoms.keySet()){
			for (Integer key2 : atoms.keySet()){
				if(atoms.get(key).getSymbol() == "C" && atoms.get(key2).getSymbol() == "C"){

					int i = 0;
					int j = 0; 
					//+1000 corresponds to the bond beginning of the bond on the Carbon
					for (Integer key3 : MyParser.bonds.keySet()){
						if(MyParser.bonds.get(key3).getB() == key){
							keyBonds[i] = key3 + 1000; 
							i++;
						}
						if(MyParser.bonds.get(key3).getB() == key2){
							key2Bonds[j] = key3 + 1000;
							j++;
						}
					    if(MyParser.bonds.get(key3).getE() == key){
					    	keyBonds[i] = key3;
					    	i++;
						}
						if(MyParser.bonds.get(key3).getE() == key2){
							key2Bonds[j] = key3;
							j++; 
						}
					}

				}

			}
		}
		int b1 = 0;
		int b2 = 0;
		int b3 = 0;
		int b4 = 0; 
		if(keyBonds.length == 2 && key2Bonds.length == 2){
			

		for(int i = 0; i < keyBonds.length; i++){
				if(i == 0){
					if(keyBonds[i] > 1000){
						b1 = MyParser.bonds.get(keyBonds[i]).getE();
					}
					if(keyBonds[i] < 1000){
						b1 = MyParser.bonds.get(keyBonds[i]).getB();
					}
				}else{
					if(keyBonds[i] > 1000){
						b2 = MyParser.bonds.get(keyBonds[i]).getE();
					}
					if(keyBonds[i] < 1000){
						b2 = MyParser.bonds.get(keyBonds[i]).getB();
					}
				}
		}
				for(int i = 0; i < keyBonds.length; i++){
					if(i == 0){
						if(key2Bonds[i] > 1000){
							b3 = MyParser.bonds.get(keyBonds[i]).getE();
						}
						if(key2Bonds[i] < 1000){
							b3 = MyParser.bonds.get(keyBonds[i]).getB();
						}
					}else{
						if(key2Bonds[i] > 1000){
							b4 = MyParser.bonds.get(keyBonds[i]).getE();
						}
						if(key2Bonds[i] < 1000){
							b4 = MyParser.bonds.get(keyBonds[i]).getB();
						}
					}
				
		}
				
		g.addEdge(new JUNGbond(atoms.get(b1),atoms.get(b3), 20), atoms.get(b1),atoms.get(b3));
		
		g.addEdge(new JUNGbond(atoms.get(b2),atoms.get(b4), 20), atoms.get(b2),atoms.get(b4));

		
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
