package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class ColorSurface extends Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color innerColor=Color.WHITE;
	
	public abstract double area();
	public abstract void fill(Graphics g);
	
	
	public Color getInnerColor() {
		return this.innerColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor=innerColor;
	}
	

}
