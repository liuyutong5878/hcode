package com.test;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Test {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String str = "MjAxNS0wMi0yNiMwMCNLNTk5IzEyOjQxIzIyOjM2IzMzMDAwMEs1OTgwTSNXQ04jR1pRIzExOjE3I+atpuaYjCPlub/lt54jMjYjMzIjMTAwMDAwMzAwMDQwMDAwMDAwMDAzMDAwMDAwMDA5MTAwMDAwMDAwMCNDMSMxNDE5ODU1NjU2Mjg0IzRBQkI0OEM5QzRBMDM1MTRGMDMwMzQ3RjEzREJBNjNEN0UyMjMyM0QyODFGMTA5NDI1NTlCOUM1";
		System.out.println(str.length());
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(10, 10, 439, 384);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(Color.WHITE);
		panel_1.setVisible(false);
		panel.add(panel_1);
		
		textField = new JTextField();
		textField.setBounds(86, 65, 175, 21);
		textField.setColumns(10);
		textField.setRequestFocusEnabled(true);
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textField.getText().length()>0){
					panel_1.setVisible(true);
				}else{
					panel_1.setVisible(false);
				}
				panel_1.setBounds(textField.getX(), textField.getY()+textField.getHeight()+1, 98, 170);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		panel.add(textField);
	}
}
