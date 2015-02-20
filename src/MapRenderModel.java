import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;



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
	private char[][] worldMap;
	private char[][] undergroundMap;
	private char[][] mapToLoad; 
	private int[] currentRoom;
	private String currentArea;
	private HashMap<Character, TerrainProperties> terrainSet;
	private MapFactory mapFactory;
	
	public MapRenderModel()
	{
		terrainGrid = new TerrainTile[32][18];
		mapFactory = new MapFactory();
		//this char holds the entire worldMap, so we only read this during the start of the game and never again
		worldMap = mapFactory.readMap("worldmap.csv");
		//should work for other maps as well...
		undergroundMap = mapFactory.readMap("undergroundmap.csv");
		terrainSet = new HashMap<Character, TerrainProperties>();
		populateTerrainSet();
		currentRoom = new int[2];
		currentRoom[0] = 0;
		currentRoom[1] = 0;
		currentArea = "overworld";
		updateMapRenderModel(0,0,"overworld");
		
	}
	
	private void populateTerrainSet()
	{
		//Fills the HashMap with references to all types of terrain
		BufferedReader reader = null;
		char terrainSetKey;
		String[] terrainSetValue;
		TerrainProperties TP;
		String subLine;
		try
		{
			reader = new BufferedReader(new FileReader("terrainDesignations.txt"));
			String line = reader.readLine();
			while(line != null)
			{
				while(line.charAt(0) == '#')
					line = reader.readLine();
				terrainSetKey = line.charAt(0);
				subLine = line.substring(3);
				terrainSetValue = subLine.split(", ");
				TP = new TerrainProperties(terrainSetValue);
				terrainSet.put(terrainSetKey, TP);
				line = reader.readLine();
			}
		}
		catch(IOException e)
		{
		}
	}
	
	private void toggleArea(String areaToLoad)
	{
		// Decides which area to actually load 
		if(areaToLoad.equals(currentArea))
		{
			currentArea = "overworld";
			mapToLoad = worldMap;
		}
		else // this else will be fixed with a hashmap containing all areas
			//This allows us to exchange our "areatoload"-string for a "maptoload"-char-array
		{
			currentArea = areaToLoad;
			mapToLoad = undergroundMap;
		}
	}
	
	public void updateMapRenderModel(int roomX, int roomY, String areaToLoad)
	{
		currentRoom[0] = roomX;
		currentRoom[1] = roomY;
		if(areaToLoad != null)
			toggleArea(areaToLoad); 
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
	
	public TerrainTile[][] getUpdatedMap()
	{
		return terrainGrid;
	}
	
	public int[] getCurrentRoom()
	{
		return currentRoom;
	}

	public boolean isWalkable(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].isWalkable();
	}
	public boolean isLink(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].isLink();
	}
	public String linksTo(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].linksTo();
	}

}