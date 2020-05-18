package core;

import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

	/*
		客户端的类
		开启客户端
	*/
	
	public static void startClient(String userName) throws Exception {
		System.out.println("-----Client-----");
		Socket client=new Socket(InetAddress.getLocalHost(),8888);
		Send send=new Send(client,userName);//初始化客户端发送
		new Thread(new Receive(client)).start();//初始化客户端接收
	}
	

}
