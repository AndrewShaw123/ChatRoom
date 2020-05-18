package view;

import listener.LoginListener;

import javax.swing.*;
import java.awt.*;

public class Login {
	
	public static JFrame loginFrm=null;
	public static JLabel subTitleLabel=null;
	public static JLabel userNameLabel=null;
	public static JLabel passWordLabel=null;
	public static JButton signInButton=null;
	public static JButton signUpButton=null;
	public static JTextField userNameTextField=null;
	public static JPasswordField passWordTextField=null;
	
	public static JPanel subTitlePanel=null;
	public static JPanel userNamePanel=null;
	public static JPanel passWordPanel=null;
	public static JPanel buttonPanel=null;
	
	public static JLabel errorMessage=null;
	
	public static JLabel background=null;
	
	public static LoginListener loginListener=null;
	
	public static void main(String[] args) {
		login();
	}
	
	public static void login() {
		initLoginView();
		addLoginListener();
	}
	
	private static void initLoginView() {
		
		Font font=new Font("宋体",Font.PLAIN,18);
		
		subTitleLabel=new JLabel("用户登录");
		subTitleLabel.setFont(new Font("宋体", Font.PLAIN, 28));
		userNameLabel=new JLabel("用户名:");
		userNameLabel.setFont(font);
		passWordLabel=new JLabel("密 码:");
		passWordLabel.setFont(font);
		
		userNameTextField=new JTextField(20);
		passWordTextField=new JPasswordField(20);
		
		signInButton=new JButton("登录");
		signInButton.setFont(font);
		signUpButton=new JButton("注册");
		signUpButton.setFont(font);
		
		subTitlePanel=new JPanel();
		userNamePanel=new JPanel();
		passWordPanel=new JPanel();
		buttonPanel=new JPanel();
		
		errorMessage=new JLabel("用户名或密码错误");
		errorMessage.setForeground(Color.red);
		errorMessage.setVisible(false);
		errorMessage.setBounds(200,50,120,100);
		errorMessage.setFont(new Font("宋体", Font.PLAIN, 14));
		
		loginFrm=new JFrame("Login");
		
		background=new JLabel(new ImageIcon("src/img/bg.jpg"));
		background.setBounds(0,0,500,500);
		
		//-------------------------------------------------------------------
		
		loginFrm.setSize(500,500);
		loginFrm.setResizable(false);
		loginFrm.setLocationRelativeTo(null);//位置设置在屏幕中间
		loginFrm.setVisible(true);
		loginFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		subTitlePanel.setOpaque(false);
		subTitlePanel.add(subTitleLabel);
		subTitlePanel.setBounds(200,50,120,50);
		
		userNamePanel.setOpaque(false);
		userNamePanel.add(userNameLabel);
		userNamePanel.add(userNameTextField);
		userNamePanel.setBounds(100,140,300,50);
		
		passWordPanel.setOpaque(false);
		passWordPanel.add(passWordLabel);
		passWordPanel.add(passWordTextField);
		passWordPanel.setBounds(105,250,300,50);
		
		buttonPanel.setOpaque(false);
		buttonPanel.add(signUpButton);
		buttonPanel.add(signInButton);
		buttonPanel.setBounds(150,350,200,200);

		loginFrm.add(background);
		background.add(subTitlePanel);
		background.add(errorMessage);
		background.add(userNamePanel);
		background.add(passWordPanel);
		background.add(buttonPanel);
	}
	
	private static void addLoginListener() {
		
		loginListener=new LoginListener();
		signInButton.addActionListener(loginListener);
		signUpButton.addActionListener(loginListener);
		
	}
	
}
