import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private BufferedImage playerCharacterImage;
	private ImageIcon playerCharacterIcon;
	
	public Cell(int x, int y)
	{
		super();
		try
		{
			playerCharacterImage = ImageIO.read(new File("playerCharacter.png"));
		}
		catch(IOException e)
		{
		}
		playerCharacterIcon = new ImageIcon(playerCharacterImage);
		setOpaque(false); // turn transparency on
		//cellX = x;
		//cellY = y;
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
