package Experiment_3;

public class Color {
	private int red;
	private int green;
	private int blue;
	Color(){}
	Color(int r,int g,int b){
		if(r>=0&&r<=255) {
			this.red=r;
		}
		else r=-1;
		if(g>=0&&g<=255) {
			this.green=g;
		}
		else g=-1;
		if(b>=0&&b<=255) {
			this.blue=b;
		}
		else b=-1;
	}
	public void setRed(int v) {
		this.red=v;
	}
	public void setGreen(int v) {
		this.green=v;
	}
	public void setBlue(int v) {
		this.blue=v;
	}
	public int getRed() {
		return this.red;
	}
	public int getGreen() {
		return this.green;
	}
	public int getBlue() {
		return this.blue;
	}
	public boolean equals(Color c) {
		if(this.getBlue()==c.getBlue()&&this.getGreen()==c.getGreen()&&this.getRed()==c.getRed()) {
			return true;
		}
		else return false;
	}
}