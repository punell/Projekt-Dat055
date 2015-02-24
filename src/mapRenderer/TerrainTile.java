package mapRenderer;
import javax.swing.JLabel;


public class TerrainTile extends JLabel 
{
	private boolean walkable;
	private boolean isLink;
	private int riskOfEncounter;
	private String linksTo;
	private String description;
	
	public TerrainTile(TerrainProperties TP)
	{
		super();
		walkable = TP.walkable;
		isLink = TP.isLink;
		linksTo = TP.linksTo;
		riskOfEncounter = TP.riskOfEncounter;
		description = TP.description;
		setIcon(TP.terrainIcon);
	}
	public boolean isWalkable()
	{
		return walkable;
	}
	public boolean isLink()
	{
		return isLink;
	}
	public String linksTo()
	{
		return linksTo;
	}
}
