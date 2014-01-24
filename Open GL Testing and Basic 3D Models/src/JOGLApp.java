



import java.io.IOException;
import java.util.Vector;

import simplerjogl.*;

public class JOGLApp
{
		private static JOGLRenderer MyRenderer;
		
		public static float[] data;
		public static float[] vertexArray;
		public static Vector<int[]> facesArray;
		
		
        public static void main (String[] args) throws IOException 
        {
                
                
			ObjReader OR = new ObjReader("C:\\Users\\Owner\\Documents\\Spacer.obj");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			vertexArray = new float[OR.tmpVertices.size()];
			vertexArray = OR.tmpVertices.getData();
			System.out.println(vertexArray.length);
			facesArray = OR.tmpFaces;
			System.out.println(facesArray.elementAt(87).length);
                
                JOGLRenderer MyRenderer = new JOGLRenderer();
                
                Frame frame = Frame.createFrame ("SimplerJOGL App", false);
                frame.addGLEventListener (MyRenderer);
                frame.start();
            
                WindowListener w = new WindowListener(frame, MyRenderer);
        }

       
}