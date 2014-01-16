



import java.io.IOException;

import simplerjogl.*;

public class JOGLApp
{
		private JOGLRenderer MyRenderer;
		
		public static float[] data;
		public static float[] vertexArray;
		public static float[][] normalArray;
		
		
        public static void main (String[] args) throws IOException 
        {
                
                
			ObjReader OR = new ObjReader("C:\\Users\\Owner\\Documents\\Spacer.obj");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			data = new float[OR.tmpVertices.size()];
			data = OR.tmpVertices.getData();
			vertexArray = new float[OR.tmpVertices.size()];
			
			System.out.println(OR.tmpVertices.size());
			
			for(int i = 0; i < OR.tmpVertices.size()/3; i++){
				vertexArray[i] = data[i];		


			}

                
                JOGLRenderer MyRenderer = new JOGLRenderer();
                
                Frame frame = Frame.createFrame ("SimplerJOGL App", false);
                frame.addGLEventListener (MyRenderer);
                frame.start();
            
                WindowListener w = new WindowListener(frame, MyRenderer);
        }

       
}