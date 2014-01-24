
package simplerjogl.shell;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * An engine class to drive a (primitive) terminal shell alongside the
 * OpenGL drawing context
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2011-02-08
 */
public class Shell implements KeyListener
{
	/**
	 * The shell prompt string
	 */
	protected final static String PROMPT = "> ";

	/**
	 * The character that separates words (think real hard before changing
	 * this to something other than a space)
	 * 
	 * TODO a strong argument could be made that this should be an array of
	 * possible non-word characters
	 * 
	 * TODO an even stronger argument could be made that built-in String
	 * processor commands should be used rather than redundantly defining
	 * word or non-word characters
	 */
	protected final static char WORD_BREAK = ' ';

	/**
	 * The character that separates lines
	 * 
	 * TODO this maybe should be done using either an array of options or
	 * built-in String processor commands?
	 */
	protected final static char LINE_BREAK = '\n';

	/**
	 * A list of objects to receive updates from the Shell when events
	 * occur
	 */
	protected EventListenerList shellListeners;

	/**
	 * Where we'll be entering our text
	 */
	protected JTextArea shell;

	/**
	 * The pane that contains the shell (so that we can scroll back and see
	 * what we wrote)
	 */
	protected JScrollPane pane;

	/**
	 * A string for parsing shell input
	 */
	protected String wordBuffer;

	/**
	 * A ShellEvent in which to collect the data for the next event
	 */
	protected ShellEvent eventBuffer;

	/**
	 * Has a command been received?
	 */
	private boolean haveCommand;

	/**
	 * Are we currently reading input from the shell?
	 */
	private boolean isMidRead;

	/**
	 * Are we in the middle of printing a line?
	 */
	private boolean isMidPrint;

	/**
	 * Default constructor
	 * 
	 * @param frame
	 *            The frame in which the shell's scrolling pane will be
	 *            added (on the east -- i.e. right -- side)
	 */
	/*
	 * TODO it should be configurable where the shell appears
	 */
	public Shell (JFrame frame)
	{
		shellListeners = new EventListenerList ();
		shell = new JTextArea ();
		shell.setColumns (20);
		pane = new JScrollPane (shell);
		frame.getContentPane ().add (pane, BorderLayout.EAST);
		isMidRead = false;
		isMidPrint = false;
	}

	/* TODO Finish documenting Shell */

	public void addShellListener (ShellListener t)
	{
		shellListeners.add (ShellListener.class, t);
		t.registerShell (this);
	}

	public void removeShellListener (ShellListener t)
	{
		shellListeners.remove (ShellListener.class, t);
	}

	public void keyPressed (KeyEvent e)
	{}

	public void keyReleased (KeyEvent e)
	{}

	public void keyTyped (KeyEvent e)
	{
		if (isMidRead)
		{
			if ( (e.getKeyChar () == WORD_BREAK) || (e.getKeyChar () == LINE_BREAK))
			{
				if (haveCommand)
				{
					eventBuffer.addParameter (wordBuffer);
				}
				else
				{
					eventBuffer.setCommand (wordBuffer);
					haveCommand = true;
				}
				print (String.valueOf (e.getKeyChar ()));
				clearWordBuffer ();
				if (e.getKeyChar () == LINE_BREAK)
				{
					isMidRead = false;
					ShellListener[] listeners = shellListeners.getListeners (ShellListener.class);
					for (ShellListener l : listeners)
					{
						l.commandComplete (new ShellEvent (eventBuffer));
					}
				}
			}
			else
			{
				wordBuffer = wordBuffer + e.getKeyChar ();
				print (String.valueOf (e.getKeyChar ()));
			}
		}
	}

	public void print (String s)
	{
		shell.setText (shell.getText ().concat (s));
		isMidPrint = !s.endsWith ("\n");
	}

	public void println (String s)
	{
		print (s + "\n");
	}

	public void readln ()
	{
		if (isMidPrint)
		{
			println ("\n");
		}
		print (PROMPT);
		clearEventBuffer ();
		isMidRead = true;
	}

	public boolean isMidRead ()
	{
		return isMidRead;
	}

	protected void clearWordBuffer ()
	{
		wordBuffer = new String ();
	}

	protected void clearEventBuffer ()
	{
		eventBuffer = new ShellEvent (this);
		clearWordBuffer ();
		haveCommand = false;
	}
}