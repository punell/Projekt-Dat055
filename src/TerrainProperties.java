import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class TerrainProperties 
{
	public boolean walkable = false;
	public boolean isLink = false;
	public String linksTo;
	public int riskOfEncounter;
	public Image terrainImage;
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
		linksTo = properties[3];
		riskOfEncounter = Integer.parseInt(properties[4]);
		try
		{
			terrainImage = ImageIO.read(new File(properties[2]));
			terrainImage = terrainImage.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
			terrainIcon = new ImageIcon(terrainImage);
		}
		catch(IOException e)
		{
		}
	}
}
