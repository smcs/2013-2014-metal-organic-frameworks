
package shell;

import simplerjogl.*;
import simplerjogl.shell.ShellEvent;

public class JOGLRenderer extends Renderer
{
	private final static int TEAPOT = 0;
	private final static int TORUS = 1;
	private final static int SPHERE = 2;
	private final static int CUBE = 3;
	private final static int CONE = 4;
	private final static String[] commands = { "teapot", "torus", "sphere", "cube", "cone" };
	private int shape;
	private Light l;
	private Material m;
	private float xRot, yRot, zRot;

	public void init ()
	{
		shape = TEAPOT;
		l = new Light (gl);
		l.enable ();
		m = new Material (gl);
		m.setDiffuse (.2, .2, 1, 1);
		m.setSpecular (.6, .6, .8, 1);
		m.use ();
		xRot = 0;
		yRot = 0;
		zRot = 0;
		shell.readln ();
	}

	public void display ()
	{
		gl.glLoadIdentity ();
		glu.gluLookAt (0, 0, 5, 0, 0, 0, 0, 1, 0);
		gl.glRotatef (xRot, 1, 0, 0);
		gl.glRotatef (yRot, 0, 1, 0);
		gl.glRotatef (zRot, 0, 0, 1);
		switch (shape)
		{
			case TEAPOT:
				glut.glutSolidTeapot (1);
				break;
			case TORUS:
				glut.glutSolidTorus (0.25f, 1, 100, 100);
				break;
			case SPHERE:
				glut.glutSolidSphere (1, 100, 100);
				break;
			case CUBE:
				glut.glutSolidCube (2);
				break;
			case CONE:
				gl.glTranslatef (0, -1, 0);
				gl.glRotatef (90, -1, 0, 0);
				glut.glutSolidCone (1, 2, 100, 100);
				break;
		}
		xRot += 0.1f;
		yRot += 0.2f;
		zRot += 0.3f;
	}

	public void commandComplete (ShellEvent e)
	{
		int oldShape = shape;
		for (int i = 0; i < commands.length; i++ )
		{
			if (e.getCommand ().equals (commands[i]))
			{
				shape = i;
			}
		}
		if (shape != oldShape)
		{
			shell.println ("Changing to " + commands[shape] + "...");
		}
		else
		{
			shell.println ("Command \"" + e.toString () + "\" ignored.");
		}
		shell.readln ();
	}
}