package com.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.config.SysConfig;
import com.constant.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.logic.LoginFrameLogic;
import com.util.LinkLabel;
import com.util.MyDocument;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Properties;

public class LoginFrame {

	private static JFrame frame;
	private static JTextField textField_userName;
	private static JPasswordField passwordField;
	private static JTextField textField_code;
	private static Gson gson = new Gson();
	private static String keyAndValue = "";
	
	private final static String ACCOUNT_CONFIG = "account_config";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		start();
	}

	
	public static void start(){
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
     * 返回Properties对象
     * @return Properties
     */
    private static Properties getProperties(){
        try {
            File file = new File(ACCOUNT_CONFIG + System.getProperty("file.separator")+ "account.properties");
            InputStream in = new FileInputStream(file);
            Properties pro = new Properties();
            pro.load(in);
            return pro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
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
		
		frame = new JFrame("");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 350;
		int height = 270;
		frame.setResizable(false);
		frame.setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("抢票助手");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel_1.setBounds(135, 10, 84, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("登录名：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label.setBounds(36, 64, 73, 15);
		frame.getContentPane().add(label);
		
		textField_userName = new JTextField(getProperties().getProperty("uesr_name"));
		textField_userName.setBounds(105, 61, 156, 21);
		frame.getContentPane().add(textField_userName);
		textField_userName.setColumns(10);
		
		JLabel label_1 = new JLabel("密　码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_1.setBounds(36, 100, 64, 15);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField(getProperties().getProperty("password"));
		passwordField.setBounds(105, 97, 156, 21);
		frame.getContentPane().add(passwordField);
		
		JLabel label_2 = new JLabel("验证码：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_2.setBounds(36, 135, 64, 15);
		frame.getContentPane().add(label_2);
		
		textField_code = new JTextField();
		textField_code.setColumns(10);
		textField_code.setBounds(105, 129, 79, 21);
		textField_code.setDocument(new MyDocument(4));
		frame.getContentPane().add(textField_code);
		
		//得到cookie 并且返回随机的key
		keyAndValue = LoginFrameLogic.getCookieKeyAndValue();
		
		final JLabel lblNewLabel = new JLabel();  //显示验证码的标签
		//加载验证码
		loadCode(lblNewLabel, false);
		lblNewLabel.setBounds(193, 126, 83, 28);
		frame.getContentPane().add(lblNewLabel);
		
		LinkLabel label_3 = new LinkLabel("点击刷新", null);
		label_3.setBounds(276, 130, 73, 23);
		frame.getContentPane().add(label_3);
		
		final JButton btnNewButton = new JButton("登录");  //登录按钮
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnNewButton.setBounds(105, 186, 93, 23);
		frame.getContentPane().add(btnNewButton);
		frame.getRootPane().setDefaultButton(btnNewButton);   //回车出发按钮的点击事件
		
		LinkLabel linkLabel = new LinkLabel("注册", Constants.REGISTER_URL);
		linkLabel.setBounds(208, 184, 32, 23);
		frame.getContentPane().add(linkLabel);
		
		
		//验证码输入框的监听事件
		textField_code.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				//验证码达到4个字符之后自动触发按钮的点击事件
				if(textField_code.getText().length() == 4){
					login(btnNewButton, lblNewLabel);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		
		//刷新验证码点击事件
		label_3.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				//加载验证码
				loadCode(lblNewLabel, false);
			}
		});
		
		//登录按钮的点击事件
		btnNewButton.addActionListener(new ActionListener() {
			//点击登录触发
			public void actionPerformed(ActionEvent arg0) {
				login(btnNewButton, lblNewLabel);
			}
		});
	}
	
	/**
	 * 登录按钮的触发方法
	 * @param btnNewButton
	 * @param lblNewLabel
	 */
	public static void login(JButton btnNewButton, JLabel lblNewLabel){
		btnNewButton.setText("加载中");
		String name = textField_userName.getText().trim();
		String password = String.valueOf(passwordField.getPassword()).trim();
		String code = textField_code.getText().trim();
		if(name.equals("")){
			JOptionPane.showMessageDialog(frame, "登录名不能为空!");
			btnNewButton.setText("登陆");
			textField_userName.requestFocusInWindow();  //获得焦点
			return;
		}
		if(password.equals("")){
			JOptionPane.showMessageDialog(frame, "密码不能为空!");
			passwordField.requestFocusInWindow();
			btnNewButton.setText("登陆");
			return;
		}
		if(code.equals("")){
			JOptionPane.showMessageDialog(frame, "验证码不能为空!");
			textField_code.requestFocusInWindow();
			btnNewButton.setText("登陆");
			return;
		}
		Type type = new TypeToken<JsonObject>(){}.getType();
		//首先验证验证码
		String entityStr = LoginFrameLogic.verLoginRandCode(code);
		JsonObject jsonObject = gson.fromJson(entityStr, type);
		jsonObject = jsonObject.getAsJsonObject("data");
		if(jsonObject.get("result").toString().equals("\"0\"")){
			//验证码不正确
			JOptionPane.showMessageDialog(frame, "验证码错误！");
			btnNewButton.setText("登陆");
			textField_code.setText("");
			return;
		}
		
		String loginStr = LoginFrameLogic.login(name, password, code, keyAndValue);
		jsonObject = gson.fromJson(loginStr, type);
		String messages = jsonObject.get("messages").toString();
		jsonObject = jsonObject.getAsJsonObject("data");
		messages = messages.replace("[\"", "");
		messages = messages.replace("\"]", "");
		
		if(jsonObject != null && jsonObject.get("loginCheck")!= null && jsonObject.get("loginCheck").toString().equals("\"Y\"") ){
			LoginFrameLogic.login();
			//登录成功
			frame.dispose();
			frame.setVisible(false); //不释放资源
			TicketFrame.start();
		}else{
			//登录错误
			JOptionPane.showMessageDialog(frame, messages);
			frame.dispose();
			frame.setVisible(false); //不释放资源
			start();
			return;
		}
	}
	
	
	/**
	 * 显示验证码
	 * @param lblNewLabel
	 * @param isType  true-文件方式      false-二进制流
	 */
	public static void loadCode(JLabel lblNewLabel, boolean isType){
		if(!isType){
			//读取二进制流显示图片
			InputStream inputStream = null;
			try {
				inputStream = LoginFrameLogic.getInputStream();
				BufferedImage bufferedImage = ImageIO.read(inputStream);
				if(bufferedImage != null){
					lblNewLabel.setIcon(new ImageIcon(bufferedImage));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				try {
					inputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			//写文件方式
			String randNum = LoginFrameLogic.getLoginRandCode();
			ImageIcon loginRandImg=new ImageIcon(SysConfig.getDefConfig().getCache_path()+"/login_"+randNum+".jpg");  
			lblNewLabel.setIcon(loginRandImg);
		}
	}
}
