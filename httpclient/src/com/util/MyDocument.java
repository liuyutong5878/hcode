package com.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 * 
 * @ClassName MyDocument.java
 * @Description 限制输入框的字符长度
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月26日 下午4:30:50
 */
public class MyDocument extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int maxLength = 10;

	public MyDocument(int newMaxLength) {
		super();
		maxLength = newMaxLength;
	}

	public MyDocument() {
		this(10);
	}

	// 重载父类的insertString函数
	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		if (getLength() + str.length() > maxLength) {// 这里假定你的限制长度为10
			return;
		} else {
			super.insertString(offset, str, a);
		}
	}
}
