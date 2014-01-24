
package particle_effect;

import javax.media.opengl.*;

import simplerjogl.*;
import simplerjogl.sprites.*;

public class RainDrop extends Particle
{
	private Material water;
	private double rotation;

	public RainDrop (GL2 gl, double left, double top, double front, double right, double bottom, double back)
	{
		super (gl, left, top, front, right, bottom, back);
		water = new Material (gl);
		water.setDiffuse (.8, .8, 1, .5);
		water.setSpecular (1, 1, 1, .5);
		water.setShininess (128);
		Material.enableBlending (gl);
		rotation = Math.random () * 360;
	}

	protected void reset ()
	{
		super.reset ();
		y = 10;
		dy = -.1;
	}

	public void move ()
	{
		if (y < bottom)
		{
			reset ();
		}
		y += dy;
	}

	public void spriteDraw ()
	{
		water.use ();
		gl.glRotated (rotation, 0, 1, 0);
		glut.glutSolidCube ((float) .1);
		rotation++ ;
	}
}
