package core;

import view.ServiceView;
import dto.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@SuppressWarnings("all")
public class TCPService {
	
	public static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList<>();//Channel���� ʵ��Ⱥ��
	
	public static void startService() throws IOException {
		
		System.out.println("-----Service-----");
		ServerSocket server=new ServerSocket(8888);
		ServiceView.serviceView();
		ServiceView.portField.setText(""+server.getLocalPort());
		while(true) {
			Socket client=server.accept();
			System.out.println("һ���ͻ��˽���������");
			
			Channel channel=new Channel(client);
			
			ServiceView.modelSigninUsers.addElement(channel.name);//���뵽�������ʾ���б��� ��ʾ�����˵�����
			ServiceView.messageTextArea.setText(ServiceView.messageTextArea.getText()+channel.name+"  ��   "+loginTime()+"  ʱ��¼��"+"\n");
			
			all.add(channel);//ÿ����һ���û����뵽������
			
			List onlineUserList=new ArrayList();//���ߵ��˵�����
			onlineUserList.add("������");
			for(Channel c:all) {
				onlineUserList.add(c.name);
			}
			channel.sendOnlineUserList(new Message(onlineUserList,"onlineUserList"));//���͵��ͻ��˸��¿ͻ�����ʾ���ߵ��˵�����
			
			ServiceView.logineNumberField.setText(""+all.size());//�������ʾ��������
			
			new Thread(channel).start();
		}
	}
	
	private static String loginTime() {
		Date date=new Date();
		DateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String day=sd.format(date);
		return day;
	}

	public static void main(String[] args) {
		try {
			startService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
