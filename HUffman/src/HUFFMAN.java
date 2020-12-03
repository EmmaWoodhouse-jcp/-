import javax.swing.*;
import java.awt.*;

public class HUFFMAN {
    private JFrame j;
    private JButton button_1,button_2,button_3,button_4,button_5,button_6;

    HUFFMAN(){
        j=new JFrame("哈夫曼编码译码器");
        button_1=new JButton("1. 选取文件");
        button_2=new JButton("2. 建立哈夫曼树");
        button_3=new JButton("3. 对文件编码并保存文件中");
        button_4=new JButton("4. 选择文件并解码并存储");
        button_5=new JButton("5. 对编码文件进行压缩");
        button_6=new JButton("6. 对编码文件进行解压缩");
        j.add(button_1);
        j.add(button_2);
        j.add(button_3);
        j.add(button_4);
        j.add(button_5);
        j.add(button_6);
        j.setLayout(new FlowLayout());
        j.setSize(800,200);
        j.setVisible(true);

        MyListener_one m1=new MyListener_one(this);
        button_1.addActionListener(m1);
        button_2.addActionListener(m1);
        button_3.addActionListener(m1);
        button_4.addActionListener(m1);
        button_5.addActionListener(m1);
        button_6.addActionListener(m1);
    }

    public JFrame getJ() {
        return j;
    }

    public JButton getButton_1() {
        return button_1;
    }

    public JButton getButton_2() {
        return button_2;
    }

    public JButton getButton_3() {
        return button_3;
    }

    public JButton getButton_4() {
        return button_4;
    }

    public JButton getButton_5() {
        return button_5;
    }

    public JButton getButton_6() {
        return button_6;
    }

    public void setButton_6(JButton button_6) {
        this.button_6 = button_6;
    }


    public static void main(String[] args) {
        HUFFMAN h=new HUFFMAN();
    }
}
