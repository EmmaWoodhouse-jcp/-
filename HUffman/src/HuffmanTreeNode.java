public class HuffmanTreeNode {
    private int lchild;
    private int rchild;
    private int parent;
    private int w;
    private char ch;
    public HuffmanTreeNode(){
           lchild=0;
           rchild=0;
           parent=0;
           w=0;
           ch='\0';
    }

    public int getLchild() {
        return lchild;
    }

    public void setLchild(int lchild) {
        this.lchild = lchild;
    }

    public int getRchild() {
        return rchild;
    }

    public void setRchild(int rchild) {
        this.rchild = rchild;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }
}
