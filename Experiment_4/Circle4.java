package Experiment_4;

import Experiment_2.Circle;

public class Circle4 extends Circle{
	Circle4(){
		super(0,0,1);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Circle4) {
			Circle4 w=(Circle4)obj;
			if(this.getcenter().getx()==w.getcenter().getx()&&this.getcenter().gety()==w.getcenter().gety()&&this.getr()==w.getr()) {
				return true;
			}
			else return false;
		}	
		else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Circle4 []"+"("+this.getcenter().getx()+"£¬"+this.getcenter().gety()+")"+"r="+this.getr();
	}
	
}
