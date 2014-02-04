package objloader;

import simplerjogl.Frame;



public class JOGLApp
{
	public static void main (String[] args)
	{
		JOGLRenderer renderer = new JOGLRenderer ();
		StructureOrganizer S = new StructureOrganizer();
		
		Frame frame = Frame.createFrame ("SimplerJOGL App", false);
		frame.addGLEventListener (renderer);
		frame.start ();
	}
}