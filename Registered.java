package 注册及登录;

import 系统相关者.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Registered extends JFrame  implements ActionListener{

    //账户
    private JPanel p1;
    private JLabel labelID;
    private JTextArea textID;

    //密码
    private JPanel p2;
    private JLabel labelPassword;
    private JTextArea textPassword;

    //用户类型
    private JPanel p3;
    private JLabel labelType;
    private JComboBox<String> cmbType;

    //姓名
    private JPanel p4;
    private JLabel labelName;
    private JTextArea textName;

    //单位
    private JPanel p5;
    private JLabel labelUnit;
    private JTextArea textUnit;

    //电话
    private JPanel p6;
    private JLabel labelTelephone;
    private JTextArea textTelephone;

    //可借书数
    private JPanel p7;
    private JLabel labelCan_borrow;
    private JComboBox<String> cmbCan_borrow;

    //注册按钮
    private JPanel p8;
    private JButton register;

    public Registered(){
        //最外层
        this.setTitle("注册器");
        this.setLayout(new GridLayout(12,1,1,1));
        this.setSize(300,700);
        this.setLocation(500,200);
        //账户
        p1=new JPanel();
        labelID=new JLabel("账户（1~9位数字）：");
        textID=new JTextArea(1,10);
        textID.setBounds(10,10,10,10);
        p1.add(labelID);
        p1.add(textID);
        this.add(p1);

        //密码
        p2=new JPanel();
        labelPassword=new JLabel("密码（0~14位数字）：");
        textPassword=new JTextArea(1,10);
        textPassword.setBounds(10,10,10,10);
        p2.add(labelPassword);
        p2.add(textPassword);
        this.add(p2);
        //类型
        p3=new JPanel();
        labelType=new JLabel("类型");
        cmbType=new JComboBox<String>();
        cmbType.addItem("--请选择--");
        cmbType.addItem("1.普通读者");
        cmbType.addItem("2.图书管理员");
        cmbType.addItem("3.系统管理员");
        p3.add(labelType);
        p3.add(cmbType);
        this.add(p3);
        //姓名
        p4=new JPanel();
        labelName=new JLabel("姓名：");
        textName=new JTextArea(1,10);
        textName.setBounds(10,10,10,10);
        p4.add(labelName);
        p4.add(textName);
        this.add(p4);

        //单位
        p5=new JPanel();
        labelUnit=new JLabel("单位：");
        textUnit=new JTextArea(1,10);
        textUnit.setBounds(10,10,10,10);
        p5.add(labelUnit);
        p5.add(textUnit);
        this.add(p5);

        //电话
        p6=new JPanel();
        labelTelephone=new JLabel("电话(11位数字（China）)：");
        textTelephone=new JTextArea(1,10);
        textTelephone.setBounds(10,10,10,10);
        p6.add(labelTelephone);
        p6.add(textTelephone);
        this.add(p6);

        //可借书数量
        p7=new JPanel();
        labelCan_borrow=new JLabel("数量");
        cmbCan_borrow=new JComboBox<String>();
        cmbCan_borrow.addItem("--请选择--");
        cmbCan_borrow.addItem("5（学生）");
        cmbCan_borrow.addItem("10（教工）");
        p7.add(labelCan_borrow);
        p7.add(cmbCan_borrow);
        this.add(p7);

        //注册按钮
        p8=new JPanel();
        register=new JButton("注册");
        p8.add(register);
        this.add(p8);

        //绑定监听器
        register.addActionListener(this);
        this.setVisible(true);
    }

    public JTextArea getTextID() {
        return textID;
    }

    public JTextArea getTextPassword() {
        return textPassword;
    }

    public JComboBox<String> getCmbType() {
        return cmbType;
    }

    public JTextArea getTextName() {
        return textName;
    }

    public JTextArea getTextUnit() {
        return textUnit;
    }

    public JTextArea getTextTelephone() {
        return textTelephone;
    }

    public JComboBox<String> getCmbCan_borrow() {
        return cmbCan_borrow;
    }

    public JButton getRegister() {
        return register;
    }

    public static void main(String[] args) {
        Registered a= new Registered() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==this.getRegister()){
                    int id;
                    char[] password;
                    int type;
                    char[] name;
                    char[] unit;
                    long telephone;
                    int can_borrow;
                    id=Integer.parseInt(this.getTextID().getText());
                    password=this.getTextPassword().getText().toCharArray();
                    type=this.getCmbType().getSelectedIndex(); //1.普通读者 2.图书管理员 3.系统管理员
                    name=this.getTextName().getText().toCharArray();
                    unit=this.getTextUnit().getText().toCharArray();
                    telephone=Long.parseLong(this.getTextTelephone().getText());
                    can_borrow=this.getCmbCan_borrow().getSelectedIndex()*5;//1.5(学生） 2.10（教工）

                    //序列化对象写入文件
                    File file=new File("D:/用户.txt");
                    try {
                        if(file.exists()){
                            //查重
                            FileInputStream fis=new FileInputStream(file);
                            ObjectInputStream ois=new ObjectInputStream(fis);
                            List<User> users=new ArrayList<User>();
                            while (fis.available()>0){
                                User u=(User)ois.readObject();
                                users.add(u);
                            }
                            ois.close();
                            boolean isSRepeated=false;
                            for(int i=0;i<users.size();i++){
                                if(id==users.get(i).getID()) isSRepeated=true;
                            }
                            if(!isSRepeated) {
                                FileOutputStream fos = new FileOutputStream(file, true);
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                int pos = Math.toIntExact(fos.getChannel().position() - 4);          //而序列化对象在写入文件时，系统会自动添加aced 0005的一个头部，并且占4个字节,如果写入过对象，我们把前面那个4个字节去掉在写入到文件里就OK了
                                fos.getChannel().truncate(pos);
                                oos.writeObject(new User(id, password, type, name, unit, telephone, can_borrow));
                                oos.close();
                                JOptionPane.showMessageDialog(null,"注册成功！");
                            }
                            else JOptionPane.showMessageDialog(null,"账户重复请重新输入");
                        }
                        else{
                            file.createNewFile();
                            FileOutputStream fos=new FileOutputStream(file);
                            ObjectOutputStream oos= new ObjectOutputStream(fos);
                            oos.writeObject(new User(id,password,type,name,unit,telephone,can_borrow));
                            oos.close();
                            JOptionPane.showMessageDialog(null,"注册成功！");
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,"注册失败（请严格按照要求输入注册信息！");
                        ex.printStackTrace();
                    }

                }
            }
        };
    }
}
