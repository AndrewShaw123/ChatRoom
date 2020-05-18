package listener;

import core.TCPClient;
import dao.LoginAndRegisterDao;
import dto.User;
import view.ClientView;
import view.Login;
import view.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener{
	
	private String userName;
	private String passWord;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action=e.getActionCommand();
		
		if(action.equals("登录")) {
			try {
				loginAction();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if(action.equals("注册")){
			registerAction();
		}
		
	}

	private void registerAction() {
		Login.loginFrm.dispose();
		Register.register();//打开注册界面
	}

	private void loginAction() throws Exception {
		 
		userName=Login.userNameTextField.getText();
		passWord=Login.passWordTextField.getText();
		if(userName.equals("")||passWord.equals("")) {
			Login.errorMessage.setVisible(true);
			return;
		}
		
		User user=new User(userName,passWord); 
		System.out.println(user);
		if(LoginAndRegisterDao.queryByUserNameAndPassWord(user)) {
			//Enter System 登录成功
			System.out.println("Enter System...");
			
			Login.loginFrm.dispose();
			ClientView.clientView(userName);
			TCPClient.startClient(userName);
			
		}else {
			Login.errorMessage.setVisible(true);
		}
		
	}

}
