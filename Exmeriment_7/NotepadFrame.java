package Exmeriment_7;

import javax.swing.*;

public class NotepadFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar jmenubar;
	private JMenu fileJmenu;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem fileExit;
	private JTextArea myText;
	private boolean changed=false;
	private boolean saved=false;
	private boolean opened=false;
	NotepadFrame(){
		super("�����ı�������");
		//��Ӳ˵�
		jmenubar=new JMenuBar();
		fileJmenu=new JMenu("�ļ�");
		open=new JMenuItem("��");
		save=new JMenuItem("����");
		saveAs=new JMenuItem("���Ϊ");
		fileExit=new JMenuItem("�˳�");
		fileJmenu.add(open);
		fileJmenu.add(save);
		fileJmenu.add(saveAs);
		fileJmenu.add(fileExit);
		jmenubar.add(fileJmenu);
		this.setJMenuBar(jmenubar);
		
		//�����ı���
		myText=new JTextArea();
		JScrollPane textPane=new JScrollPane();
		textPane.setBounds(20,20,100,50);
		textPane.setViewportView(myText);
		this.add(textPane);
		
		//���ļ�
		MyListener ml=new MyListener(this);
		open.addActionListener(ml);
		save.addActionListener(ml);
		saveAs.addActionListener(ml);
		fileExit.addActionListener(ml);
		myText.getDocument().addDocumentListener(ml);

		this.addWindowListener(ml);

		
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	
	public JMenuBar getJmenubar() {
		return jmenubar;
	}
	public JMenu getFileJmenu() {
		return fileJmenu;
	}
	public JMenuItem getOpen() {
		return open;
	}
	public JMenuItem getSave() {
		return save;
	}
	public JMenuItem getSaveAs() {
		return saveAs;
	}
	public JMenuItem getFileExit() {
		return fileExit;
	}
	public JTextArea getMyText() {
		return myText;
	}
	public boolean getChanged() {
		return this.changed;
	}
	public boolean getSaved() {
		return this.saved;
	}
	public boolean getOpened() {
		return this.opened;
	}
	public void setChanged(boolean t) {
		this.changed=t;
	}	
	public void setSaved(boolean t) {
		this.saved=t;
	}
	public void setOpened(boolean t) {
		this.opened=t;
	}
	public static void main(String[] args) {
		NotepadFrame a=new NotepadFrame();
	}
}
