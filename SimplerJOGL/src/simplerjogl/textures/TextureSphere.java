
package simplerjogl.textures;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.TextureCoords;

/* TODO document TextureSphere */
public class TextureSphere extends TextureModel
{
	private double radius;
	private double divisions;

	public TextureSphere (GL2 gl, String fileName, double radius, int divisions)
	{
		super (gl, fileName);
		this.radius = Math.abs (radius);
		this.divisions = Math.abs (divisions);
	}

	public void draw ()
	{
		super.draw ();
		TextureCoords tc = texture.getImageTexCoords ();
		double textureWidth = tc.right () - tc.left ();
		double textureHeight = tc.bottom () - tc.top ();
		double theta1, theta2, theta3;
		double ex, ey, ez, px, py, pz;
		if (divisions < 4 || radius <= 0 || !textureLoaded ())
		{
			gl.glBegin (GL.GL_POINTS);
			{
				gl.glVertex3d (0, 0, 0);
			}
			gl.glEnd ();
			return;
		}
		for (int j = 0; j < divisions / 2; j++ )
		{
			theta1 = j * 2 * Math.PI / divisions - (Math.PI / 2);
			theta2 = (j + 1) * 2 * Math.PI / divisions - (Math.PI / 2);
			gl.glBegin (GL.GL_TRIANGLE_STRIP);
			{
				for (int i = 0; i <= divisions; i++ )
				{
					theta3 = i * 2 * Math.PI / divisions;
					ex = Math.cos (theta2) * Math.cos (theta3);
					ey = Math.sin (theta2);
					ez = Math.cos (theta2) * Math.sin (theta3);
					px = radius * ex;
					py = radius * ey;
					pz = radius * ez;
					gl.glNormal3d (ex, ey, ez);
					gl.glTexCoord2d ( (i / divisions) * textureWidth, (2 * (j + 1) / divisions) * textureHeight);
					gl.glVertex3d (px, py, pz);
					ex = Math.cos (theta1) * Math.cos (theta3);
					ey = Math.sin (theta1);
					ez = Math.cos (theta1) * Math.sin (theta3);
					px = radius * ex;
					py = radius * ey;
					pz = radius * ez;
					gl.glNormal3d (ex, ey, ez);
					gl.glTexCoord2d ( (i / divisions) * textureWidth, (2 * j / divisions) * textureHeight);
					gl.glVertex3d (px, py, pz);
				}
			}
			gl.glEnd ();
		}
	}
}
