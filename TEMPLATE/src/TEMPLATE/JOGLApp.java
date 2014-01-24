
package TEMPLATE;

import simplerjogl.*;

public class JOGLApp
{
	public static void main (String[] args)
	{
		Renderer renderer = new JOGLRenderer ();
		Frame frame = Frame.createFrame ("SimplerJOGL App", false);
		frame.addGLEventListener (renderer);
		frame.start ();
	}
}