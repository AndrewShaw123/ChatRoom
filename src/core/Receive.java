package core;

import dto.FileMessage;
import dto.FontAttribute;
import dto.FontStyle;
import dto.Message;
import util.ChatUtil;
import util.IOUtil;
import view.ClientView;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

@SuppressWarnings("all")
public class Receive implements Runnable{

	/*
		�ͻ��˽��յ���
		���ܷ���˵���Ϣ
	*/
	private static ObjectInputStream ois;
	private static Socket client;
	private static boolean isRunning=true;
	public static AttributeSet attributeSet=null;
	public static StyleContext styleContext=StyleContext.getDefaultStyleContext();
	public static List<ImageIcon> emojiList=null;//���������
	public static List<Integer> emojiPos=null;//������������λ�ü�¼������

	public Receive(Socket client) {
		this.client=client;
		try {
			ois=new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Object receiveMessage() {
		Object datas=null;
		try {
			datas = ois.readObject();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			release();
		} catch (IOException e) {
			e.printStackTrace();
			release();
		}
		return datas;
		
	}
	

	@Override
	public void run() {

	    /*
	        �������߳�ʹ�ͻ���һֱ���ڽ���״̬
	        ���ʵʱ����Ϣ
        */
		while(isRunning) {

			Message msg=(Message)receiveMessage();
            System.out.println("���ܷ����ܵģ�");
			System.out.println(msg);
			
			if(msg==null) {
				return;
			}

			if(msg.getStatus().equals("fileSend")){
                receiveFile(msg);
            }

			if(msg.getStatus().equals("onlineUserList")) {
                onlineUserListStatus(msg);
			}


			if(msg.getStatus().equals("systemMessage")) {
                systemMessageStatus(msg);
			}

			if(msg.getStatus().equals("logout")){
                logoutStatus(msg);
            }
			
			
			if(msg.getStatus().equals("normalMessage")) {
                normalMessageStatus(msg);
			}
			
			
			if(msg.getStatus().lastIndexOf("@")!=-1) {
				if((msg.getStatus().substring(0,msg.getStatus().lastIndexOf("@"))).equals("privateMessage")) {
                    privateMessageStatus(msg);
				}
			}
			
		}
		
	}

	private void onlineUserListStatus(Message msg){
        List list=(List)msg.getMessage();
        ClientView.modelSigninUsers.removeAllElements();
        for (Object user : list) {
            System.out.println(user);
            ClientView.modelSigninUsers.addElement(user);
        }
    }

    private void systemMessageStatus(Message msg){
        System.out.println(msg);
        System.out.println(msg.getMessage());
        attributeSet=styleContext.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground,Color.BLACK);
        appendMessageArea(""+msg.getMessage());
    }

    private void receiveFile(Message msg){

        FileMessage fileMessage=(FileMessage)msg.getMessage();
        File getFile=fileMessage.getFile();
        String fileType=getFile.toString().substring(getFile.toString().lastIndexOf("."));
        System.out.println("))))"+fileType);
        int n = JOptionPane.showConfirmDialog(ClientView.clientFrm, "���յ�"+fileMessage.getSendName()+"�����ļ�("+fileType+"����)�Ƿ���ܣ�", "�Ƿ�����ļ�",JOptionPane.YES_NO_OPTION);
        if(n==JOptionPane.YES_OPTION){
            JFileChooser chooseSavePath=new JFileChooser();
            int i=chooseSavePath.showSaveDialog(ClientView.clientFrm);
            if(i==JFileChooser.APPROVE_OPTION){
                File receiveFile=chooseSavePath.getSelectedFile();
                IOUtil.saveFile(getFile,receiveFile);
                JOptionPane.showMessageDialog(ClientView.clientFrm, "���ճɹ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void logoutStatus(Message msg){
	    systemMessageStatus(msg);
    }

    private void normalMessageStatus(Message msg){
        FontStyle fontStyle=(FontStyle)msg.getMessage();
        if(fontStyle.getEmojiList()!=null) {
            emojiList=fontStyle.getEmojiList();
            emojiPos=fontStyle.getEmojiPos();
        }

        String normalMessage=fontStyle.getMessage();

        FontAttribute fontAttribute=fontStyle.getFontAttribute();

        attributeSet=styleContext.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground,fontAttribute.getFontColor());
        attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.Family,fontAttribute.getFontFamily());
        attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.FontSize,fontAttribute.getFontSize());
        attributeSet=styleContext.addAttribute(attributeSet,fontAttribute.getFontSyle(),true);
        appendMessageArea(""+normalMessage);
    }

    private void privateMessageStatus(Message msg){
        FontStyle fontStyle=(FontStyle)msg.getMessage();
        if(fontStyle.getEmojiList()!=null) {
            emojiList=fontStyle.getEmojiList();
            emojiPos=fontStyle.getEmojiPos();
        }

        String normalMessage=fontStyle.getMessage();

        FontAttribute fontAttribute=fontStyle.getFontAttribute();

        attributeSet=styleContext.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground,fontAttribute.getFontColor());
        attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.Family,fontAttribute.getFontFamily());
        attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.FontSize,fontAttribute.getFontSize());
        attributeSet=styleContext.addAttribute(attributeSet,fontAttribute.getFontSyle(),true);

        appendPrivateMessageArea(normalMessage);//���˽�ĵ���Ϣ ��ӵ�˽�ĵ������
        appendMessageArea(""+normalMessage);//ͬʱ��ӵ�Ⱥ�ĵ������
    }

	private void appendPrivateMessageArea(String normalMessage){//�ͻ����ڽ��ܵ�˽����Ϣ���ڽ�����ʾ����Ϣ
        Document document=null;
        try {
            document=ClientView.privateMessageArea.getDocument();
            int messageStart=normalMessage.indexOf("��")+2;//����\n��\t����+2

            if(normalMessage.indexOf("[")!=-1){//���ͷ��
                String sendName=normalMessage.substring(0,normalMessage.indexOf("["));
                System.out.println("sendName="+sendName);

                File fileIcon=new File("src\\img\\usericon\\"+sendName+".jpg");

                if(fileIcon.exists()){//������ͷ�� ��ʾͷ��
                    ClientView.privateMessageArea.setCaretPosition(document.getLength());
                    System.out.println("exist");
                    ImageIcon icon=new ImageIcon(fileIcon.toString());
                    icon.setImage(icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));//����ͼƬ��Сͳһ
                    ClientView.privateMessageArea.insertIcon(icon);
                }else{//������ͷ�� ʹ��Ĭ�ϵ�ͷ������ʹ�ø�MyBatisС���ͷ����ΪĬ��ͷ��
                    ClientView.privateMessageArea.setCaretPosition(document.getLength());
                    System.out.println("notExist");
                    ImageIcon icon=new ImageIcon("src\\img\\usericon\\defaultIcon.png");
                    icon.setImage(icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
                    ClientView.privateMessageArea.insertIcon(icon);
                }
            }

            document.insertString(document.getLength(),normalMessage.substring(0,messageStart),null);

            if(emojiList!=null) {//�б��飨��ñ���ͱ�������λ�� �ﵽͼ�Ļ�ϣ�
                document.insertString(document.getLength(),normalMessage.substring(messageStart,messageStart+emojiPos.get(0)),attributeSet);
                for(int i=0;i<emojiList.size();i++) {
                    ClientView.privateMessageArea.setCaretPosition(document.getLength());
                    ImageIcon icon=new ImageIcon(emojiList.get(i).toString());
                    ClientView.privateMessageArea.insertIcon(icon);
                    if(i+1<emojiList.size()){
                        document.insertString(document.getLength(),normalMessage.substring(messageStart+emojiPos.get(i),messageStart+emojiPos.get(i+1)),attributeSet);
                    }else{
                        document.insertString(document.getLength(),normalMessage.substring(messageStart+emojiPos.get(i)),attributeSet);
                    }
                }
            }else{//�ޱ��� ֱ������ı�
                document.insertString(document.getLength(),normalMessage.substring(messageStart),attributeSet);
            }
            document.insertString(document.getLength(),"\n",null);//��ӻس�����
            ClientView.privateMessageArea.setCaretPosition(document.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
	}
	
	private void appendMessageArea(String normalMessage) {//�ͻ����ڽ��ܵ�Ⱥ����Ϣ���ڽ�����ʾ����Ϣ
		Document document=null;
		try {
			document=ClientView.messageArea.getDocument();
			int messageStart=normalMessage.indexOf("��")+2;//����\n��\t����+2

            if(normalMessage.indexOf("[")!=-1){//���ͷ��
                String sendName=normalMessage.substring(0,normalMessage.indexOf("["));
                System.out.println("sendName="+sendName);

                File fileIcon=new File("src\\img\\usericon\\"+sendName+".jpg");

                if(fileIcon.exists()){
                    ClientView.messageArea.setCaretPosition(document.getLength());
                    System.out.println("exist");
                    ImageIcon icon=new ImageIcon(fileIcon.toString());
                    icon.setImage(icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));//����ͼƬ��Сͳһ
                    ClientView.messageArea.insertIcon(icon);
                }else{
                    ClientView.messageArea.setCaretPosition(document.getLength());
                    System.out.println("notExist");
                    ImageIcon icon=new ImageIcon("src\\img\\usericon\\defaultIcon.png");
                    icon.setImage(icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));
                    ClientView.messageArea.insertIcon(icon);
                }
            }

            document.insertString(document.getLength(),normalMessage.substring(0,messageStart),null);

			if(emojiList!=null) {//�б��飨��ñ���ͱ�������λ�� �ﵽͼ�Ļ�ϣ�
                document.insertString(document.getLength(),normalMessage.substring(messageStart,messageStart+emojiPos.get(0)),attributeSet);
				for(int i=0;i<emojiList.size();i++) {
                    ClientView.messageArea.setCaretPosition(document.getLength());
                    ImageIcon icon=new ImageIcon(emojiList.get(i).toString());//���ֱ����emojiList.get(i)�õ��Ļ����鲻�ᶯ ��ʵһ��ʼ��·�������������鷳�ˣ�������
					ClientView.messageArea.insertIcon(icon);
                    if(i+1<emojiList.size()){
				        document.insertString(document.getLength(),normalMessage.substring(messageStart+emojiPos.get(i),messageStart+emojiPos.get(i+1)),attributeSet);
                    }else{
                        document.insertString(document.getLength(),normalMessage.substring(messageStart+emojiPos.get(i)),attributeSet);
                    }
				}
			}else{//�ޱ��� ����ı�
                document.insertString(document.getLength(),normalMessage.substring(messageStart),attributeSet);
            }
            document.insertString(document.getLength(),"\n",null);
			ClientView.messageArea.setCaretPosition(document.getLength());
            emojiList=null;
            emojiPos=null;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

    public static void setIsRunning(boolean isRunning) {
        Receive.isRunning = isRunning;
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void release() {
		isRunning=false;
		ChatUtil.close(ois,client);
	}

	public static void close(){
        try {
            client.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
