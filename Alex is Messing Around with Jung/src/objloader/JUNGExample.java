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
	private parser p = new parser(); 

	
	public JUNGExample() throws XMLStreamException, Exception{
		
		//add nodes from the parser with 
		for (Integer key : p.nodes.keySet()){
			atoms.put(key, new JUNGatom(p.nodes.get(key).getName(),p.nodes.get(key).getName()
					,p.nodes.get(key).getElement()));
			g.addVertex(atoms.get(key)); 
		}
		
		//add edges of bonds from the parser 
		for (Integer key : p.bonds.keySet()){
			g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getB()),
					atoms.get(p.bonds.get(key).getE()), 20)
					, atoms.get(p.bonds.get(key).getB()), atoms.get(p.bonds.get(key).getE()),
					EdgeType.UNDIRECTED); 
		}
		
		for (Integer key : p.bonds.keySet()){
			for (Integer key2 : p.bonds.keySet()){
				//if the two bonds start at the same element 
				if(p.bonds.get(key).getB() == p.bonds.get(key2).getE() &&
						!checkLoop(p.bonds.get(key).getE(),p.bonds.get(key2).getB())){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getE()),
							atoms.get(p.bonds.get(key2).getB()), 500)
							, atoms.get(p.bonds.get(key).getE()), atoms.get(p.bonds.get(key2).getB()),
							EdgeType.UNDIRECTED); 
				}
				if(p.bonds.get(key).getE() == p.bonds.get(key2).getE() &&
						!checkLoop(p.bonds.get(key).getB(),p.bonds.get(key2).getB())){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getB()),
							atoms.get(p.bonds.get(key2).getB()), 500)
							, atoms.get(p.bonds.get(key).getB()), atoms.get(p.bonds.get(key2).getB()),
							EdgeType.UNDIRECTED); 
				}
				if(p.bonds.get(key).getB() == p.bonds.get(key2).getB() &&
						!checkLoop(p.bonds.get(key).getE(),p.bonds.get(key2).getE())){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getE()),
							atoms.get(p.bonds.get(key2).getE()), 500)
							, atoms.get(p.bonds.get(key).getE()), atoms.get(p.bonds.get(key2).getE()),
							EdgeType.UNDIRECTED); 
				}
				if(p.bonds.get(key).getE() == p.bonds.get(key2).getB() &&
						!checkLoop(p.bonds.get(key).getB(),p.bonds.get(key2).getE())){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getB()),
							atoms.get(p.bonds.get(key2).getE()), 500)
							, atoms.get(p.bonds.get(key).getB()), atoms.get(p.bonds.get(key2).getE()),
							EdgeType.UNDIRECTED); 
				}
			}
		}
		 
		
		System.out.println("# bonds is: " + g.getEdges().size());
		
 
	}


	private boolean checkLoop(Integer key, Integer key2) {
		System.out.println("I've been called");
		int[][] connected = new int[40][40];
		
		for (Integer key3 : p.bonds.keySet()){
			connected[p.bonds.get(key3).getB()][p.bonds.get(key3).getE()] = 1; 
			connected[p.bonds.get(key3).getE()][p.bonds.get(key3).getB()] = 1; 
		}
		int count = 0; 
		while(count > 0){
			for (Integer key3 : p.nodes.keySet()){
				for (Integer key4 : p.nodes.keySet()){
					for (Integer key5 : p.nodes.keySet()){
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
		for(Integer key3 : p.bonds.keySet()){
			if(key == p.bonds.get(key3).getB()){
				if(p.nodes.get(key).getElement() != 6){
					result = true; 
				}
			}
			if(key2 == p.bonds.get(key3).getB()){
				if(p.nodes.get(key2).getElement() != 6){
					result = true; 
				}
			}
		}
		System.out.println(key);
		System.out.println(key2); 
		System.out.println("The first element is: "+ p.nodes.get(key).getElement());
		System.out.println("The second element is: "+ p.nodes.get(key2).getElement());
		
		if(p.nodes.get(key).getElement() == 6 && p.nodes.get(key2).getElement() == 6){
			System.out.println("The result is true");
			return true;
		}else{
			System.out.println("The result is false");
			return false;
		}
		 
	}
}
