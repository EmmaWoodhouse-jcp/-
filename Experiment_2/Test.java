package Experiment_2;
import java.util.*;

public class Test {
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		Date date=new Date();
		System.out.println("����ʱ�䣺");
		System.out.println(date.toString());
		int ans;
		System.out.println("�������һ���������Ƚϵ�Բ��x��y,r");
		int x,y,r;
		x=in.nextInt();
		y=in.nextInt();
		r=in.nextInt();
		Point w=new Point(x,y);
		//System.out.println(w.getx()+" "+w.gety());
		//System.out.println("�������޸ĺ������X Y:");
		//w.setx(in.nextInt());
		//w.sety(in.nextInt());
		Circle an[]=new Circle[5];
		an[0]=new Circle(w,r);
		
		//System.out.println("��һ��Բ���ܳ�����ֱ�Ϊ��");
		//System.out.println(an[0].perimeter());
		//System.out.println(an[0].area());
		
		for(int i=1;i<5;i++) {
			System.out.println("������"+i+"�������͵�һ��Բ�Ƚϵ�Բ����Ϣ��x��y,r");
			x=in.nextInt();
			y=in.nextInt();
			r=in.nextInt();

			an[i]=new Circle(x,y,r);

			System.out.println("������Բ�Ĺ�ϵ�ǣ�");
			
			ans=an[0].relation(an[i]);
			if(ans==0) System.out.println("ͬһ��Բ");
			else if(ans==1)System.out.println("ͬ��Բ");
			else if(ans==2)System.out.println("�ཻ��Բ");
			else if(ans==3) System.out.println("�����Բ");
			else if(ans==4)System.out.println("������Բ");
		}
		
		
		
		
		
		in.close();
	}
}
