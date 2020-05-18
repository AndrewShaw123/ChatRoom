package view;

import listener.ServiceListener;
import util.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
@SuppressWarnings("all")
public class ServiceView {
	
	public static JFrame serviceFrm=null;
	
	public static JLabel portLabel=null;
	public static JTextField portField=null;
	
	public static JLabel messageLabel=null;
	
	public static JButton stopButton=null;
	
	public static JTextArea messageTextArea=null;
	
	public static JLabel signinUsersLabel=null;
	
	public static Box signinUserslist=null;
	
	public static JList signinUsersList=null;
	public static JScrollPane signinUsersListScroll=null;
	public static DefaultListModel modelSigninUsers=null;

	public static JPanel portPanel=null;
	public static Box userListBox=null;
	public static Box messageBox=null;
	public static JPanel loginNumberPanel=null;
	
	public static JLabel loginNumberLabel=null;
	public static JTextField logineNumberField=null;
	
	public static ServiceListener serviceListener=null;

	public static JLabel background=null;

	public static JButton history=null;

	public static JScrollPane textMessageAreaScrollPane=null;
	
	public static void serviceView() {
		initServiceView();
		addServiceListener();
	}
	
	private static void initServiceView() {
		
		
		serviceFrm=new JFrame("服务端");

		background=new JLabel(new ImageIcon("src/img/bg.jpg"));
		background.setBounds(0,0,750,600);

		messageLabel=new JLabel("消息列表：");
		messageTextArea=new JTextArea();
		messageTextArea.setEditable(false);

		textMessageAreaScrollPane=new JScrollPane(messageTextArea);
		textMessageAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		portLabel=new JLabel("端口号：");
		portField=new JTextField(10);
		portField.setEditable(false);
		
		stopButton=new JButton("停止");
		
		signinUserslist=Box.createVerticalBox();
		signinUsersLabel=new JLabel("在线用户：");
		signinUsersList=new JList();
		modelSigninUsers=new DefaultListModel();
		signinUsersList.setModel(modelSigninUsers);
		signinUsersListScroll=new JScrollPane(signinUsersList);
		signinUsersListScroll.setPreferredSize(new Dimension(50,500));
		signinUsersListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		signinUserslist.add(signinUsersLabel);
		signinUserslist.add(signinUsersListScroll);

		portPanel=new JPanel();
		portPanel.add(portLabel);
		portPanel.add(portField);
		portPanel.add(stopButton);
		portPanel.setOpaque(false);
		portPanel.setBounds(0,20,350,100);

		history=new JButton("日志消息");
		history.setOpaque(false);
		history.setBounds(450,30,150,20);

		userListBox=Box.createVerticalBox();
		loginNumberPanel=new JPanel();
		loginNumberLabel=new JLabel("在线人数：");
		logineNumberField=new JTextField(5);
		logineNumberField.setText("0");
		logineNumberField.setEditable(false);
		loginNumberPanel.add(loginNumberLabel);
		loginNumberPanel.add(logineNumberField);
		loginNumberPanel.setOpaque(false);
		loginNumberPanel.setBounds(13,50,200,400);

		userListBox.add(signinUserslist);
		userListBox.setOpaque(false);
		userListBox.setBounds(50,80,100,400);

		messageBox=Box.createVerticalBox();
		messageBox.add(messageLabel);
		messageBox.add(textMessageAreaScrollPane);
		messageBox.setOpaque(false);
		messageBox.setBounds(180,80,500,400);
		
		serviceFrm.setSize(750,600);
		serviceFrm.setResizable(false);
		serviceFrm.setLocationRelativeTo(null);//位置设置在屏幕中间
		serviceFrm.setVisible(true);
		//serviceFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serviceFrm.setDefaultCloseOperation(serviceFrm.DO_NOTHING_ON_CLOSE);
		serviceFrm.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {//调用监听器的关闭功能
				IOUtil.writeHistory(messageTextArea.getText(),"service",true);//关闭时记录消息
				System.exit(0);
			}
		});

		serviceFrm.add(background);

		background.add(loginNumberPanel);
		background.add(portPanel);
		background.add(userListBox);
		background.add(messageBox);
		background.add(history);
	}
	
	private static void addServiceListener() {
		serviceListener=new ServiceListener();
		stopButton.addActionListener(serviceListener);
		history.addActionListener(serviceListener);
	}
	
}
