
package bouncing_ball;

import simplerjogl.*;

public class JOGLRenderer extends Renderer
{
	private Ball b;
	private Material glass;
	private Light l;

	public void init ()
	{
		b = new Ball (gl);
		glass = new Material (gl);
		glass.setDiffuse (1, 1, 1, .25);
		glass.setSpecular (.8, .8, 1, .4);
		glass.setShininess (100);
		Material.enableBlending (gl);
		l = new Light (gl);
		l.setPosition (100, 100, 100, 1);
		l.enable ();
	}

	public void display ()
	{
		glu.gluLookAt (0, 0, 40, 0, 0, 0, 0, 1, 0);
		b.draw ();
		glass.use ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (11, 0, 0);
			gl.glScaled (.5, 22, 22);
			glut.glutSolidCube (1);
		}
		gl.glPopMatrix ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (-11, 0, 0);
			gl.glScaled (.5, 22, 22);
			glut.glutSolidCube (1);
		}
		gl.glPopMatrix ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (0, 11, 0);
			gl.glScaled (22, .5, 22);
			glut.glutSolidCube (1);
		}
		gl.glPopMatrix ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (0, -11, 0);
			gl.glScaled (22, .5, 22);
			glut.glutSolidCube (1);
		}
		gl.glPopMatrix ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (0, 0, 11);
			gl.glScaled (22, 22, .5);
			glut.glutSolidCube (1);
		}
		gl.glPopMatrix ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (0, 0, -11);
			gl.glScaled (22, 22, .5);
			glut.glutSolidCube (1);
		}
		gl.glPopMatrix ();
	}
}