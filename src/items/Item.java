package items;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public abstract class Item implements Serializable
{
	private String name;
	private ImageIcon itemIcon;
	private String actionCommand;
	public Item(String name, Image itemImage, String actionCommand)
	{
		this.name = name;
		itemIcon = new ImageIcon(itemImage);
		this.actionCommand = actionCommand;
		
	}
	
	public String getName()
	{
		return name;
	}
	public ImageIcon getIcon()
	{
		return itemIcon;
	}
	
	public String getActionCommand()
	{
		return actionCommand;
	}
	
}
