package command;

import geometry.Rectangle;

public class ModifyRectangleCmd implements Command{
	private Rectangle newState;
	private Rectangle oldState;
	private Rectangle original;
	
	
	public ModifyRectangleCmd(Rectangle oldState,Rectangle newState) {
		this.newState=newState;
		this.oldState=oldState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.setUpperLeft(newState.getUpperLeft());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}
	
	@Override
	public void unexecute() {
		oldState.setUpperLeft(original.getUpperLeft());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}
}

