package 主界面及其子菜单;

import 系统相关者.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement extends JFrame implements ActionListener {
    private JButton Binput;
    private JButton Bupdate;
    private JButton Bdelete;
    private JButton Bquery;
    private JButton BchangePassword;
    private JButton Bexit;
    private JButton BqueryAll;
    private int TYPE;
    private MianInterface mm;                   //指向主界面的引用为操控主界面的显示

    //主面板增删
    public UserManagement(MianInterface mm){
        this.TYPE=mm.getTYPE();
        this.mm=mm;
        this.setTitle("用户管理");
        this.setSize(300,400);
        this.setLayout(null);
        this.setLocation(500,200);
        if(TYPE==3) {
            Binput = new JButton("1. 用户信息录入");
            Binput.setBounds(40, 40, 150, 20);
            Bupdate = new JButton("2. 用户信息修改");
            Bupdate.setBounds(40, 80, 150, 20);
            Bdelete = new JButton("3. 用户信息删除");
            Bdelete.setBounds(40, 120, 150, 20);
            Bquery = new JButton("4. 用户信息查询");
            Bquery.setBounds(40, 160, 150, 20);
            BchangePassword = new JButton("5. 用户密码修改");
            BchangePassword.setBounds(40, 200, 150, 20);
            BqueryAll = new JButton("*6. 展示用户信息库");
            BqueryAll.setBounds(40, 240, 150, 20);
            Bexit = new JButton("7. 返回主菜单");
            Bexit.setBounds(40, 280, 150, 20);

            this.add(Binput);
            this.add(Bupdate);
            this.add(Bdelete);
            this.add(Bquery);
            this.add(BchangePassword);
            this.add(BqueryAll);
            this.add(Bexit);

            Binput.addActionListener(this);
            Bupdate.addActionListener(this);
            Bdelete.addActionListener(this);
            Bquery.addActionListener(this);
            BchangePassword.addActionListener(this);
            BqueryAll.addActionListener(this);
            Bexit.addActionListener(this);
        }
        else if(TYPE==1||TYPE==2){
            BchangePassword = new JButton("1. 用户密码修改");
            BchangePassword.setBounds(40, 40, 150, 20);
            Bexit = new JButton("2. 返回主菜单");
            Bexit.setBounds(40, 80, 150, 20);
            this.add(BchangePassword);
            this.add(Bexit);

            BchangePassword.addActionListener(this);
            Bexit.addActionListener(this);
        }
        this.setVisible(true);
    }

    public JButton getBinput() {
        return Binput;
    }
    public JButton getBupdate() {
        return Bupdate;
    }
    public JButton getBdelete() {
        return Bdelete;
    }
    public JButton getBquery() {
        return Bquery;
    }
    public JButton getBchangePassword() {
        return BchangePassword;
    }
    public JButton getBqueryAll() {
        return BqueryAll;
    }
    public JButton getBexit() {
        return Bexit;
    }
    public int getTYPE() {
        return TYPE;
    }
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
        else JOptionPane.showMessageDialog(null,"此系统现在无任何用户，请先注册");
        return users;
    }

    //按钮事件(内部类监听器)
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.getBinput()){
                input();
        }
        else if(e.getSource()==this.getBupdate()){
            try {
                update();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource()==this.getBdelete()){
            delete();
        }
        else if(e.getSource()==this.getBquery()){
            query();
        }
        else if(e.getSource()==this.getBchangePassword()){
            changePassword();
        }
        else if(e.getSource()==this.getBqueryAll()){
            if(TYPE==3){
                try {
                    queryAll();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else JOptionPane.showMessageDialog(null,"没有权限");
        }
        else if(e.getSource()==this.getBexit()){
            this.dispose();
            this.mm.setVisible(true);
        }
    }

    //具体实现
    private void input(){
        JFrame Finput=new JFrame("用户添加");
        Finput.setSize(400,800);
        Finput.setLayout(new GridLayout(9,1,1,1));
        Finput.setLocation(500,200);
        //账户
        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("账户（1~9位数字）：");
        JTextArea textID=new JTextArea(1,10);
        p1.add(labelID);
        p1.add(textID);
        Finput.add(p1);
        //密码
        JPanel p2=new JPanel();
        JLabel labelPassword=new JLabel("密码（0~14位数字）：");
        JTextArea textPassword=new JTextArea(1,10);
        p2.add(labelPassword);
        p2.add(textPassword);
        Finput.add(p2);
        //用户类型
        JPanel p3=new JPanel();
        JLabel labelType=new JLabel("类型");
        JComboBox<String> cmbType=new JComboBox<String>();
        cmbType.addItem("--请选择--");
        cmbType.addItem("1.普通读者");
        cmbType.addItem("2.图书管理员");
        cmbType.addItem("3.系统管理员");
        p3.add(labelType);
        p3.add(cmbType);
        Finput.add(p3);
        //姓名
        JPanel p4=new JPanel();
        JLabel labelName=new JLabel("姓名：");
        JTextArea textName=new JTextArea(1,10);
        p4.add(labelName);
        p4.add(textName);
        Finput.add(p4);
        //单位
        JPanel p5=new JPanel();
        JLabel labelUnit=new JLabel("单位：");
        JTextArea textUnit=new JTextArea(1,10);
        p5.add(labelUnit);
        p5.add(textUnit);
        Finput.add(p5);
        //电话
        JPanel p6=new JPanel();
        JLabel labelTelephone=new JLabel("电话(11位数字（China）)：");
        JTextArea textTelephone=new JTextArea(1,10);
        textTelephone.setBounds(10,10,10,10);
        p6.add(labelTelephone);
        p6.add(textTelephone);
        Finput.add(p6);
        //可借书数
        JPanel p7=new JPanel();
        JLabel labelCan_borrow=new JLabel("数量");
        JComboBox<String> cmbCan_borrow=new JComboBox<String>();
        cmbCan_borrow.addItem("--请选择--");
        cmbCan_borrow.addItem("5（学生）");
        cmbCan_borrow.addItem("10（教工）");
        p7.add(labelCan_borrow);
        p7.add(cmbCan_borrow);
        Finput.add(p7);
        //添加按钮
        JPanel p8=new JPanel();
        JButton BAdd=new JButton("添加");
        p8.add(BAdd);
        Finput.add(p8);
        Finput.setVisible(true);
        //添加监听器（匿名类）
        BAdd.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id;
                    char[] password;
                    int type;
                    char[] name;
                    char[] unit;
                    long telephone;
                    int can_borrow;
                    id=Integer.parseInt(textID.getText());
                    password=textPassword.getText().toCharArray();
                    type=cmbType.getSelectedIndex(); //1.普通读者 2.图书管理员 3.系统管理员
                    name=textName.getText().toCharArray();
                    unit=textUnit.getText().toCharArray();
                    telephone=Long.parseLong(textTelephone.getText());
                    can_borrow=cmbCan_borrow.getSelectedIndex();//1.5(学生） 2.10（教工）
                    File file=new File("D:/用户.txt");
                    try {
                        List<User> users=getUsers();
                        boolean isSRepeated=false;
                        for(int i=0;i<users.size();i++){
                            if(users.get(i).getID()==Integer.parseInt(textID.getText())) isSRepeated=true;
                        }
                        if(!isSRepeated) {
                            FileOutputStream fos = new FileOutputStream(file, true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            int pos = Math.toIntExact(fos.getChannel().position() - 4);          //而序列化对象在写入文件时，系统会自动添加aced 0005的一个头部，并且占4个字节,如果写入过对象，我们把前面那个4个字节去掉在写入到文件里就OK了
                            fos.getChannel().truncate(pos);
                            oos.writeObject(new User(id, password, type, name, unit, telephone, can_borrow));
                            oos.close();
                            JOptionPane.showMessageDialog(null, "添加成功！");
                        }
                        else JOptionPane.showMessageDialog(null, "账户重复，重新输入！");
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null,"添加失败（请严格按照要求输入注册信息！");
                        ex.printStackTrace();
                    }
                }
            }
        );
    }
    private void update() throws IOException, ClassNotFoundException {
        JFrame Fupdate=new JFrame("用户信息修改");
        Fupdate.setSize(400,500);
        Fupdate.setLayout(new GridLayout(5,1,1,1));
        Fupdate.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fupdate.add(p1);

        Fupdate.setVisible(true);

        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> users;
                try {
                    users = getUsers();
                    boolean isSeek=false;
                    int markeUsersI=-1;
                    for(int i=0;i<users.size();i++){
                        if(textID.getText().equals(String.valueOf(users.get(i).getID()))){
                            isSeek=true;
                            markeUsersI=i;
                            break;
                        }
                    }
                    if(isSeek){
                        System.out.println("OK!isseeked");
                        JComboBox<String> cmbChange=new JComboBox<String>();
                        cmbChange.addItem("-请选择修改项目-");
                        cmbChange.addItem("1.修改账号");
                        cmbChange.addItem("2.修改密码");
                        cmbChange.addItem("3.修改用户类型");
                        cmbChange.addItem("4.修改姓名");
                        cmbChange.addItem("5.修改单位");
                        cmbChange.addItem("6.修改电话");
                        cmbChange.addItem("7.修改可借书数量");
                        Fupdate.add(cmbChange);
                        Fupdate.remove(p1);
                        Fupdate.setVisible(true);
                        //确定按钮
                        JPanel p8=new JPanel();
                        JButton buttonChoice=new JButton("确定");
                        p8.add(buttonChoice);
                        Fupdate.add(p8);
                        Fupdate.setVisible(true);
                        int finalMarkeUsersI = markeUsersI;
                        buttonChoice.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ee) {
                                if(ee.getSource()==buttonChoice) {
                                    int t = cmbChange.getSelectedIndex();
                                    JTextArea textChangId = null;
                                    JTextArea textPassword = null;
                                    JComboBox<String> cmbType;
                                    JTextArea textName = null;
                                    JTextArea textUnit = null;
                                    JTextArea textTelephone = null;
                                    JTextArea textCan_borrow = null;
                                    JPanel pC1 = null;
                                    JPanel p2 = null;
                                    JPanel p3 = null;
                                    JPanel p4 = null;
                                    JPanel p5 = null;
                                    JPanel p6 = null;
                                    JPanel p7 = null;

                                    JPanel panelButtonChanged = new JPanel();
                                    JButton buttonChanged = new JButton("修改");
                                    panelButtonChanged.add(buttonChanged);
                                    Fupdate.add(panelButtonChanged);
                                    if (t == 1) {
                                        //账号
                                        pC1 = new JPanel();
                                        JLabel labelChangId = new JLabel("修改后的账号：");
                                        textChangId = new JTextArea(1, 10);
                                        pC1.add(labelChangId);
                                        pC1.add(textChangId);
                                        Fupdate.add(pC1);
                                        Fupdate.setVisible(true);
                                    } else if (t == 2) {
                                        //密码
                                        p2 = new JPanel();
                                        JLabel labelPassword = new JLabel("修改后的密码（0~14位数字）：");
                                        textPassword = new JTextArea(1, 10);
                                        p2.add(labelPassword);
                                        p2.add(textPassword);
                                        Fupdate.add(p2);
                                        Fupdate.setVisible(true);
                                    } else if (t == 3) {
                                        //用户类型
                                        p3 = new JPanel();
                                        JLabel labelType = new JLabel("修改后的类型");
                                        cmbType = new JComboBox<String>();
                                        cmbType.addItem("--请选择--");
                                        cmbType.addItem("1.普通读者");
                                        cmbType.addItem("2.图书管理员");
                                        cmbType.addItem("3.系统管理员");
                                        p3.add(labelType);
                                        p3.add(cmbType);
                                        Fupdate.add(p3);
                                        Fupdate.setVisible(true);
                                    } else if (t == 4) {
                                        //姓名
                                        p4 = new JPanel();
                                        JLabel labelName = new JLabel("修改后的姓名：");
                                        textName = new JTextArea(1, 10);
                                        p4.add(labelName);
                                        p4.add(textName);
                                        Fupdate.add(p4);
                                        Fupdate.setVisible(true);
                                    } else if (t == 5) {
                                        //单位
                                        p5 = new JPanel();
                                        JLabel labelUnit = new JLabel("修改后的单位：");
                                        textUnit = new JTextArea(1, 10);
                                        p5.add(labelUnit);
                                        p5.add(textUnit);
                                        Fupdate.add(p5);
                                        Fupdate.setVisible(true);
                                    } else if (t == 6) {
                                        //电话
                                        p6 = new JPanel();
                                        JLabel labelTelephone = new JLabel("修改后的电话(11位数字（China）)：");
                                        textTelephone = new JTextArea(1, 10);
                                        textTelephone.setBounds(10, 10, 10, 10);
                                        p6.add(labelTelephone);
                                        p6.add(textTelephone);
                                        Fupdate.add(p6);
                                        Fupdate.setVisible(true);
                                    } else if (t == 7) {
                                        //可借书数
                                        p7 = new JPanel();
                                        JLabel labelCan_borrow = new JLabel("修改后的数量");
                                        textCan_borrow = new JTextArea(1,3);
                                        p7.add(labelCan_borrow);
                                        p7.add(textCan_borrow);
                                        Fupdate.add(p7);
                                        Fupdate.setVisible(true);
                                    }

                                    //final这些组件才可在内部类使用
                                    JTextArea finalTextPassword = textPassword;
                                    JTextArea finalTextName = textName;
                                    JTextArea finalTextUnit = textUnit;
                                    JTextArea finalTextTelephone = textTelephone;
                                    JTextArea finalTextCan_borrow = textCan_borrow;
                                    JPanel finalP2 = p2;
                                    JPanel finalP3 = p3;
                                    JPanel finalP4 = p4;
                                    JPanel finalP5 = p5;
                                    JPanel finalP6 = p6;
                                    JPanel finalP7 = p7;
                                    JPanel finalPC = pC1;

                                    buttonChanged.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent eee) {
                                            if(eee.getSource()==buttonChanged) {
                                                int T = cmbChange.getSelectedIndex();
                                                if (T == 1) {
                                                    users.get(finalMarkeUsersI).setID(Integer.parseInt(textID.getText()));
                                                    Fupdate.remove(finalPC);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 2) {
                                                    users.get(finalMarkeUsersI).setPassword(finalTextPassword.getText().toCharArray());
                                                    Fupdate.remove(finalP2);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 3) {
                                                    users.get(finalMarkeUsersI).setType(cmbChange.getSelectedIndex());
                                                    Fupdate.remove(finalP3);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 4) {
                                                    users.get(finalMarkeUsersI).setName(finalTextName.getText().toCharArray());
                                                    Fupdate.remove(finalP4);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 5) {
                                                    users.get(finalMarkeUsersI).setUnit(finalTextUnit.getText().toCharArray());
                                                    Fupdate.remove(finalP5);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 6) {
                                                    users.get(finalMarkeUsersI).setTelephone(Long.parseLong(finalTextTelephone.getText()));
                                                    Fupdate.remove(finalP6);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 7) {
                                                    users.get(finalMarkeUsersI).setCan_borrow(Integer.parseInt(finalTextCan_borrow.getText()));
                                                    Fupdate.remove(finalP7);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                }

                                                //重新写入文件
                                                File file = new File("D:/用户.txt");
                                                try {
                                                    FileOutputStream fos = new FileOutputStream(file);
                                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                                    for (int i = 0; i < users.size(); i++) {
                                                        oos.writeObject(users.get(i));
                                                    }
                                                    oos.close();
                                                    JOptionPane.showMessageDialog(null, "修改成功！");
                                                } catch (IOException ex) {
                                                    JOptionPane.showMessageDialog(null, "修改失败！");
                                                    ex.printStackTrace();
                                                }
                                            }
                                        }//第三重监听器
                                    });
                                }
                            }//二重监听器
                        });
                    }
                    else JOptionPane.showMessageDialog(null,"无该用户！");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void delete(){
        JFrame Fdelete=new JFrame("用户信息删除");
        Fdelete.setSize(200,300);
        Fdelete.setLayout(new GridLayout(5,1,1,1));
        Fdelete.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fdelete.add(p1);

        Fdelete.setVisible(true);
        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> users;
                try {
                    users = getUsers();
                    boolean isSeek=false;
                    int markeUsersI=-1;
                    for(int i=0;i<users.size();i++){
                        if(textID.getText().equals(String.valueOf(users.get(i).getID()))){
                            isSeek=true;
                            markeUsersI=i;
                            break;
                        }
                    }
                    if (isSeek) {
                        users.remove(markeUsersI);              //移除该成员
                        File file = new File("D:/用户.txt");
                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        for (int i = 0; i < users.size(); i++) {
                            oos.writeObject(users.get(i));
                        }
                        oos.close();
                        JOptionPane.showMessageDialog(null, "删除成功！");
                    }
                    else JOptionPane.showMessageDialog(null, "不存在该用户！");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "删除失败！");
                    ex.printStackTrace();
                }
            } //第一重监听器重写方法
        });//第一重监听器
    }
    private void query(){
        JFrame Fquery=new JFrame("用户信息查找");
        Fquery.setSize(500,600);
        Fquery.setLayout(new GridLayout(5,1,1,1));
        Fquery.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fquery.add(p1);

        Fquery.setVisible(true);
        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> users;
                try {
                    users = getUsers();
                    boolean isSeek=false;
                    int markeUsersI=-1;
                    for(int i=0;i<users.size();i++){
                        if(textID.getText().equals(String.valueOf(users.get(i).getID()))){
                            isSeek=true;
                            markeUsersI=i;
                            break;
                        }
                    }
                    if (isSeek) {                                                           //设置文本区的内容
                        JTextArea textShowMessage=new JTextArea(18,25);
                        textShowMessage.append("账号: "+users.get(markeUsersI).getID());
                        textShowMessage.append("\n");
                        textShowMessage.append("密码: "+ String.valueOf(users.get(markeUsersI).getPassword()));
                        textShowMessage.append("\n");
                        int t=users.get(markeUsersI).getType();
                        if(t==1) textShowMessage.append("用户类型: 普通读者");
                        else if(t==2) textShowMessage.append("用户类型: 图书管理员");
                        else if(t==3) textShowMessage.append("用户类型: 系统管理员");
                        textShowMessage.append("\n");
                        textShowMessage.append("名字: "+ String.valueOf(users.get(markeUsersI).getName()));
                        textShowMessage.append("\n");
                        textShowMessage.append("单位: "+ String.valueOf(users.get(markeUsersI).getUnit()));
                        textShowMessage.append("\n");
                        textShowMessage.append("电话号: "+users.get(markeUsersI).getTelephone());
                        textShowMessage.append("\n");
                        textShowMessage.append("可借书数量: "+users.get(markeUsersI).getCan_borrow());

                        textShowMessage.setEnabled(false);
                        Fquery.add(textShowMessage);
                        Fquery.setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null, "不存在该用户！");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "删除成功！");
                    ex.printStackTrace();
                }
            } //第一重监听器重写方法
        });//第一重监听器
    }
    private void changePassword(){
        JFrame FchangePassword=new JFrame("用户密码修改");
        FchangePassword.setSize(300,400);
        FchangePassword.setLayout(new GridLayout(5,1,1,1));
        FchangePassword.setLocation(500,200);
        //账号
        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        p1.add(labelID);p1.add(textID);
        FchangePassword.add(p1);
        //原密码
        JPanel p2=new JPanel();
        JLabel labelPassword_1=new JLabel("原密码：");
        JTextArea textPassword_1=new JTextArea(1,10);
        p2.add(labelPassword_1);p2.add(textPassword_1);
        FchangePassword.add(p2);
        //确认按钮
        JPanel p3=new JPanel();
        JButton buttonConfirm=new JButton("确认");
        p3.add(buttonConfirm);
        FchangePassword.add(p3);

        FchangePassword.setVisible(true);
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a=String.valueOf(textID.getText());
                String b=String.valueOf(textPassword_1.getText());
                System.out.println("");
                try {
                    List<User> users=getUsers();
                    boolean isSeek=false;
                    int markeUsersI=-1;
                    for(int i=0;i<users.size();i++){
                        if(a.equals(String.valueOf(users.get(i).getID()))){
                            if(b.equals(String.valueOf(users.get(i).getPassword()))){
                                isSeek=true;
                                markeUsersI=i;
                                break;
                            }
                        }
                    }
                    if(isSeek){
                        FchangePassword.remove(p1);
                        FchangePassword.remove(p2);
                        FchangePassword.remove(p3);
                        //新密码
                        JPanel p4=new JPanel();
                        JLabel labelPassword_2=new JLabel("新密码：");
                        JTextArea textPassword_2=new JTextArea(1,10);
                        p4.add(labelPassword_2);p4.add(textPassword_2);
                        //确定修改
                        JPanel p5=new JPanel();
                        JButton buttonConfirmChange=new JButton("修改密码");
                        p5.add(buttonConfirmChange);
                        //更新面板
                        FchangePassword.add(p4);
                        FchangePassword.add(p5);
                        FchangePassword.repaint();
                        FchangePassword.setVisible(true);

                        int finalMarkeUsersI = markeUsersI;
                        buttonConfirmChange.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ee) {
                                if(ee.getSource()==buttonConfirmChange){
                                    char[] newPassword=textPassword_2.getText().toCharArray();
                                    users.get(finalMarkeUsersI).setPassword(newPassword);
                                    File file=new File("D:/用户.txt");
                                    try {
                                        FileOutputStream fos=new FileOutputStream(file);
                                        ObjectOutputStream oos=new ObjectOutputStream(fos);
                                        for (int i = 0; i < users.size(); i++) {
                                            oos.writeObject(users.get(i));
                                        }
                                        oos.close();
                                        JOptionPane.showMessageDialog(null, "修改成功！");
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(null, "修改失败！");
                                    }
                                }
                            }
                        });
                    }
                    else JOptionPane.showMessageDialog(null, "用户不存在或者密码错误！");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void queryAll() throws IOException, ClassNotFoundException {
        JFrame FqueryAll=new JFrame("全部用户");
        FqueryAll.setSize(1000,600);
        FqueryAll.setLayout(new GridLayout(1,1,1,1));
        FqueryAll.setLocation(500,200);

        JTextArea textAll=new JTextArea(100,50);
        JScrollPane scroll = new JScrollPane(textAll);
        FqueryAll.add(scroll);
        List<User> users=getUsers();
        for(int i=0;i<users.size();i++){
            textAll.append("用户<<"+i+">>");
            textAll.append("账号: "+users.get(i).getID());
            textAll.append("\n");
            textAll.append("密码: "+ String.valueOf(users.get(i).getPassword()));
            textAll.append("\n");
            int t=users.get(i).getType();
            if(t==1) textAll.append("用户类型: 普通读者");
            else if(t==2) textAll.append("用户类型: 图书管理员");
            else if(t==3) textAll.append("用户类型: 系统管理员");
            textAll.append("\n");
            textAll.append("名字: "+ String.valueOf(users.get(i).getName()));
            textAll.append("\n");
            textAll.append("单位: "+ String.valueOf(users.get(i).getUnit()));
            textAll.append("\n");
            textAll.append("电话号: "+users.get(i).getTelephone());
            textAll.append("\n");
            textAll.append("可借书数量: "+users.get(i).getCan_borrow());
            textAll.append("\n");
            textAll.append("\n");
        }
        textAll.setEnabled(false);
        FqueryAll.setVisible(true);
    }

}
