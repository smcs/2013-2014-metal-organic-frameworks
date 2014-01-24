
package simplerjogl.sprites;

import javax.media.opengl.*;

/* TODO document Particle */
public class Particle extends Bouncer
{
	public Particle (GL2 gl, double left, double top, double front, double right, double bottom, double back)
	{
		super (gl, left, top, front, right, bottom, back);
		reset ();
	}

	protected void reset ()
	{
		x = Math.random () * (right - left) + left;
		y = Math.random () * (top - bottom) + bottom;
		z = Math.random () * (back - front) + back;
		dx = Math.random () * .25;
		dy = Math.random () * .5 - dx;
		dz = Math.random () * .75 - dx - dy;
	}

	public void move ()
	{
		if ( (x < left) || (x > right) || (y < bottom) || (y > top) || (z < back) || (z > front))
		{
			reset ();
		}
		x += dx;
		y += dy;
		z += dz;
	}

	public void spriteDraw (boolean wireframe)
	{
		if (wireframe) {
			gl.glBegin(GL.GL_LINE_LOOP);
		} else {
			gl.glBegin (GL.GL_TRIANGLES);
		}
		
		gl.glVertex3d (-.05, 0, 0);
		gl.glVertex3d (0, -.1, 0);
		gl.glVertex3d (.05, 0, 0);

		gl.glEnd ();
	}
	
	public void spriteDraw() {
		spriteDraw(false);
	}
}
