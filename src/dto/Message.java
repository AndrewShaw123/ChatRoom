package dto;

import java.io.Serializable;

public class Message implements Serializable{

	/*
		��װ�û����͵���Ϣ�ͷ�����Ϣ������
		���͵���Ϣʹ����Object����-->�����Ϳ���ѡ����ʱ���Ͱ���������Ϣ��������Ϣ��װ��һ�����壨�ٷ�װ��
	*/
	private Object message;
	/*
	 * 1 normalMessage  �����û�����Ⱥ����Ϣ
	 * 2 loginName      �����û�����
	 * 3 systemMessage  ����ϵͳ��Ϣ
	 * 4 onlineUserList �����û�����
	 * 5 privateMessage ˽����Ϣ
	 * 6 logout         �˳���Ϣ
	 * 7 fileSend       �����ļ�
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
