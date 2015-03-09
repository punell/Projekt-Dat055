package mapRenderer;
import javax.swing.JLabel;


/**TerrainTile makes up the terrain
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class TerrainTile extends JLabel 
{
	private boolean walkable;
	private boolean isLink;
	private int riskOfEncounter;
	private String linksTo;
	private String description;
	
	/**Constructor
	 * @param A TerrainProperties object that details the behaviour of
	 * the TerrainTile
	 */
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
	/**Checks if this TerrainTile is walkable
	 * @return true/false
	 */
	public boolean isWalkable()
	{
		return walkable;
	}
	/**Checks if this TerrainTile links to something
	 * @return true/false
	 */
	public boolean isLink()
	{
		return isLink;
	}
	/**@return what this TerrainTile links to
	 */
	public String linksTo()
	{
		return linksTo;
	}
	/**@return the risk of encounter a monster on this TerrainTile
	 */
	public int encounterChance()
	{
		return riskOfEncounter;
	}
}
