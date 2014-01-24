package simplerjogl.loader.obj;

import java.io.*;
import java.util.*;

import javax.media.opengl.*;

import simplerjogl.*;
import simplerjogl.loader.*;

/**
 * Load a Wavefront OBJ file into a SimplerJOGL model.
 * 
 * @see http://en.wikipedia.org/wiki/Wavefront_.obj_file
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * 
 */
public class ObjLoader extends Loader {
	public ObjLoader(GL gl) {
		super(gl);
	}

	public static final String DEFN_FACE = "f";
	public static final String DEFN_VERTEX = "v";
	public static final String DEFN_TEXTURE = "vt";
	public static final String DEFN_NORMAL = "vn";
	public static final String DEFN_PARAMETER_SPACE = "vp";
	public static final String DEFN_SMOOTHING = "s";

	/**
	 * Load a Wavefront OBJ file from a character stream (probably a file, but
	 * who knows).
	 * 
	 * @param r
	 * @throws IOException
	 */
	public LoadedObjModel load(Reader r) throws IOException {
		BufferedReader reader = new BufferedReader(r);
		String line = null;
		int readerLineNumber = 0;
		List<Vertex> vertices = new LinkedList<Vertex>();
		List<Face> faces = new LinkedList<Face>();

		while ((line = reader.readLine()) != null) {
			readerLineNumber++;
			if (line.length() > 0) {
				switch (line.charAt(0)) {
				case '#': // comment
					break;
				case 'v': // vertex
					vertices.add(parseVertex(new Definition(line, 3, 4)));
					break;
				case 'f': // face
					faces.add(parseFace(gl, new Definition(line, 3), vertices));
					break;
				default:
					// ignore all other lines
				}
			}
		}
		return new LoadedObjModel(gl, faces);
	}

	/**
	 * Parses a single line as a face in the format "f A B C..." or
	 * "f A/D/G B/E/H C/F/I..." where A, B, C... are a sequence of integer
	 * literals representing indices referencing the previously defined vertex
	 * list starting at 1; D, E, F are integer literals representing indices
	 * referencing the previously defined vertex texture list starting at 1; and
	 * G, H, I are integer literals representing indices referencing the
	 * previously defined vertex normal list starting at 1; and "f" is a string
	 * literal. Both vertex textures and vertex normals do not need to be
	 * included, but the slashes are required for proper identification of index
	 * meaning, e.g.: "f A//G B//H C//I". A face may be made of any number of
	 * vertices greater than 3 (the minimum number of vertices necessarily to
	 * uniquely identify a plane in space).
	 * 
	 * @param line
	 *            The line to be parsed
	 * @return If the line represents a valid face, a Face object representing
	 *         it is returned, otherwise a default Face (with no vertices) is
	 *         returned to maintain the face index counter.
	 */
	private static Face parseFace(GL gl, Definition d, List<Vertex> vertices) {
		if (!d.getType().equals(ObjLoader.DEFN_FACE)) {
			/* invalid: should start with "f" */
			return new Face(gl);
		} else {
			Face face = new Face(gl);
			for (int i = 0; i < d.getParameterCount(); i++) {
				String[] subtoken = d.getParameter(i).split("\\/");
				/* TODO Process vertex texture and normal coordinates as well */
				face.add(vertices.get(Integer.parseInt(subtoken[0]) - 1));
			}
			return face;
		}
	}

	/**
	 * Parses a single line as a vertex in the format "v X Y Z" or "v X Y Z W"
	 * where X, Y, Z and W are all float literals. "v" is a case-insensitive
	 * literal string that must prefix the vertex coordinates.
	 * 
	 * @param line
	 *            The line to be parsed
	 * @return If the line represents a valid vertex, a Vertex object
	 *         representing it is returned, otherwise a default Vertex (the
	 *         origin) is returned to maintain the vertex index counter
	 */
	private static Vertex parseVertex(Definition d) {
		if (!d.getType().equals(ObjLoader.DEFN_VERTEX)) {
			/* invalid: should start with "v" */
			return new Vertex();
		} else {
			if (d.getParameterCount() == 3) {
				return new Vertex(Float.parseFloat(d.getParameter(0)),
						Float.parseFloat(d.getParameter(1)), Float.parseFloat(d
								.getParameter(2)));
			} else if (d.getParameterCount() == 4) {
				return new Vertex(Float.parseFloat(d.getParameter(0)),
						Float.parseFloat(d.getParameter(1)), Float.parseFloat(d
								.getParameter(2)), Float.parseFloat(d
								.getParameter(3)));
			} else {
				/* invalid: should have either 3 or 4 coordinates */
				return new Vertex();
			}
		}
	}
}
