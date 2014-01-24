
package simplerjogl.sprites;

import javax.media.opengl.*;

import simplerjogl.*;

/**
 * A basic Sprite object, which extends the SimplerJOGL.Model object to
 * include an object center at (x, y, z). Shifting the center will shift the
 * whole sprite relative to that point.
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2011-01-26
 */
public abstract class Sprite extends Model
{
	protected double x, y, z;

	/**
	 * The default constructor
	 * @param gl the OpenGL drawing context in which this sprite exists
	 */
	public Sprite (GL gl)
	{
		this (gl, 0, 0, 0);
	}

	/**
	 * Construct a Sprite at specific (x, y, z) coordinates
	 * @param gl the OpenGL drawing context in which this sprite exists
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param z z-coordinate
	 */
	public Sprite (GL gl, double x, double y, double z)
	{
		super (gl);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * This method overrides the basic Model.draw() method, drawing the sprite
	 * at (or, at least, relative to) its (x, y, z) center point.
	 */
	public void draw (boolean wireframe)
	{
		gl.glPushMatrix();
		{
			gl.glTranslated (x, y, z);
			spriteDraw (wireframe);
		}
		gl.glPopMatrix();
	}
	
	public void draw() {
		draw(false);
	}

	/**
	 * @return x-coordinate
	 */
	public double getX ()
	{
		return x;
	}

	/**
	 * @return y-coordinate
	 */
	public double getY ()
	{
		return y;
	}

	/**
	 * @return z-coordinate
	 */
	public double getZ ()
	{
		return z;
	}

	/**
	 * @param newX new x-coordinate
	 * @return old x-coordinate
	 */
	public double setX (double newX)
	{
		double oldX = x;
		x = newX;
		return oldX;
	}

	/**
	 * @param newY new y-coordinate
	 * @return old y-coordinate
	 */
	public double setY (double newY)
	{
		double oldY = y;
		y = newY;
		return oldY;
	}

	/**
	 * @param newZ new z-coordinate
	 * @return old z-coordinate
	 */
	public double setZ (double newZ)
	{
		double oldZ = z;
		z = newZ;
		return oldZ;
	}

	/**
	 * This abstract method (which must be implemented in any class that
	 * extends the Sprite class) is called to animate the sprite. Depending on
	 * how the sprite is animated, a JOGLRenderer may call this method when
	 * the display is refreshed (easy, but animation speeds will vary based on
	 * display refresh rates and processor speeds) or based on a timer (more
	 * elegant -- like a lightsaber -- but needing to be implemented either in
	 * the Sprite itself (very elegant) or in the JOGLRenderer.
	 */
	public abstract void move ();

	/**
	 * This abstract method (which must be implemented in any class that
	 * extends the Sprite class) is called to draw the Sprite centered at (or,
	 * at least, relative to) the origin. The spriteDraw() method is called by
	 * the generic draw() method. The draw() method will handle all
	 * translations relative to the center (x, y, z) coordinate of Sprite --
	 * i.e. spriteDraw() does not need to worry about drawing relative to the
	 * Sprite's center, spriteDraw() does need to draw the Sprite relative to
	 * the origin.
	 */
	public abstract void spriteDraw ();
	
	public void spriteWireframe() {
		spriteDraw(true);
	}
	
	/**
	 * Any object that extends Sprite can, alternatively override this spriteDraw()
	 * method to make the OpenGL calls necessary to draw the sprite. This
	 * of the method should use the wireframe parameter to choose between
	 * solid or wireframe OpenGL calls. If an object overrides this method,
	 * the spriteDraw() method contain single call to spriteDraw(false).
	 * @param wireframe Whether or not to draw the sprite as a wireframe or solid
	 */
	public void spriteDraw(boolean wireframe) {
		spriteDraw();
	}
}
