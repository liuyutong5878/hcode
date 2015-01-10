package com.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import com.config.SysConfig;
import com.entity.Contacts;
import com.entity.Station;
import com.entity.TrainInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.logic.TicketFrameLogic;
import com.util.CalendarChooser;
import com.util.JTableComm;
import com.util.LinkLabel;
import com.util.OtherUtil;
import com.util.VFlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

public class TicketFrame {

	private JFrame frame;
	private static final int WIDTH = 750;
	private static final int HEIGHT = 730;
	private static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private static String keyAndValue = "";
	
	private static List<TrainInfo> trainInfos = new ArrayList<TrainInfo>();  //列车信息
	
	private static Gson gson = new Gson();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		start();
	}
	
	public static void start(){
		try {
			TicketFrame window = new TicketFrame();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public TicketFrame() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
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
		
		frame = new JFrame("");
		frame.setResizable(false);
		frame.setBounds((DIMENSION.width - WIDTH) / 2, (DIMENSION.height - HEIGHT) / 2, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("今日可预定："+TicketFrameLogic.resultTime()+"车票");
		lblNewLabel.setBounds(10, 20, 215, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_6 = new JLabel("农历："+TicketFrameLogic.resultLunarTime());
		lblNewLabel_6.setBounds(247, 20, 192, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("当前时间：");
		lblNewLabel_1.setBounds(492, 20, 60, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(562, 20, 123, 15);
		frame.getContentPane().add(lblNewLabel_2);
		TicketFrameLogic.resultNewTime(lblNewLabel_2);

		JLabel label = new JLabel("出发城市：");
		label.setBounds(10, 55, 60, 15);
		frame.getContentPane().add(label);
		
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(new VFlowLayout());
		
		final JScrollPane scrollPane_1 = new JScrollPane(panel_3);
		scrollPane_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrollPane_1.setVisible(false);
		frame.getContentPane().add(scrollPane_1);
		
		textField = new JTextField("武汉");
		textField.setBounds(70, 53, 108, 21);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				showStation(scrollPane_1, panel_3, textField);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_3 = new LinkLabel("", null);
		lblNewLabel_3.setBounds(188, 57, 22, 15);
		ImageIcon loginRandImg=new ImageIcon(SysConfig.getDefConfig().getImg_path()+"/1.png");  
		lblNewLabel_3.setIcon(loginRandImg);
		lblNewLabel_3.setToolTipText("互换地点");
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("到达城市：");
		lblNewLabel_4.setBounds(217, 55, 60, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_1 = new JTextField("广州");
		textField_1.setColumns(10);
		textField_1.setBounds(278, 53, 108, 21);
		textField_1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				showStation(scrollPane_1, panel_3, textField_1);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_5 = new JLabel("出发时间：");
		lblNewLabel_5.setBounds(396, 55, 60, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(459, 53, 108, 21);
		CalendarChooser dateChooser = CalendarChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(textField_2, scrollPane_1);
		frame.getContentPane().add(textField_2);
		
		//列车信息table
		String stationName = "<html>出发站<br>到达站";
		String timeStr = "<html>出发时间<br>到达时间";
		String gjrw = "<html>高级<br>软卧";
		//表头
		final Object[] columnNames = { "车次", stationName, timeStr, "历时", "商务", "特等", "一等", "二等", gjrw, "软卧", "硬卧", "软座", "硬座", "无座", "其他", "备注","隐藏列"};
		
		/*Object[][] rowData = {
				{ "G101", "<html><font color=#EE9A00>始</font>|乌鲁木齐<br><font color=#008B00>终</font>|武汉", "<html>07:53<br>12:45", "<html>04:52<br>当日到达","无", "--", "无", "无", "--", "--", "--", "--", "--", "--", "--", "<html><a>预定</a></html>" },
				{ "G101", "<html><font color=#EE9A00>始</font>|深圳北<br><font color=#436EEE>过</font>|武汉", "<html>07:53<br>12:45", "<html>04:52<br>当日到达","无", "--", "无", "无", "--", "--", "--", "--", "--", "--", "--", "<html><font color=blue><u>预定</u></html>" }};*/
		
		table = new JTable(){
			public boolean isCellEditable(int rowIndex, int ColIndex){
				return false;
			}
		};
		table.setSelectionBackground(new Color(210, 180, 140));
		table.setModel(new DefaultTableModel(new Object[][]{}, columnNames));
		
		tableStyle(table);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint()); //获得列位置
				int row = table.rowAtPoint(e.getPoint()); //获得行位置 
				String cellVal=(String)(table.getValueAt(row,col)); //获得点击单元格数据 
				
				if(col == 15 && e.getClickCount() == 1){
					Type type = new TypeToken<JsonObject>(){}.getType();
					
					String checkUserResultStr = TicketFrameLogic.getCheckUser();
					JsonObject jsonObject = gson.fromJson(checkUserResultStr, type);
					boolean status = Boolean.valueOf(jsonObject.get("status").toString());
					if(status && checkUserResultStr.contains("\"data\":{\"flag\":true}")){
						keyAndValue = TicketFrameLogic.leftTicket_init_keyAndValue();
						String secretStrm = (String)(table.getValueAt(row,col+1));
						String resultStr = TicketFrameLogic.getReserveButton(secretStrm, keyAndValue, textField.getText(), textField_1.getText(), textField_2.getText());
						
						jsonObject = gson.fromJson(resultStr, type);
						String messages = jsonObject.get("messages").toString();
						status = Boolean.valueOf(jsonObject.get("status").toString());
						if(status){
							JOptionPane.showMessageDialog(frame, "成功");
							return;
						}else{
							messages = messages.replace("[\"", "");
							messages = messages.replace("\"]", "");
							JOptionPane.showMessageDialog(frame, messages);
							return;
						}
					}else{
						JOptionPane.showMessageDialog(frame, "请退出重新登录!");
						return;
					}
					
				}else if(col < 15 && e.getClickCount() == 2){ //双击事件
	                JOptionPane.showMessageDialog(frame, row+1);
	                JOptionPane.showMessageDialog(frame, col+1);
	                JOptionPane.showMessageDialog(frame, cellVal);
	                return; 
				}else {
                	return; 
                }
			} 
		});
		JScrollPane scrollPane_2 = new JScrollPane(table);
		
		JPanel panel2 = new JPanel(new GridLayout(0, 1));
		panel2.setBounds(10, 363, 727, 260);
		panel2.setLayout(new BorderLayout());
		panel2.add(scrollPane_2, BorderLayout.CENTER);
		frame.getContentPane().add(panel2);
		
		final JButton btnNewButton = new JButton("查询");
		frame.getRootPane().setDefaultButton(btnNewButton);   //回车出发按钮的点击事件
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trainInfos.clear();
				btnNewButton.setText("查询中");
				String from = textField.getText().trim();
				String to = textField_1.getText().trim();
				String time = textField_2.getText().trim();
				if(from.equals("")){
					JOptionPane.showMessageDialog(frame, "出发城市不能为空!");
					btnNewButton.setText("查询");
					textField.requestFocusInWindow();  //获得焦点
					return;
				}
				if(to.equals("")){
					JOptionPane.showMessageDialog(frame, "到达城市不能为空!");
					btnNewButton.setText("查询");
					textField_1.requestFocusInWindow();  //获得焦点
					return;
				}
				if(time.equals("")){
					JOptionPane.showMessageDialog(frame, "出发时间不能为空!");
					btnNewButton.setText("查询");
					textField_2.requestFocusInWindow();  //获得焦点
					scrollPane_1.setVisible(false);
					return;
				}
				
				trainInfos = new ArrayList<TrainInfo>();
				String trainInfoStr = TicketFrameLogic.trainInfoStr(from, to, time);
					
				Type type = new TypeToken<JsonObject>(){}.getType();
				Type typeTrainInfo = new TypeToken<TrainInfo>(){}.getType();
				JsonObject jsonObject = gson.fromJson(trainInfoStr, type);
				JsonArray jsonArray = jsonObject.getAsJsonArray("data");
				if(jsonArray != null){
					if(jsonArray.size() >0){
						for(Object object : jsonArray){
							JsonObject jsonObject2 = gson.fromJson(object.toString(), type);
							String secretStr = jsonObject2.get("secretStr").toString();
							String buttonTextInfo = jsonObject2.get("buttonTextInfo").toString();
							String string = jsonObject2.get("queryLeftNewDTO").toString();
							TrainInfo trainInfo = gson.fromJson(string, typeTrainInfo);
							trainInfo.setSecretStr(secretStr.replace("\"", ""));
							trainInfo.setButtonTextInfo(buttonTextInfo.replace("\"", ""));
							trainInfos.add(trainInfo);
						}
					}else{
						JOptionPane.showMessageDialog(frame, "很抱歉，按您的查询条件，当前未找到从"+from+" 到"+to+" 的列车。");
						btnNewButton.setText("查询");
						return;
					}
				}else{
					String messages = jsonObject.get("messages").toString();
					messages = messages.replace("[\"", "");
					messages = messages.replace("\"]", "");
					JOptionPane.showMessageDialog(frame, messages);
					btnNewButton.setText("查询");
					return;
				}

				if(trainInfos != null && trainInfos.size() != 0){
					Object object[][]=new Object[trainInfos.size()][17]; //动态初始化一个二维数组
					int i = 0;
					for(TrainInfo trainInfo : trainInfos){
						object[i][0] = trainInfo.getStation_train_code();
						
						String startName = trainInfo.getStart_station_name();
						String endName = trainInfo.getEnd_station_name();
						String fromName = trainInfo.getFrom_station_name();
						String toName = trainInfo.getTo_station_name();
						
						if(startName.equals(fromName) && endName.equals(toName)){
							object[i][1] = "<html><font color=#EE9A00>始</font>|"+fromName+"<br><font color=#008B00>终</font>|"+toName+"";
						}
						
						if(startName.equals(fromName) && !endName.equals(toName)){
							object[i][1] = "<html><font color=#EE9A00>始</font>|"+fromName+"<br><font color=#436EEE>过</font>|"+toName+"";
						}
						
						if(!startName.equals(fromName) && endName.equals(toName)){
							object[i][1] = "<html><font color=#436EEE>过</font>|"+fromName+"<br><font color=#008B00>终</font>|"+toName+"";
						}
						
						if(!startName.equals(fromName) && !endName.equals(toName)){
							object[i][1] = "<html><font color=#436EEE>过</font>|"+fromName+"<br><font color=#436EEE>过</font>|"+toName+"";
						}
						object[i][2] = "<html>"+trainInfo.getStart_time()+"<br>"+trainInfo.getArrive_time()+"";
						
						object[i][3] = "<html>"+trainInfo.getLishi()+"<br>"+dayDifference(trainInfo.getDay_difference())+"";
						object[i][4] = numColoe(trainInfo.getSwz_num());	//商务座	
						object[i][5] = numColoe(trainInfo.getTz_num());	//特等座
						object[i][6] = numColoe(trainInfo.getZy_num());	//一等座
						object[i][7] = numColoe(trainInfo.getZe_num());	//二等座
						object[i][8] = numColoe(trainInfo.getGr_num());	//高级软卧
						object[i][9] = numColoe(trainInfo.getRw_num());	//软卧
						object[i][10] = numColoe(trainInfo.getYw_num());	//硬卧
						object[i][11] = numColoe(trainInfo.getRz_num());	//软座
						object[i][12] = numColoe(trainInfo.getYz_num());	//硬座
						object[i][13] = numColoe(trainInfo.getWz_num());	//无座
						object[i][14] = numColoe(trainInfo.getQt_num());	//其他
						if(trainInfo.getCanWebBuy().equals("Y")){
							object[i][15] = "<html><font color=blue><u>"+trainInfo.getButtonTextInfo()+"</u></html>";	//预定
						}else{
							object[i][15] = "<html><font color=#CCCCCC>"+trainInfo.getButtonTextInfo()+"</html>";	//预定
						}
						object[i][16] = trainInfo.getSecretStr();
						i++;
					}
					if(table.getColumnCount() > 0){
						((DefaultTableModel) table.getModel()).getDataVector().clear();   //清除表格数据
						((DefaultTableModel) table.getModel()).fireTableDataChanged();//通知模型更新
						table.updateUI();//刷新表格 
					}
					table.setModel(new DefaultTableModel(object, columnNames));
					tableStyle(table);
					SwingUtilities.updateComponentTreeUI(table);
				}
				btnNewButton.setText("查询");
			}
		});
		btnNewButton.setBounds(594, 51, 70, 23);
		frame.getContentPane().add(btnNewButton);
		
		final JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 203, 724, 26);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel = new LinkLabel("乘车人：", null);
		jLabel.setPreferredSize(new Dimension(51, 25));
		panel_2.add(jLabel);
		frame.getContentPane().add(panel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//得到联系人信息
		List<Contacts> constants = TicketFrameLogic.getContactsList();
		
		//初始化车站信息
		TicketFrameLogic.stationInfo();
		
		for(Contacts contact: constants){
			JPanel panel_1 = new JPanel();
			final JCheckBox checkbox = new JCheckBox();
			checkbox.setLocale(new Locale("UTF-8"));
			checkbox.setText(contact.getPassenger_name());
			checkbox.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(checkbox.isEnabled()){
						if(checkbox.isSelected()){
							final JLabel jLabel = new LinkLabel(checkbox.getText(), null);
							jLabel.setForeground(Color.BLUE);
							jLabel.setPreferredSize(new Dimension(40, 25));
							jLabel.setHorizontalAlignment(JLabel.CENTER);
							jLabel.setToolTipText("点击移除");
							checkbox.setEnabled(false);
							panel_2.add(jLabel);
							
							jLabel.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									panel_2.remove(jLabel);
									panel_2.updateUI();
									checkbox.setEnabled(true);
									checkbox.setSelected(false);
								}
							});
						}
					}
					
				}
			});
			
			panel_1.add(checkbox);
			panel_1.setPreferredSize(new Dimension(80, 25));
			panel_1.setLayout(new FlowLayout(FlowLayout.LEFT));
			panel.add(panel_1);
		}
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(10, 91, 727, 115);
		scrollPane.setViewportBorder(new TitledBorder(null, "选择乘车人", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight()+50));
		panel.revalidate(); 
		scrollPane.revalidate();
		frame.getContentPane().add(scrollPane);
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 322, 724, 26);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel_1 = new LinkLabel("席别：", null);
		jLabel_1.setPreferredSize(new Dimension(40, 25));
		panel_4.add(jLabel_1);
		frame.getContentPane().add(panel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_1.setBounds(10, 239, 727, 85);
		panel_1.setBorder(new TitledBorder(null, "选择席别", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Map<String, String> mapSeating = OtherUtil.seatingType();
		Iterator<Entry<String, String>> iter = mapSeating.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			final JCheckBox checkbox = new JCheckBox();
			checkbox.setText(key);
			checkbox.setToolTipText(val);
			checkbox.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(checkbox.isEnabled()){
						if(checkbox.isSelected()){
							final JLabel jLabel = new LinkLabel(checkbox.getText(), null);
							jLabel.setForeground(Color.BLUE);
							jLabel.setPreferredSize(new Dimension(50, 25));
							jLabel.setHorizontalAlignment(JLabel.CENTER);
							jLabel.setToolTipText("点击移除");
							checkbox.setEnabled(false);
							panel_4.add(jLabel);
							
							jLabel.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									panel_4.remove(jLabel);
									panel_4.updateUI();
									checkbox.setEnabled(true);
									checkbox.setSelected(false);
								}
							});
						}
					}
					
				}
			});
			panel_1.add(checkbox);
		}
		panel_1.revalidate(); 
		frame.getContentPane().add(panel_1);
		
		
		/**
		 * 地点互换
		 */
		lblNewLabel_3.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String to = textField.getText();
				textField.setText(textField_1.getText());
				textField_1.setText(to);
			}
		});
		
	}
	
	
	/**
	 * 表格的样式设置
	 * @param table
	 */
	public static void tableStyle(JTable table){
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowMargin(5);
		table.getTableHeader().setPreferredSize(new Dimension(1, 45));  //设置表头的高
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		
		table.setBorder(new LineBorder(new Color(192, 192, 192)));
		table.setRowHeight(40);

		for (int i = 0; i < table.getColumnCount(); i++) {
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();
			if(i ==1){
				render.setHorizontalAlignment(SwingConstants.LEFT);
			}else{
				render.setHorizontalAlignment(SwingConstants.CENTER);
			}
			if(i == 16){
				JTableComm.HiddenCell(table, i);
			}
			table.getColumnModel().getColumn(i).setCellRenderer(render);
		}
	}
	
	
	/**
	 * 加载搜索的车站信息
	 * @param panel
	 * @param textField
	 */
	public static int height = 0;
	public static void showStation(final JScrollPane scrollPane, final JPanel panel, final JTextField textField){
		height = 0;
		String text = textField.getText();
		if(text.length()>0){
			scrollPane.setVisible(true);
		}else{
			scrollPane.setVisible(false);
		}
		scrollPane.setBounds(textField.getX(), textField.getY()+textField.getHeight()+1, 98, 170);
		if(text.length()> 1){
			List<Station> stations = TicketFrameLogic.getStationList(textField.getText());
			panel.removeAll();
			panel.updateUI();
			
			for(Station station : stations){
				final JLabel jLabel = new LinkLabel(station.getStationName(), null);
				jLabel.setForeground(Color.BLUE);
				jLabel.setPreferredSize(new Dimension(40, 20));
				jLabel.setToolTipText(station.getStationName());
				jLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						scrollPane.setVisible(false);
						textField.setText(jLabel.getText().replace("<html><font color=red>", ""));
						panel.removeAll();
						panel.updateUI();
					}
				});
				height += jLabel.getPreferredSize().height;
				panel.add(jLabel);
				panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, height));
				panel.updateUI();
				scrollPane.updateUI();
			}
		}
	}
	
	
	/**
	 * 返回几日到达信息
	 * @param key
	 * @return
	 */
	public static String dayDifference(String key){
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "当日到达");
		map.put("1", "次日到达");
		map.put("2", "两日到达");
		map.put("3", "三日到达");
		map.put("4", "四日到达");
		map.put("5", "五日到达");
		map.put("6", "六日到达");
		return map.get(key);
	}
	
	
	/**
	 * 根据数量显示文字的不通颜色
	 * @return
	 */
	public static String numColoe(String num){
		Map<String, String> map = new HashMap<String, String>();
		map.put("无", "<html><font color=#CCCCCC>无</font>");
		map.put("--", "<html><font color=#CCCCCC>--</font>");
		map.put("有", "<html><font color=#26a306>有</font>");
		if(map.get(num) == null){
			return "<html><b><font color=#333>"+num+"</font></b>";
		}
		return map.get(num);
	}

	public static List<TrainInfo> getTrainInfos() {
		return trainInfos;
	}
	
}
