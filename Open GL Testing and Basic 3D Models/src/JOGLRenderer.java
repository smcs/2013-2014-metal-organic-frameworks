import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simplerjogl.*;

public class JOGLRenderer extends Renderer {
	public double LRrot, UDrot, UDx, UDy, UDz, LRx, LRy, LRz, Cdist;
	private Light l;
	// private boolean Modified = false;
	int a = 0;

	public void init() {
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

	public void display() {
		glu.gluLookAt(0, 0, Cdist, 0, 0, 0, 0, 1, 0);

		// System.out.println(a);
		// a++;

		gl.glRotated(UDrot, UDx, UDy, UDz);
		gl.glRotated(LRrot, LRx, LRy, LRz);
		// end rotating stuff

		glut.glutWireCube(5);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					gl.glPushMatrix();
					{
						gl.glTranslated(-2 + i, -2 + j, -2 + k);
						glut.glutSolidSphere(0.5, 10, 10);
					}
					gl.glPopMatrix();
				}
			}
		}

	}

	public void RotateLeft() {
		LRy = -1;
		LRz = 0;
		LRx = 0;
		LRrot = LRrot - 5;

	}

	public void RotateRight() {
		LRy = -1;
		LRz = 0;
		LRx = 0;
		LRrot = LRrot + 5;

	}

	public void RotateUp() {
		UDy = 0;
		UDz = 0;
		UDx = 1;
		UDrot = UDrot + 5;

	}

	public void RotateDown() {
		UDy = 0;
		UDz = 0;
		UDx = 1;
		UDrot = UDrot - 5;

	}

	public void ZoomIn() {
		if (Cdist > 0) {
			Cdist = Cdist - 0.1;
		}
		if (Cdist < 0) {
			Cdist = Cdist + 0.1;
		}

	}

	public void ZoomOut() {
		if (Cdist < 0) {
			Cdist = Cdist - 0.1;
		}
		if (Cdist > 0) {
			Cdist = Cdist + 0.1;
		}

	}

}