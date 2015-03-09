package gameLayer;
import items.Item;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class Cell extends JLabel 
{
	private ImageIcon playerCharacterIcon;
	private Item contents;
	
	/**Constructor. Each Cell is capable of displaying the 
	 * player character image, or hold an Item.
	 * @param playerCharacterIcon
	 */
	public Cell(ImageIcon playerCharacterIcon)
	{
		super();
		setOpaque(false); // turn transparency on
		this.playerCharacterIcon = playerCharacterIcon;
		contents = null;
	}
	
	/**Sets this Cells contents to parameter Item
	 * Also sets Cells Icon to Items Icon to display it
	 * @param item
	 */
	public void setContents(Item item)
	{
		contents = item;
		if(contents != null)
			setIcon(item.getIcon());
		else
			setIcon(null);
		
	}
	
	/**@return Item that this Cell displays
	 */
	public Item checkContents()
	{
		return contents;
	}
	
	/**Empties this Cells contents and return them
	 * @return Item in Cell
	 */
	public Item pickUpContents()
	{
		Item temp = contents;
		setContents(null);
		return temp;
	}
	
	
	/**Turns display of player character image in this Cell to parameter
	 * @param isVisible
	 */
	public void showPlayerCharacter(boolean isVisible)
	{
		if(isVisible == true)
			setIcon(playerCharacterIcon);
		else if(isVisible == false)
			setIcon(null);
	}
}
