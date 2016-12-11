package com.library.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.library.controller.Library;

public class DlgAddBooksData {
	public JPanel picturePanel;
	public JButton choosePic;
	public JButton add;
	public JButton close;
	public JTextField jtf_isbn;
	public JTextField jtf_name;
	public JTextField jtf_author;
	public JLabel jl_isbn;
	public JLabel jl_name;
	public JLabel jl_author;
	public JLabel jl_category;
	public JLabel picLabel;
	public JComboBox cb_category;
	public String picPath;
	public String picDirectory;
	public String picFileName;
	public Library library;

	public DlgAddBooksData() {
	}

	ImageIcon CreateStretchImageIcon(String imgPath, int width, int height) {
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
}