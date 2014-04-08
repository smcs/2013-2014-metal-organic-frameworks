package objloader;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout3d.Layout;
import edu.uci.ics.jung.algorithms.layout3d.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.TestGraphs;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization3d.VisualizationViewer;

public class GraphDemo extends JPanel {
	Graph<String, Number> demoGraph = TestGraphs.getDemoGraph();
	Graph<String, Number> oneComponentGraph = TestGraphs.getOneComponentGraph();
	Map<String, Graph<String, Number>> graphMap = new HashMap<String, Graph<String, Number>>();
	JComboBox layoutBox, graphBox;
	
	public GraphDemo() {
		super(new BorderLayout());
		final VisualizationViewer<String, Number> vv = new VisualizationViewer<String, Number>();
		Graph<String, Number> graph = TestGraphs.getDemoGraph();
		vv.getRenderContext().setVertexStringer(new ToStringLabeller<String>());
		Layout<String, Number> layout = new SpringLayout<String, Number>(graph);
		vv.setGraphLayout(layout);
		
		add(vv);
	}
	
	public static void main(String argv[]) {
		final GraphDemo demo = new GraphDemo();
		JFrame f = new JFrame();
		f.add(demo);
		f.setSize(600,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}