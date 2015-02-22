import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public abstract class Item implements Serializable
{
	private String name;
	private Image itemImage;
	private ImageIcon itemIcon;
	public Item(String name, Image itemImage)
	{
		this.name = name;
		/*try
		{
			itemImage = ImageIO.read(new File("textures/"+filename));
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
		}*/
		
		itemIcon = new ImageIcon(itemImage);
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
