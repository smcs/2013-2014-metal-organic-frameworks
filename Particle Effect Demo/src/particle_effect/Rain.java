
package particle_effect;

import javax.media.opengl.*;

import simplerjogl.sprites.*;

public class Rain extends ParticleEffect
{
	public Rain (GL2 gl)
	{
		super (gl, 1000, -10, 10, 10, 10, -10, -10);
		for (int i = 0; i < particles.length; i++ )
		{
			particles[i] = new RainDrop (gl, -10, 10, 10, 10, -10, -10);
			particles[i].setY (Math.random () * 20 - 10);
		}
	}
}
