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
				if(p.bonds.get(key).getB() == p.bonds.get(key2).getE()){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getE()),
							atoms.get(p.bonds.get(key2).getB()), 500)
							, atoms.get(p.bonds.get(key).getE()), atoms.get(p.bonds.get(key2).getB()),
							EdgeType.UNDIRECTED); 
				}
				if(p.bonds.get(key).getE() == p.bonds.get(key2).getE()){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getB()),
							atoms.get(p.bonds.get(key2).getB()), 500)
							, atoms.get(p.bonds.get(key).getB()), atoms.get(p.bonds.get(key2).getB()),
							EdgeType.UNDIRECTED); 
				}
				if(p.bonds.get(key).getB() == p.bonds.get(key2).getB()){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getE()),
							atoms.get(p.bonds.get(key2).getE()), 500)
							, atoms.get(p.bonds.get(key).getE()), atoms.get(p.bonds.get(key2).getE()),
							EdgeType.UNDIRECTED); 
				}
				if(p.bonds.get(key).getE() == p.bonds.get(key2).getB()){
					g.addEdge(new JUNGbond(atoms.get(p.bonds.get(key).getB()),
							atoms.get(p.bonds.get(key2).getE()), 500)
							, atoms.get(p.bonds.get(key).getB()), atoms.get(p.bonds.get(key2).getE()),
							EdgeType.UNDIRECTED); 
				}
			}
		}
		
		System.out.println("# bonds is: " + g.getEdges().size());
		
 
	}
}
