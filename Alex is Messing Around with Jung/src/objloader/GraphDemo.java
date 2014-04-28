package objloader;

import java.awt.BorderLayout;
import java.awt.Paint;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.media.j3d.Appearance;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;

import edu.uci.ics.jung.*;
import edu.uci.ics.jung.algorithms.layout3d.Layout;
import edu.uci.ics.jung.algorithms.layout3d.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.TestGraphs;
import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
import edu.uci.ics.jung.visualization.control.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization3d.VisualizationViewer;

public class GraphDemo extends JPanel {
	Graph<String, Number> demoGraph = TestGraphs.getDemoGraph();
	Graph<String, Number> oneComponentGraph = TestGraphs.getOneComponentGraph();
	Map<String, Graph<JUNGatom, JUNGbond>> graphMap = new HashMap<String, Graph<JUNGatom, JUNGbond>>();
	JComboBox layoutBox, graphBox;
	JUNGExample j = new JUNGExample();

	public GraphDemo() throws XMLStreamException, Exception {
		super(new BorderLayout());

		VisualizationViewer<JUNGatom, JUNGbond> vv = new VisualizationViewer<JUNGatom, JUNGbond>();
		// Graph<String, Number> graph = TestGraphs.getDemoGraph();

		// vv.getRenderContext().setEdgeAppearanceTransformer(new
		// EdgetoColorTransformer());
		vv.getRenderContext().setVertexStringer(
				new ToStringLabeller<JUNGatom>());
		vv.getRenderContext().setEdgeAppearanceTransformer(
				new EdgetoColorTransformer());
		Layout<JUNGatom, JUNGbond> layout = new SpringLayout<JUNGatom, JUNGbond>(
				JUNGExample.g, new BondtoEdgeTransformer());
		vv.setGraphLayout(layout);
		// GraphMouse gm = new DefaultModalGraphMouse<JUNGatom,JUNGbond>();
		// gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		// vv.setGraphMouse(gm);
		add(vv);

	}

	public static void main(String argv[]) throws XMLStreamException, Exception {
		final GraphDemo demo = new GraphDemo();
		JFrame f = new JFrame();
		f.add(demo);
		f.setSize(600, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}