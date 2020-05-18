package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ClientView;

public class OnlineUserListListener implements MouseListener{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		clickTwice(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//NotNeed
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//NotNeed
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//NotNeed
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//NotNeed
	}
	
	private void clickTwice(MouseEvent e) {
		
		if(e.getClickCount()==2){ 
			
			String select=(String)ClientView.usersList.getSelectedValue();
			
			System.out.println("##########################"+select+"##########################");
			
			if(select.equals("聊天室")) {//群聊
				
				ClientListener.isPrivateChat=false;
				
				//--------------------------------------------打开群聊的聊天框
				
				ClientView.privateMessageArea.setVisible(false);//设置私聊界面为不可见
				ClientView.privateMessageAreaScrollPane.setVisible(false);
				
				
				ClientView.messageArea.setVisible(true);//群聊界面设置为可见
				ClientView.messageAreaScrollPane.setVisible(true);
				
			}else{//私聊
				
				ClientListener.isPrivateChat=true;
				ClientListener.toUser=select;
				
				//--------------------------------------------打开私聊聊天框
				
				ClientView.messageArea.setVisible(false);//群聊界面设置为不可见
				ClientView.messageAreaScrollPane.setVisible(false);
				
				ClientView.privateMessageArea.setVisible(true);//设置私聊界面为可见
				ClientView.privateMessageAreaScrollPane.setVisible(true);
				
			}
			
		}
		
	}

}
