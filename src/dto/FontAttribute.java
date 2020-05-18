package dto;

import java.awt.*;
import java.io.Serializable;

public class FontAttribute implements Serializable{

	/*
		封装字体颜色大小字形的类
	*/
	private Color fontColor;
	private String fontFamily;
	private int fontSize;
	private int fontSyle;
	
	
	public FontAttribute() {
		
	}
	public FontAttribute(Color fontColor, String fontFamily, int fontSize, int fontSyle) {
		super();
		this.fontColor = fontColor;
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
		this.fontSyle = fontSyle;
	}
	public Color getFontColor() {
		return fontColor;
	}
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public int getFontSyle() {
		return fontSyle;
	}
	public void setFontSyle(int fontSyle) {
		this.fontSyle = fontSyle;
	}
	
	
}
