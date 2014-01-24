
package crosshairs;

import javax.media.opengl.*;

import simplerjogl.sprites.*;

public class Crosshairs extends MouseTracker
{
	public Crosshairs (GL2 gl, double eyeDepth, double crosshairDepth)
	{
		super (gl, eyeDepth - crosshairDepth, 0, 0, crosshairDepth);
	}

	public void spriteDraw ()
	{
		red.use ();
		glut.glutSolidTorus (0.05, 1, 10, 100);
	}
}
