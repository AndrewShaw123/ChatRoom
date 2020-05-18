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
	public static FontAttribute fontAttribute=new FontAttribute(Color.black, "微软雅黑", 12, Font.PLAIN);
	public static List<ImageIcon> emojiList=null;
	public static List<Integer> emojiPos=null;
	public static String userName="";
	public static File sendFile=null;


	@Override
	public void actionPerformed(ActionEvent e) {
		String action=e.getActionCommand();
		if(action.equals("发送")) {
			send();
		}else if(action.equals("关闭")) {
			close();
		}else if(action.equals("消息记录")) {
			history();
		}else if(action.equals("修改头像")){
			changeIcon();
		}else if(action.equals("发送文件")){
            fileChoose();
		}
	}


	public void send() {

	    if(sendFile!=null){
            Message msg=new Message(sendFile,"fileSend");//normalMessage 代表用户发送的字符串信息
            Send.send(msg);
            reset();
            return;
        }

	    if(emojiList!=null){
            System.out.println("clear");
		    emojiList.clear();//重新初始化表情数组
        }

		/*
		 *   获得插入的所有表情
		 */
		
		StyledDocument styleDocument=ClientView.textMessageArea.getStyledDocument();

		for(int i=0;i<styleDocument.getRootElements()[0].getElement(0).getElementCount();i++) {//接收表情
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


		if(!isPrivateChat) {//判断是群聊还是私聊
            FontStyle fontStyle=new FontStyle(message,fontAttribute,emojiList,emojiPos);
            Message msg=new Message(fontStyle,"normalMessage");//normalMessage 代表用户发送的字符串信息
            Send.send(msg);
		}else {
            FontStyle fontStyle=new FontStyle(message,fontAttribute,emojiList,emojiPos);
            /*
               通过在Message类的status（代表发送消息的类型）在里面加上要私聊的人的用户名 privateMessage@xxx
               然后服务端解析的时候通过拆分字符串的方式获得私聊的用户对象的名称达到只对一个私聊对象单独发送的功能
            */
            Message msg=new Message(fontStyle,"privateMessage@"+toUser);//privateMessage 代表用户发送的字符串信息
            Send.send(msg);
		}

        reset();

	}
	
	
	public void close() {//用户下线
		Message msg=new Message("下线了！\n","logout");//给其他用户发送下线通知
		Send.send(msg);
		ClientView.clientFrm.dispose();
        IOUtil.writeHistory(ClientView.messageArea.getText(),userName,true);//退出时记录日志 每个人根据用户名写一个日志记录的文件
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
			    return "图片(*.jpg)";
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
            icon.setImage(icon.getImage().getScaledInstance(60,50,Image.SCALE_DEFAULT));//设置图片大小统一
            ClientView.textMessageArea.insertIcon(icon);
            try {
                document.insertString(document.getLength(),sendFile.toString(),null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
	}

	private void reset(){
        ClientView.textMessageArea.setText("");//清空发送框
        fontAttribute=new FontAttribute(Color.black,"微软雅黑",12,Font.PLAIN);//重置恢复原来的字体
        ClientView.textMessageArea.setFont(new Font("微软雅黑",Font.PLAIN,12));

        if (emojiPos!=null) {
            emojiPos.clear();//重新初始化表情位置数组
        }
        if (emojiList!=null) {
            emojiList.clear();//重新初始化表情数组
        }
        emojiPos=null;
        emojiList=null;
        sendFile=null;
    }
}
