package 主界面及其子菜单;

import 系统相关者.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CirculationManagement extends JFrame implements ActionListener{
    private JButton BlendBook;
    private JButton BreturnBook;
    private JButton Bquery;
    private JButton Bexit;
    private int TYPE;
    private int ID;
    private MianInterface mm;
    public CirculationManagement(MianInterface mm,int ID){
        this.TYPE=mm.getTYPE();
        this.ID=ID;
        this.mm=mm;
        this.setTitle("图书流通管理");
        this.setSize(300,400);
        this.setLayout(null);
        this.setLocation(500,200);

        BlendBook=new JButton("1. 借书处理");
        BlendBook.setBounds(40,40,150,20);
        BreturnBook=new JButton("2. 还书处理");
        BreturnBook.setBounds(40,80,150,20);
        Bquery=new JButton("3. 借阅信息查询");
        Bquery.setBounds(40,120,150,20);
        Bexit=new JButton("4. 返回主菜单");
        Bexit.setBounds(40,160,150,20);

        this.add(BlendBook);
        this.add(BreturnBook);
        this.add(Bquery);
        this.add(Bexit);

        BlendBook.addActionListener(this);
        BreturnBook.addActionListener(this);
        Bquery.addActionListener(this);
        Bexit.addActionListener(this);
        this.setVisible(true);
    }

    public JButton getBlendBook() {
        return BlendBook;
    }
    public JButton getBreturnBook() {
        return BreturnBook;
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
    //获取用户，图书，流通。
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
    List<Circulation> getCirculation() throws IOException, ClassNotFoundException {
        File file=new File("D:/流通.txt");
        List<Circulation> circulations=new ArrayList<Circulation>();
        if(file.exists()){
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            while (fis.available()>0){
                Circulation c=(Circulation) ois.readObject();
                circulations.add(c);
            }
            ois.close();
        }
        else JOptionPane.showMessageDialog(null,"暂无借阅信息");
        return circulations;
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
    int getMarkX() throws IOException {
        File file=new File("D:/记录号.txt");
        int ans=-1;
        if(file.exists()){
            FileInputStream fis=new FileInputStream(file);
            InputStreamReader isr=new InputStreamReader(fis);
            ans=isr.read();
            isr.close();
            ans++;
            FileOutputStream fos=new FileOutputStream(file);
            OutputStreamWriter osw=new OutputStreamWriter(fos);
            osw.write(ans);
            osw.flush();
            osw.close();
        }
        else{
            ans=1;
            FileOutputStream fos=new FileOutputStream(file);
            OutputStreamWriter osw=new OutputStreamWriter(fos);
            osw.write(ans);
            osw.flush();
            osw.close();
        }
        return ans;
    }
    //按钮事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.getBlendBook()){
            lendBook();
        }
        else if(e.getSource()==this.getBreturnBook()){
            returnBook();
        }
        else if(e.getSource()==this.getBquery()){
            try {
                query();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource()==this.getBexit()){
            this.dispose();
            this.mm.setVisible(true);
        }
    }

    private void lendBook() {
        JFrame FlendBook=new JFrame("借书处理");
        FlendBook.setSize(400,500);
        FlendBook.setLayout(new GridLayout(5,1,1,1));
        FlendBook.setLocation(500,200);
        //借阅人
        JPanel p1=new JPanel();
        JLabel labelUser=new JLabel("借阅人:");
        JTextArea textUser=new JTextArea(1,10);
        p1.add(labelUser);
        p1.add(textUser);
        FlendBook.add(p1);
        //借阅书
        JPanel p2=new JPanel();
        JLabel labelBook=new JLabel("所借书:");
        JTextArea textBook=new JTextArea(1,10);
        p2.add(labelBook);
        p2.add(textBook);
        FlendBook.add(p2);
        //处理日期
        JPanel p3=new JPanel();
        JLabel labelYear=new JLabel("年:");
        JTextArea textYear=new JTextArea(1,4);
        p3.add(labelYear);p3.add(textYear);
        JLabel labelMonth=new JLabel("月:");
        JTextArea textMonth=new JTextArea(1,2);
        p3.add(labelMonth);p3.add(textMonth);
        JLabel labelDay=new JLabel("日:");
        JTextArea textDay=new JTextArea(1,2);
        p3.add(labelDay);p3.add(textDay);
        FlendBook.add(p3);

        JPanel p4=new JPanel();
        JButton buttonDetermine=new JButton("处理");
        p4.add(buttonDetermine);
        FlendBook.add(p4);
        buttonDetermine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    List<Book> books=getBooks();
                    List<User> users=getUsers();
                    boolean isUserHave=false;
                    boolean isUserE=false;
                    boolean isBookHave=false;
                    boolean isBookE=false;
                    int markUser=-1;
                    int markBook=-1;
                    for(int i=0;i<users.size();i++){
                        if(users.get(i).getID()==Integer.parseInt(textUser.getText())){
                            isUserE=true;
                            System.out.println(users.get(i).getCan_borrow());
                            if(users.get(i).getCan_borrow()>0){
                                markUser=i;
                                isUserHave=true;
                                break;
                            }
                        }
                    }
                    for(int i=0;i<books.size();i++){
                        if(textBook.getText().equals(String.valueOf(books.get(i).getNo()))){
                            isBookE=true;
                            if(books.get(i).getCount()>0){
                                markBook=i;
                                isBookHave=true;
                                break;
                            }
                        }
                    }
                    if(isUserE&&isBookE){
                        if(isUserHave && isBookHave){
                            //用户和图书修改
                            users.get(markUser).setCan_borrow(users.get(markUser).getCan_borrow()-1);
                            books.get(markBook).setCount(books.get(markBook).getCount()-1);
                            FileOutputStream fos = new FileOutputStream(new File("D:/用户.txt"));
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            for (int i = 0; i < users.size(); i++) {
                                oos.writeObject(users.get(i));
                            }
                            oos.close();
                            fos = new FileOutputStream(new File("D:/图书.txt"));
                            oos = new ObjectOutputStream(fos);
                            for (int i = 0; i < books.size(); i++) {
                                oos.writeObject(books.get(i));
                            }
                            oos.close();
                            //添加流水记录

                            int serialNo=getMarkX();
                            char[] name=textUser.getText().toCharArray();
                            int no=Integer.parseInt(textBook.getText());
                            int year=Integer.parseInt(textYear.getText());
                            int month=Integer.parseInt(textMonth.getText());
                            int day=Integer.parseInt(textDay.getText());
                            int type=1;                 //1是借书
                            int operator=ID;
                            File file=new File("D:/流通.txt");
                            if (file.exists()) {
                                fos = new FileOutputStream(file, true);
                                oos = new ObjectOutputStream(fos);
                                int pos = Math.toIntExact(fos.getChannel().position() - 4);          //而序列化对象在写入文件时，系统会自动添加aced 0005的一个头部，并且占4个字节,如果写入过对象，我们把前面那个4个字节去掉在写入到文件里就OK了
                                fos.getChannel().truncate(pos);
                                oos.writeObject((new Circulation(serialNo, name, no, year, month,day,type,operator)));
                                oos.close();
                                JOptionPane.showMessageDialog(null, "添加记录成功！");
                            } else {
                                file.createNewFile();
                                fos = new FileOutputStream(file, true);
                                oos = new ObjectOutputStream(fos);
                                oos.writeObject((new Circulation(serialNo, name, no, year, month,day,type,operator)));
                                JOptionPane.showMessageDialog(null, "添加记录成功！");
                            }
                        }
                        else{
                            if(!isUserHave) JOptionPane.showMessageDialog(null,"此用户已达到借书上限,请先还书");
                            if(!isBookHave) JOptionPane.showMessageDialog(null,"此书已无剩余，请等待其他用户还书");
                        }
                    }
                    else{
                        if(!isUserE) JOptionPane.showMessageDialog(null,"不存在此用户");
                        if(!isBookE) JOptionPane.showMessageDialog(null,"不存在此书");

                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        FlendBook.setVisible(true);
    }

    private void returnBook() {
        JFrame FreturnBook=new JFrame("还书处理");
        FreturnBook.setSize(400,500);
        FreturnBook.setLayout(new GridLayout(5,1,1,1));
        FreturnBook.setLocation(500,200);
        //还书人
        JPanel p1=new JPanel();
        JLabel labelUser=new JLabel("借阅人:");
        JTextArea textUser=new JTextArea(1,10);
        p1.add(labelUser);
        p1.add(textUser);
        FreturnBook.add(p1);
        //借阅书
        JPanel p2=new JPanel();
        JLabel labelBook=new JLabel("所还书:");
        JTextArea textBook=new JTextArea(1,10);
        p2.add(labelBook);
        p2.add(textBook);
        FreturnBook.add(p2);
        //处理日期
        JPanel p3=new JPanel();
        JLabel labelYear=new JLabel("年:");
        JTextArea textYear=new JTextArea(1,4);
        p3.add(labelYear);p3.add(textYear);
        JLabel labelMonth=new JLabel("月:");
        JTextArea textMonth=new JTextArea(1,2);
        p3.add(labelMonth);p3.add(textMonth);
        JLabel labelDay=new JLabel("日:");
        JTextArea textDay=new JTextArea(1,2);
        p3.add(labelDay);p3.add(textDay);
        FreturnBook.add(p3);

        JPanel p4=new JPanel();
        JButton buttonDetermine=new JButton("处理");
        p4.add(buttonDetermine);
        FreturnBook.add(p4);
        buttonDetermine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    List<Book> books = getBooks();
                    List<User> users = getUsers();
                    List<Circulation> Circulations = getCirculation();
                    boolean isUserHave = false;
                    boolean isUserE = false;
                    boolean isBookHave = false;
                    boolean isBookE = false;

                    boolean iscanReturn=false;
                    int markUser = -1;
                    int markBook = -1;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getID() == Integer.parseInt(textUser.getText())) {
                            isUserE = true;
                            System.out.println(users.get(i).getCan_borrow());
                            markUser = i;
                            break;
                        }
                    }
                    for (int i = 0; i < books.size(); i++) {
                        if (textBook.getText().equals(String.valueOf(books.get(i).getNo()))) {
                            isBookE = true;
                            markBook = i;
                            break;
                        }
                    }
                    for (int i = 0; i < Circulations.size(); i++) {
                        int a=Integer.parseInt(textBook.getText());
                        int b=Integer.parseInt(String.valueOf(Circulations.get(i).getNo()));
                        if (textUser.getText().equals(String.valueOf(Circulations.get(i).getName()))&&a==b) {
                            System.out.println("Yes");

                            if (Circulations.get(i).getType() == 1){
                                iscanReturn = true;
                                Circulations.get(i).setType(2);
                            }
                        }
                    }
                    if(iscanReturn){
                        File file=new File("D:/流通.txt");
                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        for (int i = 0; i < Circulations.size(); i++) {
                            oos.writeObject(Circulations.get(i));
                        }
                        oos.close();
                        JOptionPane.showMessageDialog(null, "修改成功！");
                        if (isUserE && isBookE) {
                            //用户和图书修改
                            users.get(markUser).setCan_borrow(users.get(markUser).getCan_borrow() + 1);
                            books.get(markBook).setCount(books.get(markBook).getCount() + 1);
                            fos = new FileOutputStream(new File("D:/用户.txt"));
                            oos = new ObjectOutputStream(fos);
                            for (int i = 0; i < users.size(); i++) {
                                oos.writeObject(users.get(i));
                            }
                            oos.close();
                            fos = new FileOutputStream(new File("D:/图书.txt"));
                            oos = new ObjectOutputStream(fos);
                            for (int i = 0; i < books.size(); i++) {
                                oos.writeObject(books.get(i));
                            }
                            oos.close();
                            //添加流水记录

                            int serialNo = getMarkX();
                            char[] name = textUser.getText().toCharArray();
                            int no = Integer.parseInt(textBook.getText());
                            int year = Integer.parseInt(textYear.getText());
                            int month = Integer.parseInt(textMonth.getText());
                            int day = Integer.parseInt(textDay.getText());
                            int type = 2;                 //2是还书
                            int operator = ID;
                            file = new File("D:/流通.txt");
                            if (file.exists()) {
                                fos = new FileOutputStream(file, true);
                                oos = new ObjectOutputStream(fos);
                                int pos = Math.toIntExact(fos.getChannel().position() - 4);          //而序列化对象在写入文件时，系统会自动添加aced 0005的一个头部，并且占4个字节,如果写入过对象，我们把前面那个4个字节去掉在写入到文件里就OK了
                                fos.getChannel().truncate(pos);
                                oos.writeObject((new Circulation(serialNo, name, no, year, month, day, type, operator)));
                                oos.close();
                                JOptionPane.showMessageDialog(null, "添加记录成功！");
                            } else {
                                file.createNewFile();
                                fos = new FileOutputStream(file, true);
                                oos = new ObjectOutputStream(fos);
                                oos.writeObject((new Circulation(serialNo, name, no, year, month, day, type, operator)));
                                JOptionPane.showMessageDialog(null, "添加记录成功！");
                            }
                        } else {
                            if (!isUserE) JOptionPane.showMessageDialog(null, "不存在此用户");
                            if (!isBookE) JOptionPane.showMessageDialog(null, "不存在此书");
                         }
                    } else JOptionPane.showMessageDialog(null, "已经还过此书了");
                    } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        FreturnBook.setVisible(true);

    }

    private void query() throws IOException, ClassNotFoundException {
        JFrame Fquery=new JFrame("查看流通记录");
        Fquery.setLayout(null);
        Fquery.setSize(630,500);
        Fquery.setLocation(500,200);

        List<Circulation> Circulations=getCirculation();
        if(Circulations.size()>0) {
            String[] columnTitle = new String[]{"流水号", "用户名", "书号", "日期", "借还书类型","操作人"};
            Object[][] tableDate = new Object[100][6];              //书的最大数量在这里设置
            int i;
            for (i = 0; i < Circulations.size(); i++) {
                tableDate[i][0] = String.valueOf(Circulations.get(i).getSerialNo());
                tableDate[i][1] = new String(Circulations.get(i).getName());
                tableDate[i][2] = String.valueOf(Circulations.get(i).getNo());
                tableDate[i][3] = Circulations.get(i).getYear()+"-"+Circulations.get(i).getMonth()+"-"+Circulations.get(i).getDay();
                if(Circulations.get(i).getType()==1) {
                    tableDate[i][4]=new String("借书");
                }
                else {
                    tableDate[i][4]=new String("还书");
                }
                tableDate[i][5] = String.valueOf(Circulations.get(i).getOperator());
            }
            JOptionPane.showMessageDialog(null, "共找到" + i + "条记录");
            JTable tableBook = new JTable(tableDate, columnTitle);
            tableBook.setEnabled(false);
            JScrollPane p1 = new JScrollPane(tableBook);
            p1.setBounds(0, 0, 600, 500);
            Fquery.add(p1);
            Fquery.setVisible(true);
        }
        else JOptionPane.showMessageDialog(null, "库存无书");
    }
}
