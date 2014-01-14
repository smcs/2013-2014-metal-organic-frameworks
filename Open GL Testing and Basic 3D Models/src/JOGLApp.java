



import java.io.IOException;

import simplerjogl.*;

public class JOGLApp
{
		private JOGLRenderer MyRenderer;
		
		
        public static void main (String[] args) throws IOException
        {
                
                
			
				
				ObjReader OR = new ObjReader("C:\\Users\\Owner\\Documents\\Spacer.obj");


                
                JOGLRenderer MyRenderer = new JOGLRenderer(OR);
                Frame frame = Frame.createFrame ("SimplerJOGL App", false);
                frame.addGLEventListener (MyRenderer);
                frame.start();
            
                WindowListener w = new WindowListener(frame, MyRenderer);
        }

       
}