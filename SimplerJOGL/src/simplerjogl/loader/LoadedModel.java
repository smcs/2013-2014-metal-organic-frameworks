package simplerjogl.loader;

import javax.media.opengl.GL;

import simplerjogl.*;

/**
 * A model loaded from a file.
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * 
 */
public abstract class LoadedModel extends Model {

	public LoadedModel(GL gl) {
		super(gl);
	}
}
