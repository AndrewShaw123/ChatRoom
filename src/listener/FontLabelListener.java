package listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;

import view.ClientView;
import dto.FontAttribute;
import util.NewFont;


public class FontLabelListener implements MouseListener{
	
	private final int[] fontStyle=new int[]{Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
	private final int[] fontSize= {12,18,20,22,24,26,28};
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JColorChooser colorChooser = new JColorChooser();
		Color color = colorChooser.showDialog(ClientView.clientFrm,"×ÖÌåÑÕÉ«",Color.BLACK);
		String family=ClientView.fontFamilyChooseComboBox.getSelectedItem().toString();
		int style=fontStyle[ClientView.fontStyleChooseComboBox.getSelectedIndex()];
		int size=fontSize[ClientView.fontSizeChooseComboBox.getSelectedIndex()];
		if(color!=null) {
			NewFont.setNewFont(ClientView.textMessageArea,color,family,style,size);
			ClientListener.fontAttribute=new FontAttribute(color,family,size,style);
		}
		
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
