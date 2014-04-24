package objloader;

import java.awt.Frame;

import javax.swing.JFrame;





public class JOGLApp
{
	 
	public static void main (String[] args) 
	{
		
		//parser p = new parser();
		//JOGLRenderer renderer = new JOGLRenderer ();
		//StructureOrganizer S = new StructureOrganizer(p);
		GraphDemo g = new GraphDemo(); 
		//JUNGExample j = new JUNGExample(); 
		
		JFrame frame = new JFrame();
		frame.add(g);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
		
		
		
		//frame.addGLEventListener (renderer);
		//frame.start ();
		
		
	}
}