package Experiment_6;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class SimpleCaculator {
	private JFrame f;
	private JTextArea num1,num2,num3;
	private JLabel h1,h2;
	private JLabel d;
	private JButton cheng,chu,jia,jian;
	private JButton n;
	public void go() {
		f=new JFrame("¼òÒ×¼ÆËãÆ÷");
		f.setLayout(new FlowLayout());
		num1=new JTextArea(1,10);
		num2=new JTextArea(1,10);
		num3=new JTextArea(1,10);
		h1=new JLabel();
		h2=new JLabel("=");
		cheng=new JButton("*");
		chu=new JButton("/");
		jia=new JButton("+");
		jian=new JButton("-");
		num1.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		num2.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		num3.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
		f.add(num1);
		f.add(h1);
		f.add(num2);
		f.add(h2);
		f.add(num3);
		f.add(cheng);
		f.add(chu);
		f.add(jia);
		f.add(jian);
		
		
		MyListener ml=new MyListener(this);
		cheng.addActionListener(ml);
		chu.addActionListener(ml);
		jia.addActionListener(ml);
		jian.addActionListener(ml);
		
		
		f.setSize(300,200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public JFrame getF() {
		return f;
	}
	public JTextArea getNum1() {
		return num1;
	}
	public JTextArea getNum2() {
		return num2;
	}
	public JTextArea getNum3() {
		return num3;
	}
	public JLabel getH1() {
		return h1;
	}
	public JLabel getH2() {
		return h2;
	}
	public JLabel getD() {
		return d;
	}
	public JButton getCheng() {
		return cheng;
	}
	public JButton getChu() {
		return chu;
	}
	public JButton getJia() {
		return jia;
	}
	public JButton getJian() {
		return jian;
	}
	public JButton getN() {
		return n;
	}
	
	
}
