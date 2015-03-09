package mapRenderer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;



/**MapRenderModel creates the terrain that makes up the world
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class MapRenderModel
{	
	/** Reads the text-documents that makes up the world, and creates the
	 *  terrain necessary to display it. This can potentially create many different
	 *  worlds, such as an underworld or different in-door worlds (buildings, temples, dungeons etc)
	 *  
	 *  This class is way to big. We can cut out the whole terrain-creation-part and maybe make
	 *  some sort of factory from it.
	 */
	private TerrainTile[][] terrainGrid;
	private char[][] overworldMap;
	private char[][] undergroundMap;
	private char[][] mapToLoad; 
	private int[] currentRoom;
	private String currentArea;
	private HashMap<Character, TerrainProperties> terrainSet;
	private MapFactory mapFactory;
	private int screenWidth;
	private int screenHeight;
	
	/**Constructor
	 * @param screenWidth The width of the screen
	 * @param screenHeight The height of the screen
	 */
	public MapRenderModel(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		terrainGrid = new TerrainTile[32][18];
		mapFactory = new MapFactory();
		//this char holds the entire overworldMap, so we only read this during the start of the game and never again
		overworldMap = mapFactory.readMap("resource/textfiles/overworldmap.csv");
		//should work for other maps as well...
		undergroundMap = mapFactory.readMap("resource/textfiles/undergroundmap.csv");
		terrainSet = new HashMap<Character, TerrainProperties>();
		populateTerrainSet();
		currentRoom = new int[2];
		currentRoom[0] = 0;
		currentRoom[1] = 0;
		currentArea = "overworld";
		updateMapRenderModel(0,0);
		
	}
	
	/**@return the current area MapRenderModel works with
	 */
	public String getCurrentArea()
	{
		return currentArea;
	}
	/**Sets the current area to parameter
	 * @param area name of area
	 */
	public void setCurrentArea(String area)
	{
		currentArea = area;
	}
	
	/**Creates TerrainProperties objects for all types of terrain found in
	 * terrainDesignations.txt, and puts them into a HashMap to be used by
	 * TerrainTiles later on. 
	 */
	private void populateTerrainSet()
	{
		BufferedReader reader = null;
		char terrainSetKey;
		String[] terrainSetValue;
		TerrainProperties TP;
		String subLine;
		try
		{
			InputStream filepath = getClass().getClassLoader().getResourceAsStream("resource/textfiles/terrainDesignations.txt");
			InputStreamReader streamReader = new InputStreamReader(filepath);
			reader = new BufferedReader(streamReader);
			String line = reader.readLine();
			while(line != null)
			{
				while(line.charAt(0) == '#')
					line = reader.readLine();
				terrainSetKey = line.charAt(0);
				subLine = line.substring(3);
				terrainSetValue = subLine.split(", ");
				TP = new TerrainProperties(terrainSetValue, screenWidth, screenHeight);
				terrainSet.put(terrainSetKey, TP);
				line = reader.readLine();
			}
		}
		catch(IOException e)
		{
		}
	}
	
	/**Creates a new terrainGrid for the MapRenderView to display.
	 * The grid is then filled with TerrainTiles. Parameters are
	 * location to display.
	 * @param roomX x-coordinate of room
	 * @param roomY y-coordinate of room
	 */
	public void updateMapRenderModel(int roomX, int roomY)
	{
		currentRoom[0] = roomX;
		currentRoom[1] = roomY;
		if(currentArea.equals("overworld"))
		{
			mapToLoad = overworldMap;
		}
		else if(currentArea.equals("underground"))
		{
			mapToLoad = undergroundMap;
		}
		int cellY=0; 
		for(int row=roomY*18;row<(roomY+1)*18;row++)
		{
			int cellX=0;
			for(int col=roomX*32;col<(roomX+1)*32;col++)
			{
				try
				{
					terrainGrid[cellX][cellY] = new TerrainTile(terrainSet.get(mapToLoad[col][row]));
					cellX++;
				}
				catch(Exception e)
				{
					terrainGrid[cellX][cellY] = new TerrainTile(terrainSet.get('V'));
					cellX++;
				}
			}
			cellY++;
		}
	}
	
	/**@return The terrainGrid
	 */
	public TerrainTile[][] getUpdatedMap()
	{
		return terrainGrid;
	}
	
	/**@return Currently built room coordinates
	 */
	public int[] getCurrentRoom()
	{
		return currentRoom;
	}

	/**Checks if parameter location is walkable by the player
	 * @param cellX x-coordinate
	 * @param cellY y-coordinate
	 * @return true/false
	 */
	public boolean isWalkable(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].isWalkable();
	}
	/**Checks if parameter location is a link to another area
	 * @param cellX x-coordinate
	 * @param cellY y-coordinate
	 * @return true/false
	 */
	public boolean isLink(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].isLink();
	}
	/**Checks where parameter location links to
	 * @param cellX x-coordinate
	 * @param cellY y-coordinate
	 * @return name of the area the location links to
	 */
	public String linksTo(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].linksTo();
	}
	/**Gets the chance of encountering a monster at parameter location
	 * @param cellX x-coordinate
	 * @param cellY y-coordinate
	 * @return chance of encounter
	 */
	public int encounterChance(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].encounterChance();
	}

}