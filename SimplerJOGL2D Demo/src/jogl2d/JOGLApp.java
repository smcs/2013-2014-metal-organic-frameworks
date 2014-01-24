
package jogl2d;

import simplerjogl.*;

public class JOGLApp
{
	public static void main (String[] args)
	{
		Renderer renderer = new JOGLRenderer ();
		Frame frame = Frame.createFrame ("SimplerJOGL 2D Demo", true);
		frame.addGLEventListener (renderer);
		frame.start ();
	}
}
