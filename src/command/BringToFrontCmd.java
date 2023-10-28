package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	
	private Shape shapeMoving;
	private DrawingModel model;
	private int currentIndex;
	private int lastIndex;
	
	public BringToFrontCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shapeMoving = shape;
		currentIndex = model.getShapes().indexOf(shapeMoving);
		lastIndex = model.getShapes().size() - 1;
	}

	@Override
	public void execute() {
		model.getShapes().add(model.getShapes().remove(currentIndex));
	}

	@Override
	public void unexecute() {
		model.getShapes().add(currentIndex, model.getShapes().remove(lastIndex));
	}

}
