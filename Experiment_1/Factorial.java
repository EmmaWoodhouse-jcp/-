package Experiment_1;
import java.util.*;

public class Factorial {
	public static void main(String[] args) {
	    long a;
	    System.out.println("请输入需要阶乘的一个整数:");
	    Scanner in = new Scanner(System.in);
	    a = in.nextInt();
	    a=gui(a);
	    if(a==0) System.out.println("1");
	    else if(a<0) System.out.println("这是负数");
	    else System.out.println("结果为 ："+a);
		in.close();
	}
	public static long gui(long a) {
		if(a==1) return 1;
		else return a*gui(a-1);
	}
}
