import java.awt.Image;
import java.io.Serializable;


public class ItemEquipment extends Item implements Serializable
{
	private String slot;
	private String effect;
	private int effectValue;
	
	public ItemEquipment(String[] properties, Image itemImage) 
	{
		super(properties[0], itemImage);
		slot = properties[1];
		effect = properties[2];
		effectValue = Integer.parseInt(properties[3]);
		
	}
	
	public String getSlot()
	{
		return slot;
	}
	
	public String getEffect()
	{
		return effect;
	}
	
	public int getEffectValue()
	{
		return effectValue;
	}

}
