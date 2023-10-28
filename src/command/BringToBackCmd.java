package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	
	private Shape shapeMoving;
	private DrawingModel model;
	private int currentIndex;
	private int firstIndex;
	
	public BringToBackCmd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shapeMoving = shape;
		currentIndex = model.getShapes().indexOf(shapeMoving);
		firstIndex = 0;
	}

	@Override
	public void execute() {
		model.getShapes().add(firstIndex, model.getShapes().remove(currentIndex));
	}

	@Override
	public void unexecute() {
		model.getShapes().add(currentIndex, model.getShapes().remove(firstIndex));
	}

}
