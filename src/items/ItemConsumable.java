package items;
import java.awt.Image;
import java.io.Serializable;


public class ItemConsumable extends Item implements Serializable
{
	private String[] effect;
	private int[] effectValue;
	
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
	
	public String[] getEffect()
	{
		return effect;
	}
	
	
	public int[] getEffectValue()
	{
		return effectValue;
	}

}
