
package simplerjogl.shell;

import java.util.*;

/**
 * An class to describe events triggered by the Shell
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2011-02-08
 */
@SuppressWarnings ("serial")
public class ShellEvent extends EventObject
{
	/**
	 * The command verb (a single word)
	 */
	protected String command;

	/**
	 * The command parameters (nouns, each a single word)
	 */
	/*
	 * TODO would an ArrayList or Vector be more efficient than an array?
	 */
	protected String[] parameters;

	/**
	 * Default constructor
	 * 
	 * @param source
	 *            The Shell which triggered the event
	 */
	public ShellEvent (Shell source)
	{
		super (source);
		command = new String ();
		parameters = new String[0];
	}

	/**
	 * Copy constructor
	 * 
	 * @param other
	 *            Another initialized ShellEvent
	 */
	public ShellEvent (ShellEvent other)
	{
		super (other);
		setCommand (other.command);
		setParameters (other.parameters);
	}

	/**
	 * @return the command verb (a single word)
	 */
	public String getCommand ()
	{
		return new String (command);
	}

	/**
	 * @return an array of nouns (parameters to the command verb, each a
	 *         single word)
	 */
	public String[] getParameters ()
	{
		String[] a = new String[parameters.length];
		System.arraycopy (parameters, 0, a, 0, parameters.length);
		return a;
	}

	/**
	 * @return the command verb and parameter nouns as entered in the shell
	 */
	public String getString ()
	{
		String s = new String (command);
		for (String p : parameters)
		{
			s += " " + p;
		}
		return s;
	}

	/**
	 * @return the command verb and parameter nouns formatted as a method
	 *         call (which is, in essence, what they really are)
	 */
	public String toString ()
	{
		String s = new String (command + " (");
		for (String p : parameters)
		{
			s = s + p + ", ";
		}
		if (parameters.length > 0)
		{
			s = s.substring (0, s.length () - 2);
		}
		s = s.concat (")");
		return s;
	}

	/**
	 * Change the command verb
	 * 
	 * @param s
	 *            the new command verb (should be a single word)
	 */
	/*
	 * TODO should confirm that command verb is a single word
	 */
	public void setCommand (String s)
	{
		command = new String (s);
	}

	/**
	 * Change the list of parameter nouns (should each be a single word)
	 * 
	 * @param a
	 *            the array of nouns
	 */
	/*
	 * TODO should confirm that each noun is a single word
	 */
	public void setParameters (String[] a)
	{
		parameters = new String[a.length];
		System.arraycopy (a, 0, parameters, 0, a.length);
	}

	/**
	 * Add an additional parameter noun to the end of the parameter list
	 * (useful when constructing the ShellEvent)
	 * 
	 * @param s
	 *            the additional parameter noun
	 */
	/*
	 * TODO confirm that the parameter noun is a single word
	 */
	public void addParameter (String s)
	{
		String[] newParam = new String[parameters.length + 1];
		System.arraycopy (parameters, 0, newParam, 0, parameters.length);
		newParam[parameters.length] = new String (s);
		parameters = newParam;
	}
}
