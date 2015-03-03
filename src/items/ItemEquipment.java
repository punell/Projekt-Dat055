package items;
import java.awt.Image;
import java.io.Serializable;


public class ItemEquipment extends Item implements Serializable
{
	private String[] slot;
	private String[] effect;
	private int[] effectValue;
	private String actionCommand;
	
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
	
	public String[] getSlot()
	{
		return slot;
	}
	
	public String[] getEffect()
	{
		return effect;
	}
	
	public int[] getEffectValue()
	{
		return effectValue;
	}
	
	/*public String getActionCommand()
	{
		return actionCommand;
	}*/
	
	

}
