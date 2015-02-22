import java.awt.Image;
import java.io.Serializable;


public class ItemConsumable extends Item implements Serializable
{
	private String effect;
	private int effectValue;
	private String unlockValue;
	
	public ItemConsumable(String[] properties, Image itemImage)
	{
		super(properties[0], itemImage);
		effect = properties[1];
		if(effect.equals("unlock"))
		{
			unlockValue = properties[2];
			effectValue = 0;
		}
		else
		{
			unlockValue = null;
			effectValue = Integer.parseInt(properties[2]);
		}
	}
	
	public String getEffect()
	{
		return effect;
	}
	
	public String getUnlockValue()
	{
		return unlockValue;
	}
	
	public int getEffectValue()
	{
		return effectValue;
	}

}
