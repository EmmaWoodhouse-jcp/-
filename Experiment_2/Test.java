package Experiment_2;
import java.util.*;

public class Test {
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		Date date=new Date();
		System.out.println("北京时间：");
		System.out.println(date.toString());
		int ans;
		System.out.println("请输入第一个用来做比较的圆：x，y,r");
		int x,y,r;
		x=in.nextInt();
		y=in.nextInt();
		r=in.nextInt();
		Point w=new Point(x,y);
		//System.out.println(w.getx()+" "+w.gety());
		//System.out.println("请输入修改后的坐标X Y:");
		//w.setx(in.nextInt());
		//w.sety(in.nextInt());
		Circle an[]=new Circle[5];
		an[0]=new Circle(w,r);
		
		//System.out.println("第一个圆的周长面积分别为：");
		//System.out.println(an[0].perimeter());
		//System.out.println(an[0].area());
		
		for(int i=1;i<5;i++) {
			System.out.println("请输入"+i+"个用来和第一个圆比较的圆的信息：x，y,r");
			x=in.nextInt();
			y=in.nextInt();
			r=in.nextInt();

			an[i]=new Circle(x,y,r);

			System.out.println("这俩个圆的关系是：");
			
			ans=an[0].relation(an[i]);
			if(ans==0) System.out.println("同一个圆");
			else if(ans==1)System.out.println("同心圆");
			else if(ans==2)System.out.println("相交的圆");
			else if(ans==3) System.out.println("分离的圆");
			else if(ans==4)System.out.println("包含的圆");
		}
		
		
		
		
		
		in.close();
	}
}
