package geometry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.io.Serializable;
import java.awt.AlphaComposite;
import java.awt.Color;

public class Donut extends Circle implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int innerR;
	//float alpha = 0.75f;
	//int type = AlphaComposite.SRC_OVER; 
	//AlphaComposite composite = 
	//AlphaComposite.getInstance(type, alpha);
	
	public Donut () {
		
	}
	public Donut(Point center,int radius, int innerR) {
		super(center,radius);
		this.innerR=innerR;
	}
	public Donut(Point center,int radius,int innerR,boolean selected) {
		this(center,radius,innerR);
		setSelected(selected);
	}
	public Donut(Point center,int radius,int innerR,boolean selected,Color color) {
		this(center,radius,innerR,selected);
		setColor(color);
	}
	public Donut(Point center,int radius,int innerR,boolean selected,Color color,Color innerColor) {
		this(center,radius,innerR,selected,color);
		setInnerColor(innerColor);
	}
	
	public double area() {
		return super.area()+this.innerR*this.innerR*Math.PI;
	}
	public double circumference() {
		return super.circumference()+2*this.innerR*Math.PI;
	}
	
	public int CompareTo(Object o) {
		if(o instanceof Donut) {
			return (int) (this.area()-((Donut)o).area());
		}
		return 0;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D=(Graphics2D)g;
		Shape out=new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(), getCenter().getY() - this.radius, this.radius*2,this.radius*2);
		Shape inner=new Ellipse2D.Double(getCenter().getX() - this.innerR, getCenter().getY() - this.innerR, this.innerR * 2, this.innerR * 2);
		Area circle=new Area(out);
		circle.subtract(new Area(inner));
		
		g2D.setColor(getInnerColor());
		g2D.fill(circle);
		g2D.setColor(getColor());
		g2D.draw(circle);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX()-3,getCenter().getY()-3 , 6, 6);
			g.drawRect(getCenter().getX()-radius-3,getCenter().getY()-3, 6, 6);
			g.drawRect(getCenter().getX()+radius-3, getCenter().getY()-3, 6, 6);
			g.drawRect(getCenter().getX()-3, getCenter().getY()-radius-3,6,6);
			g.drawRect(getCenter().getX()-3, getCenter().getY()+radius-3, 6, 6);
			g.setColor(Color.BLACK);
		}

		
	  /*super.draw(g);
	  g.setColor(getColor());
	  g.drawOval(getCenter().getX() - this.innerR, getCenter().getY() - this.innerR, this.innerR * 2, this.innerR * 2);
	
	*/}
	/*public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.white);
		g.fillOval(getCenter().getX() - this.innerR+1, getCenter().getY() - this.innerR+1, this.innerR * 2-2, this.innerR * 2-2);
	}*/
	public boolean equals(Object o) {
		if(o instanceof Donut) {
			Donut pomocna=(Donut)o;
			if(super.equals(pomocna) && this.innerR==pomocna.getInnerR()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		return super.contains(x, y) && this.center.distance(x, y) >= innerR;
	}
	
	@Override
	public Donut clone() {
		Donut donut=new Donut();
		donut.setCenter(this.getCenter());
		donut.setRadius(this.getRadius());
		donut.setInnerR(this.getInnerR());
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		
		return donut;
	}
	
	/*public boolean contains(Point p) {
		return super.contains(p) && this.center.distance(p.getX(), p.getY()) >= innerR;
	}*/
	
	public int getInnerR() {
		return this.innerR;
	}
	public void setInnerR(int innerR) {
		this.innerR=innerR;
	}
	public String toString() {
		return "Donut , CenterX= "+this.center.getX()+" , CenterY= "+this.center.getY()+" , radius= "+this.radius + 
				" , innerRadius= " +this.innerR + " , innerColor= "+getInnerColor().getRGB()+" , outlineColor= "+getColor().getRGB();
	}

}
