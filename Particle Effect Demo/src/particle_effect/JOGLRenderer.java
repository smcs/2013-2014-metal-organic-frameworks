
package particle_effect;

import simplerjogl.*;

public class JOGLRenderer extends Renderer
{
	private Rain shower;
	private Light l;
	private int rot = 0;

	public void init ()
	{
		shower = new Rain (gl);
		l = new Light (gl);
		l.enable ();
	}

	public void display ()
	{
		glu.gluLookAt (0, 0, 10, 0, 0, 0, 0, 1, 0);
		shower.draw ();
	}
}