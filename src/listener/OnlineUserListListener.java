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
			
			if(select.equals("������")) {//Ⱥ��
				
				ClientListener.isPrivateChat=false;
				
				//--------------------------------------------��Ⱥ�ĵ������
				
				ClientView.privateMessageArea.setVisible(false);//����˽�Ľ���Ϊ���ɼ�
				ClientView.privateMessageAreaScrollPane.setVisible(false);
				
				
				ClientView.messageArea.setVisible(true);//Ⱥ�Ľ�������Ϊ�ɼ�
				ClientView.messageAreaScrollPane.setVisible(true);
				
			}else{//˽��
				
				ClientListener.isPrivateChat=true;
				ClientListener.toUser=select;
				
				//--------------------------------------------��˽�������
				
				ClientView.messageArea.setVisible(false);//Ⱥ�Ľ�������Ϊ���ɼ�
				ClientView.messageAreaScrollPane.setVisible(false);
				
				ClientView.privateMessageArea.setVisible(true);//����˽�Ľ���Ϊ�ɼ�
				ClientView.privateMessageAreaScrollPane.setVisible(true);
				
			}
			
		}
		
	}

}
