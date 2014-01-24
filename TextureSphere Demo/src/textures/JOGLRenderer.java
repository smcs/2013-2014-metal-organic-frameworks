
package textures;

import simplerjogl.*;
import simplerjogl.textures.*;

public class JOGLRenderer extends Renderer
{
	private TextureSphere sun;
	private Material glow, ray;
	private Light l;
	private double rotation;

	public void init ()
	{
		sun = new TextureSphere (gl, "images/SunTexture_2048.PNG", 1, 100);
		Material.enableBlending (gl);
		glow = new Material (gl);
		glow.setEmission (1, .9, 0, 1);
		ray = new Material (gl);
		ray.setDiffuse (1, 1, 0, .1);
		ray.setEmission (1, 1, 0, .1);
		l = new Light (gl);
		l.setPosition (0, 0, 0, 1);
		l.setDiffuse (1, (float) 0.9, 0, 1);
		l.enable ();
		gl.glClearColor (1, 1, 0, 1);
	}

	public void display ()
	{
		glu.gluLookAt (0, 0, 5, 0, 0, 0, 0, 1, 0);
		gl.glRotated (rotation, 0, 1, 0);
		glow.use ();
		sun.draw ();
		ray.use ();
		gl.glRotated (90, 1, 0, 0);
		for (double radius = 1; radius < 2; radius += .1)
		{
			glut.glutSolidSphere (radius, 10, 10);
		}
		rotation++ ;
	}
}