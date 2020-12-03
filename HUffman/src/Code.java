import java.io.Serializable;

public class Code implements Serializable {
    static int MAXN=100;
    private char[] bits;
    private  int start;
    private  char ch;
    public Code(){
        bits=new char[MAXN];
        ch='\0';
        int start=0;
    }

    public char[] getBits() {
        return bits;
    }

    public void setBitsAi(int i,char c) {
        this.bits[i]=c;
    }

    public void setBits(char[] bits) {
        this.bits = bits;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }
}
