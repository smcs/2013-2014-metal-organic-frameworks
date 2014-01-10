
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simplerjogl.Frame;
import simplerjogl.Renderer;


public class WindowListener implements KeyListener{
	private JOGLRenderer renderer;
	
	public WindowListener(Frame frame, JOGLRenderer MyRenderer){
		   frame.addKeyListener(this);
		   renderer = MyRenderer;
	}
	


	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){ //left
		renderer.RotateLeft();
		}
		if(arg0.getKeyCode() == 39){ //right
		renderer.RotateRight();
		}
		if(arg0.getKeyCode() == 38){ //up
		renderer.RotateUp();
		}
		if(arg0.getKeyCode() == 40){ //down
		renderer.RotateDown();
		}
		if(arg0.getKeyCode() == 87){ //zoom in
		renderer.ZoomIn();
		}
		if(arg0.getKeyCode() == 83){ //zoom out
		renderer.ZoomOut();
		}
	}

	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
