package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {
	
	private DrawingModel model;
	private Shape shapeMoving;
	private Shape shapeBack;
	private int currentIndex;
	private int newIndex;
	
	public ToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shapeMoving = shape;
		currentIndex = model.getShapes().indexOf(shapeMoving);
		newIndex = currentIndex - 1;
		shapeBack = model.getShapes().get(newIndex);
	}

	@Override
	public void execute() {
		model.getShapes().set(newIndex, shapeMoving);
		model.getShapes().set(currentIndex, shapeBack);
	}

	@Override
	public void unexecute() {
		model.getShapes().set(currentIndex, shapeMoving);
		model.getShapes().set(newIndex, shapeBack);
	}

}
