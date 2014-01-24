

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simplerjogl.*;


public class JOGLRenderer extends Renderer 
{
        public double LRrot,UDrot, UDx, UDy, UDz,LRx,LRy,LRz, Cdist;

        //private Light l;
        int a = 0;
        








		public void init ()
        {


        		Cdist = 8;
                LRrot = 0;
                UDrot = 0;
                UDx = 1;
                UDy = 1;
                UDz = 1;
                LRx = 1;
                LRy = 1;
                LRz = 1;
               // l = new Light(gl);
               // l.enable();

        }


        public void display ()
        {
        		glu.gluLookAt (
                    0, 8, 1,
                    0, 0, 0,
                    0, 1, 0
        				);
    			


                gl.glRotated(UDrot, UDx, UDy, UDz);
                gl.glRotated(LRrot, LRx, LRy, LRz);
                //end rotating stuff


                //TODO calculate normal vectors using cross product 
                gl.glBegin(gl.GL_LINE_LOOP);
                
                for(int i = 0; i < JOGLApp.facesArray.size(); i++){ //for every face
                	for(int j = 0; j < JOGLApp.facesArray.elementAt(i).length; j++) //for every point on the face

                	gl.glVertex3f(JOGLApp.vertexArray[3*JOGLApp.facesArray.elementAt(i)[j]]
                			,JOGLApp.vertexArray[3*JOGLApp.facesArray.elementAt(i)[j]+1]
                			,JOGLApp.vertexArray[3*JOGLApp.facesArray.elementAt(i)[j]+2]);
                }
               
                gl.glEnd();
                }

                
               
                
                


            	


        


		public void RotateLeft() {
			LRy = -1;
			LRz = 0;
			LRx = 0;

			

			LRrot = LRrot -5;
			
			
		}


		public void RotateRight() {
			LRy = -1;
			LRz = 0;
			LRx = 0;

			
		
			LRrot = LRrot +5;
			
		
			
		}


		public void RotateUp() {
			UDy = 0;
			UDz = 0;
			UDx = 1;

			
		
			UDrot = UDrot +5;
			
		
			
		}


		public void RotateDown() {
			UDy = 0;
			UDz = 0;
			UDx = 1;

		
			UDrot = UDrot -5;
			

			
		}


		public void ZoomIn() {
			if(Cdist > 0){
			Cdist = Cdist - 0.1;
			}
			if(Cdist < 0){
			Cdist = Cdist + 0.1;
			}
			
		}


		public void ZoomOut() {
			if(Cdist < 0){
			Cdist = Cdist - 0.1;
			}
			if(Cdist > 0){
			Cdist = Cdist + 0.1;
			}
			
		}



        

}