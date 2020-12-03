import javax.swing.*;
import java.io.*;

public class myDeZip {
    public void dezip() throws IOException {
        File file_1;
        JFileChooser chooser=new JFileChooser();
        int value=chooser.showSaveDialog(null);
        if(value==JFileChooser.APPROVE_OPTION) {
            file_1 = chooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(file_1);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String s = new String();
            while ((s = br.readLine()) != null) {
                sb.append(s).append('\n');
            }
            StringBuffer ssb=new StringBuffer();   //存放修改后的
            StringBuffer ss=new StringBuffer();    //存放每个十进制数
            int d=0;
            int sum=0;
            for(int i=0;i<sb.length();i++){
                if(sb.charAt(i)!='\n') {
                    //System.out.println("遍历");
                    ss.insert(d,sb.charAt(i));
                    d++;
                }
                else{
                    //System.out.println("找到一个位置");
                    sum=0;
                    int k = 0;
                    while (k<d) {
                       // System.out.println("获取ad");
                        int j = 1;
                        int ad = 1;
                        while (j < d - k) {
                            //System.out.println("ad*10");
                            ad *= 10;
                            j++;
                        }
                        sum += (ss.charAt(k)-'0') * ad;
                        k++;
                    }

                    if(i==sb.length()-1) break;;           //单独处理最后一个十进制数字
                    //获取数字后，转为二进制
                    StringBuffer sss=new StringBuffer();
                    int x=128;
                    for(int j=0;j<8;j++){
                        if(sum/x!=0){
                            sum-=x;
                            sss.append('1');
                        }
                        else sss.append('0');
                        x/=2;
                    }
                    ssb.append(sss);
                    sss.delete(0,sss.length());
                    d=0;
                }
            }
            //System.out.println("!!!");
            //System.out.println(sum);
            int b=1;
            int c;
            for( c=1;c<8;c++){
                int a=b;
                b*=2;
                if(sum>=a&&sum<b){
                    break;
                }
            }

            StringBuffer sss=new StringBuffer();
            int x=1;
            int w=c;
            while ((c--)!=1){
                x*=2;
            }

            for(int j=0;j<w;j++){
                if(sum/x!=0){
                    sum-=x;
                    sss.append('1');
                }
                else sss.append('0');
                x/=2;
            }
            ssb.append(sss);
            System.out.println(ssb);

        }
    }
}
