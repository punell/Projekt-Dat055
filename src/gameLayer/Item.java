package gameLayer;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class Item extends JLabel implements Serializable
{
	private String name;
	private Image itemImage;
	private ImageIcon itemIcon;
	public Item(String name, Image itemImage)
	{
		super();
		this.name = name;
		
		itemIcon = new ImageIcon(itemImage);
		setIcon(itemIcon);
	}
	
	public String getName()
	{
		return name;
	}
	public ImageIcon getIcon()
	{
		return itemIcon;
	}
}
