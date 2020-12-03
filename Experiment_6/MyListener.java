package Experiment_6;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;;

public class MyListener implements ActionListener{
	SimpleCaculator calc;
	MyListener(SimpleCaculator o) {
		calc=o;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==calc.getCheng()){  //乘法
				System.out.println("!!!");
				calc.getH1().setText("*");
				try {
					int a=Integer.parseInt(calc.getNum1().getText());
					int b=Integer.parseInt(calc.getNum2().getText());
					String c=String.valueOf(a*b);
					calc.getNum3().setText(c);
				}
				catch(NumberFormatException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "请传入俩个整数");
				}
		}
		if(e.getSource()==calc.getChu()){  //除法
				calc.getH1().setText("/");
				try {
					int a=Integer.parseInt(calc.getNum1().getText());
					int b=Integer.parseInt(calc.getNum2().getText());
					String c=String.valueOf(a/b);
					calc.getNum3().setText(c);
				}
				catch(NumberFormatException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "请传入俩个整数");
				}
				catch(ArithmeticException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "不可除0");
				}
		}
		if(e.getSource()==calc.getJia()){  //加法
				calc.getH1().setText("+");
				try {
					int a=Integer.parseInt(calc.getNum1().getText());
					int b=Integer.parseInt(calc.getNum2().getText());
					String c=String.valueOf(a+b);
					calc.getNum3().setText(c);
				}
				catch(NumberFormatException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "请传入俩个整数");
				}
		}
		if(e.getSource()==calc.getJian()){  //减法
			calc.getH1().setText("-");
			try {
				int a=Integer.parseInt(calc.getNum1().getText());
				int b=Integer.parseInt(calc.getNum2().getText());
				String c=String.valueOf(a-b);
				calc.getNum3().setText(c);
			}
			catch(NumberFormatException exc) {
				//System.out.println(exc);
				JOptionPane.showMessageDialog(null, "请传入俩个整数");
			}
		}	
	}
}
