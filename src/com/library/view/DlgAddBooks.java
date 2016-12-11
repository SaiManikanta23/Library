package com.library.view;

<<<<<<< HEAD
=======
import javax.imageio.ImageIO;
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
import com.library.model.*;
import javax.swing.*;

import com.library.controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
<<<<<<< HEAD
=======
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
import java.util.Date;

public class DlgAddBooks extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	private DlgAddBooksData data = new DlgAddBooksData();

	public DlgAddBooks(Library lib) {
		this.data.library = lib;
=======
	//private JFrame jf;
	private JPanel picturePanel;
	private JButton choosePic, add, close;
	private JTextField jtf_isbn, jtf_name, jtf_author;
	private JLabel jl_isbn, jl_name, jl_author, jl_category, picLabel;
	private JComboBox cb_category;
	private String picPath, picDirectory, picFileName;
	//private ImageIcon bookPic;
	private Library library;
	
	public DlgAddBooks(Library lib) {
		this.library = lib;
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add book");
		
<<<<<<< HEAD
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
=======
		choosePic = new JButton("Choose picture");
		choosePic.setBounds(300, 50, 150, 50);
		add = new JButton("Add");
		add.setBounds(75, 500, 100, 30);
		close = new JButton("Cancel");
		close.setBounds(325, 500, 100, 30);
		picturePanel = new JPanel();
		picturePanel.setBackground(Color.BLACK);
		picturePanel.setBounds(50, 20, 210, 280);

		picLabel = new JLabel();
		picLabel.setSize(210, 280);
		picturePanel.add(picLabel);

		jtf_isbn = new JTextField();
		jtf_isbn.setBounds(120, 330, 300, 25);
		jl_isbn = new JLabel("ISBN");
		jl_isbn.setBounds(40, 330, 100, 25);

		jtf_name = new JTextField();
		jtf_name.setBounds(120, 370, 300, 25);
		jl_name = new JLabel("Name");
		jl_name.setBounds(40, 370, 100, 25);

		jtf_author = new JTextField();
		jtf_author.setBounds(120, 410, 300, 25);
		jl_author = new JLabel("Author");
		jl_author.setBounds(40, 410, 100, 25);

		String[] categoryStrings = { "Children", "Cooking", "History", "Travel" };
		cb_category = new JComboBox(categoryStrings);
		cb_category.setBounds(120, 450, 300, 25);
		jl_category = new JLabel("Category");
		jl_category.setBounds(40, 450, 100, 25);

		this.add(choosePic);
		this.add(add);
		this.add(close);
		this.add(picturePanel);
		this.add(jtf_isbn);
		this.add(jtf_name);
		this.add(jtf_author);
		this.add(jl_isbn);
		this.add(jl_name);
		this.add(jl_author);
		this.add(cb_category);
		this.add(jl_category);
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49

		this.setSize(500, 600);
		this.setLayout(null);
		//this.setVisible(true); //remove by Li Huang 2014.8.15 to make it can be shown in Modal way

		//choosePic.addMouseListener(new MouseAdapter() // choose a picture
<<<<<<< HEAD
		data.choosePic.addActionListener(new ActionListener() // choose a picture
=======
		choosePic.addActionListener(new ActionListener() // choose a picture
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// System.out.println("fffff"); //test1
						FileDialog readFD = new FileDialog(new Frame(),
								"Choose a file", FileDialog.LOAD);
						readFD.setVisible(true);
<<<<<<< HEAD
						data.picDirectory = readFD.getDirectory();
						data.picFileName = readFD.getFile();
						data.picPath = data.picDirectory + data.picFileName;
						// System.out.println(picPath); //test
						ImageIcon bookImgIcon = CreateStretchImageIcon(data.picPath,
								data.picturePanel.getWidth(),
								data.picturePanel.getHeight());
						DlgAddBooks.this.data.picLabel.setIcon(bookImgIcon);
=======
						picDirectory = readFD.getDirectory();
						picFileName = readFD.getFile();
						picPath = picDirectory + picFileName;
						// System.out.println(picPath); //test
						ImageIcon bookImgIcon = CreateStretchImageIcon(picPath,
								picturePanel.getWidth(),
								picturePanel.getHeight());
						DlgAddBooks.this.picLabel.setIcon(bookImgIcon);
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
					}
				});

		//add.addMouseListener(new MouseAdapter() // add new book
<<<<<<< HEAD
		data.add.addActionListener(new ActionListener() // add new book
=======
		add.addActionListener(new ActionListener() // add new book
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Validator test = new Validator();

<<<<<<< HEAD
				String currentISBN = data.jtf_isbn.getText();
				String currentBookName = data.jtf_name.getText();
				String currentAuthor = data.jtf_author.getText();
				String currentCategoryString = (String) data.cb_category
=======
				String currentISBN = jtf_isbn.getText();
				String currentBookName = jtf_name.getText();
				String currentAuthor = jtf_author.getText();
				String currentCategoryString = (String) cb_category
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
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
<<<<<<< HEAD
					data.library.addBook(currentNewBook); // interface to other
											
					try {
						data.library.copyBookImage(data.picDirectory, data.picFileName, currentISBN);
=======

					// ***************************
					library.addBook(currentNewBook); // interface to other
															// frame, set
															// library object
															// before use

					// ***************************
					try {
						library.copyBookImage(picDirectory, picFileName, currentISBN);
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
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

<<<<<<< HEAD
		data.close.addActionListener( new ActionListener() {
=======
		close.addActionListener( new ActionListener() {
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
			@Override
			public void actionPerformed(ActionEvent e) {
				DlgAddBooks.this.dispose();
			}
		});
	}

	void loadPic(String path) {

	}

<<<<<<< HEAD
	/**
	 * @deprecated Use {@link com.library.view.DlgAddBooksData#CreateStretchImageIcon(String,int,int)} instead
	 */
	private ImageIcon CreateStretchImageIcon(String imgPath, int width,
			int height) {
				return data.CreateStretchImageIcon(imgPath, width, height);
			}
=======
	private ImageIcon CreateStretchImageIcon(String imgPath, int width,
			int height) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.print("cannot read file");
			return null;
		}

		Image scaledImg = img.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(scaledImg);
		
		return imageIcon;
	}
>>>>>>> 5777a24229b2da7e69cf2f5f2a25c29bb0dbbd49
}
