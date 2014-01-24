package objloader;

import simplerjogl.*;

public class JOGLApp
{
	public static void main (String[] args)
	{
		JOGLRenderer renderer = new JOGLRenderer ();
		StructureOrganizer S = new StructureOrganizer(renderer);
		
		Frame frame = Frame.createFrame ("SimplerJOGL App", false);
		frame.addGLEventListener (renderer);
		frame.start ();
	}
}