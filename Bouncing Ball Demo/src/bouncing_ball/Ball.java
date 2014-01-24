
package bouncing_ball;

import javax.media.opengl.*;

import simplerjogl.*;
import simplerjogl.sprites.*;

public class Ball extends Bouncer
{
	private Material redRubber;

	public Ball (GL2 gl)
	{
		super (gl, -10, 10, 10, 10, -10, -10);
		redRubber = new Material (gl);
		redRubber.setDiffuse (1, .25, .25, 1);
		redRubber.setShininess (5);
		redRubber.setAmbient (.25, 0, .25, 1);
	}

	public void spriteDraw ()
	{
		redRubber.use ();
		glut.glutSolidSphere (1, 20, 20);
	}
}
