package command;

import geometry.Point;

public class ModifyPointCmd implements Command{
	private Point oldState;
	private Point newState;
	private Point original=new Point();
	//model nam ne treba jer vec kad smo kreirali tacku tu imamo referencu
	//a referenca je i dalje ista, i tu tacku mjenjamo
	
	public ModifyPointCmd(Point oldState,Point newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	
	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());
	}
	
	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
		
	}

}
