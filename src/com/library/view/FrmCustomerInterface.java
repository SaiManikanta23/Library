package com.library.view;

import javax.swing.*;
<<<<<<< HEAD
import com.library.controller.*;
import com.library.controller.FrmBorrowBook;
import com.library.controller.FrmCustomerReturnBook;
import com.library.controller.Library;
import com.library.controller.User;

import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
=======

<<<<<<< HEAD
import com.library.controller.*;

import java.awt.*;
import java.awt.event.*;
=======
import java.awt.*;
import java.awt.event.*;
import Library.*;
>>>>>>> 1f0f08224b65a781d105c346f0ecdb9c21c4aa25
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
/**
 * Frame to guide customer
 */

public class FrmCustomerInterface extends JFrame{
<<<<<<< HEAD
=======
	/**
	 * 
	 */
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
	private static final long serialVersionUID = 1L;
	
	private Library library;
	private User user;
	
	private JButton btnBorrow, btnReturn, btnClose;
	
	FrmCustomerInterface(){
		this(null, null);
	}
	public FrmCustomerInterface(Library l, User u){
		this.library = l;
		this.user = u;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Customer Interface");
		this.setLocation(350, 50);
		
		btnBorrow = new JButton("Borrow Book");
		btnBorrow.setBounds(20, 20, 130, 30);
		
		btnReturn = new JButton("Return Book");
		btnReturn.setBounds(230, 20, 130, 30);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(125, 80, 130, 30);
		
		this.setLayout(null);
		this.add(btnBorrow);
		this.add(btnReturn);
		this.add(btnClose);
		this.setSize(400, 200);
		this.setResizable(false);
		this.setVisible(true);
		
		btnReturn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				new FrmCustomerReturnBook(library, user);
			}
		});
		
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				FrmCustomerInterface.this.dispose();
				new FrmCustomerLogin(library);
			}
		});
		
		btnBorrow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				FrmBorrowBook borrow = new FrmBorrowBook(library, user);
				borrow.setVisible(true);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FrmCustomerInterface.this.dispose();
				new FrmCustomerLogin(library);
			}
		});
	}
}
