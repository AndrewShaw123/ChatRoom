package core;

import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

	/*
		�ͻ��˵���
		�����ͻ���
	*/
	
	public static void startClient(String userName) throws Exception {
		System.out.println("-----Client-----");
		Socket client=new Socket(InetAddress.getLocalHost(),8888);
		Send send=new Send(client,userName);//��ʼ���ͻ��˷���
		new Thread(new Receive(client)).start();//��ʼ���ͻ��˽���
	}
	

}
