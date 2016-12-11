package com.library.view;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.library.controller.*;
import com.library.model.*;


public class AddUsers extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JButton addUser, close;
	private PanelUserInfo infoPanel;
	private boolean bool_isActionAdd;

	public AddUsers() {
		
		this.setTitle("Add Users");

		addUser = new JButton("Add");
		addUser.setBounds(75, 500, 100, 30);
		close = new JButton("Cancel");
		close.setBounds(325, 500, 100, 30);
		infoPanel = new PanelUserInfo();
		infoPanel.setBounds(20, 20, 400, 400);
	
		this.add(addUser);
		this.add(close);
		this.add(infoPanel);

		this.setSize(500, 600);
		this.setLayout(null);

		addUser.addActionListener(new ActionListener() // add new user
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Validator validator = new Validator();
				if (validator.isUserIdValid(infoPanel.getIdText())
						&& validator.isUserNameValid(infoPanel.getNameText())
						&& validator.isUserPasswordValid(infoPanel.getPasswordText())
						&& validator.isUserPhoneNoValid(infoPanel.getPhoneNoText())

				) {
					bool_isActionAdd = true;
					JOptionPane.showMessageDialog(AddUsers.this,
							"New user added.", "OK", JOptionPane.PLAIN_MESSAGE);
					AddUsers.this.dispose();

				}

				else {
					JOptionPane.showMessageDialog(AddUsers.this,
							"Invaild user information!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		close.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				bool_isActionAdd = false;
				AddUsers.this.dispose();
			}
		});
	}

	public boolean isActionAdd() {

		return bool_isActionAdd;

	}

	public User getUser() { //return a user with info in the panel.
		User tempUser = new User();
		infoPanel.WriteTo(tempUser);
		return tempUser;
	}


	public static void test(String[] args) {
		new AddUsers();
	}

}
