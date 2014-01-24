
package simplerjogl.textures;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;

import simplerjogl.Model;
import simplerjogl.TextureLoader;

/* TODO document TextureModel */
public class TextureModel extends Model
{
	protected Texture texture;
	protected GL2 gl;
	
	public TextureModel (GL2 gl, String fileName)
	{
		super (gl);
		this.gl = gl;
		texture = TextureLoader.load (gl, fileName);
		if (texture != null && !gl.glIsEnabled (GL.GL_TEXTURE_2D))
		{
			gl.glEnable (GL.GL_TEXTURE_2D);
		}
	}

	protected boolean textureLoaded ()
	{
		return (texture != null);
	}

	public void draw ()
	{
		if (texture != null)
		{
			texture.bind (gl);
		}
	}
}
