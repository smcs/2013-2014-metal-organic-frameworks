
package shell;

import simplerjogl.*;
import simplerjogl.shell.*;

public class JOGLApp
{
	public static void main (String[] args)
	{
		Renderer renderer = new JOGLRenderer ();
		ShellFrame frame = ShellFrame.createFrame ("SimplerJOGL Shell Demo", false);
		frame.addGLEventListener (renderer);
		frame.addShellListener (renderer);
		frame.start ();
	}
}
