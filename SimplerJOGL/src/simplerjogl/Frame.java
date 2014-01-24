
package simplerjogl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;

import com.jogamp.opengl.util.*;

/**
 * A class to display a Swing window containing an OpenGL canvas, based
 * closely on Pepijn Van Eeckhoudt's work for the {@link <a
 * href="http://jogl.dev.java.net/">NeHe JOGL Demo Ports< /a>}.
 * 
 * @author Pepijn Van Eeckhoudt
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2008-08-06
 */
public class Frame
{
	/**
	 * Default width of window frame in pixels
	 */
	protected static final int DEFAULT_WIDTH = 800;
	/**
	 * Default height of window frame in pixels
	 */
	protected static final int DEFAULT_HEIGHT = 600;
	/**
	 * Window frame containing the Swing components
	 */
	protected JFrame frame;
	/**
	 * GL drawing context
	 */
	protected GLCanvas glCanvas;
	/**
	 * Utility class to regulate frames per second when animating (as we
	 * are in the GL drawing context)
	 */
	protected FPSAnimator animator;
	/**
	 * Current width of the window frame in pixels
	 */
	protected int width;
	/**
	 * Current height of the window frame in pixels
	 */
	protected int height;
	/**
	 * Is the window frame running fullscreen?
	 */
	protected boolean fullscreen;
	/**
	 * Utility class to analyze the hardware on which the app is running
	 */
	protected GraphicsDevice usedDevice;
	/**
	 * Null value for findDisplayMode methods
	 */
	private static final int DONT_CARE = -1;

	/**
	 * Statically create an instance of the window frame, querying user to
	 * run fullscreen
	 * 
	 * @param title
	 *            window title
	 * @param fullscreen
	 *            true to attempt running fullscreen, false to run windowed
	 */
	public static Frame createFrame (String title, boolean fullscreen)
	{
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		if (fullscreen)
		{
			if (device.isFullScreenSupported ())
			{
				/* query user to run fullscreen with a generic dialog box */
				int selectedOption = JOptionPane.showOptionDialog (null, "How would you like to run this program?", title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Fullscreen", "Windowed" }, "Windowed");
				fullscreen = (selectedOption == 0);
			}
		}
		return new Frame (title, DEFAULT_WIDTH, DEFAULT_HEIGHT, fullscreen);
	}

	/**
	 * @param title
	 *            the title of the window
	 * @param width
	 *            the width of the window in pixels
	 * @param height
	 *            the height of the window in pixels
	 * @param fullscreen
	 *            true for fullscreen display, false for windowed display
	 */
	protected Frame (String title, int width, int height, boolean fullscreen)
	{
		glCanvas = new GLCanvas (new GLCapabilities (null));
		glCanvas.setSize (width, height);
		this.fullscreen = fullscreen;
		this.width = width;
		this.height = height;
		/*
		 * avoid screen flicker by allowing the hardware to define the
		 * repaint frequency
		 */
		glCanvas.setIgnoreRepaint (true);
		frame = new JFrame (title);
		frame.getContentPane ().setLayout (new BorderLayout ());
		frame.getContentPane ().add (glCanvas, BorderLayout.CENTER);
		/* we'd like to aim for 60fps, when possible */
		animator = new FPSAnimator (glCanvas, 60);
		// animator.setRunAsFastAsPossible (false);
	}

	/**
	 * Activate window frame and start animation loop
	 */
	public void start ()
	{
		try
		{
			Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
			/* don't show titlebar, grab bars, etc. if in fullscreen mode */
			frame.setUndecorated (fullscreen);
			frame.addWindowListener (new MyWindowAdapter ());
			if (fullscreen)
			{
				usedDevice = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
				usedDevice.setFullScreenWindow (frame);
				usedDevice.setDisplayMode (findDisplayMode (usedDevice.getDisplayModes (), width, height, usedDevice.getDisplayMode ().getBitDepth (), usedDevice.getDisplayMode ().getRefreshRate ()));
			}
			else
			{
				frame.setSize (frame.getContentPane ().getPreferredSize ());
				frame.setLocation ( (screenSize.width - frame.getWidth ()) / 2, (screenSize.height - frame.getHeight ()) / 2);
				frame.setVisible (true);
			}
			glCanvas.requestFocus ();
			animator.start ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			throw new RuntimeException (e);
		}
	}

	/**
	 * Quit the program when the window frame is closed
	 */
	public void stop ()
	{
		try
		{
			if (fullscreen)
			{
				usedDevice.setFullScreenWindow (null);
				usedDevice = null;
			}
			frame.dispose ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			throw new RuntimeException (e);
		}
		finally
		{
			System.exit (0);
		}
	}

	/**
	 * Match our desired display mode to possible modes supported by the
	 * current display device
	 * 
	 * @param displayModes
	 *            modes supported by current display device
	 * @param requestedWidth
	 *            desired width of window in pixels
	 * @param requestedHeight
	 *            desired height of window in pixels
	 * @param requestedDepth
	 *            desired color depth of window
	 * @param requestedRefreshRate
	 *            desired refresh rate of window
	 * @return closest match to requests found on the display device
	 */
	protected DisplayMode findDisplayMode (DisplayMode[] displayModes, int requestedWidth, int requestedHeight, int requestedDepth, int requestedRefreshRate)
	{
		DisplayMode displayMode = findDisplayModeInternal (displayModes, requestedWidth, requestedHeight, requestedDepth, requestedRefreshRate);
		if (displayMode == null)
		{
			displayMode = findDisplayModeInternal (displayModes, requestedWidth, requestedHeight, DONT_CARE, DONT_CARE);
		}
		if (displayMode == null)
		{
			displayMode = findDisplayModeInternal (displayModes, requestedWidth, DONT_CARE, DONT_CARE, DONT_CARE);
		}
		if (displayMode == null)
		{
			displayMode = findDisplayModeInternal (displayModes, DONT_CARE, DONT_CARE, DONT_CARE, DONT_CARE);
		}
		return displayMode;
	}

	/**
	 * findDisplayMode() helper function
	 * 
	 * @param displayModes
	 *            the display modes supported by the device in question
	 * @param requestedWidth
	 *            the desired width of our window, in pixels
	 * @param requestedHeight
	 *            the desired height of our window, in pixels
	 * @param requestedDepth
	 *            the desired color depth of our window
	 * @param requestedRefreshRate
	 *            the desired refresh rate for our window
	 * @return the closest match to our requests found on the display
	 *         device
	 * @see #findDisplayMode(DisplayMode[], int, int, int, int)
	 */
	protected DisplayMode findDisplayModeInternal (DisplayMode[] displayModes, int requestedWidth, int requestedHeight, int requestedDepth, int requestedRefreshRate)
	{
		DisplayMode displayMode = null;
		for (int i = 0; i < displayModes.length; i++ )
		{
			if ( (requestedWidth == DONT_CARE || displayModes[i].getWidth () == requestedWidth) && (requestedHeight == DONT_CARE || displayModes[i].getHeight () == requestedHeight)
				&& (requestedDepth == DONT_CARE || displayModes[i].getBitDepth () == requestedDepth) && (requestedRefreshRate == DONT_CARE || displayModes[i].getRefreshRate () == requestedRefreshRate))
			{
				displayMode = displayModes[i];
			}
		}
		return displayMode;
	}

	/**
	 * Pass GLEventListener registrations through to the canvas
	 * 
	 * @param l
	 *            the object to register as a GLEventListener
	 */
	public void addGLEventListener (GLEventListener l)
	{
		glCanvas.addGLEventListener (l);
	}

	/**
	 * Pass GLEventListener de-registrations through to the canvas
	 * 
	 * @param l
	 *            the object to de-register as a GLEventListener
	 */
	public void removeGLEventListener (GLEventListener l)
	{
		glCanvas.removeGLEventListener (l);
	}

	/**
	 * Pass KeyListener registrations through to the canvas
	 * 
	 * @param l
	 *            the object to register as a KeyListener
	 */
	public void addKeyListener (KeyListener l)
	{
		glCanvas.addKeyListener (l);
	}

	/**
	 * Pass KeyListener de-registrations through to the canvas
	 * 
	 * @param l
	 *            the object to de-register as a KeyListener
	 */
	public void removeKeyListener (KeyListener l)
	{
		glCanvas.removeKeyListener (l);
	}

	/**
	 * Pass MouseListener registrations through to the canvas
	 * 
	 * @param l
	 *            the object to register as a MouseListener
	 */
	public void addMouseListener (MouseListener l)
	{
		glCanvas.addMouseListener (l);
	}

	/**
	 * Pass MouseListener de-registrations through to the canvas
	 * 
	 * @param l
	 *            the object to de-register as a MouseListener
	 */
	public void removeMouseListener (MouseListener l)
	{
		glCanvas.removeMouseListener (l);
	}

	/**
	 * Pass MouseMotionListener registrations through to the canvas
	 * 
	 * @param l
	 *            the object to register as a MouseMotionListener
	 */
	public void addMouseMotionListener (MouseMotionListener l)
	{
		glCanvas.addMouseMotionListener (l);
	}

	/**
	 * Pass MouseMotionListener de-registrations through to the canvas
	 * 
	 * @param l
	 *            the object to de-register as a MouseMotionListener
	 */
	public void removeMouseMotionListener (MouseMotionListener l)
	{
		glCanvas.removeMouseMotionListener (l);
	}

	/**
	 * A class that will be triggered by the window closing and stop our
	 * program
	 */
	private class MyWindowAdapter extends WindowAdapter
	{
		public void windowClosing (WindowEvent e)
		{
			stop ();
		}
	}
}