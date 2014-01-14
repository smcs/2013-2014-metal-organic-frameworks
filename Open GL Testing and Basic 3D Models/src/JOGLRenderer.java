

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simplerjogl.*;


public class JOGLRenderer extends Renderer 
{
        public double LRrot,UDrot, UDx, UDy, UDz,LRx,LRy,LRz, Cdist;

        private Light l;
        private float[][] GL_VERTEX_ARRAY;
        private float[][] GL_NORMAL_ARRAY;
        private ObjReader OR;

        int a = 0;
        



		public JOGLRenderer(ObjReader oR) {
			OR = oR;
			GL_VERTEX_ARRAY = new float[(int) Math.floor(OR.tmpVertices.size())][3];
			GL_NORMAL_ARRAY = new float[(int) Math.floor(OR.tmpVertexNormals.size())][3];
			for(int i = 0; i < OR.tmpVertices.size(); i++){
				GL_VERTEX_ARRAY[i][0] = OR.tmpVertices.get(i);		
				GL_VERTEX_ARRAY[i][1] = OR.tmpVertices.get(i+1);
				GL_VERTEX_ARRAY[i][2] = OR.tmpVertices.get(i+2);
				}
			for(int i = 0; i < OR.tmpVertexNormals.size(); i++){
				GL_NORMAL_ARRAY[i][0] = OR.tmpVertexNormals.get(i);
				GL_NORMAL_ARRAY[i][1] = OR.tmpVertexNormals.get(i+1);
				GL_NORMAL_ARRAY[i][2] = OR.tmpVertexNormals.get(i+2);
			}
			init();
		}


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
                l = new Light(gl);
                l.enable();

        }


        public void display ()
        {
        		glu.gluLookAt (
                    0, 0, Cdist,
                    0, 0, 0,
                    0, 1, 0
        				);
    			
        		//System.out.println(a);
        		//a++;

                gl.glRotated(UDrot, UDx, UDy, UDz);
                gl.glRotated(LRrot, LRx, LRy, LRz);
                //end rotating stuff

                
                
                
                glut.glutWireCube(5);
                int rot = 0;
                for(int i = 0; i < 5;i++){
                	for(int j = 0; j<5; j++){
                		for(int k = 0; k < 5; k++){
                            gl.glPushMatrix();{// saves current data & suppresses it
                            gl.glRotated(rot, 1, 0, 0);
                			gl.glTranslated(-2+i,-2+j,-2+k); //translates that sphere
                			glut.glutSolidSphere(0.3, 10, 10); //makes a new sphere
                            gl.glPopMatrix(); //un-suppresses original matrix
                            rot = rot + 10;
                            }
                		}
                	}
                }
                
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