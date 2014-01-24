
package jogl2d;

import simplerjogl.simplerjogl2d.*;

public class JOGLRenderer extends Renderer2D
{
	public void display ()
	{
		glut.glutSolidSphere (30, 10, 10);
	}
}
