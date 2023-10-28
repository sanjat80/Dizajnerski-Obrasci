package command;

import geometry.Circle;

public class ModifyCircleCmd implements Command{
	private Circle newState;
	private Circle oldState;
	private Circle original;
	
	public ModifyCircleCmd(Circle oldState,Circle newState) {
		this.newState=newState;
		this.oldState=oldState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
	
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}
}
