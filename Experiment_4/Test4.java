package Experiment_4;

public class Test4 {
	public static void main(String args[]) {
		Point4 a=new Point4();
		Point4 b=new Point4();
		System.out.println(a.equals(b));
		System.out.println(a.toString());
		
		Circle4 c=new Circle4();
		Circle4 d=new Circle4();
		c.setr(5);
		System.out.println(c.equals(d));
		System.out.println(c.toString());
	}
}
