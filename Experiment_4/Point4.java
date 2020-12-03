package Experiment_4;
import Experiment_2.Point;

public class Point4 extends Point{
	Point4(){
		super(0,0);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point4) {
			Point4 w=(Point4)obj;
			if(this.getx()==w.getx()&&this.gety()==w.gety()) return true;
			else return false;
		}
		else{
			return false;
		}
	}
	@Override
	public String toString() {
		return "Point4"+"("+this.getx()+"£¬"+this.gety()+")";
	}
}