import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class TerrainProperties 
{
	public boolean walkable = false;
	public boolean isLink = false;
	public String filename;
	public String linksTo;
	public int riskOfEncounter;
	public BufferedImage terrainImage;
	public ImageIcon terrainIcon;
	
	public TerrainProperties(String[] properties)
	{
		if(properties[0].equals("true"))
		{
			walkable = true;
		}
		if(properties[1].equals("true"))
		{
			isLink = true;
		}
		filename = properties[2];
		linksTo = properties[3];
		riskOfEncounter = Integer.parseInt(properties[4]);
		try
		{
			terrainImage = ImageIO.read(new File(filename));
			terrainIcon = new ImageIcon(terrainImage);
		}
		catch(IOException e)
		{
		}
	}
}
