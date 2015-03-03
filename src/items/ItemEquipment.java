package items;
import java.awt.Image;
import java.io.Serializable;


public class ItemEquipment extends Item implements Serializable
{
	private String slot[];
	private String effect;
	private int effectValue;
	private String actionCommand;
	
	public ItemEquipment(String[] properties, Image itemImage) 
	{
		super(properties[0], itemImage, "ToggleEquip");
		slot = properties[1].split(":");
		effect = properties[2];
		effectValue = Integer.parseInt(properties[3]);
		//actionCommand = "ToggleEquip";
		
	}
	
	public String[] getSlot()
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
	
	/*public String getActionCommand()
	{
		return actionCommand;
	}*/
	
	

}
