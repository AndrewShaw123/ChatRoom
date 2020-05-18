package dto;

import java.io.Serializable;

public class Message implements Serializable{

	/*
		封装用户发送的信息和发送信息的类型
		发送的信息使用了Object类型-->这样就可以选择发送时候发送包含字体信息和字体信息包装成一个整体（再封装）
	*/
	private Object message;
	/*
	 * 1 normalMessage  代表用户发送群聊消息
	 * 2 loginName      代表用户姓名
	 * 3 systemMessage  代表系统消息
	 * 4 onlineUserList 在线用户名字
	 * 5 privateMessage 私聊消息
	 * 6 logout         退出消息
	 * 7 fileSend       发送文件
	 */
	private String status;
	public Message() {
		
	}
	public Message(Object message,String status){
		this.message=message;
		this.status=status;
	}
	
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Message [message=" + message + ", status=" + status + "]";
	}

}
