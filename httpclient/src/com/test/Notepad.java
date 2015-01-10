package com.test;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoableEdit;

import java.io.*;

public class Notepad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public JTextArea jta = null; // 中间的文本区域
	private File selectedFile = null;
	private JFileChooser chooser = null; // 文件选择器
	private UndoableEdit edit; // 设置还原功能
	private boolean canUndo = false;
	private JMenuItem jmiUndoItem; // 撤销的菜单
	private Clipboard clip = null; // 粘贴板
	private JDialog jdSearchDialog;

	public Notepad() {
		super("Notepad Beta");
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		jta = new JTextArea();
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);

		// *****************************右键弹出功能
		final JPopupMenu jpMenu = new JPopupMenu();
		JMenuItem jpItemUndo = new JMenuItem("撤销(U)            ");
		JMenuItem jpItemCut = new JMenuItem("剪切(T)            ");
		JMenuItem jpItemCopy = new JMenuItem("复制(C)            ");
		JMenuItem jpItemPaste = new JMenuItem("粘贴(P)            ");
		jpMenu.add(jpItemUndo);
		jpMenu.add(new JPopupMenu.Separator());
		jpMenu.add(jpItemCut);
		jpMenu.add(jpItemCopy);
		jpMenu.add(jpItemPaste);
		jpMenu.setBorder(new BevelBorder(BevelBorder.RAISED));

		jta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.isMetaDown()) {
					if (!jpMenu.isVisible()) {
						jpMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				} else {
					jpMenu.setVisible(false);
				}
			}
		});
		// *****************************实现撤销功能

		jta.getDocument().addUndoableEditListener(new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
				edit = e.getEdit();
				if (edit != null) {
					canUndo = edit.canUndo();
				} else {
					canUndo = false;
				}
			}
		});

		// **********************************设置相关菜单和事件监听
		Container content = getContentPane();
		JMenuBar jmb = new JMenuBar();
		JMenu jmFile = new JMenu("文件(F)");
		jmFile.setMnemonic('f');
		JMenu jmEdit = new JMenu("编辑(E)");
		jmEdit.setMnemonic('e');
		JMenu jmStyle = new JMenu("格式(O)");
		jmStyle.setMnemonic('e');
		JMenu jmView = new JMenu("查看(V)");
		jmView.setMnemonic('v');
		JMenu jmHelp = new JMenu("帮助(H)");
		jmHelp.setMnemonic('h');
		jmb.add(jmFile);
		jmb.add(jmEdit);
		jmb.add(jmStyle);
		jmb.add(jmView);
		jmb.add(jmHelp);
		setJMenuBar(jmb);

		JMenuItem jmiNewItem = new JMenuItem("新建(N)      Ctrl+N");
		jmiNewItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!jta.getText().equals("")) {
					int confirm = JOptionPane.showConfirmDialog(Notepad.this,
							"是否保存(S)      Ctrl+S文件？");
					if (confirm == JOptionPane.OK_OPTION) {
						if (selectedFile != null) {
							try {
								FileWriter fw = new FileWriter(selectedFile);
								fw.write(jta.getText());
								fw.close();
							} catch (Exception e) {
								// TODO: handle exception
								JOptionPane.showConfirmDialog(Notepad.this,
										"文件写入异常");
							}
							jta.setText("");
						} else {
							jta.setText("");
							JFileChooser jChooser = new JFileChooser();
							int selection = jChooser
									.showSaveDialog(Notepad.this);
							if (selection == JFileChooser.APPROVE_OPTION) {
								selectedFile = jChooser.getSelectedFile();
								try {
									FileWriter fw = new FileWriter(selectedFile);
									fw.write(jta.getText());
									fw.close();
								} catch (Exception e) {
									// TODO: handle exception
									JOptionPane.showConfirmDialog(Notepad.this,
											"文件写入异常");
								}
							}
						}
					} else if (confirm == JOptionPane.NO_OPTION) {
						jta.setText("");
					} else {
					}
				} else {
				}
			}
		});
		JMenuItem jmiOpenItem = new JMenuItem("打开(O)      Ctrl+O");
		jmiOpenItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!jta.getText().equals("")) {
					int confirm = JOptionPane.showConfirmDialog(Notepad.this,
							"是否保存(S)      Ctrl+S文件？");
					if (confirm == JOptionPane.OK_OPTION) {
						if (selectedFile != null) {
							try {
								FileWriter fw = new FileWriter(selectedFile);
								fw.write(jta.getText());
								fw.close();
							} catch (Exception e) {
								// TODO: handle exception
								JOptionPane.showConfirmDialog(Notepad.this,
										"文件写入异常");
							}
						} else {
							JFileChooser jChooser = new JFileChooser();
							int selection = jChooser
									.showSaveDialog(Notepad.this);
							if (selection == JFileChooser.APPROVE_OPTION) {
								selectedFile = jChooser.getSelectedFile();
								try {
									FileWriter fw = new FileWriter(selectedFile);
									fw.write(jta.getText());
									fw.close();
								} catch (Exception e) {
									// TODO: handle exception
									JOptionPane.showConfirmDialog(Notepad.this,
											"文件写入异常");
								}
							}
						}
					} else if (confirm == JOptionPane.NO_OPTION) {
						jta.setText("");
						JFileChooser jChooser = new JFileChooser();
						int selection = jChooser.showOpenDialog(Notepad.this);
						{
							if (selection == JFileChooser.APPROVE_OPTION) {
								selectedFile = jChooser.getSelectedFile();
								try {
									BufferedReader bf = new BufferedReader(
											new FileReader(selectedFile));
									String tempString = "";
									int i;
									while ((i = bf.read()) != -1) {
										tempString += String.valueOf((char) i);
									}
									jta.setText(tempString);
									bf.close();
								} catch (Exception e) {
									// TODO: handle exception
									JOptionPane.showConfirmDialog(Notepad.this,
											"文件保存(S)      Ctrl+S错误");
								}

							} else {
							}
						}
					} else {
					}
				} else {
					JFileChooser jChooser = new JFileChooser();
					int selection = jChooser.showOpenDialog(Notepad.this);
					if (selection == JFileChooser.APPROVE_OPTION) {
						selectedFile = jChooser.getSelectedFile();
						try {
							BufferedReader bf = new BufferedReader(
									new FileReader(selectedFile));
							String tempString = "";
							int i;
							while ((i = bf.read()) != -1) {
								tempString += String.valueOf((char) i);
							}
							jta.setText(tempString);
							bf.close();
						} catch (Exception e) {
							// TODO: handle exception
							JOptionPane.showConfirmDialog(Notepad.this,
									"文件打开(O)      Ctrl+O错误");
						}
					} else {
					}

				}
			}
		});
		JMenuItem jmiSaveItem = new JMenuItem("保存(S)      Ctrl+S");
		jmiSaveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (jta.getText().equals("")) {
				} else {
					if (selectedFile == null) {
						JFileChooser jChooser = new JFileChooser();
						int selection = jChooser.showOpenDialog(Notepad.this);
						if (selection == JFileChooser.APPROVE_OPTION) {
							selectedFile = jChooser.getSelectedFile();
							try {
								BufferedReader bf = new BufferedReader(
										new FileReader(selectedFile));
								String tempString = "";
								int i;
								while ((i = bf.read()) != -1) {
									tempString += String.valueOf((char) i);
								}
								jta.setText(tempString);
								bf.close();
							} catch (Exception e) {
								// TODO: handle exception
								JOptionPane.showConfirmDialog(Notepad.this,
										"文件打开(O)      Ctrl+O错误");
							}
						} else {
						}
					} else {
						try {
							FileWriter fw = new FileWriter(selectedFile);
							fw.write(jta.getText());
							fw.close();
						} catch (Exception e) {
							// TODO: handle exception
							JOptionPane.showConfirmDialog(Notepad.this,
									"文件写入异常");
						}
					}
				}
			}
		});
		JMenuItem jmiSaveTo = new JMenuItem("另存为(A)...");
		jmiSaveTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser jChooser = new JFileChooser();
				int selection = jChooser.showOpenDialog(Notepad.this);
				if (selection == JFileChooser.APPROVE_OPTION) {
					selectedFile = jChooser.getSelectedFile();
					try {
						BufferedWriter bf = new BufferedWriter(new FileWriter(
								selectedFile));
						bf.write(jta.getText());
						bf.close();
					} catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showConfirmDialog(Notepad.this,
								"文件打开(O)      Ctrl+O错误");
					}
				} else {
				}
			}
		});
		JMenuItem jmiExitItem = new JMenuItem("退出(X)");
		jmiExitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (jta.getText().equals(""))
					System.exit(0);
				else {
					int t = JOptionPane.showConfirmDialog(Notepad.this,
							"确定退出(X)？");
					if (t == JOptionPane.OK_OPTION) {
						System.exit(0);
					}
				}
			}
		});
		JMenuItem jmiPrintItem = new JMenuItem("打印(P)      Ctrl+P");
		jmiPrintItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
				aset.add(MediaSizeName.ISO_A4);
				PrintService[] pservices = PrintServiceLookup
						.lookupPrintServices(flavor, aset);
				PrintService defaultService = PrintServiceLookup
						.lookupDefaultPrintService();
				if (pservices.length > 0) {
					PrintService service = ServiceUI.printDialog(null, 200,
							200, pservices, defaultService, flavor, aset);
					DocPrintJob pj = service.createPrintJob();
					try {
						String fis = jta.getText();
						DocAttributeSet das = new HashDocAttributeSet();
						Doc doc = new SimpleDoc(fis, flavor, das);
						pj.print(doc, aset);
					} catch (PrintException e) {
						e.printStackTrace();
					}
				}
			}
		});
		jmFile.add(jmiNewItem);
		jmFile.add(jmiOpenItem);
		jmFile.add(jmiSaveItem);
		jmFile.add(jmiSaveTo);
		jmFile.add(new JSeparator());
		jmFile.add(jmiPrintItem);
		jmFile.add(new JSeparator());
		jmFile.add(jmiExitItem);

		// ******************************实现编辑菜单的相关item
		// ***************************由于item太多，就放在一个类中实现监听（除了jmiUndoItem）
		jmiUndoItem = new JMenuItem("撤销(U)     Ctrl+Z");
		jmiUndoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (canUndo) {
					jmiUndoItem.setEnabled(true);
					edit.undo();
				} else {
					jmiUndoItem.setEnabled(false);
				}
			}
		});
		JMenuItem jmiCutItem = new JMenuItem("剪切(T)      Ctrl+T");
		jmiCutItem.addActionListener(new EditListener());
		JMenuItem jmiCopyItem = new JMenuItem("复制(C)      Ctrl+C");
		jmiCopyItem.addActionListener(new EditListener());
		JMenuItem jmiPasteItem = new JMenuItem("粘贴(P)      Ctrl+V");
		jmiPasteItem.addActionListener(new EditListener());
		JMenuItem jmiDeleteItem = new JMenuItem("删除(L)     Del");
		jmiDeleteItem.addActionListener(new EditListener());
		JMenuItem jmiSearchItem = new JMenuItem("查找(F)      Ctrl+F");
		jmiSearchItem.addActionListener(new EditListener());
		JMenuItem jmiSearchNextItem = new JMenuItem("查找下一个(N)   F3");
		jmiSearchNextItem.addActionListener(new EditListener());
		JMenuItem jmiReplaceItem = new JMenuItem("替换");
		jmiReplaceItem.addActionListener(new EditListener());
		JMenuItem jmiGoToItem = new JMenuItem("转到");
		jmiGoToItem.addActionListener(new EditListener());
		JMenuItem jmiSelectAllItem = new JMenuItem("全选");
		jmiSelectAllItem.addActionListener(new EditListener());
		JMenuItem jmiDateItem = new JMenuItem("日期");
		jmiDateItem.addActionListener(new EditListener());

		jmEdit.add(jmiUndoItem);
		jmEdit.add(new JSeparator());
		jmEdit.add(jmiCutItem);
		jmEdit.add(jmiCopyItem);
		jmEdit.add(jmiPasteItem);
		jmEdit.add(jmiDeleteItem);
		jmEdit.add(new JSeparator());
		jmEdit.add(jmiSearchItem);
		jmEdit.add(jmiSearchNextItem);
		jmEdit.add(jmiReplaceItem);
		jmEdit.add(jmiGoToItem);
		jmEdit.add(new JSeparator());
		jmEdit.add(jmiSelectAllItem);
		jmEdit.add(jmiDateItem);

		// ************************* 风格菜单的实现
		JCheckBox jcbAutoNextLine = new JCheckBox("自动换行");
		JMenuItem jmiFontitemItem = new JMenuItem("字体");
		jmStyle.add(jcbAutoNextLine);
		jmStyle.add(jmiFontitemItem);

		JMenuItem jmiViewStatusItem = new JMenuItem("查看状态栏");
		jmiViewStatusItem.setEnabled(false);
		jmView.add(jmiViewStatusItem);

		JMenuItem jmiAboutItem = new JMenuItem("关于");
		jmiAboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HelpDialog(Notepad.this);
			}
		});

		// **********************帮助菜单
		jmHelp.add(jmiAboutItem);
		setLayout(new BorderLayout());

		JScrollPane jsp = new JScrollPane(jta);
		jsp.requestFocus(true);
		content.add(jsp, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.print("Look And Feel Exception");
			System.exit(0);
			// TODO: handle exception
		}
		JFrame frame = new Notepad();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "确认退出吗？");
				if (r == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// ***************************** 和剪贴板相关函数的实现
	public String getClipbordContent(Clipboard clip) throws Exception {
		Transferable trans = clip.getContents(null);
		if (trans != null) {
			if (trans.isDataFlavorSupported(DataFlavor.stringFlavor))
				return (String) trans.getTransferData(DataFlavor.stringFlavor);
			else
				return "";
		} else
			return "";
	}

	public void setClipbordContent(Clipboard clip, String str) throws Exception {

		Transferable trans = new StringSelection(str);
		clip.setContents(trans, null);
	}

	// ********************************监听编辑菜单下item的事件
	class EditListener implements ActionListener {
		private String tempString = "";
		String textString = jta.getText();
		String searchString = "";
		int stringLength = 0;
		int startPos;
		JTextField jtField = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("剪切(T)      Ctrl+T")) {
				tempString = jta.getSelectedText();
				try {
					setClipbordContent(clip, tempString);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jta.replaceSelection("");
			} else if (e.getActionCommand().equals("复制(C)      Ctrl+C")) {
				tempString = jta.getSelectedText();
				try {
					setClipbordContent(clip, tempString);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			} else if (e.getActionCommand().equals("粘贴(P)      Ctrl+V")) {
				try {
					tempString = getClipbordContent(clip);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int pos = jta.getCaretPosition();
				jta.insert(tempString, pos);
			} else if (e.getActionCommand().equals("删除(L)      Del")) {
				jta.replaceSelection("");
			} else if (e.getActionCommand().equals("查找(F)      Ctrl+F")) {
				jdSearchDialog = new JDialog(Notepad.this, "查找");
				Container content = jdSearchDialog.getContentPane();
				JLabel jl = new JLabel("查找内容：");
				jtField = new JTextField(20);
				jtField.requestFocus();
				JButton jbt1 = new JButton("查找");
				jbt1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						searchString = jtField.getText();
						stringLength = searchString.length();
						if (stringLength > 0) {
							startPos = jta.getText().indexOf(searchString);
							if (startPos >= 0) {
								jta.select(startPos, startPos + stringLength);
							} else {
								JOptionPane.showConfirmDialog(Notepad.this,
										"找不到" + "\"" + searchString + "\"");
							}
						} else {
							JOptionPane.showConfirmDialog(Notepad.this,
									"请输入查找内容");
						}
					}
				});

				JPanel jp1 = new JPanel(new BorderLayout(3, 1));
				jp1.add(jl, BorderLayout.WEST);
				jp1.add(jtField, BorderLayout.CENTER);
				jp1.add(jbt1, BorderLayout.EAST);

				JPanel jp3 = new JPanel(new GridLayout(1, 2));
				jp3.setBorder(BorderFactory.createTitledBorder("方向"));
				JRadioButton jrb1 = new JRadioButton("向上(U)");
				JRadioButton jrb2 = new JRadioButton("向下(D)");
				ButtonGroup bgButtonGroup = new ButtonGroup();
				bgButtonGroup.add(jrb1);
				bgButtonGroup.add(jrb2);
				jp3.add(jrb1);
				jp3.add(jrb2);

				JCheckBox jcb = new JCheckBox("区分大小写(c)");
				JPanel jp2 = new JPanel(new BorderLayout());
				JButton jbt2 = new JButton("取消");
				jbt2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						jdSearchDialog.dispose();
					}
				});
				jp2.add(jcb, BorderLayout.WEST);
				jp2.add(jp3, BorderLayout.CENTER);
				jp2.add(jbt2, BorderLayout.EAST);

				content.setLayout(new BorderLayout());
				content.add(jp1, BorderLayout.NORTH);
				content.add(jp2, BorderLayout.CENTER);
				jdSearchDialog
						.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				jdSearchDialog.setLocationRelativeTo(null);
				jdSearchDialog.setSize(400, 100);
				jdSearchDialog.setResizable(false);
				jdSearchDialog.setVisible(true);
			} else if (e.getActionCommand().equals("查找下一个(N)   F3")) {
				JOptionPane.showConfirmDialog(Notepad.this,
						"查找下一个(N)   F3  Clicked");
			} else if (e.getActionCommand().equals("替换")) {
				JOptionPane.showConfirmDialog(Notepad.this, "替换 Clicked");
			} else if (e.getActionCommand().equals("转到")) {
				JOptionPane.showConfirmDialog(Notepad.this, "转到");
			} else if (e.getActionCommand().equals("全选")) {
				jta.selectAll();
			} else if (e.getActionCommand().equals("日期")) {
				int pos = jta.getCaretPosition();
				java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
				java.util.Date date = calendar.getTime();
				tempString = date.toString();
				jta.insert(tempString, pos);
			}
		}

	}
}