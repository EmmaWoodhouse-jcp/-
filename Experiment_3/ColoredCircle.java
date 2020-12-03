package Experiment_3;
import Experiment_2.Point;
import Experiment_2.Circle;

public class ColoredCircle extends Circle {
	private Color borderColor;
	private Color centerColor;
	public ColoredCircle(){
		super(0,0,0);
		borderColor=new Color(0,0,0);
		centerColor=new Color(0,0,0);
	}
	public ColoredCircle(Point center,int radius) {
		super(center,radius);
		borderColor=new Color(0,0,0);
		centerColor=new Color(0,0,0);
	}
	public ColoredCircle(Color centerColor,Color borderColor){
		super(0,0,0);
		this.centerColor=centerColor;
		this.borderColor=borderColor;
	}
	public ColoredCircle(int radius,Point center,Color centerColor,Color borderColor){
		super(center,radius);
		this.centerColor=centerColor;
		this.borderColor=borderColor;
	}
	public void setCenterColor(Color c) {
		this.centerColor=c;
	}
	public void setBorderColor(Color c){
		this.borderColor=c;
	}
	public Color getCenterColor() {
		return this.centerColor;
	}
	public Color getBorderColor() {
		return this.borderColor;
	}
	@Override
	public int relation(Circle c) {
		if(c instanceof ColoredCircle) {
			if(super.relation(c)==0) {
				ColoredCircle w=(ColoredCircle)c;
				if(this.borderColor.equals(w.getBorderColor())&&this.centerColor.equals(w.getCenterColor())) {
					System.out.println("相同");
					return 1;
				}
				else{
					System.out.println("不同");
					return 2;
				}
			}
			else{
				System.out.println("不同");
				return 2;
			}
		}
		else {
			System.out.println("前者为有颜色的圆，后者为无颜色的圆，无法比较");
			return 0;
		}
	}
}
