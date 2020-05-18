package util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class NewFont {

	/*
		把选择的字体作用在发送栏里
	*/
	public static void setNewFont(JTextPane textMessageArea,Color fontColor,String fontFamily,int fontStyle,int fontSize) {
		
		Document document=textMessageArea.getDocument();
		
		StyleContext styleContext=StyleContext.getDefaultStyleContext();

		AttributeSet attributeSet=styleContext.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground,fontColor);//添加颜色样式
		attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.Family,fontFamily);
		attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.FontSize,fontSize);
		attributeSet=styleContext.addAttribute(attributeSet,fontStyle,true);
		
		int messageEnd=textMessageArea.getSelectionEnd();
		String message;
		try {
			message=document.getText(0,messageEnd);
			document.remove(0,messageEnd);
			document.insertString(0,message,attributeSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	}

}
