
package simplerjogl;

import javax.media.opengl.*;

/**
 * A wrapper for OpenGL lights in SimplerJOGL applications
 * 
 * @author <a href="seth@battis.net">Seth Battis</a>
 * @version 2009-01-07
 */
public class Light
{
	/**
	 * The OpenGL drawing context in which this light exists
	 */
	protected GL2 gl;
	/**
	 * Next available ID for a light in this SimplerJOGL application
	 */
	private static int nextID = 1;
	/**
	 * The ID for the light is used to define it in the
	 * {@link simplerjogl.Light#gl "GL canvas"}, in the range (0-7). All
	 * lights after the 7th light will be given ID=7
	 * 
	 * @see <a * href=
	 *      "http://www.falloutsoftware.com/tutorials/gl/gl8.htm#defining_a_light_source"
	 *      >Defining * an OpenGL Light< /a>
	 * @see <a * href=
	 *      "http://www.opengl.org/resources/faq/technical/lights.htm#ligh0070"
	 *      >How * can I create more lights than GL_MAX_LIGHTS?< /a>
	 */
	protected int id;
	protected Color ambient, diffuse, specular;
	protected Vertex position, spotDirection;
	protected double spotExponent, spotCutoff;

	/**
	 * Construct a new generic light, but do not turn it on. Defaults to a
	 * white directional light at (0, 0, 1) with dark gray ambient light
	 * (OpenGL default)
	 */
	public Light (GL2 gl)
	{
		this.gl = gl;
		id = Light.getNextID ();
		setAmbient (0.2f, 0.2f, 0.2f, 1f);
		setDiffuse (1, 1, 1, 1);
		setSpecular (1, 1, 1, 1);
		setPosition (0, 0, 1, 0);
		setSpotDirection (0, 0, -1);
		setSpotExponent (0);
		setSpotCutoff (180);
	}

	/**
	 * Copy constructor, create a duplicate of another Light object with a
	 * new unique ID in this object
	 * 
	 * @see #id
	 */
	public Light (Light other)
	{
		this.gl = other.gl;
		id = Light.getNextID ();
		setAmbient (other.ambient);
		setDiffuse (other.diffuse);
		setSpecular (other.specular);
		setPosition (other.position);
		setSpotDirection (other.spotDirection);
		setSpotExponent (other.spotExponent);
		setSpotCutoff (other.spotCutoff);
	}

	/**
	 * @return next available light ID in this SimplerJOGL app (nota bene:
	 *         there can be no more than 8 lights in an OpenGL context, a
	 *         9th or greater light definition will override the 8th light
	 * @see #id
	 */
	protected static int getNextID ()
	{
		nextID++ ;
		return GL2.GL_LIGHT0 + Math.min (7, nextID);
	}

	/**
	 * @return true if all light components EXCEPT ID are identical, false
	 *         otherwise
	 */
	public boolean equals (Light other)
	{
		if (other == null)
		{
			return false;
		}
		return this.ambient.equals (other.ambient) && this.diffuse.equals (other.diffuse) && this.specular.equals (other.specular) && this.position.equals (other.position) && this.spotDirection.equals (other.spotDirection)
			&& (this.spotExponent == other.spotExponent) && (this.spotCutoff == other.spotCutoff);
	}

	/**
	 * Enable this light (and lighting in general, if necessary)
	 */
	public void enable ()
	{
		/* turn on lighting if necessary */
		if (!gl.glIsEnabled (GL2.GL_LIGHTING))
		{
			gl.glEnable (GL2.GL_LIGHTING);
		}
		/* enable this light in particular */
		gl.glEnable (id);
		/* apply this light's settings */
		gl.glLightfv (id, GL2.GL_AMBIENT, ambient.getRGBAf (), 0);
		gl.glLightfv (id, GL2.GL_DIFFUSE, diffuse.getRGBAf (), 0);
		gl.glLightfv (id, GL2.GL_SPECULAR, specular.getRGBAf (), 0);
		gl.glLightfv (id, GL2.GL_POSITION, position.getXYZWf (), 0);
		gl.glLightfv (id, GL2.GL_SPOT_DIRECTION, spotDirection.getXYZf (), 0);
		gl.glLightf (id, GL2.GL_SPOT_EXPONENT, (float) spotExponent);
		gl.glLightf (id, GL2.GL_SPOT_CUTOFF, (float) spotCutoff);
	}

	/**
	 * Disable this light
	 */
	public void disable ()
	{
		/* disable this light in particular */
		gl.glDisable (id);
	}

	/**
	 * @return previous ambient lighting color
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      * compositing< /a>
	 */
	public Color setAmbient (float red, float green, float blue, float alpha)
	{
		return setAmbient (new Color (red, green, blue, alpha));
	}

	/**
	 * @return previous ambient lighting color
	 */
	public Color setAmbient (Color c)
	{
		Color oldAmb = ambient;
		ambient = new Color (c);
		gl.glLightfv (id, GL2.GL_AMBIENT, ambient.getRGBAf (), 0);
		return oldAmb;
	}

	/**
	 * @return previous diffuse light color
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      * compositing< /a>
	 */
	public Color setDiffuse (float red, float green, float blue, float alpha)
	{
		return setDiffuse (new Color (red, green, blue, alpha));
	}

	/**
	 * @return previous diffuse light color
	 */
	public Color setDiffuse (Color c)
	{
		Color oldDiff = diffuse;
		diffuse = new Color (c);
		gl.glLightfv (id, GL2.GL_DIFFUSE, diffuse.getRGBAf (), 0);
		return oldDiff;
	}

	/**
	 * @return previous specular light color
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      * compositing< /a>
	 */
	public Color setSpecular (float red, float green, float blue, float alpha)
	{
		return setSpecular (new Color (red, green, blue, alpha));
	}

	/**
	 * @return previous specular light color
	 */
	public Color setSpecular (Color c)
	{
		Color oldSpec = specular;
		specular = new Color (c);
		gl.glLightfv (id, GL2.GL_SPECULAR, specular.getRGBAf (), 0);
		return oldSpec;
	}

	/**
	 * @return previous position Vertex
	 * @see <a href="http://glprogramming.com/red/appendixf.html#name1"> *
	 *      Homogenous Coordinates< /a>
	 */
	public Vertex setPosition (float x, float y, float z, float w)
	{
		return setPosition (new Vertex (x, y, z, w));
	}

	/**
	 * @return previous position Vertex
	 */
	public Vertex setPosition (Vertex v)
	{
		Vertex oldPos = position;
		position = new Vertex (v);
		gl.glLightfv (id, GL2.GL_POSITION, position.getXYZWf (), 0);
		return oldPos;
	}

	/**
	 * @return the previous spot direction Vertex
	 */
	public Vertex setSpotDirection (float x, float y, float z)
	{
		return setSpotDirection (new Vertex (x, y, z));
	}

	/**
	 * @return the previous spot direction Vertex
	 */
	public Vertex setSpotDirection (Vertex v)
	{
		Vertex oldSpot = spotDirection;
		spotDirection = new Vertex (v);
		gl.glLightfv (id, GL2.GL_SPOT_DIRECTION, spotDirection.getXYZf (), 0);
		return oldSpot;
	}

	/**
	 * @return previous spot exponent value
	 */
	public double setSpotExponent (double spotExponent)
	{
		double oldSpot = this.spotExponent;
		this.spotExponent = spotExponent;
		gl.glLightf (id, GL2.GL_SPOT_EXPONENT, (float) this.spotExponent);
		return oldSpot;
	}

	/**
	 * @return previous spot cutoff value
	 */
	public double setSpotCutoff (double spotCutoff)
	{
		double oldSpot = this.spotCutoff;
		this.spotCutoff = spotCutoff;
		gl.glLightf (id, GL2.GL_SPOT_CUTOFF, (float) this.spotCutoff);
		return oldSpot;
	}
}