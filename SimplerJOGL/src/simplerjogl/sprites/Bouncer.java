
package simplerjogl.sprites;

import javax.media.opengl.*;

/**
 * A Sprite that automatically bounces off of the walls of an invisible box
 * (feel free to draw a box at those boundaries...)
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2011-02-08
 */
public abstract class Bouncer extends Sprite
{
	/**
	 * The current velocity of the Bouncer in x-, y- and z-components
	 */
	protected double dx, dy, dz;

	/**
	 * The boundaries within which the Bouncer, er... bounces
	 */
	protected double left, top, front, right, bottom, back;

	/**
	 * Contructor for a Bouncer with a random velocity -- but user-defined
	 * boundaries, starting at a random point within the boundaries
	 * 
	 * @param gl
	 *            The OpenGL context in which the Bouncer will draw
	 * @param left
	 *            The left boundary along the x-axis
	 * @param top
	 *            The top boundary along the y-axis
	 * @param front
	 *            The front boundary along the z-axis
	 * @param right
	 *            The right boundary along the x-axis
	 * @param bottom
	 *            The bottom boundary along the y-axis
	 * @param back
	 *            The back boundary along the z-axis
	 */
	public Bouncer (GL2 gl, double left, double top, double front, double right, double bottom, double back)
	{
		this (gl, 0, 0, 0, Math.random (), Math.random (), Math.random (), left, top, front, right, bottom, back);
	}

	/**
	 * Constructor for a bouncer with user-defined boundaries, position and
	 * velocity
	 * 
	 * @param gl
	 *            The drawing context in which the Bouncer is drawn
	 * @param x
	 *            The starting x-coordinate of the Bouncer
	 * @param y
	 *            The starting y-coordinate of the Bouncer
	 * @param z
	 *            The starting z-coordinate of the Bouncer
	 * @param dx
	 *            The starting x-velocity of the Bouncer
	 * @param dy
	 *            The starting y-velocity of the bouncer
	 * @param dz
	 *            The starting z-velocity of the bouncer
	 * @param left
	 *            The left boundary along the x-axis
	 * @param top
	 *            The top boundary along the y-axis
	 * @param front
	 *            The front boundary along the z-axis
	 * @param right
	 *            The right boundary along the x-axis
	 * @param bottom
	 *            The bottom boundary along the y-axis
	 * @param back
	 *            The back boundary along the z-axis
	 */
	public Bouncer (GL2 gl, double x, double y, double z, double dx, double dy, double dz, double left, double top, double front, double right, double bottom, double back)
	{
		super (gl);
		this.x = Math.max (left, Math.min (x, right));
		this.y = Math.max (bottom, Math.min (y, top));
		this.z = Math.max (back, Math.min (z, front));
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
		this.left = left;
		this.top = top;
		this.front = front;
		this.right = right;
		this.bottom = bottom;
		this.back = back;
	}

	/**
	 * Draws the bouncer at its current position. Note that the bouncer
	 * updates its location on each OpenGL refresh. One possible extension
	 * would control the speed of the Bouncer by using a timer to sequence
	 * the calls to move()
	 * @param wireframe Whether or not to draw the Bouncer as a wireframe
	 */
	public void draw (boolean wireframe)
	{
		move ();
		gl.glPushMatrix ();
		{
			gl.glTranslated (x, y, z);
			spriteDraw (wireframe);
		}
		gl.glPopMatrix ();
	}
	
	/**
	 * Draws the bouncer at its current position. Note that the bouncer
	 * updates its location on each OpenGL refresh. One possible extension
	 * would control the speed of the Bouncer by using a timer to sequence
	 * the calls to move()
	 */
	public void draw() {
		draw(false);
	}

	/**
	 * Calculates the next position of the Bouncer in space
	 */
	public void move ()
	{
		if ( (x <= left) || (x >= right))
		{
			dx *= -1;
		}
		if ( (y <= bottom) || (y >= top))
		{
			dy *= -1;
		}
		if ( (z <= back) || (z >= front))
		{
			dz *= -1;
		}
		x += dx;
		y += dy;
		z += dz;
	}
}