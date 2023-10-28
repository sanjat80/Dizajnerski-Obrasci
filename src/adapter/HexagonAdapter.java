package adapter;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import geometry.Shape;
import hexagon.Hexagon;


public class HexagonAdapter extends Shape implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon=hexagon;
	}
	
	public HexagonAdapter(int x,int y,int radius) {
		hexagon=new Hexagon(x,y,radius);
		
	}
	@Override
	public boolean contains(int x,int y) {
		return hexagon.doesContain(x, y);
	}
	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.hexagon.getX() - 3, hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() - getR() - 3, hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() + getR() - 3, hexagon.getY() - 3, 6, 6);
			//g.drawRect(this.hexagon.getX() - 3, hexagon.getY() - getR() - 3, 6, 6);
			//g.drawRect(this.hexagon.getX() - 3, hexagon.getY() + getR() - 3, 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	public boolean equals(Object object) {
		if (object instanceof HexagonAdapter) {
			HexagonAdapter hexagon = (HexagonAdapter) object;
			if (this.getX() == hexagon.getX() && this.getY() == hexagon.getY() && this.getR() == hexagon.getR()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(byX);
		hexagon.setY(byY);
	}
	public int compareTo(Shape newHexagon) {
		if(newHexagon instanceof HexagonAdapter) {
			return hexagon.getR()-((HexagonAdapter)newHexagon).getR();
		}
		return 0;	
	}
	
	@Override
	public HexagonAdapter clone() {
		HexagonAdapter hexagon=new HexagonAdapter(this.getX(),this.getY(),this.getR());
		/*hexagon.setX(this.getX());
		hexagon.setY(this.getY());
		hexagon.setR(this.getR());*/
		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());
		
		
		return hexagon;
		
	}
	
	public int getR() {
		return hexagon.getR();
	}
	public int getX() {
		return hexagon.getX();
	}
	public int getY() {
		return hexagon.getY();
	}
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	
	public void setR(int radius) {
		hexagon.setR(radius);
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	public void setInnerColor(Color areaColor) {
		hexagon.setAreaColor(areaColor);
	}
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}
	
	public String toString() {
		return "Hexagon , centerX= "+getX()+" , centerY= "+getY()+" , radius= " +getR()+" , innerColor= "+getInnerColor().getRGB()+
				" , outlineColor= "+getColor().getRGB();
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
