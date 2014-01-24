package simplerjogl.loader.obj;

import java.io.*;

/**
 * A discrete definition from a Wavefront OBJ file, defined in a single line.
 * 
 * @see http://en.wikipedia.org/wiki/Wavefront_.obj_file
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * 
 */
public class Definition {
	private String type;
	private String[] parameter;

	/**
	 * Construct a definition from a line of text. Requires a definition type
	 * followed by a minimum of 3 parameter tokens (e.g. "v 10.2 3 4.11" or
	 * "f 21 32 6 17")
	 * 
	 * @param text
	 * @throws IOException
	 */
	public Definition(String text) throws IOException {
		this(text, 3);
	}

	/**
	 * Construct a definition from a line of text, specifying a specific minimum
	 * number of parameter tokens.
	 * 
	 * @param text
	 * @param minimumParameterCount
	 * @throws IOException
	 */
	public Definition(String text, int minimumParameterCount)
			throws IOException {
		this(text, minimumParameterCount, Integer.MAX_VALUE - 1);
	}

	/**
	 * Construct a definition from a line of text, specifying both a specific
	 * minimum and a specific maximum number of parameter tokens.
	 * 
	 * @param text
	 * @param minimumParameterCount
	 * @param maximumParameterCount
	 * @throws IOException
	 */
	public Definition(String text, int minimumParameterCount,
			int maximumParameterCount) throws IOException {
		String[] token = text.split("\\s");

		if (token.length < minimumParameterCount + 1) {
			throw new IOException("Invalid definition: " + (token.length - 1)
					+ " parameters, minimum of " + minimumParameterCount
					+ " expected: \"" + text + "\"");
		}

		if (token.length > maximumParameterCount + 1) {
			throw new IOException("Invalid definition: " + (token.length - 1)
					+ " parameters, maximum of " + maximumParameterCount
					+ " expected: \"" + text + "\"");
		}

		type = token[0];
		parameter = new String[token.length - 1];
		for (int i = 0; i < parameter.length; i++) {
			parameter[i] = token[i + 1];
		}
	}

	/**
	 * @return The type of definition (defined in ObjLoader)
	 * @see http://en.wikipedia.org/wiki/Wavefront_.obj_file
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return The number of parameter tokens
	 */
	public int getParameterCount() {
		return parameter.length;
	}

	/**
	 * @param index
	 *            zero-based
	 * @return The specified parameter
	 */
	public String getParameter(int index) {
		return parameter[index];
	}
}
