package core;

import dto.FileMessage;
import dto.FontStyle;
import dto.Message;
import util.ChatUtil;
import view.ServiceView;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Channel implements Runnable{

    /*
		channe���ܵ�����
		ÿһ��Channel���һ���û��߳�
		�����û���������������û���
		һֱ���ڽ�����Ϣ��״̬
		����Ϣ��ʱ���ж���������ϢȻ��ͨ����������ת��
    */

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket client;
	private volatile boolean isRunning=true;
	public String name;

	public Channel(Socket client) {
		this.client=client;
		try {
			ois=new ObjectInputStream(client.getInputStream());
			oos=new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			release();
		}
		
		Message receiveName=(Message)receive();
		if(receiveName.getStatus().equals("loginName")) {
			this.name=(String)receiveName.getMessage();
			sendOtherSystemMessage(new Message(name+"�����ˣ�\n","systemMessage"));
		}
		
	}

	private Object receive() {
		Object datas=null;
		try {
			datas=ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			release();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			release();
		}
		return datas;
	}
	
	private void send(Object datas) {
		
		try {
			oos.writeObject(datas);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			release();
		}
		
	}
	
	@Override
	public void run() {
		
		while(isRunning) {
			
			Message str=(Message)receive();
			
			if(str!=null) {

			    if(str.getStatus().equals("fileSend")){
                    sendOtherFileMessage(str);
                }
				
				if(str.getStatus().equals("normalMessage")) {//Ⱥ����Ϣ
					sendOtherNormal(str);
				}
				
				if(str.getStatus().equals("systemMessage")) {//ϵͳ��Ϣ
					sendOtherSystemMessage(str);
				}

				if(str.getStatus().equals("logout")){//�˳���Ϣ
					sendOtherLogoutMessage(str);
				}
				
				if(str.getStatus().lastIndexOf("@")!=-1) {//˽��
					if((str.getStatus().substring(0,str.getStatus().lastIndexOf("@"))).equals("privateMessage")) {
						sendPrivateMessage(str);
					}
				}

			}
			
		}
	}
	
	
	private void sendOtherNormal(Object msg) {
		Message m=(Message)msg;
		FontStyle fontMessage=(FontStyle)m.getMessage();

		fontMessage.setMessage(name+"["+sendTime()+"]��"+"\n"+"\t"+fontMessage.getMessage());
		m.setMessage(fontMessage);
		for(Channel other:TCPService.all) {
			other.send(m);
		}
	}
	
	private void sendOtherSystemMessage(Object msg) {
		Message m=(Message)msg;
		m.setMessage("��ϵͳ��Ϣ����"+m.getMessage());
		for(Channel other:TCPService.all) {
			other.send(m);
		}
		
	}

	private void sendOtherLogoutMessage(Object msg){
		Message m=(Message)msg;
		m.setMessage("��ϵͳ��Ϣ����"+this.name+m.getMessage());
        this.setRunning(false);
		for(Channel other:TCPService.all) {
			if((other.name).equals(this.name)){
				continue;
			}
			other.send(m);
		}
		TCPService.all.remove(this);

		List onlineUserList=new ArrayList();//���ߵ��˵�����
		onlineUserList.add("������");
		for(Channel c:TCPService.all) {
			onlineUserList.add(c.name);
		}
		sendOnlineUserList(new Message(onlineUserList,"onlineUserList"));
		ServiceView.logineNumberField.setText(""+TCPService.all.size());
		ServiceView.messageTextArea.setText(ServiceView.messageTextArea.getText()+this.name+"  ��   "+sendTime()+"  ʱ������"+"\n");
		ServiceView.modelSigninUsers.removeElement(this.name);

	}

	private void sendOtherFileMessage(Object msg){

	    Message m=(Message)msg;
        File file=(File)m.getMessage();
        FileMessage fileMessage=new FileMessage(name,file);
        m.setMessage(fileMessage);
        for(Channel other:TCPService.all) {
            if((other.name).equals(this.name)) {
                continue;
            }
            other.send(m);
        }
    }
	
	private void sendPrivateMessage(Object msg) {
		
		Message m=(Message)msg;
		String str=(String)m.getStatus();
		String toUser=str.substring(str.lastIndexOf("@")+1);
		FontStyle fontMessage=(FontStyle)m.getMessage();
		fontMessage.setMessage(name+"["+sendTime()+"]��"+toUser+"˵��"+"\n"+"\t"+fontMessage.getMessage());
		m.setMessage(fontMessage);
		for(Channel other:TCPService.all) {
			if((other.name).equals(toUser)||(other.name).equals(this.name)) {
				other.send(msg);
			}
		}
		
		
	}
	
	public void sendOnlineUserList(Object msg) {
		Message m=(Message)msg;
		for(Channel other:TCPService.all) {
			other.send(m);
		}
	}
	
	private String sendTime() {//����ʱ��
		Date date=new Date();
		DateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String day=sd.format(date);
		return day;
	}
	
	private void release() {
		this.isRunning=false;
		ChatUtil.close(ois,oos,client);
	}
	
	//----------------------------------------------------------

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
}
 