import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class TerrainType extends JLabel
{
	private boolean walkable;
	private BufferedImage terrainImage;
	
	public TerrainType(boolean walkable, String filename)
	{
		super();
		try
		{
			terrainImage = ImageIO.read(new File(filename));
			setIcon(new ImageIcon(terrainImage));
			this.walkable = walkable;
		}
		catch(IOException e)
		{
		}
	}
	
	public boolean isWalkable()
	{
		return walkable;
	}
}
