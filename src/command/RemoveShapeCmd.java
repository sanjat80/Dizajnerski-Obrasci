package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command{
	private Shape shape;
	private DrawingModel model;
	private int oldIndex;
	
	public RemoveShapeCmd(Shape shape,DrawingModel model) {
		this.shape=shape;
		this.model=model;
	}

	@Override
	public void execute() {
		oldIndex = model.getShapes().indexOf(shape);
		shape.setSelected(false);
		model.remove(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().add(oldIndex, shape);
	}
	

}
