package com.library.view;

import javax.swing.*;
import com.library.controller.BorrowBook;
import com.library.controller.CustomerReturnBook;
import com.library.controller.Library;
import com.library.controller.User;

import java.awt.event.*;
/**
 * Frame to guide customer
 */

public class CustomerInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private Library library;
	private User user;
	
	private JButton btnBorrow, btnReturn, btnClose;
	
	CustomerInterface(){
		this(null, null);
	}
	public CustomerInterface(Library l, User u){
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
				new CustomerReturnBook(library, user);
			}
		});
		
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				CustomerInterface.this.dispose();
				new CustomerLogin(library);
			}
		});
		
		btnBorrow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				BorrowBook borrow = new BorrowBook(library, user);
				borrow.setVisible(true);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CustomerInterface.this.dispose();
				new CustomerLogin(library);
			}
		});
	}
}
