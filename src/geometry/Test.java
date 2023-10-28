package geometry;

public class Test {

	public static void main(String[] args) {
		Point p1=new Point();
		p1.setX(12);
		p1.setY(17);
		System.out.printf("Koordinate x i y tacke p su: " +p1.getX() + ", " + p1.getY());
		
		Rectangle r1=new Rectangle(new Point(7,3),10,10);
		System.out.println(r1.toString());
		
		
		

	}

}