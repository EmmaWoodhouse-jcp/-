package Experiment_5;
import java.util.*;

public class Test {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n1,m1;
		int n2,m2;
		System.out.println("输入矩阵a的行列：");
		n1=in.nextInt();	m1=in.nextInt();
		Matrix a = null;
		try {
			a = new Matrix(n1,m1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("输入矩阵b的行列：");
		n2=in.nextInt();	m2=in.nextInt();
		Matrix b = null;
		try {
			b = new Matrix(n2,m2);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("输入矩阵a的具体元素");
		for(int i=0;i<a.getRows();i++) {
			for(int j=0;j<a.getCols();j++) {
				try {
					a.setData(i, j, in.nextDouble());
				} catch (IllegalIndexException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("输入矩阵b的具体元素");
		for(int i=0;i<b.getRows();i++) {
			for(int j=0;j<b.getCols();j++) {
				try {
					b.setData(i, j, in.nextDouble());
				} catch (IllegalIndexException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(a.equals(b)) System.out.println("YES");
		else System.out.println("NO");
		
		System.out.println(a.toString());
		
		Matrix c=new Matrix();
		try {
			c=Matrix.multiply(a, b);
		} catch (MatrixMultiplicationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalIndexException e) {
			e.printStackTrace();
		}
		for(int i=0;i<c.getRows();i++) {
			for(int j=0;j<c.getCols();j++) {
				try {
					System.out.print(c.getData(i, j)+" ");
				} catch (IllegalIndexException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		
		
		in.close();
	}
}
