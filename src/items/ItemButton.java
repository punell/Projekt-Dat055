package items;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ItemButton extends JButton 
{
	private Item item;
	
	public ItemButton(int i) //for testing
	{
		super(Integer.toString(i));
	}
	public ItemButton() 
	{
		super();
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
	}

	public ItemButton(Item item) 
	{
		super(item.getIcon());
		this.item = item;
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
		setActionCommand(item.getActionCommand());
	}
	
	public Item getItem()
	{
		Item temp = item;
		item = null;
		//removeAllActionListeners();
		setActionCommand(null);
		setIcon(null);
		return temp;
	}
	
	public void setItem(Item item)
	{
		this.item = item;
		setIcon(item.getIcon());
		setActionCommand(item.getActionCommand());
	}
	
	public void clear()
	{
		item = null;
		setIcon(null);
		setActionCommand(null);
	}
	
	public void removeAllActionListeners()
	{
		for(ActionListener al : this.getActionListeners())
		{
			removeActionListener(al);
		}
	}
	
	

}
