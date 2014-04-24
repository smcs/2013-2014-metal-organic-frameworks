package objloader;

import java.awt.Paint;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineAttributes;

import org.apache.commons.collections15.Transformer;

public class EdgetoColorTransformer implements Transformer<JUNGbond, Appearance>{

	
	public EdgetoColorTransformer(){
		System.out.println("I'm Alive!");
	}
	@Override
	public Appearance transform(JUNGbond arg0) {
		System.out.println("I'm returning an appearance");
		Appearance a = new Appearance(); 
		a.setLineAttributes(new LineAttributes(10, LineAttributes.PATTERN_DASH_DOT, true)); 
		ColoringAttributes c = new ColoringAttributes(); 
		c.setColor(250,0,0); 
		a.setColoringAttributes(c); 
		return a;  

	}




}
