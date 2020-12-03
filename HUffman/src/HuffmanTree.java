import javax.swing.*;
import java.io.*;
import java.util.Map;

public class HuffmanTree {
    private HuffmanTreeNode[] An;      //tree
    private  Code[] codes;
    private int n;
    private int m;
    static int maxx=2147483647;
    static StringBuffer sb;
    HuffmanTree(Map<Character, Integer> map,StringBuffer sbb){
        n=map.size();
        m=n*2-1;
        An=new HuffmanTreeNode[m+1];
        int d=1;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c=entry.getKey();
            int v=entry.getValue();
            An[d]=new HuffmanTreeNode();
            An[d].setCh(c);
            An[d].setW(v);
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            d++;
        }
        for(int i=map.size()+1;i<=m;i++){
            An[i]=new HuffmanTreeNode();
        }
        sb=sbb;
    }
    public void setHuffmanTree(){
        int p1,p2;
        int small_one,small_tow;
        for (int i = n+1; i <= m; i++) {
            p1 = 1;
            p2 = 1;
            small_one = maxx;
            small_tow = maxx;
            for (int j = 1; j < i; j++) {
                if (An[j].getParent() == 0) {
                    if (An[j].getW() <small_one) {
                        small_tow =small_one;
                       small_one = An[j].getW();
                        p2 = p1;
                        p1 = j;
                    }
                    else if (An[j].getW() < small_tow) {
                        small_tow = An[i].getW();
                        p2 = j;
                    }
                }
            }
            An[p1].setParent(i);
            An[p2].setParent(i);
            An[i].setLchild(p1);
            An[i].setRchild(p2);
            An[i].setW(An[p1].getW()+An[p2].getW());
        }
    }
    public void myShow(){
        System.out.println("叶子节点");
        for(int i=1;i<=n;i++){
            if(An[i].getCh()=='\n') System.out.println("第"+i+"个"+"回车换行");
            else System.out.println("第"+i+"个"+An[i].getCh());
            System.out.println("左孩子:"+An[i].getLchild()+"右孩子"+An[i].getRchild()+"双亲"+An[i].getParent());
            System.out.println("权值"+An[i].getW());
        }
        System.out.println();
        System.out.println("非叶子节点");
        for(int i=n+1;i<=m;i++){
            System.out.println("第"+i+"个节点");
            System.out.println("左孩子:"+An[i].getLchild()+"右孩子"+An[i].getRchild()+"双亲"+An[i].getParent());
            System.out.println("权值"+An[i].getW());
        }
    }
    public void Huffman_Code() throws IOException, ClassNotFoundException {
        int c,p;
        Code cd=new Code();
        codes=new Code[n+1];
        for(int i=1;i<=n;i++){
            cd.setStart(n-1);
            c=i;
            p=An[i].getParent();
            cd.setCh(An[i].getCh());
            cd.getBits()[cd.getStart()]='\0';
            while(p!=0){
                cd.setStart(cd.getStart()-1);
                if(An[p].getLchild()==c) cd.setBitsAi(cd.getStart(),'0');
                else cd.setBitsAi(cd.getStart(),'1');
                c=p;
                p=An[p].getParent();
            }
            codes[i]=new Code();
            codes[i].setBits(cd.getBits().clone());
            codes[i].setStart(cd.getStart());
            codes[i].setCh(cd.getCh());
        }

        for(int i=1;i<=n;i++){
            if(codes[i].getCh()!='\n') System.out.println("字符:"+codes[i].getCh()+"的编码为");
            else System.out.println("字符:回车的编码为");
            System.out.println(codes[i].getBits());
        }
        File file_1;
        System.out.println("选择加密文件存储的位置（cod文件)");
        JFileChooser chooser=new JFileChooser();
        int value=chooser.showSaveDialog(null);
        if(value==JFileChooser.APPROVE_OPTION) {
            //System.out.println("!!!!");
            file_1 = chooser.getSelectedFile();
            FileOutputStream fos = new FileOutputStream(file_1);

            OutputStreamWriter osw=new OutputStreamWriter(fos);
            for(int i=0;i<sb.length();i++){
                for(int j=1;j<codes.length;j++){
                    if(codes[j].getCh()==sb.charAt(i)){
                        for(int k=1;codes[j].getBits()[k]!='\0';k++) {
                            //System.out.println(codes[j].getBits()[k]);
                            osw.write(codes[j].getBits()[k]);
                            osw.flush();
                        }
                        osw.write('\n');
                    }
                }
            }
        }
        System.out.println("选择密码本存储的位置：");
        chooser=new JFileChooser();
        value=chooser.showSaveDialog(null);
        file_1=chooser.getSelectedFile();
        FileOutputStream fos=new FileOutputStream(file_1);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 1; i <= n; i++) {
            System.out.println("####");
            oos.writeObject(codes[i]);
        }
    }
}
