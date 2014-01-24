
package simplerjogl.shell;

import java.awt.*;

import javax.swing.JOptionPane;

import simplerjogl.Frame;

/**
 * A frame that has a shell pane
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2011-02-08
 */
public class ShellFrame extends Frame
{
	/**
	 * The shell engine
	 */
	protected Shell shell;

	/**
	 * @return the shell engine object
	 */
	public Shell getShell ()
	{
		return shell;
	}

	/**
	 * Statically create an instance of a GLFrame
	 * 
	 * @param title
	 *            the title of the window
	 * @param fullscreen
	 *            true to ask to run fullscreen, false to just run windowed
	 */
	public static ShellFrame createFrame (String title, boolean fullscreen)
	{
		/*
		 * get some information about the physical display system that
		 * we're using
		 */
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		if (fullscreen)
		{
			/* ask if we should run fullscreen */
			if (device.isFullScreenSupported ())
			{
				/*
				 * throw up a generic dialog box to ask if we should run
				 * windowed or fullscreen
				 */
				int selectedOption = JOptionPane.showOptionDialog (null, "How would you like to run this program?", title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Fullscreen", "Windowed" }, "Windowed");
				fullscreen = (selectedOption == 0);
			}
		}
		/* create the desired window and return a reference to it */
		return new ShellFrame (title, DEFAULT_WIDTH, DEFAULT_HEIGHT, fullscreen);
	}

	/**
	 * @param title
	 *            The title displayed on the window
	 * @param width
	 *            The width of the window in pixels
	 * @param height
	 *            The height of the window in pixels
	 * @param fullscreen
	 *            Is the "window" an actual window or fullscreen?
	 */
	protected ShellFrame (String title, int width, int height, boolean fullscreen)
	{
		super (title, width, height, fullscreen);
		shell = new Shell (frame);
		addKeyListener (shell);
	}

	/**
	 * Add an object to the list of ShellListeners to receive events
	 * triggered by the Shell
	 * 
	 * @param t
	 *            the object that will be listening
	 */
	public void addShellListener (ShellListener t)
	{
		shell.addShellListener (t);
	}

	/**
	 * Remove an object from the list of ShellListeners that receive events
	 * triggered by the Shell
	 * 
	 * @param t
	 *            the object to be removed from the list
	 */
	public void removeShellListener (ShellListener t)
	{
		shell.removeShellListener (t);
	}
}
