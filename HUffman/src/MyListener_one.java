import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyListener_one implements ActionListener {
    private HUFFMAN oo;
    private File file_1;
    private HuffmanTree Hf;
    private HuffmanTree_DeCode deCode;
    MyListener_one(HUFFMAN o){
        oo=o;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==oo.getButton_1()){
                JFileChooser chooser=new JFileChooser();
                int value=chooser.showSaveDialog(null);
                if(value==JFileChooser.APPROVE_OPTION){
                    file_1=chooser.getSelectedFile();
                }
            }
            else if(e.getSource()==oo.getButton_2()){
                InputStreamReader isrr= null;
                StringBuffer sb=new StringBuffer();
                try {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(file_1),"UTF-8");
                    BufferedReader br=new BufferedReader(isr);
                    String s="";
                    while((s=br.readLine())!=null){
                        sb.append(s).append("\n");
                        //sb.append(s);
                    }
                    sb.deleteCharAt(sb.length() - 1);   //把最后的换行删掉，最后的换行不属于文本内容
                    System.out.println(sb);
                    System.out.println(sb.length());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Map<Character,Integer>map = new HashMap<Character,Integer>();
                for(int i=0;i<sb.length();i++){
                    if(!map.containsKey(sb.charAt(i))){
                        map.put(sb.charAt(i),1);
                    }
                    else{
                        map.put(sb.charAt(i),map.get(sb.charAt(i))+1);
                    }
                }

                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if(entry.getKey()=='\n') System.out.println("Key = " + "\\n" + ", Value = " + entry.getValue());
                    else System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
                //建立哈夫曼树
                Hf=new HuffmanTree(map,sb);
                Hf.setHuffmanTree();
                Hf.myShow();
            }
            else if(e.getSource()==oo.getButton_3()){
                try {
                    Hf.Huffman_Code();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if(e.getSource()==oo.getButton_4()){
                deCode=new HuffmanTree_DeCode();
                try {
                    deCode.Huffman_DeCode();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if(e.getSource()==oo.getButton_5()){
                myZip mz=new myZip();
                try {
                    mz.Ziping();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if(e.getSource()==oo.getButton_6()){
                myDeZip dz=new myDeZip();
                try {
                    dz.dezip();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    }
}
