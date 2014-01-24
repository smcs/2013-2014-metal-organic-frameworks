
package simplerjogl;

import javax.media.opengl.*;
import com.jogamp.opengl.util.texture.*;
import java.io.*;

/**
 * A texture loading class -- copied wholesale from Eric Itomura's post at
 * {@link <a href=
 * "http://forum.java.sun.com/thread.jspa?threadID=5197213&tstart=210">Java
 * Game Development< /a>} It should be capable of loading JPEG, GIF and PNG
 * files as textures.
 * 
 * @author Eric Itomura
 * @version 2003-11-02
 */
public class TextureLoader
{
	/**
	 * Texture loader utilizes JOGL's provided utilities to produce a
	 * texture.
	 * 
	 * @param gl The OpenGL context into which to load the texture
	 * @param fileName
	 *            relative filename from execution point
	 * @return a texture bound to the OpenGL context
	 */
	public static Texture load (GL2 gl, String fileName)
	{
		Texture texture = null;
		try
		{
			texture = TextureIO.newTexture (new File (fileName), false);
			texture.setTexParameteri (gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
			texture.setTexParameteri (gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		}
		catch (Exception e)
		{
			System.out.println (e.getMessage ());
			System.out.println ("Error loading texture " + fileName);
		}
		return texture;
	}
}