package com.library.view;


import javax.swing.*;

import com.library.controller.UsersManagement;
import com.library.controller.Library;
public class AdminInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private Library library;
	
	public AdminInterface(Library lib){
		this.library=lib;

		this.setSize(600, 280);
		this.setLocation(250,40);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("Admin Interface"); 
		
		JButton btnBooks = new JButton("Books");
		btnBooks.setBounds(140, 80, 90, 40);
		this.add(btnBooks);
		
		JButton btnCustomers = new JButton("Customers");
		btnCustomers.setBounds(260, 80, 110, 40);
		this.add(btnCustomers);
		
		JButton btnCategories = new JButton("Categories");
		btnCategories.setBounds(400, 80, 110, 40);
		this.add(btnCategories);
				JButton btnClose = new JButton("Close"); 
		btnClose.setBounds(450, 190, 90, 40);
		this.add(btnClose);
		
		btnBooks.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	new BooksManagement(library);
		    }
		});
		
		btnCustomers.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	UsersManagement frmUserManagement = new UsersManagement(library);
		    	frmUserManagement.setVisible(true);
		    }
		});
		
		btnCategories.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	new FrmViewCategories();
		    }
		});
		
		btnClose.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	AdminInterface.this.setVisible(false);
		    }
		});
		}
	
	public AdminInterface() {}
}
