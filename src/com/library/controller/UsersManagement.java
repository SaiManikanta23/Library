//TODO: change this class name to FrmUsersManagement 
// need to implement add, update, delete method seperately
// CRUD operations of users
package com.library.controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.library.model.*;
import com.library.view.*;

public class UsersManagement extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Library library;
	private Validator validator=new Validator();
	
	private PanelUserInfo pnlUserInfo;
	private boolean isResponseTbUserSelecetedChanged=true;
	private JTable tbUser;
	private DefaultTableModel tbUserModel;
	
	private final int N_User_Table_Columns = 3;
	private final String[] TBUserColumnTitle = { "userId", "userName", "userObj" };	
	private final int TBUser_UserObjColIndex=2; //the column index of the user table
	
	private class SelectionListener implements ListSelectionListener {

        SelectionListener(JTable table) {
        }
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
        		if (!getResponseTbUserSelectedChanged()) {
        			return;
        		}
           }
    }
	
	public UsersManagement(){
		this(null);
	}
	
	public UsersManagement(Library library){
		this.library=library;
		
		this.setSize(850, 600);
		this.setLocation(250,40);
		this.setTitle("Customers Management");	
		this.setLayout(null);
		
		Object rowData[][] = {};
		tbUserModel = new DefaultTableModel(rowData,
				TBUserColumnTitle);
		tbUser= new JTable();
		tbUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbUser.setModel(tbUserModel);
		SelectionListener listener = new SelectionListener(tbUser);
		tbUser.getSelectionModel().addListSelectionListener(listener);
		
		JScrollPane scrollPane = new JScrollPane(tbUser);
		scrollPane.setBounds(50, 50, 400, 400);
		this.add(scrollPane);
		
		// No reports of customers are expected in this Frame
		
		JButton add_Button = new JButton("Add");
		add_Button.setVisible(true);
		add_Button.setBounds(60, 520, 100, 30);
		this.add(add_Button);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setVisible(true);
		btnUpdate.setBounds(200, 520, 100, 30);
		this.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setVisible(true);
		btnDelete.setBounds(340, 520, 100, 30);
		this.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.setVisible(true);
		btnClose.setBounds(635, 520, 100, 30);
		this.add(btnClose);
		
		pnlUserInfo = new PanelUserInfo();
		this.add(pnlUserInfo);
		pnlUserInfo.setBounds(480, 50,350,400);
		
		//----------- Init ---------------------------
		this.refreshUserTableUI();
		
		//------------ Event Handlers ------------------------------------
		btnClose.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	UsersManagement.this.dispose();
		    }
		});
		
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	deleteSelectedUser();
		    	refreshUserTableUI();
		    }
		});
		
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	updateSelectedUser();
		    	refreshUserTableUI();
		    }
		});
		
		add_Button.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	AddUsers dlgAddUser =new AddUsers();
		    	dlgAddUser.setModal(true);dlgAddUser.setVisible(true);
		    	
				if (dlgAddUser.isActionAdd()) {
		    		User user = dlgAddUser.getUser();
		    		if (user != null)
		    		{	
		    			addUser(user);
		    			refreshUserTableUI();
		    		}
		    	}
		    }
		});
	}
	
	/**
	 * Get current selected user
	 * @return current selected user in the User Table,if no user is selected, return null
	 */
	private User getSelectedUser() {
		JTable table = this.tbUser;
		int selRow = table.getSelectedRow();
		if (selRow<0) //no selection
			return null;
        User user = (User)table.getValueAt(selRow, TBUser_UserObjColIndex);
        return user;
	}
	
	/**
	 * Fill the table with books according to the UI options
	 */
	private void refreshUserTableUI() {
		this.setResponseTbUserSelectedChanged(false);
		PanelUserInfo panel_User = this.pnlUserInfo;
		panel_User.cmbUserType.setSelectedIndex(0);
		panel_User.txtId.setText("");
		panel_User.txtName.setText("");
		panel_User.txtPassword.setText("");
		panel_User.txtPhoneNo.setText("");
		panel_User.txtAddress.setText("");
		refreshUserTable();
		this.setResponseTbUserSelectedChanged(true);
		
	}
	
	private void refreshUserTable() {
		int n = tbUserModel.getRowCount();
		for (int i=0;i<n;i++)
			tbUserModel.removeRow(0);
		
		Object[][] data = getAllUserTableData();
		
		if (data==null) {
			return;
		}
		
		int nDataRows = data.length;
		for (int i=0;i<nDataRows;i++){
			this.tbUserModel.addRow(data[i]);
		}
	}
	

	
	private Object[] createUserTableRowData(User user) {
		Object[] row = new Object[N_User_Table_Columns];
		row[0]=user.getUserId();
		row[1]=user.getUserName();
		row[2]=user;
		return row;
	}
	
	private Object[][] getAllUserTableData() {
		if (this.library==null)
			return null;
		else {
			List<User> users =library.showUserList();
			int num_Of_Users = users.size();//number of users
			Object[][] booksData = new Object[num_Of_Users][];
			for (int i=0;i<num_Of_Users;i++) {
				booksData[i]=createUserTableRowData(users.get(i));
			}

			return booksData;
		}
	}
	
	/**
	 * Set whether will the TbBooksSelectedChanged envent handler will be triggered
	 */
	private void setResponseTbUserSelectedChanged(boolean enable) {
		isResponseTbUserSelecetedChanged = enable;
	}
	
	private boolean getResponseTbUserSelectedChanged() {
		return isResponseTbUserSelecetedChanged;
	}
	
	
	private void deleteSelectedUser() {
		User user =  getSelectedUser();
		if (user==null) {
			JOptionPane.showMessageDialog(null, "Please select a user to delete!");
			return;
		}
		
				boolean suc = this.library.deleteUser(user.getUserId());
		if (!suc) {
			
			JOptionPane.showMessageDialog(UsersManagement.this,
					"This user have holding book(s).", "Error",
					JOptionPane.ERROR_MESSAGE);
			
		} else
		{
			JOptionPane.showMessageDialog(null, "user "+user.getUserName()+" deleted");
		}
	}
	
	private void updateSelectedUser() {
		User user = getSelectedUser();
		if (user == null) {
			JOptionPane.showMessageDialog(null,
				"Please select a user to update!");
			return;
		}

		User newUser = new User();
		this.pnlUserInfo.WriteTo(newUser);
		
		boolean suc = this.library.updateUser(user.getUserId(), newUser);
		
		if (!suc) {
			JOptionPane.showMessageDialog(null, "User not exists.");
		} else {
			JOptionPane.showMessageDialog(null, "user " + user.getUserName()
					+ " updated");
		}
	}
	
	private void addUser(User user) {
		if (user==null) {
			JOptionPane.showMessageDialog(null, "Cannot add null user!");
			return;
		}
		
		if (! validator.isUserValid(user)) {
			JOptionPane.showMessageDialog(null, "Cannot add invalid user!");
			return;
		}
		
		boolean suc = this.library.addUser(user);
		if (!suc) {
			JOptionPane.showMessageDialog(null, "Add user failed. Please check the user is valid!");
			return;
		}
	}
	
	/**
	 * Test
	 * @param args
	 */
	public static void test(String[] args) {
		Library lib=new Library();
		Book b1 = new Book();
		Book b2 = new Book();
		
		b1.setIsbn("111");
		b1.setBookName("book1");
		b1.setCategory(Category.HISTORY);
		
		b2.setIsbn("222");
		b2.setBookName("book2");
		b2.setCategory(Category.COOKING);
		
		lib.addBook(b1);
		lib.addBook(b2);
		
		User u1 = new User();
		u1.setUserName("Sai");
		u1.setUserId(111);
		u1.setPassword("it is a secrect");
		u1.setPhoneNo("512-309-4444");
		u1.setAddress("2330N oliver, Wichita, Kansas");
		
		User u2 = new User();
		u2.setAdmin(true);
		u2.setUserName("Chaitanya");
		u2.setUserId(222);
		u2.setPassword("My password is here");
		u2.setPhoneNo("858-222-1111");
		u2.setAddress("Wichita State University, Wichita, Kansas");
		
		lib.addUser(u1);
		lib.addUser(u2);
		
		UsersManagement frame = new UsersManagement(lib);
		
		frame.setVisible(true);
	}
}
