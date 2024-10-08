package command;

import adapter.HexagonAdapter;

public class ModifyHexagonCmd implements Command{
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original=new HexagonAdapter();
	
	public ModifyHexagonCmd(HexagonAdapter oldState,HexagonAdapter newState)
	{
		this.oldState=oldState;
		this.newState=newState;
	}

	@Override
	public void execute() {
		original=oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setR(original.getR());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}
}
