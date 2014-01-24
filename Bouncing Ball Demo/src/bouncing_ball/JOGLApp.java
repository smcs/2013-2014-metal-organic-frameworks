
package bouncing_ball;

import simplerjogl.*;

public class JOGLApp
{
	public static void main (String[] args)
	{
		Renderer renderer = new JOGLRenderer ();
		Frame frame = Frame.createFrame ("SimplerJOGL App", true);
		frame.addGLEventListener (renderer);
		frame.addMouseMotionListener (renderer);
		frame.start ();
	}
}