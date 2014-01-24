
package simplerjogl.sprites;

import javax.media.opengl.*;

/**
 * line of sight must be parallel to Z-axis; up-vector of eye must be
 * parallel to Y-axis; eye must be looking at origin
 */
/* TODO document MouseTracker */
public abstract class MouseTracker extends Sprite
{
	private double screenW, screenH, modelW, modelH;

	public MouseTracker (GL2 gl, double distance)
	{
		/* default to the origin */
		this (gl, distance, 0, 0, 0);
	}

	public MouseTracker (GL2 gl, double distance, double x, double y, double z)
	{
		/* default SimplerJOGL angle of view */
		this (gl, distance, 45, x, y, z);
	}

	public MouseTracker (GL2 gl, double distance, double angleOfView)
	{
		/* default to the origin */
		this (gl, distance, angleOfView, 0, 0, 0);
	}

	public MouseTracker (GL2 gl, double distance, double angleOfView, double x, double y, double z)
	{
		/* default SimplerJOGL screen size */
		this (gl, distance, angleOfView, x, y, z, 800, 577);
	}

	public MouseTracker (GL2 gl, double distance, double angleOfView, double x, double y, double z, int screenW, int screenH)
	{
		super (gl, x, y, z);
		this.screenW = screenW;
		this.screenH = screenH;
		/*
		 * the right triangle with the distance from the eye to the object
		 * as the base and the distance to the top of the screen as the
		 * height
		 */
		double hypotenuse = distance / Math.cos (Math.toRadians (angleOfView / 2.0));
		double height = hypotenuse * Math.sin (Math.toRadians (angleOfView / 2));
		/*
		 * double the height (for the similar triangle below the horizon)
		 * to get the model view height
		 */
		modelH = 2 * height;
		/*
		 * use similar ratios to compute the model view width from the
		 * screen dimensions
		 */
		modelW = (modelH * this.screenW) / this.screenH;
	}

	public MouseTracker (GL2 gl)
	{
		super (gl);
	}

	public void draw (boolean wireframe)
	{
		gl.glPushMatrix ();
		{
			gl.glTranslated (x, y, z);
			spriteDraw (wireframe);
		}
		gl.glPopMatrix ();
	}
	
	public void draw() {
		draw(false);
	}

	public void move ()
	{}

	public void move (int mouseX, int mouseY)
	{
		/*
		 * convert from frame origin in top-left corner of window with
		 * positive Y-axis pointing downward to screen coordinate system
		 * with origin at the center of the screen
		 */
		double screenX = mouseX - (screenW / 2);
		double screenY = (screenH / 2) - mouseY;
		/*
		 * use similar ratios to compute model coordinates from the screen
		 * coordinates
		 */
		x = (screenX * modelW) / screenW;
		y = (screenY * modelH) / screenH;
	}
}
