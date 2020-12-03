package Experiment_2;

public class Point {
	private int  x;
	private int y;
	public Point() {
		this.x=0;
		this.y=0;
	}
	public Point(int xx,int yy){
		this.x=xx;
		this.y=yy;
	}
	public int getx() {
		return this.x;
	}
	public int gety() {
		return this.y;
	}
	public void setx(int x) {
		this.x=x;
	}
	public void sety(int y) {
		this.y=y;
	}
	public double distance(Point a) {
		return (double)(Math.sqrt((this.x-a.x)*(this.x-a.x)+(this.y-a.y)*(this.y-a.y)));
	}
	/*public boolean equals(Point c) {
		if(this.x==c.x&&this.y==c.y) return true;
		else return false;
	}*/
}