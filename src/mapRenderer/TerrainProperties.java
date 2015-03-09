package mapRenderer;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**TerrainProperties is basically a struct to allow for multiple values in a
 * single element (used in Lists)
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class TerrainProperties 
{
	public boolean walkable = false;
	public boolean isLink = false;
	public String linksTo;
	public int riskOfEncounter;
	public String description;
	public Image terrainImage;
	public ImageIcon terrainIcon;
	
	/**Constructor
	 * @param properties is a list of all properties the
	 * terrain type will have
	 * @param screenWidth
	 * @param screenHeight
	 */
	public TerrainProperties(String[] properties, int screenWidth, int screenHeight)
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
		description = properties[5];
		try
		{
			String filename = "resource/textures/"+properties[2];
			terrainImage = ImageIO.read(getClass().getClassLoader().getResource(filename));
			terrainImage = terrainImage.getScaledInstance(screenWidth/32, screenHeight/18, Image.SCALE_DEFAULT);
			terrainIcon = new ImageIcon(terrainImage);
		}
		catch(IOException e)
		{
		}
	}
}
