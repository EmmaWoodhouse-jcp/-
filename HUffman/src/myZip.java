import javax.swing.*;
import java.io.*;

public class myZip {
    private File file_1; //编码文件
    private File file_2; //编码文件的压缩文件
    private int a; //压缩前字节数
    private int b; //压缩后字节数
    public void Ziping() throws IOException {
        JFileChooser chooser=new JFileChooser();
        int value=chooser.showSaveDialog(null);
        if(value==JFileChooser.APPROVE_OPTION){
            file_1 = chooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file_1);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb=new StringBuffer();
            String s = new String();
            int nn=0;
            int n2=0;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                nn++;
            }
            nn--;
            a=nn+sb.length();
            System.out.println(a);
            System.out.println(sb);
            StringBuffer sb_2=new StringBuffer();       //存储压缩后字符串
            int w=sb.length()%8;
            int i=0;
            for(;i<sb.length()-w;i+=8){
                int sum=0;
                int d=0;
                for(int k=i;k<i+8;k++){
                    int cc=1;
                    int d2=(8-d)-1;
                    while (d2!=0){
                        cc*=2;
                        d2--;
                    }
                    sum+=(sb.charAt(k)-'0')*cc;
                    d++;
                }
                //System.out.println(sum);
                sb_2.append(sum).append('\n');
            }

            int sum=0;
            int d=0;
            for(int k=i;k<i+w;k++){
                int cc=1;
                int d2=(w-d)-1;
                while (d2!=0){
                    cc*=2;
                    d2--;
                }
                sum+=(sb.charAt(k)-'0')*cc;
                d++;
            }
            System.out.println(sum);
            sb_2.append(sum);
            System.out.println("选择存放的位置");
            chooser=new JFileChooser();
            value=chooser.showSaveDialog(null);
            if(value==JFileChooser.APPROVE_OPTION){
                file_2=chooser.getSelectedFile();
                FileOutputStream fos=new FileOutputStream(file_2);
                OutputStreamWriter osw=new OutputStreamWriter(fos);
                osw.write(String.valueOf(sb_2));
                osw.flush();
            }
            b=sb_2.length();
            //System.out.println(sb_2.length());
            System.out.println("与编码文件的压缩比为：");
            System.out.println((double)b/a*100+"%");
            System.out.println("选择原文件");
            chooser=new JFileChooser();
            value=chooser.showSaveDialog(null);
            if(value==JFileChooser.APPROVE_OPTION){
                File file_3=chooser.getSelectedFile();
                System.out.println("与未编码的原文件的压缩比为：");
                //System.out.println(file_3.length());
                System.out.println((double)file_3.length()/a*100+"%");
            }
        }
    }
}
