
package axes;

import simplerjogl.*;

/**
 * Generic SimplerJOGL rendering canvas
 * 
 * @author Seth Battis
 * @version 2009-01-07
 */
public class JOGLRenderer extends Renderer
{
	private Light l; // a simple light
	private Material m; // a simple material
	private double r; // the current amount of rotation (in degrees)

	/**
	 * Called prior to the display of the canvas
	 */
	public void init ()
	{
		l = new Light (gl);
		/*
		 * the light is coming from the direction of
		 * "over our left shoulder"
		 */
		l.setPosition (-2, 5, 5, 0);
		/* the "color" of our light is purple */
		l.setDiffuse (1, 1, 1, 1);
		/* the color of the light in specular highlights is white */
		l.setSpecular (1, 1, 1, 1);
		/* the light in the shadows is black -- so none */
		l.setAmbient (0, 0, 0, 1);
		/* turn on the light */
		l.enable ();
		m = new Material (gl);
		/* the "color" of the material is red */
		m.setDiffuse (1, 0, 0, 1);
		/* the material reflects green on the specular highlights */
		m.setSpecular (0, 1, 0, 1);
		/* the material is only a little shiny */
		m.setShininess (10);
		/* start using this material */
		m.use ();
		/* start with no rotation */
		r = 0;
		enableAxes ();
		setAxisLength (2.5);
	}

	/**
	 * Essentially an event handler: called each time a new frame needs to
	 * be drawn
	 */
	public void display ()
	{
		/* set the location and focal point of our camera */
		glu.gluLookAt (5, 5, 5, 0, 0, 0, -1, 1, -1);
		gl.glPushMatrix ();
		{
			/* rotate our teapot some (changing) number of degrees */
			gl.glRotated (r, -0.25, 1, 0.5);
			glut.glutSolidTeapot (1);
			/* increment the amount of rotation for the next frame */
			r += 0.5;
		}
		gl.glPopMatrix ();
	}
}