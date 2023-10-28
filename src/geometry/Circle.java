package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends ColorSurface implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point center;
	protected int radius;
	private boolean selected;
	
	public Circle() {
		
	}
	public Circle(Point center,int radius) {
		this.center=center;
		this.radius=radius;
	}
	public Circle(Point center,int radius,boolean selected)
	{
		this.center=center;
		this.radius=radius;
		this.selected=selected;
	}
	public Circle(Point center,int radius,boolean selected,Color color) {
		this(center,radius,selected);
		setColor(color);
	}
	public Circle(Point center,int radius, boolean selected,Color color,Color innerColor) {
		this(center,radius,selected,color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return this.radius*this.radius*Math.PI;
	}
	public double circumference() {
		return 2*this.radius*Math.PI;
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return (this.radius-((Circle)o).radius);
		}
		return 0;
	}
	@Override
	public void moveBy(int byX,int byY) {
		this.center.moveBy(byX, byY);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius	, this.center.getY() - radius, radius*2, radius*2); 
		this.fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}
		
	}
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius	 + 1, this.center.getY() - radius + 1, radius*2 - 2, radius*2 - 2);
	}
	@Override
	public boolean contains(int x,int y) {
		return this.center.distance(x, y) <= this.radius; 
	}
	
	public boolean contains(Point p) {
		return this.contains(p);
	}
	
	public boolean equals(Object o) {
		if(o instanceof Circle) {
			Circle pomocna=(Circle)o;
			if(this.center.equals(pomocna.getCenter()) && this.radius == pomocna.getRadius())
			{
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Circle clone() {
		Circle circle=new Circle();
		circle.setCenter(this.getCenter());
		circle.radius=this.getRadius();
		circle.setColor(this.getColor());
		circle.setInnerColor(this.getInnerColor());
		
		return circle;
	}

	public Point getCenter() {
		return this.center;
	}
	public void setCenter(Point center) {
		this.center=center;
	}
	
	public int getRadius() {
		return this.radius;
	}
	public void setRadius(int radius){
		this.radius=radius;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	public void setSelected(boolean selected) {
		this.selected=selected;
	}
	public String toString() {
		return "Circle , CenterX= " + this.center.getX() +" , CenterY= "+this.center.getY()+" , radius= " + this.radius +
				" , innerColor= "+getInnerColor().getRGB()+" , outlineColor= "+getColor().getRGB();
	}

}
