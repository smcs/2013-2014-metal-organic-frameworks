
package TEMPLATE;

import simplerjogl.*;

public class JOGLRenderer extends Renderer
{
	public void init ()
	{}

	public void display ()
	{
		glu.gluLookAt (0, 0, 5, 0, 0, 0, 0, 1, 0);
	}
}