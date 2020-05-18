package view;

import listener.RegisterListener;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("all")
public class Register {
	
	public static JFrame signUpFrm=null;
	
	public static JLabel subTitleLabel=null;
	public static JLabel setUserNameLabel=null;
	public static JLabel setPassWordLabel=null;
	public static JLabel setConfirmPassWord=null;
	public static JTextField userNameField=null;
	public static JPasswordField passWordField=null;
	public static JPasswordField confirmPassWordField=null;
	public static JTextField chooseIconField=null;
	public static JButton signupButton=null;
	public static JButton back2LoginButton=null;
	
	public static JPanel subTitlePanel=null;
	public static JPanel userNamePanel=null;
	public static JPanel passWordPanel=null;
	public static JPanel confirmPassWordPanel=null;
	public static JPanel buttonPanel=null;
	public static JPanel chooseIconPanel=null;

	public static JLabel userExistLabel=null;
	public static JLabel notTheSameLabel=null;
	public static JLabel notNullLabel=null;

	public static JLabel background=null;

	public static JButton userIcon=null;

	public static JFileChooser iconFile=null;

	public static RegisterListener registerListener=null;
	
	public static void register(){
		initRegisterView();
		addRegisterListener();
	}
	
	private static void initRegisterView() {

		Font font=new Font("����",Font.PLAIN,18);
		Font errorfont=new Font("����", Font.PLAIN, 14);

		subTitleLabel=new JLabel("�û�ע��");
		subTitleLabel.setFont(new Font("����", Font.PLAIN, 28));
		setUserNameLabel=new JLabel("�����û���:");
		setUserNameLabel.setFont(font);
		setPassWordLabel=new JLabel("��������:");
		setConfirmPassWord=new JLabel("ȷ������:");
		setPassWordLabel.setFont(font);
		setConfirmPassWord.setFont(font);
		userNameField=new JTextField(15);
		passWordField=new JPasswordField(15);
		confirmPassWordField=new JPasswordField(15);
		signupButton=new JButton("ע��");
		signupButton.setFont(font);
		back2LoginButton=new JButton("���ص�¼");
		back2LoginButton.setFont(font);
		userIcon=new JButton("ѡ��ͼƬ");
		userIcon.setFont(new Font("����", Font.PLAIN, 14));
		chooseIconField=new JTextField(15);
		chooseIconField.setEditable(false);

		background=new JLabel(new ImageIcon("src/img/bg.jpg"));
		background.setBounds(0,0,500,500);

		userExistLabel=new JLabel("�û����Ѿ�����");
		userExistLabel.setBounds(200,50,120,100);
		userExistLabel.setForeground(Color.red);
		userExistLabel.setVisible(false);
		userExistLabel.setFont(errorfont);

		notTheSameLabel=new JLabel("�������벻һ��");
		notTheSameLabel.setBounds(200,50,120,100);
		notTheSameLabel.setForeground(Color.red);
		notTheSameLabel.setVisible(false);
		notTheSameLabel.setFont(errorfont);

		notNullLabel=new JLabel("�ı�����Ϊ��");
		notNullLabel.setBounds(200,50,120,100);
		notNullLabel.setForeground(Color.red);
		notNullLabel.setVisible(false);
		notNullLabel.setFont(errorfont);

		subTitlePanel=new JPanel();
		subTitlePanel.setOpaque(false);
		userNamePanel=new JPanel();
		userNamePanel.setOpaque(false);
		passWordPanel=new JPanel();
		passWordPanel.setOpaque(false);
		confirmPassWordPanel=new JPanel();
		confirmPassWordPanel.setOpaque(false);
		buttonPanel=new JPanel();
		buttonPanel.setOpaque(false);
		chooseIconPanel=new JPanel();
		chooseIconPanel.setOpaque(false);
		
		//------------------------------------------------------------
		signUpFrm=new JFrame("Signup");
		signUpFrm.setSize(500,500);
		signUpFrm.setLocationRelativeTo(null);//λ����������Ļ�м�
		signUpFrm.setVisible(true);
		signUpFrm.setResizable(false);
		signUpFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		chooseIconPanel.add(userIcon);
		chooseIconPanel.add(chooseIconField);
		chooseIconPanel.setBounds(95,320,300,50);

		subTitlePanel.add(subTitleLabel);
		subTitlePanel.setBounds(200,50,120,50);

		userNamePanel.add(setUserNameLabel);
		userNamePanel.add(userNameField);
		userNamePanel.setBounds(92,140,300,50);

		passWordPanel.add(setPassWordLabel);
		passWordPanel.add(passWordField);
		passWordPanel.setBounds(100,200,300,50);

		confirmPassWordPanel.add(setConfirmPassWord);
		confirmPassWordPanel.add(confirmPassWordField);
		confirmPassWordPanel.setBounds(100,260,300,50);

		buttonPanel.add(back2LoginButton);
		buttonPanel.add(signupButton);
		buttonPanel.setBounds(100,380,300,50 );

		signUpFrm.add(background);
		background.add(chooseIconPanel);
		background.add(subTitlePanel);
		background.add(userExistLabel);
		background.add(notNullLabel);
		background.add(notTheSameLabel);
		background.add(userNamePanel);
		background.add(passWordPanel);
		background.add(confirmPassWordPanel);
		background.add(buttonPanel);
	}
	
	private static void addRegisterListener() {
		registerListener=new RegisterListener();
		signupButton.addActionListener(registerListener);
		back2LoginButton.addActionListener(registerListener);
		userIcon.addActionListener(registerListener);
	}

}
