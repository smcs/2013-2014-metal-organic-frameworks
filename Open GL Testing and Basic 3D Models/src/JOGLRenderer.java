

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simplerjogl.*;


public class JOGLRenderer extends Renderer 
{
        public double LRrot,UDrot, UDx, UDy, UDz,LRx,LRy,LRz, Cdist;

        private Light l;
        private float[][] vertexArray;
        private float[][] normalArray;
        private ObjReader objReader;

        int a = 0;
        








		public void init ()
        {

        		//float x = JOGLApp.vertexArray[0][0];
        		//System.out.println(x);
        		Cdist = 8;
                LRrot = 0;
                UDrot = 0;
                UDx = 1;
                UDy = 1;
                UDz = 1;
                LRx = 1;
                LRy = 1;
                LRz = 1;
                l = new Light(gl);
                l.enable();

        }


        public void display ()
        {
        		glu.gluLookAt (
                    0, 8, 1,
                    0, 0, 0,
                    0, 1, 0
        				);
    			
        		//System.out.println(vertexArray[a][0]);
        		//a++;

                gl.glRotated(UDrot, UDx, UDy, UDz);
                gl.glRotated(LRrot, LRx, LRy, LRz);
                //end rotating stuff


                 // Build Quad From A Triangle Strip
                for(int i = 0; i < JOGLApp.vertexArray.length/3; i++){
                gl.glBegin(gl.GL_TRIANGLE_STRIP);
                if(9*i+9 > JOGLApp.vertexArray.length){
                	return;
                }
                gl.glVertex3f(JOGLApp.vertexArray[9*i], JOGLApp.vertexArray[9*i+1], JOGLApp.vertexArray[9*i+2]); // Top Right
              
                gl.glVertex3f(JOGLApp.vertexArray[9*i+3], JOGLApp.vertexArray[9*i+4], JOGLApp.vertexArray[9*i+5]); // Top Left
            
                gl.glVertex3f(JOGLApp.vertexArray[9*i+6], JOGLApp.vertexArray[9*i+7], JOGLApp.vertexArray[9*i+8]); // Bottom Right
                
                gl.glEnd();
                }
                  // Done Building Triangle Strip
                
                //glut.glutWireCube(5);
               // int rot = 0;
                //for(int i = 0; i < 5;i++){
                //	for(int j = 0; j<5; j++){
                //		for(int k = 0; k < 5; k++){
                 //           gl.glPushMatrix();{// saves current data & suppresses it
                 //           gl.glRotated(rot, 1, 0, 0);
                //			gl.glTranslated(-2+i,-2+j,-2+k); //translates that sphere
                //			glut.glutSolidSphere(0.3, 10, 10); //makes a new sphere
                 //           gl.glPopMatrix(); //un-suppresses original matrix
                 //           rot = rot + 10;
                //            }
                //		}
                //	}
              //  }
                
                //gl.glEnableClientState(GL_VERTEX_ARRAY);
                //gl.glEnableClientState(GL_NORMAL_ARRAY);

                
               
                
                


            	

      
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