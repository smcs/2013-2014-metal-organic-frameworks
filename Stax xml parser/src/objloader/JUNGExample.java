package objloader;
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
			
		   switch(p.return_nodes().get(key).getID()){
		   
		   case(12):
			   atoms.put(key, new JUNGatom("Carbon","C", 12.0107));
		   break; 
		   
		   case(16):
			   atoms.put(key, new JUNGatom("Oxygen", "O", 15.9994)); 
		   break; 
		   }
		}
		//add weights for each bond
		for (Integer key : p.return_bonds().keySet()) {
			//weight from beginning to end
			carJUNGbondioxide.addEdge(new JUNGbond(atoms.get(p.return_bonds().get(key).getB()),
					atoms.get(p.return_bonds().get(key).getE())),
					atoms.get(p.return_bonds().get(key).getB()),
					atoms.get(p.return_bonds().get(key).getE()), EdgeType.DIRECTED);
			
			carJUNGbondioxide.addEdge(new JUNGbond(atoms.get(p.return_bonds().get(key).getE()),
					atoms.get(p.return_bonds().get(key).getB())),
					atoms.get(p.return_bonds().get(key).getE()),
					atoms.get(p.return_bonds().get(key).getB()), EdgeType.DIRECTED);
		}
		

		/* choose a basic layout for our graph */
		Layout<JUNGatom, JUNGbond> layout = new FRLayout<JUNGatom, JUNGbond>(carJUNGbondioxide);

		/* connect the layout to a visualization engine */
		BasicVisualizationServer<JUNGatom, JUNGbond> bvs = new BasicVisualizationServer<JUNGatom, JUNGbond>(layout);

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
