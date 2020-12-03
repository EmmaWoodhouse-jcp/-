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
		if(e.getSource()==calc.getCheng()){  //�˷�
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
					JOptionPane.showMessageDialog(null, "�봫����������");
				}
		}
		if(e.getSource()==calc.getChu()){  //����
				calc.getH1().setText("/");
				try {
					int a=Integer.parseInt(calc.getNum1().getText());
					int b=Integer.parseInt(calc.getNum2().getText());
					String c=String.valueOf(a/b);
					calc.getNum3().setText(c);
				}
				catch(NumberFormatException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "�봫����������");
				}
				catch(ArithmeticException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "���ɳ�0");
				}
		}
		if(e.getSource()==calc.getJia()){  //�ӷ�
				calc.getH1().setText("+");
				try {
					int a=Integer.parseInt(calc.getNum1().getText());
					int b=Integer.parseInt(calc.getNum2().getText());
					String c=String.valueOf(a+b);
					calc.getNum3().setText(c);
				}
				catch(NumberFormatException exc) {
					//System.out.println(exc);
					JOptionPane.showMessageDialog(null, "�봫����������");
				}
		}
		if(e.getSource()==calc.getJian()){  //����
			calc.getH1().setText("-");
			try {
				int a=Integer.parseInt(calc.getNum1().getText());
				int b=Integer.parseInt(calc.getNum2().getText());
				String c=String.valueOf(a-b);
				calc.getNum3().setText(c);
			}
			catch(NumberFormatException exc) {
				//System.out.println(exc);
				JOptionPane.showMessageDialog(null, "�봫����������");
			}
		}	
	}
}
