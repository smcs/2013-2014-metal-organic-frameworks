
package crosshairs;

import java.awt.event.MouseEvent;

import simplerjogl.*;

public class JOGLRenderer extends Renderer
{
	private Crosshairs sight;
	private Light l;

	public void init ()
	{
		sight = new Crosshairs (gl, 20, 5);
		l = new Light (gl);
		l.enable ();
	}

	public void display ()
	{
		glu.gluLookAt (0, 0, 20, 0, 0, 0, 0, 1, 0);
		sight.draw ();
	}

	public void mouseMoved (MouseEvent e)
	{
		if (sight != null)
		{
			sight.move (e.getX (), e.getY ());
		}
	}
}