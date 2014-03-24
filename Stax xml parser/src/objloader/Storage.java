package objloader;

import edu.uci.ics.jung.graph.util.EdgeType;

public class Storage {
	//a place to put temporarily removed code
	
	
	/* define a few handy JUNGatoms 
	JUNGatom carbon = new JUNGatom("Carbon", "C", 12.0107);
	JUNGatom oxygen1 = new JUNGatom("Oxygen", "O", 15.9994);
	JUNGatom oxygen2 = new JUNGatom(oxygen1);
	JUNGatom oxygen3 = new JUNGatom(oxygen2); 
	JUNGatom carbon2 = new JUNGatom(carbon); 

	 add the JUNGatoms (and dynamically created JUNGbonds) to the graph 

	weights for bonds, always 2-way 
	carJUNGbondioxide.addEdge(new JUNGbond(carbon, oxygen1), carbon, oxygen1, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(oxygen1, carbon), oxygen1, carbon, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(carbon, oxygen2), carbon, oxygen2, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(oxygen2, carbon), oxygen2, carbon, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(carbon, oxygen3), carbon, oxygen3, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(oxygen3, carbon), oxygen3, carbon, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(oxygen3, carbon2), oxygen3, carbon2, EdgeType.DIRECTED);
	carJUNGbondioxide.addEdge(new JUNGbond(carbon2, oxygen3), carbon2, oxygen3, EdgeType.DIRECTED);
	
	/*
}

