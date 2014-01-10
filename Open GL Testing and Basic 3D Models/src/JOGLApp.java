



import simplerjogl.*;

public class JOGLApp
{
		private JOGLRenderer MyRenderer;
        public static void main (String[] args)
        {
                JOGLRenderer MyRenderer = new JOGLRenderer ();
               
                Frame frame = Frame.createFrame ("SimplerJOGL App", false);
                frame.addGLEventListener (MyRenderer);
                frame.start();
            
                WindowListener w = new WindowListener(frame, MyRenderer);
        }

       
}