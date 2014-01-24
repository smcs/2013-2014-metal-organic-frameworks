package simplerjogl;

import java.util.*;

/**
 * A class to manage 2D and 3D homogeneous coordinates for SimplerJOGL
 * applications
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2009-01-07
 */
public class Vertex {
	/**
	 * Cartesian coordinates of the vertex
	 */
	protected double x, y, z;
	/**
	 * For homogeneous coordinates
	 * 
	 * @see <a href="http://glprogramming.com/red/appendixf.html#name1"> *
	 *      Homogenous Coordinates< /a>
	 */
	protected double w;

	/**
	 * Default constructor, vertex at the origin
	 */
	public Vertex() {
		this(0, 0, 0, 1);
	}

	/**
	 * Set to a specific position in homogeneous coordinates
	 * 
	 * @see #w Field Details
	 */
	public Vertex(double x, double y, double z, double w) {
		setPosition(x, y, z, w);
	}

	/**
	 * Set to a specific position in 3D Cartesian coordinates
	 */
	public Vertex(double x, double y, double z) {
		setPosition(x, y, z, 1);
	}

	/**
	 * Set to a specific position in 2D Cartesian coordinates in the z=0 plane
	 */
	public Vertex(double x, double y) {
		setPosition(x, y, 0, 1);
	}

	/**
	 * Copy constructor, duplicates the coordinates of another Vector into this
	 * object
	 */
	public Vertex(Vertex other) {
		setPosition(other.x, other.y, other.z, other.w);
	}

	/**
	 * @return true if (x, y, z, w) coordinates of both Vectors are identical,
	 *         false otherwise
	 */
	public boolean equals(Vertex other) {
		if (other == null) {
			return false;
		}
		return (this.x == other.x) && (this.y == other.y)
				&& (this.z == other.z) && (this.w == other.w);
	}

	/**
	 * Set to a specific position in homogeneous coordinates
	 * 
	 * @return double vector of previous homogeneous coordinates
	 * @see #w Field Details
	 */
	public double[] setPosition(double x, double y, double z, double w) {
		double[] oldPos = { this.x, this.y, this.z, this.w };
		setX(x);
		setY(y);
		setZ(z);
		setW(w);
		return oldPos;
	}

	/**
	 * Set to a specific position in homogeneous coordinates
	 * 
	 * @return float vector of previous homogeneous coordinates
	 * @see #w Field Details
	 * @deprecated
	 */
	public float[] setPosition(float x, float y, float z, float w) {
		double[] oldPos = setPosition((double) x, (double) y, (double) z,
				(double) w);
		float[] oldPosf = { (float) oldPos[0], (float) oldPos[1],
				(float) oldPos[2], (float) oldPos[3] };
		return oldPosf;
	}

	/**
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return x coordinate as a float
	 */
	public float getXf() {
		return (float) x;
	}

	/**
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return y coordinate as a float
	 */
	public float getYf() {
		return (float) y;
	}

	/**
	 * @return z coordinate
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @return z coordinate as a float
	 */
	public float getZf() {
		return (float) z;
	}

	/**
	 * @return w coordinate
	 */
	public double getW() {
		return w;
	}

	/**
	 * @return w coordinate as a float
	 */
	public float getWf() {
		return (float) w;
	}

	/**
	 * @return previous x coordinate value
	 */
	public double setX(double x) {
		double oldX = this.x;
		this.x = x;
		return oldX;
	}

	/**
	 * @return previous x coordinate value
	 * @deprecated
	 */
	public float setX(float x) {
		return (float) setX((double) x);
	}

	/**
	 * @return previous y coordinate value
	 */
	public double setY(double y) {
		double oldY = this.y;
		this.y = y;
		return oldY;
	}

	/**
	 * @return previous y coordinate value
	 * @deprecated
	 */
	public float setY(float y) {
		return (float) setY((double) y);
	}

	/**
	 * @return previous z coordinate value
	 */
	public double setZ(double z) {
		double oldZ = this.z;
		this.z = z;
		return oldZ;
	}

	/**
	 * @return previous z coordinate value
	 * @deprecated
	 */
	public float setZ(float z) {
		return (float) setZ((double) z);
	}

	/**
	 * For portability, {@literal w<0} is not recommended, w coordinate is set
	 * to absolute value of w parameter
	 * 
	 * @return previous w coordinate value
	 * @see #w Field Details
	 */
	public double setW(double w) {
		double oldW = this.w;
		this.w = Math.abs(w);
		return oldW;
	}

	/**
	 * For portability, {@literal w<0} is not recommended, w coordinate is set
	 * to absolute value of w parameter
	 * 
	 * @return previous w coordinate value
	 * @see #w Field Details
	 * @deprecated
	 */
	public float setW(float w) {
		return (float) setW((double) w);
	}

	/**
	 * @return double vector of (x, y, z, w) coordinates
	 */
	public double[] getXYZW() {
		double[] xyzw = { x, y, z, w };
		return xyzw;
	}

	/**
	 * @return float vector of (x, y, z, w) coordinates
	 */
	public float[] getXYZWf() {
		double[] xyzw = getXYZW();
		float[] xyzwf = { (float) xyzw[0], (float) xyzw[1], (float) xyzw[2],
				(float) xyzw[3] };
		return xyzwf;
	}

	/**
	 * @return double vector of (x, y, z) coordinates, normalizing homogeneous
	 *         coordinates to (x/w, y/w/, z/w) if w > 0
	 */
	public double[] getXYZ() {
		if (w > 0) {
			double[] xyz = { x / w, y / w, z / w };
			return xyz;
		} else {
			double[] xyz = { x, y, z };
			return xyz;
		}
	}

	/**
	 * @return float vector of (x, y, z) coordinates, normalizing homogeneous
	 *         coordinates to (x/w, y/w/, z/w) if w > 0
	 */
	public float[] getXYZf() {
		double[] xyz = getXYZ();
		float[] xyzf = { (float) xyz[0], (float) xyz[1], (float) xyz[2] };
		return xyzf;
	}

	public String toString() {
		return new String("(" + x + ", " + y + ", " + z + ", " + w + ")");
	}

	public String toString3D() {
		return new String("(" + x / w + ", " + y / w + ", " + z / w + ")");
	}

	public String toString2D() {
		return new String("(" + x / w + ", " + y / w + ")");
	}

	/**
	 * Compute a normal vector to a surface defined by the three
	 * counter-clockwise vertices in the plane of the surface
	 * 
	 * @param A
	 * @param B
	 * @param C
	 * @return coordinates of an origin-based normal vector
	 */
	public static Vertex normalVector(Vertex A, Vertex B, Vertex C) {
		Vertex v1 = new Vertex(A.x - B.x, A.y - B.y, A.z - B.z);
		Vertex v2 = new Vertex(B.x - C.x, B.y - C.y, B.z - C.z);
		return new Vertex(v1.y * v2.z - v1.z * v2.y, v1.x * v2.z - v1.z * v2.x,
				v1.x * v2.y - v1.y * v2.x);
	}

	/**
	 * Compute a normal vector to an arbitrary polygon using Newell's Method
	 * 
	 * @see http://www.opengl.org/wiki/Calculating_a_Surface_Normal
	 * @param polygon
	 * @return coordinates of an origin-based normal vector
	 */
	public static Vertex normalVector(List<Vertex> polygon) {
		Vertex normal = new Vertex();
		for (int i = 0; i < polygon.size(); i++) {
			Vertex v1 = polygon.get(i);
			Vertex v2 = polygon.get((i + 1) % polygon.size());
			normal.setX(normal.getX()
					+ ((v1.getY() - v2.getY()) * (v1.getZ() + v2.getZ())));
			normal.setY(normal.getY()
					+ ((v1.getZ() - v2.getZ()) * (v1.getX() + v2.getX())));
			normal.setZ(normal.getZ()
					+ ((v1.getX() - v2.getX()) * (v1.getY() + v2.getY())));
		}
		return normal;
	}
}