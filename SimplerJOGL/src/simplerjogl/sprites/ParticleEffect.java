
package simplerjogl.sprites;

import javax.media.opengl.*;

/* TODO document ParticleEffect */
public class ParticleEffect extends Sprite
{
	protected Particle particles[];

	public ParticleEffect (GL2 gl, int numParticles, double left, double top, double front, double right, double bottom, double back)
	{
		super (gl);
		particles = new Particle[numParticles];
		for (int i = 0; i < particles.length; i++ )
		{
			particles[i] = new Particle (gl, left, top, front, right, bottom, back);
		}
	}

	public void move ()
	{
		for (int i = 0; i < particles.length; i++ )
		{
			particles[i].move ();
		}
	}

	public void draw (boolean wireframe)
	{
		super.draw (wireframe);
		move ();
	}
	
	public void draw() {
		draw(false);
	}

	public void spriteDraw (boolean wireframe)
	{
		for (int i = 0; i < particles.length; i++ )
		{
			particles[i].draw (wireframe);
		}
	}
	
	public void spriteDraw() {
		spriteDraw(false);
	}
}
