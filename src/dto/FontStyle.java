package dto;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

public class FontStyle implements Serializable{

	/*
		封装信息的内容和字体和表情包的类
	*/
	private String message;
	private FontAttribute fontAttribute;
	private List<ImageIcon> emojiList;
	private List<Integer> emojiPos;
	public FontStyle() {

	}

	public FontStyle(String message, FontAttribute fontAttribute, List<ImageIcon> emojiList, List<Integer> emojiPos) {
		this.message = message;
		this.fontAttribute = fontAttribute;
		this.emojiList = emojiList;
		this.emojiPos = emojiPos;
	}

	public String getMessage() {
		return message;
	}

	public FontAttribute getFontAttribute() {
		return fontAttribute;
	}

	public List<ImageIcon> getEmojiList() {
		return emojiList;
	}

	public List<Integer> getEmojiPos() {
		return emojiPos;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFontAttribute(FontAttribute fontAttribute) {
		this.fontAttribute = fontAttribute;
	}

	public void setEmojiList(List<ImageIcon> emojiList) {
		this.emojiList = emojiList;
	}

	public void setEmojiPos(List<Integer> emojiPos) {
		this.emojiPos = emojiPos;
	}

	@Override
	public String toString() {
		return "FontStyle{" +
				"message='" + message + '\'' +
				", fontAttribute=" + fontAttribute +
				", emojiList=" + emojiList +
				", emojiPos=" + emojiPos +
				'}';
	}
}
