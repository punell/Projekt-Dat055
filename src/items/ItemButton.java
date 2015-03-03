package items;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ItemButton extends JButton 
{
	private Item item;
	public ItemButton() 
	{
		super();
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
	}
	
	public Item getItem()
	{
		Item temp = item;
		item = null;
		setActionCommand(null);
		setIcon(null);
		setToolTipText(null);
		return temp;
	}
	
	public void setItem(Item item)
	{
		this.item = item;
		setIcon(item.getIcon());
		setActionCommand(item.getActionCommand());
		if(item instanceof ItemEquipment)
		{
			ItemEquipment itemCasted = (ItemEquipment)item;
			StringBuilder sb = new StringBuilder();
			sb.append("<html>"+itemCasted.getName()+"<br>");
			
			sb.append("Equipment Slots:<br>");
			for(int i=0;i<itemCasted.getSlot().length;i++)
			{
				sb.append(itemCasted.getSlot()[i]+"<br>");
			}
			sb.append("<br>Statbonuses:<br>");
			for(int i=0;i<itemCasted.getEffect().length;i++)
			{
				sb.append(itemCasted.getEffect()[i]+": ");
				sb.append(itemCasted.getEffectValue()[i]+"<br>");
			}
			sb.append("</html>");
			String tooltip = sb.toString();
			setToolTipText(tooltip);
		}
		
		if(item instanceof ItemConsumable)
		{
			ItemConsumable itemCasted = (ItemConsumable)item;
			StringBuilder sb = new StringBuilder();
			sb.append("<html>"+itemCasted.getName()+"<br>");
			for(int i=0;i<itemCasted.getEffect().length;i++)
			{
				sb.append(itemCasted.getEffect()[i] + ": " + itemCasted.getEffectValue()[i] + "<br>");
			}
			sb.append("</html");
			String tooltip = sb.toString();
			setToolTipText(tooltip);
		}
		
	}
	
	public void clear()
	{
		item = null;
		setIcon(null);
		setActionCommand(null);
		setToolTipText(null);
	}
	
	public void removeAllActionListeners()
	{
		for(ActionListener al : this.getActionListeners())
		{
			removeActionListener(al);
		}
	}
	
	

}
