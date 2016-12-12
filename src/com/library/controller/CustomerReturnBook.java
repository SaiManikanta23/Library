package com.library.controller;

import javax.swing.*;
import com.library.model.*;
import com.library.view.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class CustomerReturnBook extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final int N_Book_Table_Columns = 4;
	private final int TBBook_BookObjColIndex = 3; 
	private final String[] TBBookColumnTitle = {"ISBN", "Book Name", "Author", "bookObj"};
	
	private boolean isResponseTbBooksSelecetedChanged = true;
	
	private add_Book_ToAccount library;
	private User user;
	
	private JLabel display_Message;
	private JButton return_Button, close_Button;
	private JTable books_Table;
	private JPanel left_Panel, right_Panel;
	private PanelBookInfo bookInfo;
	private JScrollPane scrTbBook;
	
	private DefaultTableModel tbBooksModel;
	
	CustomerReturnBook(){
		this(null, null);
	}
	
	public CustomerReturnBook(add_Book_ToAccount l, User u){
		this.library = l;
		this.user = u;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocation(350, 50);
		
		left_Panel = new JPanel();
		left_Panel.setLayout(null);
		
		right_Panel = new JPanel();
		
		bookInfo = new PanelBookInfo(library);
		bookInfo.setEditable(false);
		
		display_Message = new JLabel();
		display_Message.setText("Rented Books:");
		display_Message.setBounds(20, 20, 90, 30);
		
		return_Button = new JButton("Return");
		return_Button.setBounds(80, 400, 75, 30);
		
		close_Button = new JButton("Close");
		close_Button.setBounds(220, 400, 75, 30);
		
		tbBooksModel = new DefaultTableModel(
				new Object[][]{},
				TBBookColumnTitle
		);
		
		books_Table = new JTable();
		books_Table.setModel(tbBooksModel);
		books_Table.setBorder(new LineBorder(new Color(0, 0, 0)));
		books_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//hide unnecessary columns
		books_Table.getColumnModel().getColumn(TBBook_BookObjColIndex).setMinWidth(0);
		books_Table.getColumnModel().getColumn(TBBook_BookObjColIndex).setMaxWidth(0);
		books_Table.getColumnModel().getColumn(TBBook_BookObjColIndex).setWidth(0);
		
		SelectionListener listener = new SelectionListener(books_Table);
		books_Table.getSelectionModel().addListSelectionListener(listener);
		
		scrTbBook=new JScrollPane(books_Table);
		scrTbBook.setSize(200, 200);
		scrTbBook.setBounds(20, 55, 380, 330);
		
		left_Panel.add(scrTbBook);
		left_Panel.add(display_Message);
		left_Panel.add(return_Button);
		left_Panel.add(close_Button);
		left_Panel.setSize(600, 500);
		left_Panel.setBounds(5, 5, 400, 600);
		
		right_Panel.setSize(500, 500);
		right_Panel.setBounds(450, 20, 240, 400);
		right_Panel.setLayout(new BorderLayout());
		right_Panel.add(bookInfo,BorderLayout.CENTER);
		
		this.setTitle("Return Book");
		this.add(left_Panel);
		this.add(right_Panel);
		this.setSize(720, 500);
		this.setResizable(false);
		this.setVisible(true);
		
		return_Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				if(getSelectedBook() == null){
					JOptionPane.showMessageDialog(CustomerReturnBook.this, "Please select a book!", "Return Failed", JOptionPane.WARNING_MESSAGE);
				} else{
					int n = JOptionPane.showConfirmDialog(
							CustomerReturnBook.this,
						    "Are you sure you want to return " + getSelectedBook().getBookName() + "?",
						    "Return Confirm",
						    JOptionPane.YES_NO_OPTION);
					if(n == 0){
						double fine = library.fine(getISBN(), new Date());
						CustomerReturnBookFinish dlgReturnFinish=new CustomerReturnBookFinish(fine);
						dlgReturnFinish.setModal(true);
						dlgReturnFinish.setVisible(true);
						library.returnBook(getISBN());
						refreshTable();
					}
				}
			}
		});
		
		close_Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				CustomerReturnBook.this.dispose();
			}
		});
		
		//--- init ----
		refreshTable();
	}
	
	private void refreshTable(){
		int n = tbBooksModel.getRowCount();
		for(int i = 0;i < n;i++){
			tbBooksModel.removeRow(0);
		}
		Object[][] data = getRentedBookTableData();
		
		int nDataRow=data.length;
		for (int i = 0;i < nDataRow;i++) {
			this.tbBooksModel.addRow(data[i]);
		}
	}
	
	private String getISBN(){
		Book book = getSelectedBook();
		String isbn = book.getIsbn();
		return isbn;
	}
	
	private Object[] createBookTableRowData(Book book){
		Object[] row = new Object[N_Book_Table_Columns];
		row[0] = book.getIsbn();
		row[1] = book.getBookName();
		row[2] = book.getAuthor();
		row[3] = book;
		return row; 
	}
	
	private Object[][] getRentedBookTableData(){
		if(this.library == null){
			return null;
		} else{
			if(this.user == null){
				return null;
			} else{
				ArrayList<Book> books = library.showBookList_BorrowedByCustomer(user.getUserId());
				int nBooks = books.size();
				Object[][] booksData = new Object[nBooks][];
				for(int i = 0;i < nBooks;i++){
					booksData[i] = createBookTableRowData(books.get(i));
				}
				return booksData;
			}
		}
	}
	
	private Book getSelectedBook() {
		JTable table = this.books_Table;
		int selRow = table.getSelectedRow();
		if(selRow < 0)
			return null;
        Book book = (Book)table.getValueAt(selRow, TBBook_BookObjColIndex);
        return book;
	}
	
	@SuppressWarnings("unused")
	private void setResponseTbBooksSelectedChanged(boolean enable) {
		isResponseTbBooksSelecetedChanged = enable;
	}
	
	private boolean getResponseTbBooksSelectedChanged() {
		return isResponseTbBooksSelecetedChanged;
	}
	
	private class SelectionListener implements ListSelectionListener {
        @SuppressWarnings("unused")
		JTable table;
        SelectionListener(JTable table) {
            this.table = table;
        }
        public void valueChanged(ListSelectionEvent e) {
        		if (!getResponseTbBooksSelectedChanged()) {
        			return;
        		}
                Book book = getSelectedBook();
                if (book == null)
                	bookInfo.clear();
                else
                	bookInfo.ReadFrom(book);
            }
    }
	
}
