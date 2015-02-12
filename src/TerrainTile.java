import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class TerrainTile extends JLabel 
{
	private boolean walkable;
	private boolean isLink;
	private int riskOfEncounter;
	private String linksTo;
	private BufferedImage terrainImage;
	
	public TerrainTile(TerrainProperties TP)
	{
		super();
		walkable = TP.walkable;
		isLink = TP.isLink;
		linksTo = TP.linksTo;
		riskOfEncounter = TP.riskOfEncounter;
		
		try
		{
			terrainImage = ImageIO.read(new File(TP.filename));
			setIcon(new ImageIcon(terrainImage));
		}
		catch(IOException e)
		{
		}
	}
	public boolean isWalkable()
	{
		return walkable;
	}
	public boolean isLink()
	{
		return isLink;
	}
	public String getLink()
	{
		return linksTo;
	}
}
