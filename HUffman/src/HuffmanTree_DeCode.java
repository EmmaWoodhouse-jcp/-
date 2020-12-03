import javax.swing.*;
import java.io.*;

public class HuffmanTree_DeCode {
    private File file_1; //编码文件
    private File file_2; //密码本
    private File file_3; //解压后存放的地方
    private int num_bb;//编码文的个数
    private String[] sn;//存储每个编码
    private int Num; //密码种类个数
    private Code[] codes;//存储密码种类
    public void Huffman_DeCode() throws IOException, ClassNotFoundException {
        System.out.println("选择要解码的加密文件：");
        JFileChooser chooser = new JFileChooser();
        int value = chooser.showSaveDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
            file_1 = chooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file_1);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            sn = new String[100];
            String s = new String();
            num_bb = 0;
            while ((s = br.readLine()) != null) {
                sn[num_bb] = s;
                num_bb++;
            }
        }
        System.out.println("选择要解码的加密文件的密码本");
        chooser = new JFileChooser();
        value = chooser.showSaveDialog(null);
        if (value == JFileChooser.APPROVE_OPTION) {
            file_2 = chooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file_2);
            ObjectInputStream ois = new ObjectInputStream(fis);
            codes = new Code[100];
            Num = 0;
            try {
                codes[Num] = new Code();
                while ((codes[Num] = (Code) ois.readObject()) != null) {
                    System.out.println(codes[Num].getCh());
                    System.out.println(codes[Num].getBits());
                    Num++;
                }
            } catch (EOFException e) {
            } finally {
                ois.close();
            }
        }
        String c = new String();
        StringBuffer outs=new StringBuffer();
        int w=0;
        for (int i = 0; i < num_bb; i++) {
            for (int j = 0; j < Num; j++) {
                int d=100;
                c = String.valueOf(codes[j].getBits());
                for (int k = 1; k < c.length(); k++) {
                    if (c.charAt(k) == '\0') {
                        d=k;
                        break;
                    }
                }
                c = c.substring(1, d);
                if (sn[i].equals(c)){
                    //System.out.println("YES");
                    outs.insert(w,codes[j].getCh());
                    w++;
                }
            }
        }
        System.out.println("解密后的内容");
        System.out.println(outs);
        System.out.println("请选择存放的地方");
        chooser=chooser = new JFileChooser();
        value = chooser.showSaveDialog(null);
        if(value==JFileChooser.APPROVE_OPTION){
            file_3=chooser.getSelectedFile();
            FileOutputStream fos=new FileOutputStream(file_3);
            OutputStreamWriter osw=new OutputStreamWriter(fos);
            osw.write(String.valueOf(outs));
            osw.flush();
        }
    }
}
