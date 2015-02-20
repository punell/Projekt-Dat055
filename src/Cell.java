import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Cell extends JLabel 
{
	/** Cell is basically just an invisible filler, but it's used to display
	 *  different things on top of the Map-layer
	 */
	//Cell-coordinates might be useful sometime in the future...
	//private int cellX;
	//private int cellY;
	private ImageIcon playerCharacterIcon;
	
	public Cell(int x, int y, ImageIcon playerCharacterIcon)
	{
		super();
		setOpaque(false); // turn transparency on
		//cellX = x;
		//cellY = y;
		this.playerCharacterIcon = playerCharacterIcon;
	}
	
	public void showPlayerCharacter(boolean isVisible)
	{
		if(isVisible == true)
			setIcon(playerCharacterIcon);
		else if(isVisible == false)
			setIcon(null);
	}
	
	public void showMonster(boolean isVisible)
	{
		//stub, can be used to show monsters/NPCs
	}
}
