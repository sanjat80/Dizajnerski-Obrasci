package command;

import geometry.Donut;

public class ModifyDonutCmd implements Command{
	private Donut newState;
	private Donut oldState;
	private Donut original;
	
	public ModifyDonutCmd(Donut oldState,Donut newState) {
		this.newState=newState;
		this.oldState=oldState;
	}
	
	@Override
	public void execute() {
		original=oldState.clone();
	
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerR(newState.getInnerR());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}
	
	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setInnerR(original.getInnerR());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}
}
