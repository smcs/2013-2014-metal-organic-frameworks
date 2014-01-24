
package simplerjogl.shell;

import java.util.EventListener;

/**
 * An interface for a class that will receive ShellEvents triggered by the
 * Shell
 * 
 * @author <a href="mailto:seth@battis.net">Seth Battis</a>
 * @version 2011-02-08
 */
public interface ShellListener extends EventListener
{
	/**
	 * @param t
	 *            the shell to be registered
	 */
	/*
	 * TODO I suspect that this should really do something (like, maybe,
	 * register the shell with the ShellListener?)
	 */
	public void registerShell (Shell t);

	/**
	 * The callback method that will execute when a command is completely
	 * entered in the shell (usually signified by the user pressing enter
	 * after a syntactically valid command)
	 * 
	 * @param e
	 *            a description of the command that triggered the event
	 */
	public abstract void commandComplete (ShellEvent e);
}