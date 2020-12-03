package Experiment_2;

public class Circle {
    private Point center;
    private int radius;
    
	public Circle(){}
	public Circle(Point c,int r){
		this.center=new Point();      //调用Point的第一个构造方法				
		this.center=c;
		this.radius=r;
	}
	public Circle(int xx,int yy,int r){
		this.center=new Point(xx,yy);	//调用point的第二个构造方法
		this.radius=r;
	}
	public Point getcenter() {
		return this.center;
	}
	public int getr() {
		return this.radius;
	}
	public void setr(int w) {
		this.radius=w;
	}
	public double perimeter() {
		return Math.PI*2*radius;
	}
	public double area() {
		return (Math.PI*(radius*radius));
	}
	public int relation(Circle c) {
		if(this.center.equals(c.center)&&c.radius==this.radius) return 0;
		else if(this.center.equals(c.center)&&c.radius!=this.radius) return 1;
		else if(this.center.distance(c.center)<this.radius+c.radius) {
			if(this.radius>c.radius) {
				if(this.radius-c.radius<=this.center.distance(c.center)) return 2;
				else return 4;
			}
			else{
				if(c.radius-this.radius<=this.center.distance(c.center)) return 2;
				else return 4;
			}
		}
		else return 3;
	}
	/*public boolean equals(Circle c) {
		if(this.center.equals(c.center)) {
			if(this.radius==c.radius) return true;
			else return false;
		}
		else return false;
	}*/
}