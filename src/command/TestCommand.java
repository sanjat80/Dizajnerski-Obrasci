package command;
import java.awt.Color;

import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;



public class TestCommand {

	public static void main(String[] args) {
		Point p1=new Point(10,10, false, Color.red);
		Point p2=new Point(20,20, false, Color.black);
		DrawingModel model=new DrawingModel();
		
		AddShapeCmd addPointCmd=new AddShapeCmd(p1,model);
		addPointCmd.execute();
		System.out.println(model.getShapes().size());
		addPointCmd.unexecute();
		System.out.println(model.getShapes().size());
		
		ModifyPointCmd modifyPointCmd=new ModifyPointCmd(p1,p2);
		
		modifyPointCmd.execute();
		
		System.out.println(p1);
		
		
		//p1=p2;
		
		modifyPointCmd.unexecute();
		
		System.out.println(p1);
		
		Point p3=new Point(28,28);
		Point p4=new Point(50,50);
		
		Line l1=new Line(p3,p4);
		
		AddShapeCmd addLineCmd=new AddShapeCmd(l1,model);
		
		addLineCmd.execute();
		System.out.println(model.getShapes().size());
		addLineCmd.unexecute();
		System.out.println(model.getShapes().size());
		
		

	}

}
