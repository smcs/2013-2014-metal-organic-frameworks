package objloader;

import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * This is the XML parser which extracts useful information from a cdxml file
 * generated by ChemDraw. This information will be passed onto the display
 * methods to be turned into a 3D structure.
 * 
 * @author juliegeng
 * 
 */
public class parser {
	
	private static HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
	private static HashMap<Integer, Bond> bonds= new HashMap <Integer, Bond>(); 
	private Bond currBond = new Bond(); 
	private Node currNode = new Node();
	private int Number_of_Bonds;
	private String Text = null; 
	private String tagContent = null; // TagContent is any attribute associated with
								// a bond or node
	
	public parser() throws XMLStreamException, Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		// "The class javax.xml.stream.XMLInputFactory is a root component of
		// the Java StAX API. From this class you can create both an
		// XMLStreamREader
		// and an XMLEventReader." In other words, this defines the StAX parser.

		FileInputStream fis = new FileInputStream("xml/carbon dioxide .cdxml");
		// The InputStream reads a cdxml file.
		XMLStreamReader reader = factory.createXMLStreamReader(fis);
		// This iterates through the XML file using next().

		while (reader.hasNext()) { // The XMLStreamReader is iterating through
									// the file.
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT: // Indicates an event is a
													// start element
				if ("n".equals(reader.getLocalName())) { // if "n" is detected
					//System.out.println("previous val: " + currNode.getID());
					currNode.setID(Integer.parseInt(reader.getAttributeValue(null, "id")));
					//System.out.println(Integer.parseInt(reader.getAttributeValue(null, "id")));
					
					// Access the value of "id" of this node
					// id: A unique identifier for an object, used when other
					// objects refer to it.
					
					currNode.setElement(Integer.parseInt(reader.getAttributeValue(null, "Element")));
					if (currNode.getID() == 16){
					currNode.setElement(2);
					}
					// Access the value of "Element" of this node
					// Element: The atomic number of the atom representing this
					// node.
					// If not specified, the atomic number is 6, corresponding
					// to carbon
					// (my personal favorite, FYI)! 
				}

				if ("b".equals(reader.getLocalName())) { // Analogous to the "n"
															// scenario
					currBond.setID(Integer.parseInt(reader.getAttributeValue(null, "id")));
					currBond.setB(Integer.parseInt(reader.getAttributeValue(null, "B")));
					currBond.setE(Integer.parseInt(reader.getAttributeValue(null, "E")));
					if(reader.getAttributeValue(null, "Order") != null){
					currBond.setOrder (Integer.parseInt(reader.getAttributeValue(null, "Order")));}
					// Order: The order of a bond object (single/double/triple)
				}
				break;
				
			 case XMLStreamConstants.CHARACTERS:
				 tagContent = reader.getText().trim();
				 break;

			case XMLStreamConstants.END_ELEMENT: // indicates the end of an
													// element
				switch (reader.getLocalName()) {
				case "n":
					nodes.put(currNode.getID(), currNode); 
					currNode = new Node(); 
					break;
				
				case "b":
					bonds.put(currBond.getID(), currBond);
					currBond = new Bond();
					break;
				}
				break;
	}
		}
	}
	
	public HashMap<Integer, Node> return_nodes(){
		return nodes; 
	}
	
	public HashMap<Integer, Bond> return_bonds(){
		 
		for (Integer key : bonds.keySet()) {
			System.out.println("Bonds Data is" +
					bonds.get(key).getB()+ " "+ bonds.get(key).getE());
		}
		return bonds;
		
		
	}
}