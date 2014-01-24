
package axes;

import simplerjogl.*;

/**
 * Main class for a generic SimplerJOGL application
 * 
 * @author Seth Battis
 * @version 2009-01-07
 */
public class JOGLApp
{
	public static void main (String[] args)
	{
		/* instantiate the SimplerJOGL canvas */
		Renderer renderer = new JOGLRenderer ();
		/* instantiate a window to hold the canvas */
		Frame frame = Frame.createFrame ("SimplerJOGL Visible Axes Demo", true);
		/*
		 * bind the canvas to the window's event loop, listening for GL
		 * display events
		 */
		frame.addGLEventListener (renderer);
		/* display the window (and canvas) */
		frame.start ();
	}
}