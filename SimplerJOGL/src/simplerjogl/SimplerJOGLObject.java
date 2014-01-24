
package simplerjogl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class SimplerJOGLObject
{
	protected GL2 gl;

	public SimplerJOGLObject()
	{}
	
	/**
	 * @param gl
	 *            OpenGL drawing context for this object
	 */
	public SimplerJOGLObject (GL gl)
	{
		setGL(gl);
	}

	public SimplerJOGLObject(SimplerJOGLObject other)
	{
		setGL(other.gl);
	}
	
	/**
	 * @param gl
	 *            new OpenGL drawing context
	 * @return old OpenGL drawing context
	 */
	public GL2 setGL (GL gl)
	{
		GL2 oldGl = this.gl;
		this.gl = (GL2) gl;
		return oldGl;
	}

	/**
	 * @return current OpenGL drawing context
	 */
	public GL getGL ()
	{
		return gl;
	}
}
