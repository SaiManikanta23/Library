package Library;

import javax.swing.*;
import com.library.model.*;
import com.library.view.*;
import com.library.model.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Frame to display rented books to the customer and return the rented books.
 */


public class FrmCustomerReturnBook extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int N_Book_Table_Columns = 4;
	private final int TBBook_BookObjColIndex = 3; 
	private final String[] TBBookColumnTitle = {"ISBN", "Book Name", "Author", "bookObj"};
	
	private boolean isResponseTbBooksSelecetedChanged = true;
	
	private Library library;
	private User user;
	
	private JLabel lblMsg;
	private JButton btnReturn, btnClose;
	private JTable tbBooks;
	private JPanel pnlLeft, pnlRight;
	private PanelBookInfo pnlBookInfo;
	private JScrollPane scrTbBook;
	
	private DefaultTableModel tbBooksModel;
	
	FrmCustomerReturnBook(){
		this(null, null);
	}
	
	public FrmCustomerReturnBook(Library l, User u){
		this.library = l;
		this.user = u;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocation(350, 50);
		
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);
		
		pnlRight = new JPanel();
		
		pnlBookInfo = new PanelBookInfo(library);
		pnlBookInfo.setEditable(false);
		
		lblMsg = new JLabel();
		lblMsg.setText("Rented Books:");
		lblMsg.setBounds(20, 20, 90, 30);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(80, 400, 75, 30);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(220, 400, 75, 30);
		
		tbBooksModel = new DefaultTableModel(
				new Object[][]{},
				TBBookColumnTitle
		);
		
		tbBooks = new JTable();
		tbBooks.setModel(tbBooksModel);
		tbBooks.setBorder(new LineBorder(new Color(0, 0, 0)));
		tbBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//hide unnecessary columns
		tbBooks.getColumnModel().getColumn(TBBook_BookObjColIndex).setMinWidth(0);
		tbBooks.getColumnModel().getColumn(TBBook_BookObjColIndex).setMaxWidth(0);
		tbBooks.getColumnModel().getColumn(TBBook_BookObjColIndex).setWidth(0);
		
		SelectionListener listener = new SelectionListener(tbBooks);
		tbBooks.getSelectionModel().addListSelectionListener(listener);
		
		scrTbBook=new JScrollPane(tbBooks);
		scrTbBook.setSize(200, 200);
		scrTbBook.setBounds(20, 55, 380, 330);
		
		pnlLeft.add(scrTbBook);
		pnlLeft.add(lblMsg);
		pnlLeft.add(btnReturn);
		pnlLeft.add(btnClose);
		pnlLeft.setSize(600, 500);
		pnlLeft.setBounds(5, 5, 400, 600);
		
		pnlRight.setSize(500, 500);
		pnlRight.setBounds(450, 20, 240, 400);
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(pnlBookInfo,BorderLayout.CENTER);
		
		this.setTitle("Return Book");
		this.add(pnlLeft);
		this.add(pnlRight);
		this.setSize(720, 500);
		this.setResizable(false);
		this.setVisible(true);
		
		btnReturn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				if(getSelectedBook() == null){
					JOptionPane.showMessageDialog(FrmCustomerReturnBook.this, "Please select a book!", "Return Failed", JOptionPane.WARNING_MESSAGE);
				} else{
					int n = JOptionPane.showConfirmDialog(
							FrmCustomerReturnBook.this,
						    "Are you sure you want to return " + getSelectedBook().getBookName() + "?",
						    "Return Confirm",
						    JOptionPane.YES_NO_OPTION);
					if(n == 0){
						double fine = library.fine(getISBN(), new Date());
						DlgCustomerReturnBookFinish dlgReturnFinish=new DlgCustomerReturnBookFinish(fine);
						dlgReturnFinish.setModal(true);
						dlgReturnFinish.setVisible(true);
						library.returnBook(getISBN());
						refreshTable();
					}
				}
			}
		});
		
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae){
				FrmCustomerReturnBook.this.dispose();
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
		JTable table = this.tbBooks;
		int selRow = table.getSelectedRow();
		if(selRow < 0)
			return null;
        Book book = (Book)table.getValueAt(selRow, TBBook_BookObjColIndex);
        return book;
	}
	
	private void setResponseTbBooksSelectedChanged(boolean enable) {
		isResponseTbBooksSelecetedChanged = enable;
	}
	
	private boolean getResponseTbBooksSelectedChanged() {
		return isResponseTbBooksSelecetedChanged;
	}
	
	private class SelectionListener implements ListSelectionListener {
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
                	pnlBookInfo.clear();
                else
                	pnlBookInfo.ReadFrom(book);
            }
    }
	
}
