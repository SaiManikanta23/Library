package com.library.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;


<<<<<<< HEAD

public class FrmAbout extends JDialog{

=======
/**
 * About form, show author and version information
 */
public class FrmAbout extends JDialog{

	/**
	 * 
	 */
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
	private static final long serialVersionUID = 1L;
	private JTextField txtAuthor;
	private JTextField txtVersion;
	private JButton btnOk;
	
	private Random r;
	
	private final String StrAuthors = "Sai Manikanta & Viswa Chaitanya";
	private final String StrVersion = "Version: "+ new DecimalFormat("#0.00").format(1.0);
	
	public FrmAbout() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("About");
		this.setSize(500,500);
		this.setLayout(null);
		
		r = new Random();
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(50,50,400,50);
		txtAuthor.setBorder(null);
		txtAuthor.setBackground(this.getBackground());
		txtAuthor.setFont(new Font(null, Font.BOLD, 20));
		
		txtVersion = new JTextField();
		txtVersion.setBounds(195,180,130,30);
		txtVersion.setBorder(null);
		txtVersion.setBackground(this.getBackground());
		txtVersion.setFont(new Font(null, Font.BOLD, 20));
		
		txtAuthor.setAutoscrolls(true);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(215,320,70,50);
		
		this.add(txtAuthor);
		this.add(txtVersion);
		this.add(btnOk);
		
		//--------- init -----------------
		txtAuthor.setText(StrAuthors);
		txtVersion.setText(StrVersion);
		
		
		this.setVisible(true);
		
		txtAuthor.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me){
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				txtAuthor.setForeground(randomColor());
				txtAuthor.setLocation(x, y);
			}
		});
		txtVersion.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me){
				int x = r.nextInt(400);
				int y = r.nextInt(400);
				txtVersion.setForeground(randomColor());
				txtVersion.setLocation(x, y);
			}
		});
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				FrmAbout.this.dispose();
			}
		});
	}
	
	private Color randomColor() {
		int r = (int)(Math.random()*255);
		int g = (int)(Math.random()*255);
		int b = (int)(Math.random()*255);
		return new Color(r,g,b);
	}
	
	public static void test(String args[]) {
		new FrmAbout();
	}
}
