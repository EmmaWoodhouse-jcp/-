package Exmeriment_7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyListener implements ActionListener,DocumentListener,WindowListener{
	NotepadFrame oo;
	private File file;
	private File file2;
	MyListener(NotepadFrame o){
		this.oo=o;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==oo.getOpen()) {	//��
			
			//�ڴ��ļ�ǰ���ı����ѱ䶯���ж��Ƿ񱣴�д������ݡ�
			if(oo.getChanged()) {
				int ans=JOptionPane.showConfirmDialog(null,"�Ƿ񱣴�","���������ļ�",JOptionPane.YES_NO_OPTION);
				if(ans==0) {
					JFileChooser chooser=new JFileChooser();
					int value=chooser.showSaveDialog(null);
					if(value==JFileChooser.APPROVE_OPTION) {
						oo.setSaved(true);
						file2=chooser.getSelectedFile();
						try {
							OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file2),"UTF-8");
							osw.write(oo.getMyText().getText());
							osw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
			
			//�����ļ�
			JFileChooser chooser=new JFileChooser();
			int value=chooser.showOpenDialog(null);
			if(value==JFileChooser.APPROVE_OPTION) {
				file=chooser.getSelectedFile();
				oo.setOpened(true);
				try {
					InputStreamReader isrr = new InputStreamReader(new FileInputStream(file),"UTF-8");
					BufferedReader isr = new BufferedReader(isrr);
					
					StringBuffer str = new StringBuffer();
					String s;
					while ((s = isr.readLine()) != null) {
					    str.append(s).append('\n');
					}
					isr.close();
					oo.getMyText().setText(str.toString());
					oo.setChanged(false);
				} catch (UnsupportedEncodingException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
		}
		if(e.getSource()==oo.getSave()) {  //����
			oo.setSaved(true);
			OutputStreamWriter isr;
			try {
				isr = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
				isr.write(oo.getMyText().getText());
				isr.close();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==oo.getSaveAs()) { //���Ϊ
			
			JFileChooser chooser=new JFileChooser();
			int value=chooser.showSaveDialog(null);
			if(value==JFileChooser.APPROVE_OPTION) {
				oo.setSaved(true);
				file2=chooser.getSelectedFile();
				try {
					OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file2),"UTF-8");
					osw.write(oo.getMyText().getText());
					osw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		//�ر��ж��Ƿ񱣴洦�����XЧ��һ��
		if(e.getSource()==oo.getFileExit()) {
			if(oo.getChanged()==true&&oo.getSaved()==false&&oo.getOpened()==false) {
				int ans=JOptionPane.showConfirmDialog(null,"�Ƿ񱣴�","���ڹر��ļ�",JOptionPane.YES_NO_OPTION);
				if(ans==0) {
					JFileChooser chooser=new JFileChooser();
					int value=chooser.showSaveDialog(null);
					if(value==JFileChooser.APPROVE_OPTION) {
						oo.setSaved(true);
						file2=chooser.getSelectedFile();
						try {
							OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file2),"UTF-8");
							osw.write(oo.getMyText().getText());
							osw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
					System.exit(0);
				}
			}		
			
			//�ļ��޸�����δ���棬����һ���Ѿ��򿪵��ļ�����Ҫ���б���
			else if(oo.getChanged()==true&&oo.getSaved()==false&&oo.getOpened()==true) {
				int ans=JOptionPane.showConfirmDialog(null,"�Ƿ񱣴�","���ڹر��ļ�",JOptionPane.YES_NO_OPTION);
				if(ans==0) {
					oo.setSaved(true);
					OutputStreamWriter isr;
					try {
						isr = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
						isr.write(oo.getMyText().getText());
						isr.close();
					}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		System.exit(0);
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
	
		oo.setChanged(true);
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		
		oo.setChanged(true);
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	//��X�ж��Ƿ񱣴洦����ر�Ч��һ��
	public void windowClosing(WindowEvent e) {
		//�ļ��޸�����δ���棬����һ��δ�򿪵��ļ�����Ҫ�������Ϊ
		if(oo.getChanged()==true&&oo.getSaved()==false&&oo.getOpened()==false) {
			int ans=JOptionPane.showConfirmDialog(null,"�Ƿ񱣴�","���ڹر��ļ�",JOptionPane.YES_NO_OPTION);
			if(ans==0) {
				JFileChooser chooser=new JFileChooser();
				int value=chooser.showSaveDialog(null);
				if(value==JFileChooser.APPROVE_OPTION) {
					oo.setSaved(true);
					file2=chooser.getSelectedFile();
					try {
						OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file2),"UTF-8");
						osw.write(oo.getMyText().getText());
						osw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else {
				System.exit(0);
			}
		}		
		
		//�ļ��޸�����δ���棬����һ���Ѿ��򿪵��ļ�����Ҫ���б���
		else if(oo.getChanged()==true&&oo.getSaved()==false&&oo.getOpened()==true) {
			int ans=JOptionPane.showConfirmDialog(null,"�Ƿ񱣴�","���ڹر��ļ�",JOptionPane.YES_NO_OPTION);
			if(ans==0) {
				oo.setSaved(true);
				OutputStreamWriter isr;
				try {
					isr = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
					isr.write(oo.getMyText().getText());
					isr.close();
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				System.exit(0);
			}
		}		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
