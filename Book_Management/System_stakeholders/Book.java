package 系统相关者;

import java.io.Serializable;

public class Book implements Serializable {
    private char[] no;                             //书号
    private char[] name;                        //书名
    private char[] author;                      //作者名
    private char[] press;                       //出版社
    private int count;                          //藏书量
    public Book(char[] no, char[] name, char[] author, char[] press, int count) {
        this.no = no;
        this.name = name;
        this.author = author;
        this.press = press;
        this.count = count;
    }

    public char[] getNo() {
        return no;
    }

    public void setNo(char[] no) {
        this.no = no;
    }

    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public char[] getAuthor() {
        return author;
    }

    public void setAuthor(char[] author) {
        this.author = author;
    }

    public char[] getPress() {
        return press;
    }

    public void setPress(char[] press) {
        this.press = press;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
