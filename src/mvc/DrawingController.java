package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;


import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.ModifyCircleCmd;
import command.ModifyDonutCmd;
import command.ModifyHexagonCmd;
import command.ModifyLineCmd;
import command.ModifyPointCmd;
import command.ModifyRectangleCmd;
import command.RemoveShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import observer.Observable;
import observer.Observer;

public class DrawingController implements Observable{
	private DrawingModel model;
	private DrawingFrame frame;
	private ArrayList<Command> undoCommands = new ArrayList<>();
	private ArrayList<Command> redoCommands = new ArrayList<>();
	private DefaultListModel<String> commandLoggerList;
	private ArrayList<Observer> observers=new ArrayList<Observer>();
	private List<String> logsText;
	private int nextNumber;
	
	public List<String> getLog() {
		return logsText;
	}


	public void setLog(List<String> logsText) {
		this.logsText = logsText;
	}


	public int getNextNumber() {
		return nextNumber;
	}


	public void setNextNumber(int nextNumber) {
		this.nextNumber = nextNumber;
	}


	public DrawingController(DrawingModel model,DrawingFrame frame)
	{
		this.model=model;
		this.frame=frame;
		this.commandLoggerList=frame.getDefaultList();
	}
	
	
	public void deselectShapes() {
		ArrayList<Shape> deselectedShapes=new ArrayList<Shape>();
		for(Shape shape: model.getSelectedShapes()) {
			if(shape.isSelected()) {
				deselectedShapes.add(shape);
			}
		}
		for(Shape shape: deselectedShapes) {
			shape.setSelected(false);
			model.removeSelected(shape);
			commandLoggerList.addElement("Deselected" + shape.toString());
		}
	}
	
	protected void thisMouseClicked(MouseEvent me) {
		Shape addShape;
		Point click = new Point(me.getX(), me.getY());
		boolean ctrl=false;
		boolean contains=false;
		

		if (frame.getTglbtnSelect().isSelected()) {
			Collections.reverse(this.model.getShapes());
			Iterator<Shape> it = model.getShapes().iterator();
			if((me.getModifiers() & ActionEvent.CTRL_MASK)==ActionEvent.CTRL_MASK) {
				ctrl=true;
			}
			
			while (it.hasNext()) {
				Shape shape = it.next();
				if(shape.contains(click.getX(), click.getY()) && !shape.isSelected()) {
					if(ctrl == false) {
						if(model.getSelectedShapes() != null) {
							deselectShapes();
						}
						commandLoggerList.addElement("Selected" + shape.toString());
						model.addSelect(shape);
					}
					else {
						commandLoggerList.addElement("Selected" + shape.toString());
						model.addSelect(shape);
					}
					contains=true;
					break;
				}
			}
			if(contains == false && model.getSelectedShape()!=null) {
				deselectShapes();
			}
			
			Collections.reverse(this.model.getShapes());
			notifyObservers();

		} else if (frame.getTglbtnPoint().isSelected()) {

			addShape = new Point(click.getX(), click.getY(), false, frame.getOutlineColor());
			AddShapeCmd pointCommand=new AddShapeCmd(addShape,model);
			undoCommands.add(pointCommand);
			redoCommands.clear();
			pointCommand.execute();
			commandLoggerList.addElement("Add" + addShape.toString());

		} else if (frame.getTglbtnLine().isSelected()) {
			
			if (model.getStartPoint() == null)
				model.setStartPoint(click);
			else {
				addShape=new Line(model.getStartPoint(),new Point(me.getX(),me.getY()),false, frame.getOutlineColor());
				AddShapeCmd lineCommand=new AddShapeCmd(addShape,model);
				undoCommands.add(lineCommand);
				redoCommands.clear();
				lineCommand.execute();
				commandLoggerList.addElement("Add" + addShape.toString());
				model.setStartPoint(null);
			}

		} else if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlg = new DlgCircle();

			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.getBtnOutlineColor().setBackground(frame.getOutlineColor());
			dlg.getBtnOutlineColor().setVisible(false);
			dlg.getBtnInnerColor().setBackground(frame.getInnerColor());
			dlg.getBtnInnerColor().setVisible(false);
			dlg.setVisible(true);
			
			if (dlg.isConfirm()) {
				addShape = dlg.getCircle();
				AddShapeCmd circleCommand=new AddShapeCmd(addShape,model);
				undoCommands.add(circleCommand);
				redoCommands.clear();
				circleCommand.execute();
				commandLoggerList.addElement("Add"+addShape.toString());
			}

		} else if (frame.getTglbtnDonut().isSelected()) {
			
			DlgDonut dlg = new DlgDonut();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(click.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(click.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.getBtnOutlineColor().setBackground(frame.getOutlineColor());
			dlg.getBtnOutlineColor().setVisible(false);
			dlg.getBtnInnerColor().setBackground(frame.getInnerColor());
			dlg.getBtnInnerColor().setVisible(false);
			dlg.setVisible(true);

			if (dlg.isConfirm()) {
				addShape=dlg.getDonut();
				AddShapeCmd donutCommand=new AddShapeCmd(addShape,model);
				undoCommands.add(donutCommand);
				redoCommands.clear();
				donutCommand.execute();
				commandLoggerList.addElement("Add"+addShape.toString());
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {
			
	
			DlgRectangle dlg = new DlgRectangle();
			dlg.setModal(true);
			dlg.getTxtX().setText("" + Integer.toString(me.getX()));
			dlg.getTxtX().setEditable(false);
			dlg.getTxtY().setText("" + Integer.toString(me.getY()));
			dlg.getTxtY().setEditable(false);
			dlg.getBtnOutlineColor().setBackground(frame.getOutlineColor());
			dlg.getBtnOutlineColor().setVisible(false);
			dlg.getBtnInnerColor().setBackground(frame.getInnerColor());
			dlg.getBtnInnerColor().setVisible(false);
			dlg.setVisible(true);

			if (dlg.isConfirm()) {
				addShape=dlg.getRectangle();
				AddShapeCmd rectangleCommand= new AddShapeCmd(addShape,model);
				undoCommands.add(rectangleCommand);
				redoCommands.clear();
				rectangleCommand.execute();
				commandLoggerList.addElement("Add"+addShape.toString());
			}
		}
		else if(frame.getTglbtnHexagon().isSelected()) {
			
			DlgHexagon dlg=new DlgHexagon();
			dlg.setModal(true);
			dlg.getTxtX().setText(""+Integer.toString(me.getX()));
			dlg.getTxtY().setText(""+Integer.toString(me.getY()));
			dlg.getBtnOutlineColor().setBackground(frame.getOutlineColor());
			dlg.getBtnOutlineColor().setVisible(false);
			dlg.getBtnInnerColor().setBackground(frame.getInnerColor());
			dlg.getBtnInnerColor().setVisible(false);
			dlg.setVisible(true);
			
			
			if(dlg.isConfirm()) {
				addShape=dlg.getHexagon();
				AddShapeCmd hexagonCommand=new AddShapeCmd(addShape,model);
				undoCommands.add(hexagonCommand);
				redoCommands.clear();
				hexagonCommand.execute();
				commandLoggerList.addElement("Add"+addShape.toString());
			}
		}
		
		frame.repaint();

	}
	protected void delete() {
		if (model.getSelectedShapes() != null) {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Warning message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				Command removeShapeCmd = null;
				for(Shape shape : model.getSelectedShapes()) {
					removeShapeCmd=new RemoveShapeCmd(shape,model);
					undoCommands.add(removeShapeCmd);
					redoCommands.clear();
					removeShapeCmd.execute();
					commandLoggerList.addElement("Delete" + shape.toString());
				}
				model.getSelectedShapes().clear();
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have to select some shape to delete!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		
		notifyObservers();
		frame.repaint();
	}
	
	public void buttonModify(ActionEvent e)
	{
		if (model.getSelectedShape() != null) {
			modify();
		} else {
			JOptionPane.showMessageDialog(null, "Please, select a shape to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getTglbtnSelect().setSelected(true);
		}
		frame.getTglbtnSelect().setSelected(false);

	}

	protected void modify() {

		Shape selectedShape = model.getSelectedShape();

		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				Point p = (Point) selectedShape;
				DlgPoint dialog = new DlgPoint();

				dialog.getTxtX().setText("" + Integer.toString(p.getX()));
				dialog.getTxtY().setText("" + Integer.toString(p.getY()));
				dialog.getBtnColor().setBackground(p.getColor());
				dialog.setVisible(true);

				if (dialog.isConfirm()) {
					ModifyPointCmd pointCommand=new ModifyPointCmd((Point)selectedShape,dialog.getP());
					undoCommands.add(pointCommand);
					redoCommands.clear();
					pointCommand.execute();
					commandLoggerList.addElement("Modify" + dialog.getP().toString());
					frame.repaint();
				}

			} else if (selectedShape instanceof Donut) {

				Donut donut = (Donut) selectedShape;
				DlgDonut dialog = new DlgDonut();

				dialog.getTxtX().setText("" + Integer.toString(donut.getCenter().getX()));
				dialog.getTxtY().setText("" + Integer.toString(donut.getCenter().getY()));
				dialog.getTxtR().setText("" + Integer.toString(donut.getRadius()));
				dialog.getTxtInnerR().setText("" + Integer.toString(donut.getInnerR()));
				dialog.getBtnInnerColor().setBackground(donut.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(donut.getColor());
				dialog.setModal(true);
				dialog.setVisible(true);

				if (dialog.isConfirm()) {
					ModifyDonutCmd donutCommand=new ModifyDonutCmd((Donut)selectedShape,dialog.getDonut());
					undoCommands.add(donutCommand);
					redoCommands.clear();
					donutCommand.execute();
					commandLoggerList.addElement("Modify" + dialog.getDonut().toString());
					frame.repaint();
				}
			} else if (selectedShape instanceof Circle && (selectedShape instanceof Donut) == false) {

				Circle circle = (Circle) selectedShape;
				DlgCircle dialog = new DlgCircle();

				dialog.getTxtX().setText("" + Integer.toString(circle.getCenter().getX()));
				dialog.getTxtY().setText("" + Integer.toString(circle.getCenter().getY()));
				dialog.getTxtR().setText("" + Integer.toString(circle.getRadius()));
				dialog.getBtnInnerColor().setBackground(circle.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(circle.getColor());

				dialog.setVisible(true);
				dialog.setModal(true);

				if (dialog.isConfirm()) {
					ModifyCircleCmd circleCommand=new ModifyCircleCmd((Circle)selectedShape,dialog.getCircle());
					undoCommands.add(circleCommand);
					redoCommands.clear();
					circleCommand.execute();
					commandLoggerList.addElement("Modify" + dialog.getCircle().toString());
					frame.repaint();
				}

			} else if (selectedShape instanceof Line) {

				Line line = (Line) selectedShape;
				DlgLine dialog = new DlgLine();

				dialog.getTxtSPX().setText("" + Integer.toString(line.getStartPoint().getX()));
				dialog.getTxtSPY().setText("" + Integer.toString(line.getStartPoint().getY()));
				dialog.getTxtEPX().setText("" + Integer.toString(line.getEndPoint().getX()));
				dialog.getTxtEPY().setText("" + Integer.toString(line.getEndPoint().getY()));
				dialog.getBtnOutlineColor().setBackground(line.getColor());

				dialog.setVisible(true);

				if (dialog.isConfirm()) {
					ModifyLineCmd lineCommand=new ModifyLineCmd((Line)selectedShape,dialog.getLine());
					undoCommands.add(lineCommand);
					redoCommands.clear();
					lineCommand.execute();
					commandLoggerList.addElement("Modify" + dialog.getLine().toString());
					frame.repaint();
				}

			} else if (selectedShape instanceof Rectangle) {

				Rectangle rect = (Rectangle) selectedShape;
				DlgRectangle dialog = new DlgRectangle();

				dialog.getTxtX().setText("" + Integer.toString(rect.getUpperLeft().getX()));
				dialog.getTxtY().setText("" + Integer.toString(rect.getUpperLeft().getY()));
				dialog.getTxtHeight().setText("" + Integer.toString(rect.getHeight()));
				dialog.getTxtWidth().setText("" + Integer.toString(rect.getWidth()));
				dialog.getBtnInnerColor().setBackground(rect.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(rect.getColor());
				dialog.setModal(true);
				dialog.setVisible(true);

				if (dialog.isConfirm()) {
					ModifyRectangleCmd rectangleCommand=new ModifyRectangleCmd((Rectangle)selectedShape,dialog.getRectangle());
					undoCommands.add(rectangleCommand);
					redoCommands.clear();
					rectangleCommand.execute();
					commandLoggerList.addElement("Modify" + dialog.getRectangle().toString());
					frame.repaint();
				}
			}
			else if (selectedShape instanceof HexagonAdapter) {
				HexagonAdapter hex=(HexagonAdapter)selectedShape;
				DlgHexagon dialog=new DlgHexagon();
				dialog.getTxtX().setText(""+Integer.toString(hex.getX()));
				dialog.getTxtY().setText(""+Integer.toString(hex.getY()));
				dialog.getTxtR().setText(""+Integer.toString(hex.getR()));
				dialog.getBtnInnerColor().setBackground(hex.getInnerColor());
				dialog.getBtnOutlineColor().setBackground(hex.getColor());
				dialog.setModal(true);
				dialog.setVisible(true);
				
				if(dialog.isConfirm()) {
					ModifyHexagonCmd hexagonCommand=new ModifyHexagonCmd((HexagonAdapter)selectedShape,dialog.getHexagon());
					undoCommands.add(hexagonCommand);
					redoCommands.clear();
					hexagonCommand.execute();
					commandLoggerList.addElement("Modify" + dialog.getHexagon().toString());
					frame.repaint();
				}
			}

		}
	}
	
	public void toFront() {	
		if(model.getSelectedShapes().size() != 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Select one shape");
		} else {
			Shape selectedShape = model.getSelectedShape();
			if(selectedShape != null && model.getShapes().indexOf(selectedShape) != model.getShapes().size() - 1) {
				ToFrontCmd toFrontCmd = new ToFrontCmd(model, selectedShape);
				undoCommands.add(toFrontCmd);
				redoCommands.clear();
				toFrontCmd.execute();
				commandLoggerList.addElement("ToFront" + selectedShape.toString());
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Shape is already in front");
			}
		}
		frame.repaint();
	}
	
	public void toBack() {
		if(model.getSelectedShapes().size() != 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Select one shape");
		} else {
			Shape selectedShape = model.getSelectedShape();
			if(selectedShape != null && model.getShapes().indexOf(selectedShape) != 0) {
				ToBackCmd toBackCmd = new ToBackCmd(model, selectedShape);
				undoCommands.add(toBackCmd);
				redoCommands.clear();
				toBackCmd.execute();
				commandLoggerList.addElement("ToBack" + selectedShape.toString());
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Shape is already in back");
			}
		}
		frame.repaint();
	}
	
	public void bringToFront() {
		if(model.getSelectedShapes().size() != 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Select one shape");
		} else {
			Shape selectedShape = model.getSelectedShape();
			if(selectedShape != null && model.getShapes().indexOf(selectedShape) != model.getShapes().size() - 1) {
				BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, selectedShape);
				undoCommands.add(bringToFrontCmd);
				redoCommands.clear();
				bringToFrontCmd.execute();
				commandLoggerList.addElement("BringToFront" + selectedShape.toString());
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Shape is already in front");
			}
		}
		frame.repaint();
	}
	
	public void bringToBack() {
		if(model.getSelectedShapes().size() != 1) {
			JOptionPane.showMessageDialog(new JFrame(), "Select one shape");
		} else {
			Shape selectedShape = model.getSelectedShape();
			if(selectedShape != null && model.getShapes().indexOf(selectedShape) != 0) {
				BringToBackCmd bringToBackCmd = new BringToBackCmd(model, selectedShape);
				undoCommands.add(bringToBackCmd);
				redoCommands.clear();
				bringToBackCmd.execute();
				commandLoggerList.addElement("BringToBack" + selectedShape.toString());
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Shape is already in back");
			}
		}
		frame.repaint();
	}
	
	public void undo() {
		if(undoCommands.isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Dont have any undo command");
		} else {
			if(model.getSelectedShapes() != null) {
				deselectShapes();
			}
			Command command = undoCommands.get(undoCommands.size() - 1);
			undoCommands.remove(command);
			redoCommands.add(command);
			command.unexecute();
			frame.repaint();
			notifyObservers();
			commandLoggerList.addElement("Undo" + command);
		}
	}
	
	public void redo() {
		if(redoCommands.isEmpty()) {
			JOptionPane.showMessageDialog(new JFrame(), "Dont have any redo command");
		} else {
			Command command = redoCommands.get(redoCommands.size() - 1);
			redoCommands.remove(command);
			undoCommands.add(command);
			command.execute();
			frame.repaint();
			notifyObservers();
			commandLoggerList.addElement("Redo" + command);
		}
	}
	
	public void next() {
		if(logsText == null || nextNumber == logsText.size() || logsText.size() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Dont have any line in text log");
		} else {
			String line = logsText.get(nextNumber++);
			String[] loggerSplit = line.split(" ");
			String commandShapeName = loggerSplit[0];
			Shape shape = null;
			
			if(commandShapeName.contains("Undo")) {
					undo();
			} else if(commandShapeName.contains("Redo")) {
					redo();
			} else if(commandShapeName.contains("Add")) {
				if(commandShapeName.contains("Point"))
					shape = textCreatePoint(loggerSplit);
				else if(commandShapeName.contains("Line"))
					shape = textCreateLine(loggerSplit);
				else if(commandShapeName.contains("Circle"))
					shape = textCreateCircle(loggerSplit);
				else if(commandShapeName.contains("Rectangle"))
					shape = textCreateRectangle(loggerSplit);
				else if(commandShapeName.contains("Donut"))
					shape = textCreateDonut(loggerSplit);
				else if(commandShapeName.contains("Hexagon"))
					shape  = textCreateHexagon(loggerSplit);
				
				AddShapeCmd addShape = new AddShapeCmd(shape, model);
				addShape.execute();
				undoCommands.add(addShape);
				frame.repaint();
				commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("Selected")) {
				if(commandShapeName.contains("Point"))
					shape = textCreatePoint(loggerSplit);
				else if(commandShapeName.contains("Line"))
					shape = textCreateLine(loggerSplit);
				else if(commandShapeName.contains("Circle"))
					shape = textCreateCircle(loggerSplit);
				else if(commandShapeName.contains("Rectangle"))
					shape = textCreateRectangle(loggerSplit);
				else if(commandShapeName.contains("Donut"))
					shape = textCreateDonut(loggerSplit);
				else if(commandShapeName.contains("Hexagon"))
					shape  = textCreateHexagon(loggerSplit);
				
				model.addSelect(model.getShapes().get(model.getShapes().indexOf(shape)));
				frame.repaint();
				commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("Deselected")) {
				if(commandShapeName.contains("Point"))
					shape = textCreatePoint(loggerSplit);
				else if(commandShapeName.contains("Line"))
					shape = textCreateLine(loggerSplit);
				else if(commandShapeName.contains("Circle"))
					shape = textCreateCircle(loggerSplit);
				else if(commandShapeName.contains("Rectangle"))
					shape = textCreateRectangle(loggerSplit);
				else if(commandShapeName.contains("Donut"))
					shape = textCreateDonut(loggerSplit);
				else if(commandShapeName.contains("Hexagon"))
					shape  = textCreateHexagon(loggerSplit);
				
				//model.getSelectedShape().setSelected(false);
				model.getShapes().get(model.getShapes().indexOf(shape)).setSelected(false);
				model.removeSelected(model.getSelectedShape());
				frame.repaint();
				commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("Modify")) {
				if(commandShapeName.contains("Point")) {
					shape = textCreatePoint(loggerSplit);
					ModifyPointCmd modifyPointCmd = new ModifyPointCmd((Point)model.getSelectedShape(), (Point)shape);
					modifyPointCmd.execute();
					undoCommands.add(modifyPointCmd);
					frame.repaint();
				} else if(commandShapeName.contains("Line")) {
					shape = textCreateLine(loggerSplit);
					ModifyLineCmd modifyLineCmd = new ModifyLineCmd((Line)model.getSelectedShape(), (Line)shape);
					modifyLineCmd.execute();
					undoCommands.add(modifyLineCmd);
					frame.repaint();
				} else if(commandShapeName.contains("Circle")) {
					shape = textCreateCircle(loggerSplit);
					ModifyCircleCmd modifyCircleCmd = new ModifyCircleCmd((Circle)model.getSelectedShape(), (Circle)shape);
					modifyCircleCmd.execute();
					undoCommands.add(modifyCircleCmd);
					frame.repaint();
				} else if(commandShapeName.contains("Rectangle")) {
					shape = textCreateRectangle(loggerSplit);
					ModifyRectangleCmd modifyRectangleCmd = new ModifyRectangleCmd((Rectangle)model.getSelectedShape(), (Rectangle)shape);
					modifyRectangleCmd.execute();
					undoCommands.add(modifyRectangleCmd);
					frame.repaint();
				} else if(commandShapeName.contains("Donut")) {
					shape = textCreateDonut(loggerSplit);
					ModifyDonutCmd modifyDonutCmd = new  ModifyDonutCmd((Donut)model.getSelectedShape(), (Donut)shape);
					modifyDonutCmd.execute();
					undoCommands.add(modifyDonutCmd);
					frame.repaint();
				} else if(commandShapeName.contains("Hexagon")) {
					shape  = textCreateHexagon(loggerSplit);
					ModifyHexagonCmd modifyHexagonCmd = new ModifyHexagonCmd((HexagonAdapter)model.getSelectedShape(), (HexagonAdapter)shape);
					modifyHexagonCmd.execute();
					undoCommands.add(modifyHexagonCmd);
					frame.repaint();
				}
				commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("Delete")) {
				if(commandShapeName.contains("Point"))
					shape = textCreatePoint(loggerSplit);
				else if(commandShapeName.contains("Line"))
					shape = textCreateLine(loggerSplit);
				else if(commandShapeName.contains("Circle"))
					shape = textCreateCircle(loggerSplit);
				else if(commandShapeName.contains("Rectangle"))
					shape = textCreateRectangle(loggerSplit);
				else if(commandShapeName.contains("Donut"))
					shape = textCreateDonut(loggerSplit);
				else if(commandShapeName.contains("Hexagon"))
					shape  = textCreateHexagon(loggerSplit);
				
				RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model.getShapes().get(model.getShapes().indexOf(shape)), model);
				removeShapeCmd.execute();
				undoCommands.add(removeShapeCmd);
				frame.repaint();
				commandLoggerList.addElement(line);
				model.getSelectedShapes().remove(shape);
			} else if(commandShapeName.contains("BringToFront")) {
				BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, model.getSelectedShape());
				bringToFrontCmd.execute();
				undoCommands.add(bringToFrontCmd);
				frame.repaint();
				commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("BringToBack")) {
				BringToBackCmd bringToBackCmd = new BringToBackCmd(model, model.getSelectedShape());
				bringToBackCmd.execute();
				undoCommands.add(bringToBackCmd);
				frame.repaint();commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("ToFront")) {
				ToFrontCmd toFrontCmd = new ToFrontCmd(model, model.getSelectedShape());
				toFrontCmd.execute();
				undoCommands.add(toFrontCmd);
				frame.repaint();
				commandLoggerList.addElement(line);
			} else if(commandShapeName.contains("ToBack")) {
				ToBackCmd toBackCmd = new ToBackCmd(model, model.getSelectedShape());
				toBackCmd.execute();
				undoCommands.add(toBackCmd);
				frame.repaint();
				commandLoggerList.addElement(line);
			}
		}
	}
	
	public void save(String filePath) {
		if(!filePath.isEmpty()) {
			FileContext context = new FileContext(new FileHandler());
			context.saveData(filePath, this);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Please insert file path");
		}
	}
	
	public void load(String filePath) {
		if(!filePath.isEmpty()) {
			FileContext context = new FileContext(new FileHandler());
			context.loadData(filePath, this);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Please insert file path");
		}
	}
	
	private Shape textCreatePoint(String[] loggerSplit) {
		int x = Integer.parseInt(loggerSplit[3]);
		int y = Integer.parseInt(loggerSplit[6]);
		Color color = new Color(Integer.parseInt(loggerSplit[9]));
		Point point = new Point(x, y, false, color);
		return point;
	}
	
	private Shape textCreateLine(String[] loggerSplit) {
		int startX = Integer.parseInt(loggerSplit[3]);
		int startY = Integer.parseInt(loggerSplit[6]);
		int endX = Integer.parseInt(loggerSplit[9]);
		int endY = Integer.parseInt(loggerSplit[12]);
		Color color = new Color(Integer.parseInt(loggerSplit[15]));
		Line line = new Line(new Point(startX, startY), new Point(endX, endY), false, color);
		return line;
	}
	
	private Shape textCreateRectangle(String[] loggerSplit) {
		int upperX = Integer.parseInt(loggerSplit[3]);
		int upperY = Integer.parseInt(loggerSplit[6]);
		int height = Integer.parseInt(loggerSplit[9]);
		int width = Integer.parseInt(loggerSplit[12]);
		Color innerColor = new Color(Integer.parseInt(loggerSplit[15]));
		Color outlineColor = new Color(Integer.parseInt(loggerSplit[18]));
		Rectangle rectangle = new Rectangle(new Point(upperX, upperY), height, width, false, outlineColor, innerColor);
		return rectangle;
	}
	
	private Shape textCreateCircle(String[] loggerSplit) {
		int centerX = Integer.parseInt(loggerSplit[3]);
		int centerY = Integer.parseInt(loggerSplit[6]);
		int radius = Integer.parseInt(loggerSplit[9]);
		Color innerColor = new Color(Integer.parseInt(loggerSplit[12]));
		Color outlineColor = new Color(Integer.parseInt(loggerSplit[15]));
		Circle circle = new Circle(new Point(centerX, centerY), radius, false, outlineColor, innerColor);
		return circle;
	}
	
	private Shape textCreateDonut(String[] loggerSplit) {
		int centerX = Integer.parseInt(loggerSplit[3]);
		int centerY = Integer.parseInt(loggerSplit[6]);
		int radius = Integer.parseInt(loggerSplit[9]);
		int innerRadius = Integer.parseInt(loggerSplit[12]);
		Color innerColor = new Color(Integer.parseInt(loggerSplit[15]));
		Color outlineColor = new Color(Integer.parseInt(loggerSplit[18]));
		Donut donut = new Donut(new Point(centerX, centerY), radius, innerRadius, false, outlineColor, innerColor);
		return donut;
	}
	
	private Shape textCreateHexagon(String[] loggerSplit) {
		int centerX = Integer.parseInt(loggerSplit[3]);
		int centerY = Integer.parseInt(loggerSplit[6]);
		int radius = Integer.parseInt(loggerSplit[9]);
		Color innerColor = new Color(Integer.parseInt(loggerSplit[12]));
		Color outlineColor = new Color(Integer.parseInt(loggerSplit[15]));
		HexagonAdapter hexagon = new HexagonAdapter(centerX, centerY, radius);
		hexagon.setInnerColor(innerColor);
		hexagon.setColor(outlineColor);
		return hexagon;
	}


	public DrawingModel getModel() {
		return model;
	}


	public void setModel(DrawingModel model) {
		this.model = model;
	}


	public DrawingFrame getFrame() {
		return frame;
	}


	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}


	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}


	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}


	@Override
	public void notifyObservers() {
		boolean modifyEnabled=false;
		boolean deleteEnabled=false;
		if(model.getSelectedShapes() == null) {
			modifyEnabled=false;
			deleteEnabled=false;
		} else if(model.getSelectedShapes().size()==0) {
			modifyEnabled=false;
			deleteEnabled=false;
		} else if(model.getSelectedShapes().size()==1) {
			modifyEnabled=true;
			deleteEnabled=true;
		} else if(model.getSelectedShapes().size()>1){
			modifyEnabled=false;
			deleteEnabled=true;
		}
		for(Observer observer: observers) {
			observer.update(modifyEnabled, deleteEnabled);
		}
	}

	
	
	

}
