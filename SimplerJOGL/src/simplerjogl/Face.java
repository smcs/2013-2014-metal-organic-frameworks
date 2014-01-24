package simplerjogl;

import java.util.*;
import javax.media.opengl.*;

/**
 * A Face describes a single polygonal face (drawn as a triangle, quad or
 * polygon, depending on the number of vertices). The outline of the face is
 * defined by the vertices in counter-clockwise (right-hand rule) order as they
 * are added to the face.
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * 
 */
public class Face extends SimplerJOGLObject {
	private List<Vertex> vertices;

	/**
	 * Construct an empty face (to which vertices may be added later).
	 * 
	 * @param gl
	 *            The OpenGL drawing context
	 */
	public Face(GL gl) {
		super(gl);
		vertices = new LinkedList<Vertex>();
	}

	/**
	 * Construct a Face from a single vertex (one anticipates that others may be
	 * added later...)
	 * 
	 * @param gl
	 *            The OpenGl drawing context
	 * @param v
	 *            The vertex to be added
	 */
	public Face(GL gl, Vertex v) {
		this(gl);
		add(v);
	}

	/**
	 * Construct a Face from an arbitrary list of vertices.
	 * 
	 * @param gl
	 *            The OpenGL drawing context
	 * @param l
	 *            The list of vertices from which to construct the face
	 */
	public Face(GL gl, List<Vertex> l) {
		this(gl);
		addAll(l);
	}

	/**
	 * Copy constructor
	 * 
	 * @param other
	 */
	public Face(Face other) {
		this(other.gl);
		Iterator<Vertex> i = other.vertices.iterator();
		while (i.hasNext()) {
			Vertex v = i.next();
			add(v);
		}
	}

	/**
	 * Copy a vertex to this face.
	 * 
	 * @param v
	 */
	public void add(Vertex v) {
		vertices.add(new Vertex(v));
	}

	/**
	 * Add a list of vertices to this face. Vertices will be copied to the face.
	 * 
	 * @param l
	 *            The list of vertices to add.
	 */
	public void addAll(List<Vertex> l) {
		Iterator<Vertex> i = l.iterator();
		while (i.hasNext()) {
			Vertex v = i.next();
			add(v);
		}
	}

	/**
	 * Draw this face either filled or as a wireframe. 3 vertex faces will be
	 * drawn as triangles, 4 vertex faces as quads and all othes as polygons.
	 * 
	 * @param wireframe
	 *            Whether or not to draw this face as a wireframe
	 */
	public void draw(boolean wireframe) {
		if (vertices.size() > 0) {
			if (wireframe) {
				gl.glBegin(GL.GL_LINE_LOOP);
			} else {
				if (vertices.size() == 3) {
					gl.glBegin(GL2.GL_TRIANGLES);
				} else if (vertices.size() == 4) {
					gl.glBegin(GL2.GL_QUADS);
				} else {
					gl.glBegin(GL2.GL_POLYGON);
				}
			}
			
			gl.glNormal3fv(surfaceNormal().getXYZf(), 0);
			
			Iterator<Vertex> i = vertices.iterator();
			while (i.hasNext()) {
				Vertex v = i.next();
				gl.glVertex4fv(v.getXYZWf(), 0);
			}

			gl.glEnd();
		}
	}

	/**
	 * Draw this face filled.
	 */
	public void draw() {
		draw(false);
	}

	/**
	 * Calculate the normal vector to this face
	 * 
	 * @return The coordinates of an origin-based normal vector
	 */
	public Vertex surfaceNormal() {
		return Vertex.normalVector(this.vertices);
	}
}