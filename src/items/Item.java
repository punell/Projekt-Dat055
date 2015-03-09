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


/**
 * This is an abstract superclass for different item types
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 */
public abstract class Item implements Serializable
{
	private String name;
	private ImageIcon itemIcon;
	private String actionCommand;
	/**
	 * The constructor creates name, itemIcon and actionCommand
	 * @param name The name of the item
	 * @param itemImage Image of the item
	 * @param actionCommand The usage type of the item
	 */
	public Item(String name, Image itemImage, String actionCommand)
	{
		this.name = name;
		itemIcon = new ImageIcon(itemImage);
		this.actionCommand = actionCommand;
		
	}
	
	/**
	 * @return returns item name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return returns item image 
	 */
	public ImageIcon getIcon()
	{
		return itemIcon;
	}
	
	/**
	 * @return returns the action of the item
	 */
	public String getActionCommand()
	{
		return actionCommand;
	}
	
}
