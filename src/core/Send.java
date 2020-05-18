package core;

import dto.Message;
import util.ChatUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Send{

	/*
		客户端发送的类
		发送消息到服务端
	*/

	private static ObjectOutputStream oos;
	private static Socket client;
	private String name;
	public Send(Socket client,String name) {
		this.client=client;
		this.name=name;
		Message msg=new Message(name,"loginName");
		try {
			oos=new ObjectOutputStream(client.getOutputStream());
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
			release();
		}
	}
	
	public static void send(Object msg) {
		
		try {
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			release();
		}
		
	}
	
	public static void release() {
		ChatUtil.close(oos,client);
	}

	public static void close(){
		try {
			client.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
