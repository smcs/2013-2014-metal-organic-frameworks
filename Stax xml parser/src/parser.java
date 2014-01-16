import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class parser {
	public static void main(String[] args) throws XMLStreamException {
		List<Bond> bondList = null;
		List<CDXML_Node> nodeList = null;
		CDXML_Node currNode = null;
		Bond currBond = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(ClassLoader
				.getSystemResourceAsStream("xml/Terephthalic acid.cdxml"));

		while (reader.hasNext()) {
			int event = reader.next();

			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if ("n".equals(reader.getLocalName())) {
					currNode = new CDXML_Node();
					currNode.id = reader.getAttributeValue(null, "id");
				}

				if ("n".equals(reader.getLocalName())) {
					nodeList = new ArrayList<CDXML_Node>();
				}

				if ("b".equals(reader.getLocalName())) {
					currBond = new Bond();
					currBond.id = reader.getAttributeValue(null, "id");
				}

				if ("b".equals(reader.getLocalName())) {
					bondList = new ArrayList<Bond>();
				}
				break;

			case XMLStreamConstants.END_ELEMENT:
				if ("n".equals(reader.getLocalName())) {
					nodeList.add(currNode);
				}
				
				if ("b".equals(reader.getLocalName())) {
					bondList.add(currBond);
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
	}

	static class Bond {
		String id;
		String Order;
	}

	static class CDXML_Node {
		String id;
		String p;
		String Z;
	}
}
