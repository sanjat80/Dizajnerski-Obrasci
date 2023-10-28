package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {
	
	private DrawingModel model;
	private Shape shapeMoving;
	private Shape shapeFront;
	private int currentIndex;
	private int newIndex;
	
	public ToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shapeMoving = shape;
		currentIndex = model.getShapes().indexOf(shapeMoving);
		newIndex = currentIndex + 1;
		shapeFront = model.getShapes().get(newIndex);
	}

	@Override
	public void execute() {
		model.getShapes().set(newIndex, shapeMoving);
		model.getShapes().set(currentIndex, shapeFront);
	}

	@Override
	public void unexecute() {
		model.getShapes().set(currentIndex, shapeMoving);
		model.getShapes().set(newIndex, shapeFront);
	}

}
