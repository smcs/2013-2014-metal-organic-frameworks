
package simplerjogl;

/**
 * A class to manage RGBA Color objects for SimplerJOGL applications
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2009-01-07
 */
/* TODO statically define some colors? */
public class Color
{
	/**
	 * Red component of the color [0-1]
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	protected double red;
	/**
	 * Green component of the color [0-1]
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	protected double green;
	/**
	 * Blue component of the color [0-1]
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	protected double blue;
	/**
	 * Alpha (opacity) component of the color [0-1]
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 */
	protected double alpha;

	/**
	 * Default constructor is opaque black (0, 0, 0, 1)
	 */
	public Color ()
	{
		this (0, 0, 0, 1);
	}

	/**
	 * Copy constructor, duplicates another Color
	 */
	public Color (Color other)
	{
		setColor (other.red, other.green, other.blue, other.alpha);
	}

	/**
	 * Constructor for a specific color, out of range values will be
	 * truncated to fit
	 * 
	 * @param red
	 *            [0-1]
	 * @param green
	 *            [0-1]
	 * @param blue
	 *            [0-1]
	 * @param alpha
	 *            [0-1]
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public Color (double red, double green, double blue, double alpha)
	{
		setColor (red, green, blue, alpha);
	}

	/**
	 * Constructor for a specific opaque color, out of range values will be
	 * truncated to fit
	 * 
	 * @param red
	 *            [0-1]
	 * @param green
	 *            [0-1]
	 * @param blue
	 *            [0-1]
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public Color (double red, double green, double blue)
	{
		this (red, green, blue, 1);
	}

	/**
	 * Compares two Color objects for data equality
	 * 
	 * @return true if RGBA values identical, false otherwise
	 */
	public boolean equals (Color other)
	{
		if (other == null)
		{
			return false;
		}
		return (this.red == other.red) && (this.green == other.green) && (this.blue == other.blue) && (this.alpha == other.alpha);
	}

	/**
	 * Set to a specific color, out of range values will be truncated to
	 * fit
	 * 
	 * @param red
	 *            [0-1]
	 * @param green
	 *            [0-1]
	 * @param blue
	 *            [0-1]
	 * @param alpha
	 *            [0-1]
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public double[] setColor (double red, double green, double blue, double alpha)
	{
		double[] oldCol = { this.red, this.green, this.blue, this.alpha };
		setR (red);
		setG (green);
		setB (blue);
		setA (alpha);
		return oldCol;
	}

	/**
	 * @see #setColor(double, double, double, double)
	 * @deprecated
	 */
	public float[] setColor (float red, float green, float blue, float alpha)
	{
		double[] oldCol = setColor ((double) red, (double) green, (double) blue, (double) alpha);
		float[] oldColf = { (float) oldCol[0], (float) oldCol[1], (float) oldCol[2], (float) oldCol[3] };
		return oldColf;
	}

	/**
	 * Set to a specific opaque color
	 * 
	 * @return float vector of previous RGBA values
	 * @param red
	 *            [0-1]
	 * @param green
	 *            [0-1]
	 * @param blue
	 *            [0-1]
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public double[] setColor (double red, double green, double blue)
	{
		return this.setColor (red, green, blue, 1);
	}

	/**
	 * @deprecated
	 * @see #setColor(double, double, double)
	 */
	public float[] setColor (float red, float green, float blue)
	{
		double[] oldCol = setColor ((double) red, (double) green, (double) blue);
		float[] oldColf = { (float) oldCol[0], (float) oldCol[1], (float) oldCol[2] };
		return oldColf;
	}

	/**
	 * @return current red value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 */
	public double getR ()
	{
		return red;
	}

	/**
	 * @return current red value as a float
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 */
	public float getRf ()
	{
		return (float) getR ();
	}

	/**
	 * @return current green value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 */
	public double getG ()
	{
		return green;
	}

	/**
	 * @return current green value as a float
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 */
	public float getGf ()
	{
		return (float) getG ();
	}

	/**
	 * @return current blue value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 */
	public double getB ()
	{
		return blue;
	}

	/**
	 * @return current blue value as a float
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 */
	public float getBf ()
	{
		return (float) getB ();
	}

	/**
	 * @return current alpha value
	 */
	public double getA ()
	{
		return alpha;
	}

	/**
	 * @return current alpha value as a float
	 */
	public float getAf ()
	{
		return (float) getA ();
	}

	/**
	 * Set red component, out of range values will be truncated to fit
	 * 
	 * @param red
	 *            [0-1]
	 * @return previous red component value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public double setR (double red)
	{
		double oldR = this.red;
		this.red = Math.max (0, Math.min (red, 1));
		return oldR;
	}

	/**
	 * Set red component, out of range values will be truncated to fit
	 * 
	 * @param red
	 *            [0-1]
	 * @return previous red component value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public float setR (float red)
	{
		return (float) setR ((double) red);
	}

	/**
	 * Set green component, out of range values will be truncated to fit
	 * 
	 * @param green
	 *            [0-1]
	 * @return previous green component value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public double setG (double green)
	{
		double oldG = this.green;
		this.green = Math.max (0, Math.min (green, 1));
		return oldG;
	}

	/**
	 * Set green component, out of range values will be truncated to fit
	 * 
	 * @param green
	 *            [0-1]
	 * @return previous green component value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public float setG (float green)
	{
		return (float) setG ((double) green);
	}

	/**
	 * Set blue component, out of range values will be truncated to fit
	 * 
	 * @param blue
	 *            [0-1]
	 * @return previous blue component value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public double setB (double blue)
	{
		double oldB = this.blue;
		this.blue = Math.max (0, Math.min (blue, 1));
		return oldB;
	}

	/**
	 * Set blue component, out of range values will be truncated to fit
	 * 
	 * @param blue
	 *            [0-1]
	 * @return previous blue component value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public float setB (float blue)
	{
		return (float) setB ((double) blue);
	}

	/**
	 * Set alpha component, out of range values will be truncated to fit
	 * 
	 * @param alpha
	 *            [0-1]
	 * @return previous alpha value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public double setA (double alpha)
	{
		double oldA = this.alpha;
		this.alpha = Math.max (0, Math.min (alpha, 1));
		return oldA;
	}

	/**
	 * Set alpha component, out of range values will be truncated to fit
	 * 
	 * @param alpha
	 *            [0-1]
	 * @return previous alpha value
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 * @see <a href="http://id.mind.net/~zona/mmts/miscellaneousMath/intervalNotation/intervalNotation.html"
	 *      >Interval Notation< /a>
	 */
	public float setA (float alpha)
	{
		return (float) setA ((double) alpha);
	}

	/**
	 * @return float vector of current RGBA values
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 */
	public double[] getRGBA ()
	{
		double[] rgba = { red, green, blue, alpha };
		return rgba;
	}

	/**
	 * @return float vector of current RGBA values
	 * @see <a href="http://en.wikipedia.org/wiki/Rgb">RGB Color Model< /a>
	 * @see <a href="http://en.wikipedia.org/wiki/Alpha_compositing">Alpha
	 *      compositing< /a>
	 */
	public float[] getRGBAf ()
	{
		float[] rgba = { (float) red, (float) green, (float) blue, (float) alpha };
		return rgba;
	}

	/**
	 * @return The string "(Red, Green, Blue, Alpha)"
	 */
	public String toString ()
	{
		return new String ("(" + red + ", " + green + ", " + blue + ", " + alpha + ")");
	}

	/**
	 * @return The string "(Red, Green, Blue)"
	 */
	public String toStringRGB ()
	{
		return new String ("(" + red + ", " + green + ", " + blue + ")");
	}
}