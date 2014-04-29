package objloader;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.stream.XMLStreamException;

public class Visual {
	private JFrame frame;
	private JButton b1; 
	
	public Visual() throws XMLStreamException, Exception{
		 GraphDemo g = new GraphDemo(); 
			
	     JFrame frame = new JFrame();
	     frame.setSize(500,500);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	     
	     b1 = new JButton();
	     b1.setVerticalTextPosition(AbstractButton.CENTER);
	     b1.setHorizontalTextPosition(AbstractButton.LEADING);


	     b1.addActionListener(new java.awt.event.ActionListener() {
	        	 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                System.out.println("You clicked the button");
	            }
	        });   
	     
	     
	     frame.add(g.vv);
	     frame.add(b1);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	     frame.setVisible(true); 
	}
	public void ActionListener(ActionEvent e){
		
	}
	
	public static void main(String arg[]) throws XMLStreamException, Exception{
		Visual v = new Visual(); 
	}
}
