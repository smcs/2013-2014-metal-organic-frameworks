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

public class JUNGExample {
	public static void main(String[] args) throws XMLStreamException, Exception {
		
		/* define our basic graph made of JUNGatoms and JUNGbonds */
		Graph<JUNGatom, JUNGbond> carJUNGbondioxide = new SparseGraph<JUNGatom, JUNGbond>();
		
		//get molecule data from parser
		parser p = new parser();
		Vector<JUNGbond> bonds = new Vector<JUNGbond>(); 
		HashMap<Integer ,JUNGatom> atoms = new HashMap<Integer,JUNGatom>(); 
		
		//convert nodes from parser into JUNGatoms preserving the keys 
		for (Integer key : p.return_nodes().keySet()) {
			
		   System.out.println(p.return_nodes().get(key).getElement()); 
		   switch(p.return_nodes().get(key).getElement()){
		   
		   
		   case(6):
			   atoms.put(key, new JUNGatom("Carbon","C", 12.0107));
		   break; 
		   
		   case(8):
			   atoms.put(key, new JUNGatom("Oxygen", "O", 15.9994)); 
		   break; 
		   }
		}
		//add weights for each bond
		JUNGbond J; 
		for (Integer key : p.return_bonds().keySet()) {
			//weight from beginning to end
			System.out.println(atoms.get(3));
			
			J = new JUNGbond(atoms.get(p.return_bonds().get(key).getB()),
					atoms.get(p.return_bonds().get(key).getE()));
			carJUNGbondioxide.addEdge(J,
					atoms.get(p.return_bonds().get(key).getB()),
					atoms.get(p.return_bonds().get(key).getE()), EdgeType.DIRECTED);
			
			
			J = new JUNGbond(atoms.get(p.return_bonds().get(key).getE()),
					atoms.get(p.return_bonds().get(key).getB())); 
			carJUNGbondioxide.addEdge(J,
					atoms.get(p.return_bonds().get(key).getE()),
					atoms.get(p.return_bonds().get(key).getB()), EdgeType.DIRECTED);
					
			
		}
		

		/* choose a basic layout for our graph */
		Layout<JUNGatom, JUNGbond> layout = new FRLayout<JUNGatom, JUNGbond>(carJUNGbondioxide);
		layout.setSize(new Dimension(500,500));

		/* connect the layout to a visualization engine */
		BasicVisualizationServer<JUNGatom, JUNGbond> bvs = new BasicVisualizationServer<JUNGatom, JUNGbond>(layout);
		
		Point2D midpoint = new Point(250,250); 
		CrossoverScalingControl scaler = new CrossoverScalingControl();
		scaler.scale(bvs, (float) 0.5, midpoint); 
		/* set up some labeling for convenience */
		bvs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		bvs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		/* set up a Swing JFrame to present the viewer */
		JFrame frame = new JFrame("Spring Layout View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(bvs);
		frame.pack();
		frame.setVisible(true);
	}
}
