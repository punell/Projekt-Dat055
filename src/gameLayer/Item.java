package gameLayer;
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


public abstract class Item extends JButton implements Serializable
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
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
	}
	
	public String getName()
	{
		return name;
	}
	public ImageIcon getIcon()
	{
		return itemIcon;
	}
	public void removeAllActionListeners()
	{
		for(ActionListener al : this.getActionListeners())
		{
			removeActionListener(al);
		}
	}
}
