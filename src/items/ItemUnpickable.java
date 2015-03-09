package items;

import java.awt.Image;

/**
 * Defines the items the player can't pick up, for example signs
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 */
public class ItemUnpickable extends Item 
{

	/**
	 * This is the constructor 
	 * @param properties The properties of the unpickable item
	 * @param itemImage The image of the unpickable item 
	 */
	public ItemUnpickable(String[] properties, Image itemImage) 
	{
		super(properties[0], itemImage, null);

		
		
	}

}
