package geometry;

import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Color;

//ovde si modifikovala private Point startPoint i endPoint tako
//da si ih odmah inicijalizovala


public class Line extends Shape implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point startPoint=new Point();
	private Point endPoint=new Point();
	private boolean selected;
	
	public Line() {
		
	}
	public Line(Point startPoint,Point endPoint) {
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	public Line(Point startPoint, Point endPoint,boolean selected) {
		this(startPoint,endPoint);
		this.selected=selected;
	}
	public Line(Point startPoint,Point endPoint,boolean selected,Color color) {
		this(startPoint,endPoint,selected);
		setColor(color);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line) {
			return (int) (this.length()-((Line)o).length());
		}
		return 0;
	}
	@Override
	public void moveBy(int byX,int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}
	@Override //preklapanje jer sve nadredjene klase imaju te metode?
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(LineMiddleX(), LineMiddleY()-3, 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	public int LineMiddleX() {
		int lineMiddleX=((this.startPoint.getX()+this.endPoint.getX())/2);
		return lineMiddleX;
	}
	public int LineMiddleY() {
		int lineMiddleY=((this.startPoint.getY()+this.endPoint.getY())/2);
		return lineMiddleY;
	}
	
	/*public Point LineMiddle() {
		int moveByMiddleX=(this.startPoint.getX()+this.startPoint.getY())/2;
		int moveByMiddleY=(this.endPoint.getX()+this.endPoint.getY())/2;
		Point p=new Point(moveByMiddleX,moveByMiddleY);
		return p;
	}*/
		
	public double length() {
		return this.startPoint.distance(endPoint.getX(),endPoint.getY());
	}
	
	public boolean contains(int x,int y) {
		if(this.startPoint.distance(x, y) + endPoint.distance(x, y) - this.length() <=0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Object o) {
		if(o instanceof Line) {
			Line pomocna=(Line)o;
			if(this.startPoint.equals(pomocna.getStartPoint()) && this.endPoint.equals(pomocna.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Line clone() {
		Line line=new Line();
		line.setStartPoint(this.getStartPoint());
		line.setEndPoint(this.getEndPoint());
		line.setColor(this.getColor());
		
		return line;
	}
	
	public Point getStartPoint() {
		return this.startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint=startPoint;
	}
	
	public Point getEndPoint() {
		return this.endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint=endPoint;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	public void setSelected(boolean selected) {
		this.selected=selected;
	}
	
	public String toString() {
		return "Line , startX= " + this.startPoint.getX() + " , startY= " + this.startPoint.getY() + " , endX= " + 
				this.endPoint.getX() + " , endY= " + this.endPoint.getY() + " , color= "+getColor().getRGB();
	}
}
