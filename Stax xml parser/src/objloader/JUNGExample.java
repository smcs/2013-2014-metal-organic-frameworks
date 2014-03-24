package objloader;
import javax.swing.JFrame;
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
	public static void main(String[] args) {
		/* define our basic graph made of JUNGatoms and JUNGbonds */
		Graph<JUNGatom, JUNGbond> carJUNGbondioxide = new SparseGraph<JUNGatom, JUNGbond>();

		/* define a few handy JUNGatoms */
		JUNGatom carbon = new JUNGatom("Carbon", "C", 12.0107);
		JUNGatom oxygen1 = new JUNGatom("Oxygen", "O", 15.9994);
		JUNGatom oxygen2 = new JUNGatom(oxygen1);

		/* add the JUNGatoms (and dynamically created JUNGbonds) to the graph */
		carJUNGbondioxide.addEdge(new JUNGbond(carbon, oxygen1), carbon, oxygen1, EdgeType.DIRECTED);
		carJUNGbondioxide.addEdge(new JUNGbond(oxygen1, carbon), oxygen1, carbon, EdgeType.DIRECTED);
		carJUNGbondioxide.addEdge(new JUNGbond(carbon, oxygen2), carbon, oxygen2, EdgeType.DIRECTED);
		carJUNGbondioxide.addEdge(new JUNGbond(oxygen2, carbon), oxygen2, carbon, EdgeType.DIRECTED);

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