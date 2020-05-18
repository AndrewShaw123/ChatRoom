package listener;

import core.Send;
import dto.FontAttribute;
import dto.FontStyle;
import dto.Message;
import util.IOUtil;
import view.ClientHistoryView;
import view.ClientView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

@SuppressWarnings("all")
public class ClientListener implements ActionListener{
	
	public static String message="";
	public static boolean isPrivateChat=false;
	public static String toUser="";
	public static FontAttribute fontAttribute=new FontAttribute(Color.black, "΢���ź�", 12, Font.PLAIN);
	public static List<ImageIcon> emojiList=null;
	public static List<Integer> emojiPos=null;
	public static String userName="";
	public static File sendFile=null;


	@Override
	public void actionPerformed(ActionEvent e) {
		String action=e.getActionCommand();
		if(action.equals("����")) {
			send();
		}else if(action.equals("�ر�")) {
			close();
		}else if(action.equals("��Ϣ��¼")) {
			history();
		}else if(action.equals("�޸�ͷ��")){
			changeIcon();
		}else if(action.equals("�����ļ�")){
            fileChoose();
		}
	}


	public void send() {

	    if(sendFile!=null){
            Message msg=new Message(sendFile,"fileSend");//normalMessage �����û����͵��ַ�����Ϣ
            Send.send(msg);
            reset();
            return;
        }

	    if(emojiList!=null){
            System.out.println("clear");
		    emojiList.clear();//���³�ʼ����������
        }

		/*
		 *   ��ò�������б���
		 */
		
		StyledDocument styleDocument=ClientView.textMessageArea.getStyledDocument();

		for(int i=0;i<styleDocument.getRootElements()[0].getElement(0).getElementCount();i++) {//���ձ���
				ImageIcon icon = (ImageIcon) StyleConstants.getIcon(styleDocument.getRootElements()[0].getElement(0).getElement(i).getAttributes());
				if(icon!= null) {
					emojiList.add(icon);
					System.out.println("-------------------"+icon+"-------------------");
				}
		}

		message=ClientView.textMessageArea.getText();
		
		if(message.equals("")||message.trim().equals("")) {
			if(emojiList==null){
			    return;
            }
		}


		if(!isPrivateChat) {//�ж���Ⱥ�Ļ���˽��
            FontStyle fontStyle=new FontStyle(message,fontAttribute,emojiList,emojiPos);
            Message msg=new Message(fontStyle,"normalMessage");//normalMessage �����û����͵��ַ�����Ϣ
            Send.send(msg);
		}else {
            FontStyle fontStyle=new FontStyle(message,fontAttribute,emojiList,emojiPos);
            /*
               ͨ����Message���status����������Ϣ�����ͣ����������Ҫ˽�ĵ��˵��û��� privateMessage@xxx
               Ȼ�����˽�����ʱ��ͨ������ַ����ķ�ʽ���˽�ĵ��û���������ƴﵽֻ��һ��˽�Ķ��󵥶����͵Ĺ���
            */
            Message msg=new Message(fontStyle,"privateMessage@"+toUser);//privateMessage �����û����͵��ַ�����Ϣ
            Send.send(msg);
		}

        reset();

	}
	
	
	public void close() {//�û�����
		Message msg=new Message("�����ˣ�\n","logout");//�������û���������֪ͨ
		Send.send(msg);
		ClientView.clientFrm.dispose();
        IOUtil.writeHistory(ClientView.messageArea.getText(),userName,true);//�˳�ʱ��¼��־ ÿ���˸����û���дһ����־��¼���ļ�
		System.exit(0);
	}

	private void history(){
		ClientHistoryView.clientHistoryView();
        String message=IOUtil.readHistory(userName);
        ClientHistoryView.historyArea.setText(message);
	}

	private void changeIcon(){

		JFileChooser iconChosser=new JFileChooser();
		iconChosser.setFileFilter(new FileFilter(){
			public boolean accept(File f) {
				if(f.getName().endsWith(".jpg")||f.isDirectory()){
					return true;
				}
				return false;
			}
			public String getDescription() {
			    return "ͼƬ(*.jpg)";
			}
		});

		int i=iconChosser.showSaveDialog(ClientView.clientFrm);
		if(i==JFileChooser.APPROVE_OPTION){
			File fileIcon=iconChosser.getSelectedFile();
			IOUtil.saveUserIcon(fileIcon,userName);
		}

	}

	private void fileChoose(){

        JFileChooser fileChooser=new JFileChooser();
        int i=fileChooser.showOpenDialog(ClientView.clientFrm);

        if(i==JFileChooser.APPROVE_OPTION){
            sendFile=fileChooser.getSelectedFile();
            Document document=ClientView.textMessageArea.getDocument();
            ClientView.textMessageArea.setCaretPosition(document.getLength());
            ImageIcon icon=new ImageIcon("src\\img\\file.jpg");
            icon.setImage(icon.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT));//����ͼƬ��Сͳһ
            ClientView.textMessageArea.insertIcon(icon);
            try {
                document.insertString(document.getLength(),sendFile.toString(),null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
	}

	private void reset(){
        ClientView.textMessageArea.setText("");//��շ��Ϳ�
        fontAttribute=new FontAttribute(Color.black,"΢���ź�",12,Font.PLAIN);//���ûָ�ԭ��������
        ClientView.textMessageArea.setFont(new Font("΢���ź�",Font.PLAIN,12));

        if (emojiPos!=null) {
            emojiPos.clear();//���³�ʼ������λ������
        }
        if (emojiList!=null) {
            emojiList.clear();//���³�ʼ����������
        }
        emojiPos=null;
        emojiList=null;
        sendFile=null;
    }
}
