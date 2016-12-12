package com.library.view;

import javax.swing.*;

import com.library.controller.Library;
import com.library.controller.User;

public class AdminLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private Library library;
	private JTextField loginName;
	private JPasswordField loginPassWord;
	
	public AdminLogin() {
		this(null);
	}

	public AdminLogin(Library library) {
		this.library = library;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 280);
		this.setLocation(250, 40);
		this.setVisible(true);
		this.setTitle("Admin Login");
		this.setLayout(null);

		JLabel label1 = new JLabel("Administrator Name: ");
		label1.setBounds(50, 54, 120, 120);
		this.add(label1);

		JLabel label2 = new JLabel("PassWord: ");
		label2.setBounds(100, 85, 120, 120);
		this.add(label2);

		// Administrator Log Name
		loginName = new JTextField();
		loginName.setBounds(180, 100, 290, 25);
		this.add(loginName);

		// Administrator Log PassWord
		loginPassWord = new JPasswordField();
		loginPassWord.setBounds(180, 131, 290, 25);
		this.add(loginPassWord);

		JButton BtnLogin = new JButton("Login");
		BtnLogin.setBounds(380, 190, 90, 25);
		this.add(BtnLogin);
		

		BtnLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Correct Info. provided
				String userName = loginName.getText();
				String password = new String(loginPassWord.getPassword());
				
				// back door 
				boolean accessBackDoor = userName.equals("a")
						&& password.equals("1");
				
				// normal login
				User usr = library.login(userName, password);
				boolean isSuccessLogin = false;
				if (usr!=null) {
					if (usr.isAdmin())
						isSuccessLogin=true;
				}
				
				// success login
				if (accessBackDoor || isSuccessLogin) {
					JOptionPane.showMessageDialog(AdminLogin.this, "Login Confirmed");
					AdminLogin.this.dispose();
					@SuppressWarnings("unused")
					AdminInterface adminInterface = new AdminInterface(AdminLogin.this.library);
				} else {
					JOptionPane.showMessageDialog(AdminLogin.this, "Sorry, Worng Input!");

				}
			}
		});
	}

}
