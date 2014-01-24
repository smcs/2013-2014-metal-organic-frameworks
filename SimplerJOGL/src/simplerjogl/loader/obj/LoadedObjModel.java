package simplerjogl.loader.obj;

import java.util.*;

import javax.media.opengl.GL;

import simplerjogl.*;
import simplerjogl.loader.*;

/**
 * A Model imported from a Wavefront OBJ model file, ready to be rendered in
 * SimplerJOGL.
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * 
 */
public class LoadedObjModel extends LoadedModel {

	private List<Face> faces;

	/**
	 * Construct an empty model
	 * 
	 * @param gl
	 *            The OpenGL drawing context
	 */
	public LoadedObjModel(GL gl) {
		super(gl);
		faces = new LinkedList<Face>();
	}

	/**
	 * Construct a model from an arbitrary list of polygonal Faces
	 * 
	 * @param gl
	 *            The OpenGL drawing context
	 * @param faces
	 *            The list of polygonal faces
	 */
	public LoadedObjModel(GL gl, List<Face> faces) {
		this(gl);
		addAll(faces);
	}

	/**
	 * Add a face to the model
	 * 
	 * @param f
	 */
	public void add(Face f) {
		faces.add(new Face(f));
	}

	/**
	 * Copy an arbitrary list of faces to the model
	 * 
	 * @param faces
	 */
	public void addAll(List<Face> faces) {
		Iterator<Face> iterator = faces.iterator();
		while (iterator.hasNext()) {
			Face f = iterator.next();
			add(f);
		}
	}

	/**
	 * Draw the model (as either a wireframe or solid)
	 * 
	 * @param wireframe
	 *            Whether or not to draw this model as a wireframe
	 */
	public void draw(boolean wireframe) {
		Iterator<Face> iterator = faces.iterator();
		while (iterator.hasNext()) {
			Face f = iterator.next();
			f.draw(wireframe);
		}
	}

	/**
	 * Draw the model as a solid
	 */
	public void draw() {
		draw(false);
	}
}
