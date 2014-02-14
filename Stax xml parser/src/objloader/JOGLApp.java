package objloader;

import javax.xml.stream.XMLStreamException;

import simplerjogl.Frame;



public class JOGLApp
{
	 
	public static void main (String[] args) throws XMLStreamException, Exception
	{
		
		parser p = new parser();
		JOGLRenderer renderer = new JOGLRenderer ();
		StructureOrganizer S = new StructureOrganizer(p);
		
		Frame frame = Frame.createFrame ("SimplerJOGL App", false);
		frame.addGLEventListener (renderer);
		frame.start ();
	}
}