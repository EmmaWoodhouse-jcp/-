package 主界面及其子菜单;

import 系统相关者.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BookManagement extends JFrame implements ActionListener{
    private JButton Binput;
    private JButton Bupdate;
    private JButton Bdelete;
    private JButton Bquery;
    private JButton Bexit;
    private int TYPE;
    private MianInterface mm;
    public BookManagement(MianInterface mm){
        this.TYPE=mm.getTYPE();
        this.mm=mm;
        this.setTitle("图书管理");
        this.setSize(300,400);
        this.setLayout(null);
        this.setLocation(500,200);
        if(TYPE==2){
            Binput = new JButton("1. 图书信息录入");
            Binput.setBounds(40, 40, 150, 20);
            Bupdate = new JButton("2. 图书信息修改");
            Bupdate.setBounds(40, 80, 150, 20);
            Bdelete = new JButton("3. 图书信息删除");
            Bdelete.setBounds(40, 120, 150, 20);
            Bquery = new JButton("4. 图书信息查询");
            Bquery.setBounds(40, 160, 150, 20);
            Bexit = new JButton("5. 返回主菜单");
            Bexit.setBounds(40, 200, 150, 20);

            this.add(Binput);
            this.add(Bupdate);
            this.add(Bdelete);
            this.add(Bquery);
            this.add(Bexit);

            Binput.addActionListener(this);
            Bupdate.addActionListener(this);
            Bdelete.addActionListener(this);
            Bquery.addActionListener(this);
            Bexit.addActionListener(this);
        }
        else if(TYPE==1||TYPE==3){
            Bquery = new JButton("1. 图书信息查询");
            Bquery.setBounds(40, 40, 150, 20);
            Bexit = new JButton("2. 返回主菜单");
            Bexit.setBounds(40, 80, 150, 20);

            this.add(Bquery);
            this.add(Bexit);

            Bquery.addActionListener(this);
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
    public JButton getBexit() {
        return Bexit;
    }
    public int getTYPE() {
        return TYPE;
    }
    List<Book> getBooks() throws IOException, ClassNotFoundException {
        File file=new File("D:/图书.txt");
        List<Book> books=new ArrayList<Book>();
        if(file.exists()){
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            while (fis.available()>0){
                Book b=(Book) ois.readObject();
                books.add(b);
            }
            ois.close();
        }
        else JOptionPane.showMessageDialog(null,"现系统库存内无书,请先联系图书管理员添加图书");
        return books;
    }

    //按钮事件(内部类)
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.getBinput()){
            input();
        }
        else if(e.getSource()==this.getBupdate()){
            update();
        }
        else if(e.getSource()==this.getBdelete()){
            delete();
        }
        else if(e.getSource()==this.getBquery()){
            query();
        }
        else if(e.getSource()==this.getBexit()){
            this.dispose();
            this.mm.setVisible(true);
        }
    }

    //具体实现
    private void input(){
        JFrame Finput=new JFrame("图书添加");
        Finput.setSize(400,800);
        Finput.setLayout(new GridLayout(9,1,1,1));
        Finput.setLocation(500,200);
        //书号
        JPanel p1=new JPanel();
        JLabel labelBookID=new JLabel("书号（1~4位数字）：");
        JTextArea textBookID=new JTextArea(1,10);
        p1.add(labelBookID);
        p1.add(textBookID);
        Finput.add(p1);
        //书名
        JPanel p2=new JPanel();
        JLabel labelName=new JLabel("书名：");
        JTextArea textName=new JTextArea(1,10);
        p2.add(labelName);
        p2.add(textName);
        Finput.add(p2);
        //作者名
        JPanel p3=new JPanel();
        JLabel labelAuthor=new JLabel("作者名");
        JTextArea textAuthor=new JTextArea(1,10);
        p3.add(labelAuthor);
        p3.add(textAuthor);
        Finput.add(p3);
        //出版社
        JPanel p4=new JPanel();
        JLabel labelPublish=new JLabel("出版社：");
        JTextArea textPublish=new JTextArea(1,10);
        p4.add(labelPublish);
        p4.add(textPublish);
        Finput.add(p4);
        //藏书量
        JPanel p5=new JPanel();
        JLabel labelRemain=new JLabel("添加数量（藏书量）：");
        JTextArea textRemain=new JTextArea(1,10);
        p5.add(labelRemain);
        p5.add(textRemain);
        Finput.add(p5);
        //添加按钮
        JPanel p6=new JPanel();
        JButton BAdd=new JButton("添加");
        p6.add(BAdd);
        Finput.add(p6);

        Finput.setVisible(true);
        BAdd.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char[] no;                          //书号
                    char[] name;                        //书名
                    char[] author;                      //作者名
                    char[] press;                       //出版社
                    int count;                          //藏书量
                    no=textBookID.getText().toCharArray();
                    name=textName.getText().toCharArray();
                    author=textAuthor.getText().toCharArray();
                    press=textPublish.getText().toCharArray();
                    count=Integer.parseInt(textRemain.getText());
                    File file=new File("D:/图书.txt");
                    try {
                        if (file.exists()) {
                            List<Book> books=getBooks();
                            boolean isSRepeated=false;
                            for (Book book : books) {
                                String a = String.valueOf(no);
                                String b = String.valueOf(book.getNo());
                                if (a.equals(b)) isSRepeated = true;
                            }
                            if(!isSRepeated) {
                                FileOutputStream fos = new FileOutputStream(file, true);
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                int pos = Math.toIntExact(fos.getChannel().position() - 4);          //而序列化对象在写入文件时，系统会自动添加aced 0005的一个头部，并且占4个字节,如果写入过对象，我们把前面那个4个字节去掉在写入到文件里就OK了
                                fos.getChannel().truncate(pos);
                                oos.writeObject(new Book(no, name, author, press, count));
                                oos.close();
                                JOptionPane.showMessageDialog(null, "添加成功！");
                            }
                            else JOptionPane.showMessageDialog(null,"书号重复,请重新输入");
                        } else {
                            file.createNewFile();
                            FileOutputStream fos = new FileOutputStream(file, true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(new Book(no, name, author, press, count));
                            JOptionPane.showMessageDialog(null, "添加成功！");
                        }
                    }
                    catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        );
    }
    private void update(){
        JFrame Fupdate=new JFrame("图书信息修改");
        Fupdate.setSize(400,500);
        Fupdate.setLayout(new GridLayout(5,1,1,1));
        Fupdate.setLocation(500,200);
        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入书号：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fupdate.add(p1);

        Fupdate.setVisible(true);

        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> books;
                try {
                    books = getBooks();
                    boolean isSeek=false;
                    int markeUsersI=-1;
                    for(int i=0;i<books.size();i++){
                        if(textID.getText().equals(String.valueOf(books.get(i).getNo()))){
                            isSeek=true;
                            markeUsersI=i;
                            break;
                        }
                    }
                    if(isSeek){
                        System.out.println("OK!isseeked");
                        JComboBox<String> cmbChange=new JComboBox<String>();
                        cmbChange.addItem("-请选择修改项目-");
                        cmbChange.addItem("1.修改书号");
                        cmbChange.addItem("2.修改书名");
                        cmbChange.addItem("3.修改作者");
                        cmbChange.addItem("4.修改出版社");
                        cmbChange.addItem("5.修改藏书量");
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
                                    JTextArea textNo = null;
                                    JTextArea textName= null;
                                    JTextArea textAuthor = null;
                                    JTextArea textPress = null;
                                    JTextArea textCount = null;
                                    JPanel pC1 = null;
                                    JPanel p2 = null;
                                    JPanel p3 = null;
                                    JPanel p4 = null;
                                    JPanel p5 = null;

                                    JPanel panelButtonChanged = new JPanel();
                                    JButton buttonChanged = new JButton("修改");
                                    panelButtonChanged.add(buttonChanged);
                                    Fupdate.add(panelButtonChanged);
                                    if (t == 1) {
                                        //书号
                                        pC1 = new JPanel();
                                        JLabel labelNo = new JLabel("修改后的书号：");
                                        textNo = new JTextArea(1, 10);
                                        pC1.add(labelNo);
                                        pC1.add(textNo);
                                        Fupdate.add(pC1);
                                        Fupdate.setVisible(true);
                                    } else if (t == 2) {
                                        //书名
                                        p2 = new JPanel();
                                        JLabel labelName = new JLabel("修改后的书名：");
                                        textName = new JTextArea(1, 10);
                                        p2.add(labelName);
                                        p2.add(textName);
                                        Fupdate.add(p2);
                                        Fupdate.setVisible(true);
                                    } else if (t == 3) {
                                        //作者
                                        p3 = new JPanel();
                                        JLabel labelAuthor = new JLabel("修改后的作者：");
                                        textAuthor = new JTextArea(1, 10);
                                        p3.add(labelAuthor);
                                        p3.add(textAuthor);
                                        Fupdate.add(p3);
                                        Fupdate.setVisible(true);
                                    } else if (t == 4) {
                                        //出版社
                                        p4 = new JPanel();
                                        JLabel labelPress = new JLabel("修改后的出版社：");
                                        textPress = new JTextArea(1, 10);
                                        p4.add(labelPress);
                                        p4.add(textPress);
                                        Fupdate.add(p4);
                                        Fupdate.setVisible(true);
                                    } else if (t == 5) {
                                        //藏书量
                                        p5 = new JPanel();
                                        JLabel labelCount = new JLabel("修改后的藏书量：");
                                        textCount = new JTextArea(1, 10);
                                        p5.add(labelCount);
                                        p5.add(textCount);
                                        Fupdate.add(p5);
                                        Fupdate.setVisible(true);
                                    }
                                    //final这些组件才可在内部类使用
                                    JTextArea finalTextNo = textNo;
                                    JTextArea finalTextName = textName;
                                    JTextArea finalTextAuthor = textAuthor;
                                    JTextArea finalTextPress = textPress;
                                    JTextArea finalTextCount = textCount;
                                    JPanel finalPC = pC1;
                                    JPanel finalP2 = p2;
                                    JPanel finalP3 = p3;
                                    JPanel finalP4 = p4;
                                    JPanel finalP5 = p5;

                                    buttonChanged.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent eee) {
                                            if(eee.getSource()==buttonChanged) {
                                                int T = cmbChange.getSelectedIndex();
                                                if (T == 1) {
                                                    books.get(finalMarkeUsersI).setNo(textID.getText().toCharArray());
                                                    Fupdate.remove(finalPC);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 2) {
                                                    books.get(finalMarkeUsersI).setName(finalTextName.getText().toCharArray());
                                                    Fupdate.remove(finalP2);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 3) {
                                                    books.get(finalMarkeUsersI).setAuthor(finalTextAuthor.getText().toCharArray());
                                                    Fupdate.remove(finalP3);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 4) {
                                                    books.get(finalMarkeUsersI).setPress(finalTextPress.getText().toCharArray());
                                                    Fupdate.remove(finalP4);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                } else if (T == 5) {
                                                    books.get(finalMarkeUsersI).setCount(Integer.parseInt(finalTextCount.getText()));
                                                    Fupdate.remove(finalP5);
                                                    Fupdate.remove(panelButtonChanged);
                                                    Fupdate.setVisible(true);
                                                }

                                                //重新写入文件
                                                File file = new File("D:/图书.txt");
                                                try {
                                                    FileOutputStream fos = new FileOutputStream(file);
                                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                                    for (int i = 0; i < books.size(); i++) {
                                                        oos.writeObject(books.get(i));
                                                    }
                                                    oos.close();
                                                    JOptionPane.showMessageDialog(null, "修改成功！");
                                                } catch (IOException ex) {
                                                    JOptionPane.showMessageDialog(null, "修改失败");
                                                    ex.printStackTrace();
                                                }
                                            }
                                        }//第三重监听器
                                    });
                                }
                            }//二重监听器
                        });
                    }
                    else JOptionPane.showMessageDialog(null,"无此书！");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void delete(){
        JFrame Fupdate=new JFrame("图书信息删除");
        Fupdate.setSize(400,500);
        Fupdate.setLayout(new GridLayout(5,1,1,1));
        Fupdate.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入书号：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fupdate.add(p1);

        Fupdate.setVisible(true);
        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> books;
                try {
                    books = getBooks();
                    boolean isSeek=false;
                    int markeUsersI=-1;
                    for(int i=0;i<books.size();i++){
                        if(textID.getText().equals(String.valueOf(books.get(i).getNo()))){
                            isSeek=true;
                            markeUsersI=i;
                            break;
                        }
                    }
                    if (isSeek) {
                        books.remove(markeUsersI);              //移除该成员
                        File file = new File("D:/图书.txt");
                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        for (int i = 0; i < books.size(); i++) {
                            oos.writeObject(books.get(i));
                        }
                        oos.close();
                        JOptionPane.showMessageDialog(null, "删除成功！");
                    }
                    else JOptionPane.showMessageDialog(null, "不存在此书！");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "删除失败！");
                    ex.printStackTrace();
                }
            } //第一重监听器重写方法
        });//第一重监听器
    }
    private void query(){
        JFrame Fquery=new JFrame("图书查询");
        Fquery.setLayout(null);
        Fquery.setSize(300,400);
        Fquery.setLocation(500,200);

        JButton buttonQNo=new JButton("1.按书号查询");
        buttonQNo.setBounds(40, 40, 150, 20);
        Fquery.add(buttonQNo);

        JButton buttonQName=new JButton("2.按书名查询");
        buttonQName.setBounds(40,80,150,20);
        Fquery.add(buttonQName);

        JButton buttonQAuthor=new JButton("3.按作者查询");
        buttonQAuthor.setBounds(40, 120, 150, 20);
        Fquery.add(buttonQAuthor);

        JButton buttonQAll=new JButton("4.*查看全部书籍");
        buttonQAll.setBounds(40, 160, 150, 20);
        Fquery.add(buttonQAll);

        JButton buttonExit=new JButton("5.返回主菜单");
        buttonExit.setBounds(40, 200, 150, 20);
        Fquery.add(buttonExit);

        Fquery.setVisible(true);
        //按照书号查找
        buttonQNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //面板处理
                JFrame FQNo=new JFrame("按书号查找");
                FQNo.setSize(500,600);
                FQNo.setLocation(500,200);
                FQNo.setLayout(null);
                JLabel labelNo=new JLabel("输入查询的书号");
                labelNo.setBounds(10,20,150,20);
                JTextArea textNo=new JTextArea(1,10);
                textNo.setBounds(100, 20, 150, 20);
                JButton buttonSeek=new JButton("Seek");
                buttonSeek.setBounds(270,20,150,20);
                FQNo.add(labelNo);FQNo.add(textNo);FQNo.add(buttonSeek);
                FQNo.setVisible(true);

                buttonSeek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            //设置文本区的内容
                            List<Book> books=getBooks();
                            String[] columnTitle= new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                            Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                            boolean isSeeked=false;
                            int d=0;
                            for(int i=0;i<books.size();i++){
                                if(textNo.getText().equals(String.valueOf(books.get(i).getNo()))) {
                                    isSeeked=true;
                                    tableDate[d][0] = new String(books.get(i).getNo());
                                    tableDate[d][1] = new String(books.get(i).getName());
                                    tableDate[d][2] = new String(books.get(i).getAuthor());
                                    tableDate[d][3] = new String(books.get(i).getPress());
                                    tableDate[d][4] = books.get(i).getCount();
                                    d++;
                                }
                            }
                            if(isSeeked) {
                                JOptionPane.showMessageDialog(null,"共找到"+d+"本");
                                JTable tableBook = new JTable(tableDate, columnTitle);
                                tableBook.setEnabled(false);
                                JScrollPane p1 = new JScrollPane(tableBook);
                                p1.setBounds(0, 60, 470, 450);
                                FQNo.add(p1);
                                FQNo.setVisible(true);
                            }
                            else JOptionPane.showMessageDialog(null,"无该书号的书籍");
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        //按照书名查找
        buttonQName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //面板处理
                JFrame FQName=new JFrame("按书名查找");
                FQName.setSize(500,600);
                FQName.setLocation(500,200);
                FQName.setLayout(null);
                JLabel labelName=new JLabel("输入查询的书名");
                labelName.setBounds(10,20,150,20);
                JTextArea textName=new JTextArea(1,10);
                textName.setBounds(100, 20, 150, 20);
                JButton buttonSeek=new JButton("Seek");
                buttonSeek.setBounds(270,20,150,20);
                FQName.add(labelName);FQName.add(textName);FQName.add(buttonSeek);
                FQName.setVisible(true);

                buttonSeek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            //设置文本区的内容
                            List<Book> books=getBooks();
                            String[] columnTitle= new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                            Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                            boolean isSeeked=false;
                            int d=0;
                            for(int i=0;i<books.size();i++){
                                if(textName.getText().equals(String.valueOf(books.get(i).getName()))) {
                                    isSeeked=true;
                                    tableDate[d][0] = new String(books.get(i).getNo());
                                    tableDate[d][1] = new String(books.get(i).getName());
                                    tableDate[d][2] = new String(books.get(i).getAuthor());
                                    tableDate[d][3] = new String(books.get(i).getPress());
                                    tableDate[d][4] = books.get(i).getCount();
                                    d++;
                                }
                            }
                            if(isSeeked) {
                                JOptionPane.showMessageDialog(null,"共找到"+d+"本");
                                JTable tableBook = new JTable(tableDate, columnTitle);
                                tableBook.setEnabled(false);
                                JScrollPane p1 = new JScrollPane(tableBook);
                                p1.setBounds(0, 60, 470, 450);
                                FQName.add(p1);
                                FQName.setVisible(true);
                            }
                            else JOptionPane.showMessageDialog(null,"无该书名的书籍");
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        //按照作者查找
        buttonQAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //面板处理
                JFrame FQAuthor=new JFrame("按作者查找");
                FQAuthor.setSize(500,600);
                FQAuthor.setLocation(500,200);
                FQAuthor.setLayout(null);
                JLabel labelAuthor=new JLabel("输入查询的作者");
                labelAuthor.setBounds(10,20,150,20);
                JTextArea textAuthor=new JTextArea(1,10);
                textAuthor.setBounds(100, 20, 150, 20);
                JButton buttonSeek=new JButton("Seek");
                buttonSeek.setBounds(270,20,150,20);
                FQAuthor.add(labelAuthor);FQAuthor.add(textAuthor);FQAuthor.add(buttonSeek);
                FQAuthor.setVisible(true);

                buttonSeek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            //设置文本区的内容
                            List<Book> books=getBooks();
                            String[] columnTitle= new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                            Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                            boolean isSeeked=false;
                            int d=0;
                            for(int i=0;i<books.size();i++){
                                if(textAuthor.getText().equals(String.valueOf(books.get(i).getAuthor()))) {
                                    isSeeked=true;
                                    tableDate[d][0] = new String(books.get(i).getNo());
                                    tableDate[d][1] = new String(books.get(i).getName());
                                    tableDate[d][2] = new String(books.get(i).getAuthor());
                                    tableDate[d][3] = new String(books.get(i).getPress());
                                    tableDate[d][4] = books.get(i).getCount();
                                    d++;
                                }
                            }
                            if(isSeeked) {
                                JOptionPane.showMessageDialog(null,"共找到"+d+"本");
                                JTable tableBook = new JTable(tableDate, columnTitle);
                                tableBook.setEnabled(false);
                                JScrollPane p1 = new JScrollPane(tableBook);
                                p1.setBounds(0, 60, 470, 450);
                                FQAuthor.add(p1);
                                FQAuthor.setVisible(true);
                            }
                            else JOptionPane.showMessageDialog(null,"无该作者的书籍");
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        //查看全部
        buttonQAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JFrame FQAll=new JFrame("全部书籍");
                    FQAll.setSize(500,600);
                    FQAll.setLocation(500,200);
                    FQAll.setLayout(null);
                    //设置文本区的内容
                    List<Book> books=getBooks();
                    if(books.size()>0) {
                        String[] columnTitle = new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                        Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                        int i;
                        for (i = 0; i < books.size(); i++) {
                            tableDate[i][0] = new String(books.get(i).getNo());
                            tableDate[i][1] = new String(books.get(i).getName());
                            tableDate[i][2] = new String(books.get(i).getAuthor());
                            tableDate[i][3] = new String(books.get(i).getPress());
                            tableDate[i][4] = books.get(i).getCount();
                        }
                        JOptionPane.showMessageDialog(null, "共找到" + i + "本");
                        JTable tableBook = new JTable(tableDate, columnTitle);
                        tableBook.setEnabled(false);
                        JScrollPane p1 = new JScrollPane(tableBook);
                        p1.setBounds(0, 60, 470, 450);
                        FQAll.add(p1);
                        FQAll.setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null, "库存无书");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //退出按钮
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fquery.dispose();
            }
        });
    }
}
