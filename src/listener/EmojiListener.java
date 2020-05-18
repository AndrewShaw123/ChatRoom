package listener;

import view.ClientView;
import view.EmojiView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class EmojiListener implements MouseListener{

	/*
		点击图片后把图片加入到发送框
		记录表情在文字间的位置
	*/

	@Override
	public void mouseClicked(MouseEvent e) {

		if(ClientListener.emojiPos==null){
			ClientListener.emojiPos=new ArrayList<>();
		}

		if(ClientListener.emojiList==null){
			ClientListener.emojiList=new ArrayList<>();
		}

		JLabel jLabel=(JLabel)e.getSource();
		ImageIcon icon=(ImageIcon)jLabel.getIcon();
		ClientView.textMessageArea.insertIcon(icon);

		ClientListener.emojiPos.add(ClientView.textMessageArea.getText().length());

		System.out.println("inserIconPos="+ClientView.textMessageArea.getText().length());

		EmojiView.emojiFrm.dispose();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
