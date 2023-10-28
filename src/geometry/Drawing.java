package geometry;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.ArrayList;

public class Drawing extends JPanel{
	public Drawing() {
		setBackground(Color.WHITE);
	}

	public static void main(String[] args) {
		JFrame frame=new JFrame("Sanja Tica IT80/2019");
		frame.setSize(800,600); //u px, JPanel sluzi za iscrtavanje
		Drawing drawing=new Drawing(); //panel na kom iscrtavamo, jer naslije]uje JPanel
		frame.getContentPane().add(drawing); //Conten Pane je sredi[te radnog prostora
		frame.setVisible(true);
	
	}
	@Override
	public void paint(Graphics g) {
		Point p2=new Point(17,35);
		p2.draw(g);
		g.setColor(Color.green);
		Line l2=new Line(new Point(100,100),new Point(200,200));
		l2.draw(g);
		
		Circle c=new Circle(new Point(50,50),20);
		c.draw(g);
		
		Donut d=new Donut(new Point(25,22),25,20,false,Color.MAGENTA);
		d.draw(g);
		
		Donut d1=new Donut(new Point(150,150),50,55,false,Color.green,Color.yellow);
		d1.draw(g);
		
		
	}
	

}
