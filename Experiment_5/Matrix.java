package Experiment_5;

import java.util.Arrays;

public class Matrix {
	private double data[][];
	private int Rows;
	private int Cols;
	Matrix(){
		this.Rows=3;
		this.Cols=3;
		data=new double[Rows][Cols];
	}
	Matrix(int rows,int cols) throws IllegalArgumentException{
		if(rows<=0||cols<=0) throw new IllegalArgumentException();
		this.Rows=rows;
		this.Cols=cols;
		data= new double[rows][cols];
	}
	Matrix(double data[][]) throws IllegalArgumentException{
		if(data==null) throw new IllegalArgumentException();
		this.data=data;
	}
	public double getData(int row,int cols) throws IllegalIndexException{
		if(row>this.getRows()||row<0||cols>this.getCols()||cols<0) throw new IllegalIndexException();
		return this.data[row][cols];
	}
	public void setData(int row,int cols,double value) throws IllegalIndexException{
		if(row>this.getRows()||row<0||cols>this.getCols()||cols<0) throw new IllegalIndexException();
		this.data[row][cols]=value;
	}
	public int getRows() {
		return this.Rows;
	}
	public int getCols() {
		return this.Cols;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Matrix) {
			Matrix w=(Matrix)obj;
			if(this.Rows==w.getRows()&&this.Cols==w.getCols()) {
				boolean f=true;
				for(int i=0;i<this.Rows;i++) {
					for(int j=0;j<this.Cols;j++) {
						try {
							if(this.data[i][j]!=w.getData(i, j)) {
								f=false;
								break;
							}
						} catch (IllegalIndexException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(f==false) break;
				}
				if(f) return true; //完全相同
				else return false;	//有元素值不同
			}//行数列数不相同
			else return false;
		}//不是同一类
		else return false;
	}
	@Override
	public String toString() {
		return "该矩阵的行数列数分别为"+this.getRows()+" "+this.Cols;
	}
	public static Matrix multiply(Matrix a,Matrix b) throws MatrixMultiplicationException, IllegalArgumentException, IllegalIndexException{
		if(a==null||b==null) throw new MatrixMultiplicationException();
		if(a.getCols()!=b.getRows()) throw new MatrixMultiplicationException();
		Matrix c=new Matrix(a.getRows(),b.getCols());
		double sum=0;
		for(int i=0;i<a.getRows();i++) {
			for(int j=0;j<b.getCols();j++) {
				sum=0;
				for(int k=0;k<a.getCols();k++) {
					sum+=a.getData(i, k)*b.getData(k, j);
				}
				//System.out.println(sum);
				c.setData(i, j, sum);
			}
		}
		return c;
	}
	
}
