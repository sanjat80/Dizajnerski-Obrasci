package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;

public class DrawingModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes=new ArrayList<Shape>();
	private Point startPoint;
	private Shape selectedShape;
	
	
	public void add(Shape s)
	{
		shapes.add(s);
	}
	public void remove(Shape s)
	{
		shapes.remove(s);
	}
	public void addSelect(Shape s) {
		selectedShape=s;
		s.setSelected(true);
		selectedShapes.add(s);
	}
	public void removeSelected(Shape s) {
		selectedShape=null;
		selectedShapes.remove(s);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Shape getSelectedShape() {
		return selectedShape;
	}
	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}
	
	public ArrayList<Shape> getSelectedShapes(){
		return this.selectedShapes;
	}
	
	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes=selectedShapes;
	}
	
	


}
