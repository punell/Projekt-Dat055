package items;
import java.awt.Image;
import java.io.Serializable;


/**
 * Defines items  the player can consume. 
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 */
public class ItemConsumable extends Item implements Serializable
{
	private String[] effect;
	private int[] effectValue;
	
	/**
	 * This is the constructor 
	 * @param properties The properties of the consumable item
	 * @param itemImage The image of the consumable item
	 */
	public ItemConsumable(String[] properties, Image itemImage)
	{
		super(properties[0], itemImage, "Use");
		effect = properties[1].split(":");
		String[] values = properties[2].split(":");
		effectValue = new int[values.length];
		for(int i=0;i<values.length;i++)
		{
			effectValue[i] = Integer.parseInt(values[i]);
		}
		
	}
	
	/**
	 * @return Returns the effect of the consumable item
	 */
	public String[] getEffect()
	{
		return effect;
	}
	
	
	/**
	 * @return Returns the value of the consumable items effect
	 */
	public int[] getEffectValue()
	{
		return effectValue;
	}

}
