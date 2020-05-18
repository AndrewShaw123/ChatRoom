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
	
	public static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList<>();//Channel容器 实现群聊
	
	public static void startService() throws IOException {
		
		System.out.println("-----Service-----");
		ServerSocket server=new ServerSocket(8888);
		ServiceView.serviceView();
		ServiceView.portField.setText(""+server.getLocalPort());
		while(true) {
			Socket client=server.accept();
			System.out.println("一个客户端建立了连接");
			
			Channel channel=new Channel(client);
			
			ServiceView.modelSigninUsers.addElement(channel.name);//加入到服务端显示的列表中 显示在线人的名字
			ServiceView.messageTextArea.setText(ServiceView.messageTextArea.getText()+channel.name+"  在   "+loginTime()+"  时登录了"+"\n");
			
			all.add(channel);//每连接一个用户加入到容器中
			
			List onlineUserList=new ArrayList();//在线的人的名字
			onlineUserList.add("聊天室");
			for(Channel c:all) {
				onlineUserList.add(c.name);
			}
			channel.sendOnlineUserList(new Message(onlineUserList,"onlineUserList"));//发送到客户端更新客户端显示在线的人的名字
			
			ServiceView.logineNumberField.setText(""+all.size());//服务端显示在线人数
			
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
