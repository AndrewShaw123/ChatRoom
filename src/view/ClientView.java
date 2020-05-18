package view;

import listener.ClientListener;
import listener.EmojiLabelListener;
import listener.FontLabelListener;
import listener.OnlineUserListListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

@SuppressWarnings("all")
public class ClientView {
	
	public static JFrame clientFrm=null;
	public static JTextPane messageArea=null;
	public static JTextPane privateMessageArea=null;
	public static JTextPane textMessageArea=null;
	public static Box list=null; 
	public static JList usersList=null;
	public static JScrollPane listScroll=null;
	public static JLabel listLabel=null;
	
	public static DefaultListModel modelSigninUsers=null;
	
	public static JButton sendButton=null;
	public static JButton closeButton=null;

	public static JButton changeUserIcon=null;
	
	public static JPanel buttonPanel=null;
	
	public static JScrollPane messageAreaScrollPane=null;
	public static JScrollPane privateMessageAreaScrollPane=null;
	
	public static ClientListener clientListener=null;
	public static OnlineUserListListener onlineUserListListener=null;
	public static FontLabelListener fontLabelListener=null;
	public static EmojiLabelListener emojiLabelListener=null;

	public static JLabel background=null;
	public static JLabel emojiLabel=null;
	public static JLabel fontChooseLabel=null;
	
	//public static JFileChooser file=null;
	public static JButton chooseFile=null;
	public static JButton checkHistory=null;
	
	public static JComboBox fontFamilyChooseComboBox=null;
	public static JComboBox fontSizeChooseComboBox=null;
	public static JComboBox fontStyleChooseComboBox=null;
	
	public static JPanel functionPanel=null;
	
	public static final String[] FONT_STYLE= {"普通","粗体","斜体","粗斜体"};
	public static final String[] FONT_FAMILY= {"宋体","仿宋","楷体","微软雅黑"};
	public static final Integer[] FONT_SIZE= {12,18,20,22,24,26,28};
	
	public static void clientView(String userName) {
		initClientView(userName);
		addClientListener();
	}
	
	private static void initClientView(String userName) {
		
		clientFrm=new JFrame("Wlecome,"+userName);
        ClientListener.userName=userName;
		File fileIcon=new File("src\\img\\usericon\\"+userName+".jpg");
		if(fileIcon.exists()){
			clientFrm.setIconImage(new ImageIcon("src\\img\\usericon\\"+userName+".jpg").getImage());
		}else{
			clientFrm.setIconImage(new ImageIcon("src\\img\\usericon\\defaultIcon.png").getImage());
		}
		
		background=new JLabel(new ImageIcon("src/img/bg.jpg"));
		background.setBounds(0,0,750,600);
		
		buttonPanel=new JPanel();
        buttonPanel.setOpaque(false);
        changeUserIcon=new JButton("修改头像");
		closeButton=new JButton("关闭");
		buttonPanel.add(closeButton);
		buttonPanel.add(changeUserIcon);
		buttonPanel.setBounds(1,520,180,40);
		
		messageArea=new JTextPane();
		textMessageArea=new JTextPane();
		
		privateMessageArea=new JTextPane();
		privateMessageArea.setVisible(false);
		
		messageArea.setOpaque(false);
		messageArea.setEditable(false);
		messageArea.setText("聊天室界面\n");
		
		privateMessageArea.setOpaque(false);
		privateMessageArea.setEditable(false);
		privateMessageArea.setText("私聊界面\n");
		
		messageAreaScrollPane=new JScrollPane(messageArea);
		messageAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		messageAreaScrollPane.setBounds(15,20,500,332);
		messageAreaScrollPane.setOpaque(false);//设置背景为透明
		
		
		privateMessageAreaScrollPane=new JScrollPane(privateMessageArea);
		privateMessageAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		privateMessageAreaScrollPane.setBounds(15,20,500,332);
		privateMessageAreaScrollPane.setOpaque(false);//设置背景为透明
		
		textMessageArea.setBounds(15,400,500,122);
		
		listLabel=new JLabel("在线用户：");
		usersList=new JList();
		modelSigninUsers=new DefaultListModel();
		usersList.setModel(modelSigninUsers);
		usersList.setVisibleRowCount(15);
		list=Box.createVerticalBox();
		listScroll=new JScrollPane(usersList);
		listScroll.setPreferredSize(new Dimension(100,150));
		listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		list.add(listLabel);
		list.add(listScroll);
		list.setBounds(530,17,200,507);

		functionPanel=new JPanel();
		functionPanel.setOpaque(false);
		functionPanel.setBounds(10,363,300,40);
		emojiLabel=new JLabel(new ImageIcon("src/img/emoji.png"));
		fontChooseLabel=new JLabel(new ImageIcon("src/img/font.png"));
		
		
		fontFamilyChooseComboBox=new JComboBox();
		for(String str:FONT_FAMILY) {
			fontFamilyChooseComboBox.addItem(str);
		}
		fontFamilyChooseComboBox.setSelectedItem("宋体");
		
		fontStyleChooseComboBox=new JComboBox();
		for(String str:FONT_STYLE) {
			fontStyleChooseComboBox.addItem(str);
		}
		fontStyleChooseComboBox.setSelectedItem("普通");
		
		fontSizeChooseComboBox=new JComboBox();
		for(int str:FONT_SIZE) {
			fontSizeChooseComboBox.addItem(str);
		}
		fontSizeChooseComboBox.setSelectedItem(12);
		
		chooseFile=new JButton("发送文件");
		chooseFile.setBounds(300,368,100,25);

		checkHistory=new JButton("消息记录");
		checkHistory.setBounds(415,368,100,25);

        sendButton=new JButton("发送");
        sendButton.setBounds(435,525,80,27);
		
		functionPanel.add(emojiLabel);
		functionPanel.add(fontChooseLabel);
		functionPanel.add(fontFamilyChooseComboBox);
		functionPanel.add(fontStyleChooseComboBox);
		functionPanel.add(fontSizeChooseComboBox);
		
		
		clientFrm.setSize(750,605);
		clientFrm.setResizable(false);
		clientFrm.setLocationRelativeTo(null);//位置设置在屏幕中间
		clientFrm.setVisible(true);

		clientFrm.setDefaultCloseOperation(clientFrm.DO_NOTHING_ON_CLOSE);
		clientFrm.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {//调用监听器的关闭功能
				ClientListener cl=new ClientListener();
				cl.close();
			}
		});
		
		clientFrm.add(background);
		background.add(messageAreaScrollPane);
		background.add(privateMessageAreaScrollPane);
		background.add(list);
		background.add(textMessageArea);
		background.add(buttonPanel);
		background.add(functionPanel);
		background.add(chooseFile);
		background.add(checkHistory);
        background.add(sendButton);
	}
	
	private static void addClientListener() {
		clientListener=new ClientListener();
		onlineUserListListener=new OnlineUserListListener();
		fontLabelListener=new FontLabelListener();
		emojiLabelListener=new EmojiLabelListener();

		usersList.addMouseListener(onlineUserListListener);
        fontChooseLabel.addMouseListener(fontLabelListener);
        emojiLabel.addMouseListener(emojiLabelListener);

		sendButton.addActionListener(clientListener);
		closeButton.addActionListener(clientListener);
		checkHistory.addActionListener(clientListener);
        changeUserIcon.addActionListener(clientListener);
        chooseFile.addActionListener(clientListener);
	}

}
