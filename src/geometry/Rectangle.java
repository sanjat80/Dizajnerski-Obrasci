package geometry;

import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Color;

public class Rectangle extends ColorSurface implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point upperLeft;
	private int height;
	private int width;
	public boolean selected;
	
	public Rectangle() {
		
	}
	public Rectangle(Point upperLeft,int height,int width) {
		this.upperLeft=upperLeft;
		this.height=height;
		this.width=width;
	}
	
	public Rectangle(Point upperLeft, int height, int width,boolean selected) {
		this(upperLeft,height,width);
		setSelected(selected);
		
	}
	public Rectangle(Point upperLeft,int height,int width,boolean selected,Color color) {
		this(upperLeft,height,width,selected);
		setColor(color);
	}
	public Rectangle(Point upperLeft,int height, int width,boolean selected,Color color,Color innerColor) {
		this(upperLeft,height,width,selected,color);
		setInnerColor(innerColor);
	}

	
	public double area() {
		return this.height*this.width;
	}
	public double circumference() {
		return 2*(this.height+this.width);
	}
	
	public boolean contains(int x,int y) {
		if (this.getUpperLeft().getX() <= x &&
				this.getUpperLeft().getY() <= y &&
				x <= this.getUpperLeft().getX() + width &&
				y <= this.getUpperLeft().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	public boolean contains(Point p) {
		if (this.getUpperLeft().getX() <= p.getX() &&
				this.getUpperLeft().getY() <= p.getY() &&
				p.getX() <= this.getUpperLeft().getX() + width &&
				p.getY() <= this.getUpperLeft().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object o) {
		if(o instanceof Rectangle) {
			Rectangle pomocna=(Rectangle) o;
			if(this.upperLeft.equals(pomocna.getUpperLeft()) && this.height==pomocna.getHeight() && this.width==pomocna.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeft.moveBy(byX, byY);
	}
	
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeft.getX(), this.upperLeft.getY(), this.width, this.height);
		
		this.fill(g);
		
		//kad se selektuje pravougaonik na coskovima mu se nacrtaju kvadratici
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getUpperLeft().getX() - 3, getUpperLeft().getY() - 3, 6, 6);
			g.drawRect(getUpperLeft().getX() + getWidth() - 3, getUpperLeft().getY() - 3, 6, 6);
			g.drawRect(getUpperLeft().getX() - 3, getUpperLeft().getY() + getHeight() - 3, 6, 6);
			g.drawRect(getUpperLeft().getX() + getWidth() - 3, getUpperLeft().getY() + getHeight() - 3, 6, 6);
			g.setColor(Color.BLACK);
		}

	}
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeft.getX()+1, this.getUpperLeft().getY()+1, width-1, height-1);
	}
	
	@Override
	public Rectangle clone() {
		Rectangle rectangle=new Rectangle();
		rectangle.setUpperLeft(this.getUpperLeft());
		rectangle.height=this.getHeight();
		rectangle.width=this.getWidth();
		rectangle.setColor(this.getColor());
		rectangle.setInnerColor(this.getInnerColor());
		
		return rectangle;
	}
	public Point getUpperLeft() {
		return this.upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft=upperLeft;
	}
	
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height=height;
	}
	
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width=width;
	}
	
	public String toString() {
		return "Rectangle , upperX= " + this.getUpperLeft().getX() + " , upperY= " + this.getUpperLeft().getY()+ " , height= " +
				this.height + " , width= " + this.width+" , innerColor= "+this.getInnerColor().getRGB()+" , outlineColor= "+this.getColor().getRGB();
	}
}
