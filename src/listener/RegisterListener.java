package listener;

import view.Login;
import view.Register;
import dao.LoginAndRegisterDao;
import dto.User;
import util.IOUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
@SuppressWarnings("all")
public class RegisterListener implements ActionListener{
	
	private static String username;
	private static String password;
	private static String secondPassword;
	private static String iconName="defaultIcon.png";
	private static File fileIcon=null;
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action=e.getActionCommand();
		if(action.equals("×¢²á")) {
			signup();
		}else if(action.equals("·µ»ØµÇÂ¼")){
			back2login();
		}else if(action.equals("Ñ¡ÔñÍ¼Æ¬")){
			chooseIcon();
		}
		
	}

	private void back2login() {
		Register.signUpFrm.dispose();
		Login.login();
	}

	private void signup() {
	
		username=Register.userNameField.getText();
		password=Register.passWordField.getText();
		secondPassword=Register.confirmPassWordField.getText();
		
		if(username.equals("")||password.equals("")||secondPassword.equals("")) {
			setAllLabelHidden();
			Register.notNullLabel.setVisible(true);
			return;
		}
		
		if(!password.equals(secondPassword)) {
			setAllLabelHidden();
			Register.notTheSameLabel.setVisible(true);
			return;
		}
		
		if(LoginAndRegisterDao.queryByUserName(username)) {
			setAllLabelHidden();
			Register.userExistLabel.setVisible(true);
			return;
		}
		setAllLabelHidden();
		User user=new User(username,password);
		LoginAndRegisterDao.saveUser(user);
		if(fileIcon!=null){
			IOUtil.saveUserIcon(fileIcon,username);
		}
		JOptionPane.showMessageDialog(Register.signUpFrm, "×¢²á³É¹¦", "ÏûÏ¢",JOptionPane.INFORMATION_MESSAGE);
		Register.userNameField.setText("");
		Register.passWordField.setText("");
		Register.confirmPassWordField.setText("");
		Register.chooseIconField.setText("");
	}
	
	private void setAllLabelHidden() {
		Register.notNullLabel.setVisible(false);
		Register.notTheSameLabel.setVisible(false);
		Register.userExistLabel.setVisible(false);
	}

	private void chooseIcon(){

		Register.iconFile=new JFileChooser();
		Register.iconFile.setFileFilter(new FileFilter(){
			public boolean accept(File f) {
				if(f.getName().endsWith(".jpg")||f.isDirectory()){
					return true;
				}
				return false;
			}
			public String getDescription() {
				return "Í¼Æ¬(*.jpg)";
			}
		});

		int i=Register.iconFile.showSaveDialog(Register.signUpFrm);
		if(i==JFileChooser.APPROVE_OPTION){
			fileIcon=Register.iconFile.getSelectedFile();
			Register.chooseIconField.setText(fileIcon.toString());
		}
	}

}
