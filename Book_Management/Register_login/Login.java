package 注册及登录;

import 系统相关者.User;
import 主界面及其子菜单.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Login extends JFrame implements ActionListener {
    //实现登录。

    //账户
    private JPanel Pid;
    private JLabel Lid;
    private JTextArea Tid;

    //密码
    private JPanel Ppassword;
    private JLabel Lpassword;
    private JTextArea Tpassword;

    //登录
    private JPanel PBlogin;
    private JButton Blogin;

    public Login() {
        this.setTitle("图书管理系统——Login");
        this.setLayout(new GridLayout(3,1,1,1));
        this.setSize(400,300);
        this.setLocation(500,200);
        //添加账户组件
        Pid=new JPanel();
        Lid=new JLabel("账号：");
        Tid=new JTextArea(1,14);
        Tid.setBounds(10,10,10,10);
        Pid.add(Lid);
        Pid.add(Tid);
        this.add(Pid);
        //添加密码组件
        Ppassword=new JPanel();
        Lpassword=new JLabel("密码：");
        Tpassword=new JTextArea(1,14);
        Tpassword.setBounds(10,10,10,10);
        Ppassword.add(Lpassword);
        Ppassword.add(Tpassword);
        this.add(Ppassword);
        //添加按钮组件
        PBlogin=new JPanel();
        Blogin=new JButton("登录");
        PBlogin.add(Blogin);
        this.add(PBlogin);
        //Login按钮绑定监听器
        Blogin.addActionListener(this);
        //显示登录界面
        this.setVisible(true);
    }
    //获取List<User>
    List<User> getUsers() throws IOException, ClassNotFoundException {
        File file=new File("D:/用户.txt");
        List<User> users=new ArrayList<User>();
        if(file.exists()){
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            while (fis.available()>0){
                User u=(User)ois.readObject();
                users.add(u);
            }
            ois.close();
        }
        else JOptionPane.showMessageDialog(null,"密码错误！");
        return users;
    }

    public JButton getBlogin() {
        return Blogin;
    }

    public JTextArea getTid() {
        return Tid;
    }

    public JTextArea getTpassword() {
        return Tpassword;
    }

    public static void main(String[] args) {
        Login L= new Login() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==this.getBlogin()){
                    try {
                        //准备工作：把需要遍历的和文本框中的内容获取
                        String textAreaID=this.getTid().getText();
                        String textAreaPassword=this.getTpassword().getText();

                        List<User> users=getUsers();
                        User u;
                        boolean isSeeked=false;
                        for(int i=0;i<users.size();i++){
                            u=users.get(i);
                            if(textAreaID.equals(String.valueOf(u.getID()))){       //账户正确
                                isSeeked=true;
                                if(textAreaPassword.equals(String.valueOf(u.getPassword()))){
                                    MianInterface mianInterface=new MianInterface(u.getType(),u.getID());
                                    this.dispose();
                                }
                                else JOptionPane.showMessageDialog(null,"密码错误！");
                            }
                        }
                        if(!isSeeked) JOptionPane.showMessageDialog(null,"账号不存在");
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
    }

}
