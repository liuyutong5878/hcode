package com.test;
import java.awt.*;
import javax.swing.*;
 
public class HelpDialog extends JDialog{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelpDialog(Notepad f)
    {
        super(f,"help",true);
        Container content=getContentPane();
        JLabel jl1=new JLabel("Notepad 1.0");
        JLabel jl2=new JLabel("作者： Pan");
        JLabel jl3=new JLabel("Email：4443480**@qq.com");
        content.setLayout(new GridLayout(3,1));
        content.add(jl1);
        content.add(jl2);
        content.add(jl3);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(f);
        setSize(200, 100);
        setVisible(true);
    }
}