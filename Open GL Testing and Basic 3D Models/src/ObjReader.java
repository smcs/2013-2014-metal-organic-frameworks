import simplerjogl.Renderer;

import com.jogamp.common.nio.Buffers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;


public class ObjReader {
	

	 
	  public FloatList tmpVertices;
	  public FloatList tmpVertexNormals;
	  private IntList   faceIndices;
	  private IntList[] tmpFaceIndices;
	  public Vector<int[]> tmpFaces;
	 
	 
	  /*
	   * constructors, inputs a file, check for exceptions
	   */
	  public ObjReader(String filename) throws IOException {
	    this(new File(filename));
	    //calls constructor for file
	  }

	  public ObjReader(InputStream in) throws IOException {
	    this(new InputStreamReader(in));
	  }

	  public ObjReader(File file) throws IOException {
	    this (new FileReader(file));
	    //calls constructor with reader
	  }
	  
	  /*
	   * actual constructor, uses Reader r
	   */
	  public ObjReader(Reader r) throws IOException {
	    BufferedReader reader = new BufferedReader(r);
	    String line = null;
	    int lineNo = 0;
	    float[] floatTmp = new float[3];

	    //while the line is equal to the line the reader is reading and exists 
	    while ((line = reader.readLine()) != null) {
	      ++lineNo;
	      if (line.length() > 0) {
	        char c = line.charAt(0); //looks at the first character in the line 
	        // FIXME: support continuation of lines with trailing '\'
	        switch (c) {
	          case '#':
	            break;

	          case 'v': //this means the line is a vertex 
	            if (Character.isWhitespace(line.charAt(1))) {
	              addVertex(parseFloats(line, 3, floatTmp, lineNo));
	            } else if (line.startsWith("vn")) {
	              addVertexNormal(parseFloats(line, 3, floatTmp, lineNo));
	            } else {
	              //throw new IOException("Unsupported vertex command on line " + lineNo);
	            }
	            break;
	          
	          case 'f':
	        	  addFace(parseFloats(line,3,floatTmp,lineNo));

	          default:
	            // For now we ignore all other lines
	        }
	      }
	    }


	  }

	

	  //----------------------------------------------------------------------
	  // Internals only below this point
	  //

	  private void addVertex(float[] tmp) {
	    if (tmpVertices == null) {
	      tmpVertices = new FloatList();
	    }
	    for (int i = 0; i < 3; i++) {
	      tmpVertices.add(tmp[i]);
	    }
	  }

	  private void addVertexNormal(float[] tmp) {
	    if (tmpVertexNormals == null) {
	      tmpVertexNormals = new FloatList();
	    }
	    for (int i = 0; i < 3; i++) {
	      tmpVertexNormals.add(tmp[i]);
	    }
	  }
	  
	  private void addFace(float[] tmp) {
		    if (tmpFaces == null) {
		      tmpFaces = new Vector<int[]>();
		    }
		    int[] Storage = new int[tmp.length];
		    for(int i = 0; i < tmp.length; i++){
		    Storage[i] = (int) tmp[i];
		    
		    }
		      tmpFaces.add(Storage);
		    
		  }

	  private float[] parseFloats(String line, int num, float[] tmp, int lineNo) throws IOException {
	    StringTokenizer tok = new StringTokenizer(line);
	    tok.nextToken(); // skip command
	    int idx = 0;
	    while (tok.hasMoreTokens()) {
	      if (idx >= tmp.length) {
	        throw new IOException("Too many floating-point values on line " + lineNo);
	      }
	      tmp[idx++] = Float.parseFloat(tok.nextToken());
	    }
	    return tmp;
	  }
}
	 

	 