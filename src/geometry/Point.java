package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public Point() {
		
	}
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public Point(int x,int y, boolean selected) {
		this(x,y);
		setSelected(selected);
	}
	public Point(int x,int y,boolean selected,Color color) {
		this(x,y,selected);
		setColor(color);
	}
	public Point (int x,int y,Color color) {
		this(x,y);
		setColor(color);
	}
	
	public double distance(int x,int y) {
		double dx=this.x-x;
		double dy=this.y-y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point p=new Point(0,0);
			return (int) (this.distance(p.getX(), p.getY()) - ((Point) o).distance(p.getX(), p.getY()));	
		}
		return 0;
	}
	@Override
	public void moveBy(int byX, int byY) {
		this.x=this.x + byX;
		this.y=this.y+byY;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);
			g.setColor(Color.BLACK);
		}
		
	}
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.getX() && this.y == pomocna.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x,int y){
		return this.distance(x, y) <=3;
	}
	
	@Override
	public Point clone() {
		Point point=new Point();
		point.x=this.getX();
		point.y=this.getY();
		point.setColor(this.getColor());
		
		return point;
	}
	
	public int getX()
	{
		return this.x;
	}
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y=y;
	}
	public String toString() {
		return "Point , x= " + this.x + " , y= " + this.y +" , color= "+getColor().getRGB();
	}
	


}
