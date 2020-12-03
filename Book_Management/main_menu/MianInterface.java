package 主界面及其子菜单;
import 注册及登录.*;
import 系统相关者.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MianInterface extends JFrame implements ActionListener {
    private JButton BUserManagement;
    private JButton BBookManagement;
    private JButton BBookcirculation;
    private JButton BExit;
    private int TYPE;                           //记录用户类型既权限
    private int ID;
    public MianInterface(int TYPE,int ID){
        this.TYPE=TYPE;
        this.ID=ID;
        this.setTitle("图书管理系统");
        this.setSize(300,300);
        this.setLayout(null);
        this.setLocation(500,200);
        BUserManagement=new JButton("1. 用户管理");
        BUserManagement.setBounds(40,40,150,20);
        BBookManagement=new JButton("2. 图书管理系统");
        BBookManagement.setBounds(40,80,150,20);
        BBookcirculation=new JButton("3. 图书流通");
        BBookcirculation.setBounds(40,120,150,20);
        BExit=new JButton("4. 退出系统");
        BExit.setBounds(40,160,150,20);
        if(TYPE==1){
            BUserManagement=new JButton("1. 用户管理");
            BUserManagement.setBounds(40,40,150,20);
            BBookManagement=new JButton("2. 图书管理系统");
            BBookManagement.setBounds(40,80,150,20);
            BExit=new JButton("3. 退出系统");
            BExit.setBounds(40,120,150,20);
            this.add(BUserManagement);
            this.add(BBookManagement);
            this.add(BExit);
        }
        else if(TYPE==2){
            BUserManagement=new JButton("1. 用户管理");
            BUserManagement.setBounds(40,40,150,20);
            BBookManagement=new JButton("2. 图书管理系统");
            BBookManagement.setBounds(40,80,150,20);
            BBookcirculation=new JButton("3. 图书流通");
            BBookcirculation.setBounds(40,120,150,20);
            BExit=new JButton("4. 退出系统");
            BExit.setBounds(40,160,150,20);
            this.add(BUserManagement);
            this.add(BBookManagement);
            this.add(BBookcirculation);
            this.add(BExit);
        }
        else if(TYPE==3){
            BUserManagement=new JButton("1. 用户管理");
            BUserManagement.setBounds(40,40,150,20);
            BBookManagement=new JButton("2. 图书管理系统");
            BBookManagement.setBounds(40,80,150,20);
            BExit=new JButton("3. 退出系统");
            BExit.setBounds(40,120,150,20);
            this.add(BUserManagement);
            this.add(BBookManagement);
            this.add(BExit);
            this.add(BUserManagement);
            this.add(BBookManagement);
            this.add(BExit);
        }

        this.setVisible(true);

        BUserManagement.addActionListener(this);
        BBookManagement.addActionListener(this);
        BBookcirculation.addActionListener(this);
        BExit.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.getBUserManagement()){
            UserManagement Juser=new UserManagement(this);
            this.setVisible(false);
        }
        else if(e.getSource()==this.getBBookManagement()){
            BookManagement Jbook=new BookManagement(this);
            this.setVisible(false);
        }
        else if(e.getSource()==this.getBBookcirculation()){
            CirculationManagement Jcirculation=new CirculationManagement(this,ID);
            this.setVisible(false);
        }
        else if(e.getSource()==this.getBExit()){
            this.dispose();
        }
    }
    public JButton getBUserManagement() {
        return BUserManagement;
    }
    public JButton getBBookManagement() {
        return BBookManagement;
    }
    public JButton getBBookcirculation() {
        return BBookcirculation;
    }
    public JButton getBExit() {
        return BExit;
    }
    public int getTYPE() {
        return TYPE;
    }

}
