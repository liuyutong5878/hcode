package com.util;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * 
 * @ClassName LinkLabel.java
 * @Description 自定义带超链接的Label
 * 
 * @author 1904
 * @version V1.0
 * @Date 2014年12月17日 上午10:51:59
 */
public class LinkLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text, url;
	private boolean isSupported;

	public LinkLabel(String text, final String url) {
		this.text = text;
		this.url = url;
		try {
			this.isSupported = Desktop.isDesktopSupported()
					&& Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
		} catch (Exception e) {
			this.isSupported = false;
		}

		setText(false);
		addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				setText(isSupported);
				if (isSupported)
					setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				setText(false);
			}

			public void mouseClicked(MouseEvent e) {
				try {
					if(url != null){
						Desktop.getDesktop().browse(new java.net.URI(LinkLabel.this.url));
					}
				} catch (Exception ex) {
				}
			}
		});
	}

	private void setText(boolean b) {
		if (!b)
			setText("<html><font color=blue>" + text);
		else
			setText("<html><font color=red>" + text);
	}
}