package items;
import java.awt.Image;
import java.io.Serializable;


/**
 * Defines items the player can equip, like weapons and armor
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 *
 */
public class ItemEquipment extends Item implements Serializable
{
	private String[] slot;
	private String[] effect;
	private int[] effectValue;
	
	/**
	 * This is the constructor 
	 * @param properties The properties of the item
	 * @param itemImage The image of the item
	 */
	public ItemEquipment(String[] properties, Image itemImage) 
	{
		super(properties[0], itemImage, "ToggleEquip");
		slot = properties[1].split(":");
		effect = properties[2].split(":");
		String[] values = properties[3].split(":");
		effectValue = new int[values.length];
		for(int i=0;i<values.length;i++)
		{
			effectValue[i] = Integer.parseInt(values[i]);
		}
		
	}
	
	/**
	 * @return Returns the item slot
	 */
	public String[] getSlot()
	{
		return slot;
	}
	
	/**
	 * @return Returns the item effect
	 */
	public String[] getEffect()
	{
		return effect;
	}
	
	/**
	 * @return Returns the value of the effect
	 */
	public int[] getEffectValue()
	{
		return effectValue;
	}
	
	

}
