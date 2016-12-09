package com.library.view;

import com.library.model.*;
import javax.swing.*;

import com.library.controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class DlgAddBooks extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DlgAddBooksData data = new DlgAddBooksData();

	public DlgAddBooks(Library lib) {
		this.data.library = lib;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add book");
		
		data.choosePic = new JButton("Choose picture");
		data.choosePic.setBounds(300, 50, 150, 50);
		data.add = new JButton("Add");
		data.add.setBounds(75, 500, 100, 30);
		data.close = new JButton("Cancel");
		data.close.setBounds(325, 500, 100, 30);
		data.picturePanel = new JPanel();
		data.picturePanel.setBackground(Color.BLACK);
		data.picturePanel.setBounds(50, 20, 210, 280);

		data.picLabel = new JLabel();
		data.picLabel.setSize(210, 280);
		data.picturePanel.add(data.picLabel);

		data.jtf_isbn = new JTextField();
		data.jtf_isbn.setBounds(120, 330, 300, 25);
		data.jl_isbn = new JLabel("ISBN");
		data.jl_isbn.setBounds(40, 330, 100, 25);

		data.jtf_name = new JTextField();
		data.jtf_name.setBounds(120, 370, 300, 25);
		data.jl_name = new JLabel("Name");
		data.jl_name.setBounds(40, 370, 100, 25);

		data.jtf_author = new JTextField();
		data.jtf_author.setBounds(120, 410, 300, 25);
		data.jl_author = new JLabel("Author");
		data.jl_author.setBounds(40, 410, 100, 25);

		String[] categoryStrings = { "Children", "Cooking", "History", "Travel" };
		data.cb_category = new JComboBox(categoryStrings);
		data.cb_category.setBounds(120, 450, 300, 25);
		data.jl_category = new JLabel("Category");
		data.jl_category.setBounds(40, 450, 100, 25);

		this.add(data.choosePic);
		this.add(data.add);
		this.add(data.close);
		this.add(data.picturePanel);
		this.add(data.jtf_isbn);
		this.add(data.jtf_name);
		this.add(data.jtf_author);
		this.add(data.jl_isbn);
		this.add(data.jl_name);
		this.add(data.jl_author);
		this.add(data.cb_category);
		this.add(data.jl_category);

		this.setSize(500, 600);
		this.setLayout(null);
		//this.setVisible(true); //remove by Li Huang 2014.8.15 to make it can be shown in Modal way

		//choosePic.addMouseListener(new MouseAdapter() // choose a picture
		data.choosePic.addActionListener(new ActionListener() // choose a picture
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// System.out.println("fffff"); //test1
						FileDialog readFD = new FileDialog(new Frame(),
								"Choose a file", FileDialog.LOAD);
						readFD.setVisible(true);
						data.picDirectory = readFD.getDirectory();
						data.picFileName = readFD.getFile();
						data.picPath = data.picDirectory + data.picFileName;
						// System.out.println(picPath); //test
						ImageIcon bookImgIcon = CreateStretchImageIcon(data.picPath,
								data.picturePanel.getWidth(),
								data.picturePanel.getHeight());
						DlgAddBooks.this.data.picLabel.setIcon(bookImgIcon);
					}
				});

		//add.addMouseListener(new MouseAdapter() // add new book
		data.add.addActionListener(new ActionListener() // add new book
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Validator test = new Validator();

				String currentISBN = data.jtf_isbn.getText();
				String currentBookName = data.jtf_name.getText();
				String currentAuthor = data.jtf_author.getText();
				String currentCategoryString = (String) data.cb_category
						.getSelectedItem();
				// System.out.println(currentCategory); //test1
				Category currentCategory;
				switch (currentCategoryString) {
				case "Children": {
					currentCategory = Category.CHILDREN;
					break;
				}
				case "Cooking": {
					currentCategory = Category.COOKING;
					break;
				}
				case "History": {
					currentCategory = Category.HISTORY;
					break;
				}
				case "Travel": {
					currentCategory = Category.TRAVEL;
					break;
				}// case travel
				default:
					currentCategory = null;

				}// switch

				if (test.isBookIsbnValid(currentISBN)
						&& test.isContentValid(currentBookName)
						&& test.isContentValid(currentAuthor)) {

					Book currentNewBook = new Book();
					currentNewBook.setBookName(currentBookName);
					currentNewBook.setAuthor(currentAuthor);
					currentNewBook.setIsbn(currentISBN);
					currentNewBook.setLastRented(null);
					currentNewBook.setAddedDate(new Date());
					currentNewBook.setRented(false);
					currentNewBook.setOwnerId(Library.LIBRARY_OWNER_ID);
					currentNewBook.setCategory(currentCategory);
					data.library.addBook(currentNewBook); // interface to other
											
					try {
						data.library.copyBookImage(data.picDirectory, data.picFileName, currentISBN);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						System.out.println("No picture selected.");
					} finally {
						JOptionPane.showMessageDialog(DlgAddBooks.this,
								"New book added.", "OK",
								JOptionPane.PLAIN_MESSAGE);
						DlgAddBooks.this.dispose(); 
					}

				}
				else {
					JOptionPane.showMessageDialog(DlgAddBooks.this,
							"Invaild book information!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		data.close.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgAddBooks.this.dispose();
			}
		});
	}

	void loadPic(String path) {

	}

	/**
	 * @deprecated Use {@link com.library.view.DlgAddBooksData#CreateStretchImageIcon(String,int,int)} instead
	 */
	private ImageIcon CreateStretchImageIcon(String imgPath, int width,
			int height) {
				return data.CreateStretchImageIcon(imgPath, width, height);
			}
}
