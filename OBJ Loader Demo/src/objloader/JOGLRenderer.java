package objloader;

import java.io.*;
import simplerjogl.*;
import simplerjogl.loader.LoadedModel;
import simplerjogl.loader.obj.*;

/**
 * Demonstrate the use of an OBJ Loader to import Wavefront OBJ files into
 * SimplerJOGL.
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * 
 */
public class JOGLRenderer extends Renderer {

	/* a model that will be loaded from a Wavefront OBJ file */
	private LoadedModel suzanne;
	

	private int degreesOfRotation;
	private Light basicLight;

	public void init() {
		/*
		 * load the OBJ file from the model directory as a model. Note that,
		 * since we are dealing with file I/O, IOExceptions may be thrown so we
		 * either need to throw them ourselves or catch them.
		 */
		try {
			suzanne = new ObjLoader(gl).load("C:\\Users\\Owner\\Documents\\Spacer.obj");
		} catch (IOException e) {
			e.printStackTrace();
		}
		


		degreesOfRotation = 0;
		basicLight = new Light(gl);
		basicLight.enable();
	}

	public void display() {
		glu.gluLookAt(0, 0, 5, 0, 0, 0, 0, 1, 0);
		gl.glRotatef(degreesOfRotation++, 1, 1, 0);
		place(0,0,0,0);
	}
	
	public void place(int atomNumber,float dx,float dy,float dz){
		suzanne.draw();
	}
}