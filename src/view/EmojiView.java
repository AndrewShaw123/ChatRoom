package view;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import listener.EmojiListener;


public class EmojiView {
	
	public static JFrame emojiFrm=null;
	public static EmojiListener emojiListener=null;
	
	public static void emojiView() {
		ininEmojiView();
	}
	
	private static void ininEmojiView() {

		emojiFrm=new JFrame();
		emojiFrm.setLayout(null);
		emojiFrm.setSize(320, 300);
		emojiFrm.setLocationRelativeTo(ClientView.clientFrm);
		emojiFrm.setTitle("ฑํว้");
		emojiFrm.setVisible(true);
		emojiFrm.setResizable(false);
		emojiFrm.setAlwaysOnTop(true);

		for (int row=0;row<5;row++) {

			for (int col=0;col<6;col++) {
				
				ImageIcon icon=new ImageIcon("src/img/emojiMonkey/"+(6*row+col+1)+".gif");
				JLabel labelblIcon=new JLabel(icon);
				labelblIcon.setSize(50,50);
				labelblIcon.setLocation(col*50,row*50);
				addEmojiListener(labelblIcon);
				emojiFrm.add(labelblIcon);
			}

		}
		
	}
	
	private static void addEmojiListener(JLabel label) {
		emojiListener=new EmojiListener();
		label.addMouseListener(emojiListener);
	}
	
}
