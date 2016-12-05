package com.library.view;


import javax.swing.*;

import com.library.controller.*;
import com.library.controller.FrmUsersManagement;
import com.library.controller.Library;
import com.library.model.*;
import com.library.view.*;
import com.library.model.*;
import com.library.view.*;

// Joe, 

/**
 * After Administrator sucessfully Login, Main menu  (Books, Customers and Categories)
 * 

 */
public class FrmAdminInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private Library library;
	
	public FrmAdminInterface(Library lib){
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
		
		//JButton btnClose = new JButton("Clsoe");
		JButton btnClose = new JButton("Close"); 
		btnClose.setBounds(450, 190, 90, 40);
		this.add(btnClose);
		
		btnBooks.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	FrmBooksManagement frmBookManagement = new FrmBooksManagement(library);
		    }
		});
		
		btnCustomers.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	FrmUsersManagement frmUserManagement = new FrmUsersManagement(library);
		    	frmUserManagement.setVisible(true);
		    }
		});
		
		btnCategories.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	FrmViewCategories N = new FrmViewCategories();
		    }
		});
		
		btnClose.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	FrmAdminInterface.this.setVisible(false);
		    }
		});
		}
	
	public FrmAdminInterface() {}
}
