package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable, Moveable, Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean selected;
	private Color color=Color.black; //i boolean i color su obiljezja koje imaju sve klase, zbog toga staju u apstraktnu klasu
	
	public abstract boolean contains(int x,int y);
	public abstract void draw(Graphics g);
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected=selected;
	}
	
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	
	

}
