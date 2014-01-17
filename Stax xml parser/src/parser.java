import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class parser {
	public static void main(String[] args) throws XMLStreamException, Exception {
		List<Bond> bondList = new ArrayList<Bond>();
		List<CDXML_Node> nodeList = new ArrayList<CDXML_Node>();
		CDXML_Node currNode = null;
		Bond currBond = null;
		String tagContent = null; 
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileInputStream fis = new FileInputStream("xml/Terephthalic acid.cdxml");
		XMLStreamReader reader = factory.createXMLStreamReader(fis); 

		while (reader.hasNext()) {
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if ("n".equals(reader.getLocalName())) {
					currNode = new CDXML_Node();
					currNode.id = reader.getAttributeValue(null, "id");
					currNode.Element=reader.getAttributeValue(null, "Element"); 
				}

				if ("b".equals(reader.getLocalName())) {
					currBond = new Bond();
					currBond.id = reader.getAttributeValue(null, "id");
					currBond.Order=reader.getAttributeValue(null, "Order");
				}
				break;

			case XMLStreamConstants.END_ELEMENT:
			switch(reader.getLocalName()){
			case "n":
				nodeList.add(currNode);
				break;
			case "b":
				bondList.add(currBond);
				break;
			case "Order":
				currBond.Order = tagContent;
				break;
			case "Element":
				currNode.Element = tagContent;
				break; 
			}
			break; 
				
			case XMLStreamConstants.START_DOCUMENT:
				bondList = new ArrayList<Bond>();
				nodeList = new ArrayList<CDXML_Node>();
				break;
			}

		}

		for (Bond bond : bondList){
			System.out.println(bond);
		}
		
		for(CDXML_Node node: nodeList){
			System.out.println(node);
		}
	
	}

	static class Bond {
		String id;
		String Order;
		
		public String toString(){
			return id + " "+ Order; 
		}
	}

	static class CDXML_Node {
		String id;
		String p;
		String Z;
		String Element; 
		
		public String toString(){
			return id + " "+ Element; 
		}
	}

}
