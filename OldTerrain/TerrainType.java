import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class TerrainType extends JLabel
{
	/** Everything common for all types of terrain goes here
	 * 
	 */
	private boolean walkable;
	private boolean caveEntrance;
	private BufferedImage terrainImage;
	
	public TerrainType(boolean walkable, boolean caveEntrance, String filename)
	{
		super();
		try
		{
			terrainImage = ImageIO.read(new File(filename));
			setIcon(new ImageIcon(terrainImage));
			this.walkable = walkable;
			this.caveEntrance = caveEntrance;
		}
		catch(IOException e)
		{
		}
	}
	
	public boolean isWalkable()
	{
		return walkable;
	}
	public boolean isCaveEntrance()
	{
		return caveEntrance;
	}
}
