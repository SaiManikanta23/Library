package com.library.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Simply a Frame that lists all the Categories we have for different books
	 * Sai Changes
 */
public class FrmViewCategories extends JFrame {
	private static final long serialVersionUID = 1L;

	private final String StrCategories = "Children\nCooking\nHistory\nTravel";
	
	public FrmViewCategories(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setTitle("Book Categories");
		this.setSize(300, 450);
		this.setLocation(250,40);
		this.setVisible(true);
		this.setLayout(null);

		JTextArea TF = new JTextArea();
		TF.setBounds(20, 50, 260, 300);
		TF.setEditable(false);
		TF.setText(StrCategories);
		this.add(TF);
		
		JButton btnOk = new JButton("Close");
		btnOk.setBounds(120, 360, 70, 40);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		this.add(btnOk);
	}
}
