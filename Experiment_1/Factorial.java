package Experiment_1;
import java.util.*;

public class Factorial {
	public static void main(String[] args) {
	    long a;
	    System.out.println("��������Ҫ�׳˵�һ������:");
	    Scanner in = new Scanner(System.in);
	    a = in.nextInt();
	    a=gui(a);
	    if(a==0) System.out.println("1");
	    else if(a<0) System.out.println("���Ǹ���");
	    else System.out.println("���Ϊ ��"+a);
		in.close();
	}
	public static long gui(long a) {
		if(a==1) return 1;
		else return a*gui(a-1);
	}
}
