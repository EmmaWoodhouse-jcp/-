package Experiment_3;

import Experiment_2.*;
import java.util.*;

public class ColoredCircleTest {
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		ColoredCircle an[]=new ColoredCircle[4];
		
		//����1
		
		for(int i=0;i<3;i++) {
			System.out.println("�����ù��췽��"+(i+1)+"�������");
			if(i==0) {
				an[0]=new ColoredCircle();
			}
			else if(i==1) {
				int r1=16;
				Point p1=new Point(10,10);
				an[1]=new ColoredCircle(p1,r1);
			}
			else if(i==2) {
				Color a2=new Color(2,2,2);
				Color b2=new Color(20,20,20);
				an[2]=new ColoredCircle(a2,b2);
			}
			else if(i==3) {
				Point p3=new Point(15,15);
				Color a3=new Color(2,2,2);
				Color b3=new Color(20,20,20);
				int r3=22;
				an[3]=new ColoredCircle(r3,p3,a3,b3);
			}
			
			System.out.println("��"+(i+1)+"��������Ķ������");
		}
		
		//����2��3
		
		System.out.println("������������ܳ�Ϊ "+an[1].perimeter());
		System.out.println("��������������Ϊ "+an[1].area());
		
		
		//����4
		
		System.out.println("����������ı߿�ĺ�����ѱ�Ϊ��");
		System.out.printf("%d %d %d",an[2].getBorderColor().getRed(),an[2].getBorderColor().getGreen(),an[2].getBorderColor().getBlue());
		System.out.println("�������޸ĺ�ĺ����:");
		an[2].getBorderColor().setRed(in.nextInt());
		an[2].getBorderColor().setGreen(in.nextInt());
		an[2].getBorderColor().setBlue(in.nextInt());
		System.out.println("�����������޸ĺ�ı߿�ĵĺ�����ѱ�Ϊ��");
		System.out.printf("%d %d %d\n",an[2].getBorderColor().getRed(),an[2].getBorderColor().getGreen(),an[2].getBorderColor().getBlue());

		//����5
		
		//��1��.��֤��ͬ��
	
		an[1].relation(an[2]);

		
		//��2��.��֤��ͬ��
		/*Point pp=new Point(10,10);
		ColoredCircle dd=new ColoredCircle(pp,16);
		an[1].relation(dd);*/
		
		
		
		
		
		
		
		
		in.close();
	}
}
