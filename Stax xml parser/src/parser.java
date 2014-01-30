
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	private static NodeMap nodes; 
	
	public static void main(String[] args) throws XMLStreamException, Exception {
	
		Node currNode = null; // Constructs a current node
		Bond currBond = null; // Constructs a current bond
		String Text = null; 
		String tagContent = null; // TagContent is any attribute associated with
									// a bond or node

		XMLInputFactory factory = XMLInputFactory.newInstance();
		// "The class javax.xml.stream.XMLInputFactory is a root component of
		// the Java StAX API. From this class you can create both an
		// XMLStreamREader
		// and an XMLEventReader." In other words, this defines the StAX parser.

		FileInputStream fis = new FileInputStream("xml/Terephthalic acid.cdxml");
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
					currNode = new Node(); // Create a node object
					currNode.id = Integer.parseInt(reader.getAttributeValue(null, "id"));
					// Access the value of "id" of this node
					// id: A unique identifier for an object, used when other
					// objects refer to it.
					currNode.Element = Integer.parseInt(reader
							.getAttributeValue(null, "Element"));
					// Access the value of "Element" of this node
					// Element: The atomic number of the atom representing this
					// node.
					// If not specified, the atomic number is 6, corresponding
					// to carbon
					// (my personal favorite, FYI)! 
				}

				if ("b".equals(reader.getLocalName())) { // Analogous to the "n"
															// scenario
					currBond = new Bond();
					currBond.B =Integer.parseInt(reader.getAttributeValue(null, "B"));
					currBond.E = Integer.parseInt(reader.getAttributeValue(null, "E"));
					currBond.Order = Integer.parseInt(reader.getAttributeValue(null, "Order"));
					// Order: The order of a bond object (single/double/triple)
				}
				break;
				
			 case XMLStreamConstants.CHARACTERS:
				 tagContent = reader.getText().trim();
				 break;

			case XMLStreamConstants.END_ELEMENT: // indicates the end of an
													// element
				switch (reader.getLocalName()) {
				case "Element":
					;// Element is a identified as
													// a tag content.
					break;
				case "n":
					nodes.put(currNode.id, currNode); 
					break;
				
				case "b":
					nodes.get(currBond.B).addBond(currBond); 
					nodes.get(currBond.E).addBond(currBond); 
					break;
					
				case "Order":
					; // Order is a identified as a
													// tag content.
					break;
					
				
				}
				break;

	}
		}
	}
}
