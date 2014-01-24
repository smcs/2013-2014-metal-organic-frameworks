
package crosshairs;

import simplerjogl.*;

public class JOGLApp
{
	public static void main (String[] args)
	{
		Renderer renderer = new JOGLRenderer ();
		Frame frame = Frame.createFrame ("SimplerJOGL MouseTracker Sprite Demo", true);
		frame.addGLEventListener (renderer);
		frame.addMouseMotionListener (renderer);
		frame.start ();
	}
}