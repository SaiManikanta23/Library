package com.library.view;

import java.awt.BorderLayout;

import com.library.controller.*;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class BorrowBookFinish extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel Message;
	private JTextField Isbn;
	private JTextField Name;
	private JTextArea Reason;
	private JButton button_Ok;

	/**
	 * Create the dialog.
	 */
	public BorrowBookFinish(boolean isSuccess, Book book) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Borrow Book Finish");
		setBounds(100, 100, 357, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			Message = new JLabel("Success Borrowed!");
			Message.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		
		JLabel lblIsbn = new JLabel("ISBN");
		
		JLabel lblName = new JLabel("Name");
		
		Isbn = new JTextField();
		Isbn.setEditable(false);
		Isbn.setText("123");
		Isbn.setColumns(10);
		
		Name = new JTextField();
		Name.setEditable(false);
		Name.setText("Book1");
		Name.setColumns(10);
		
		Reason = new JTextArea();
		Reason.setEditable(false);
		Reason.setWrapStyleWord(true);
		Reason.setLineWrap(true);
		Reason.setForeground(Color.RED);
		Reason.setText("We are sorry you cannot borrow this book, because this book is not in our library or has already been rented by other users. Please call our customer service 123-456-789.");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(Reason, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
								.addComponent(Message)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(78)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblIsbn)
									.addGap(11)
									.addComponent(Isbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Message)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Reason)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIsbn)
						.addComponent(Isbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				button_Ok = new JButton("OK");
				button_Ok.setActionCommand("OK");
				buttonPane.add(button_Ok);
				getRootPane().setDefaultButton(button_Ok);
			}
		}

		this.button_Ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BorrowBookFinish.this.dispose();
			}
		});
		
		//----------------------- init -------------------------
		if (isSuccess) {
			setSuccess(book);
		} else {
			setFailed(book);
		}
	}
public void setSuccess(Book book) {
		if (book!=null){
			this.Message.setForeground(Color.BLACK);
			this.Message.setText("Success Borrowed!");
			this.Isbn.setText(book.getIsbn());
			this.Name.setText(book.getBookName());
			this.Reason.setVisible(false);
		}
	}
	
	public void setFailed(Book book) {
		this.Message.setForeground(Color.RED);
		this.Message.setText("Borrow Failed!");
		this.Reason.setVisible(true);
		
		if (book!=null){
			this.Isbn.setText(book.getIsbn());
			this.Name.setText(book.getBookName());
		} else	{
			this.Isbn.setText("");
			this.Name.setText("");
		}
	}
}
