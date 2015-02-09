import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;



public class MapRenderModel
{	
	/** Reads the text-documents that makes up the world, and creates the
	 *  terrain necessary to display it. This can potentially create many different
	 *  worlds, such as an underworld or different in-door worlds (buildings, temples, dungeons etc)
	 */
	private TerrainType[][] terrainGrid;
	private MapRenderView mapRenderView;
	private char[][] worldMap;
	private char[][] undergroundMap;
	
	public MapRenderModel(MapRenderView mapRenderView)
	{
		this.mapRenderView = mapRenderView;
		terrainGrid = new TerrainType[16][16];
		//this char holds the entire worldMap, so we only read this during the start of the game and never again
		worldMap = readMap("worldmap.txt");
		//should work for other maps as well...
		undergroundMap = readMap("undergroundmap.txt");
	}
	
	public void updateMapRenderModel(int roomX, int roomY) throws IOException
	{
		//this method has to be cleaned up and generalized, so we can load different places
		//see worldMap and undergroundMap in constructor above
		try
		{
			int cellY=0; 
			for(int row=roomY*16;row<(roomY+1)*16;row++) //taking room-coordinates into account allows us to display the correct room
			{
				int cellX=0;
				for(int col=roomX*16;col<(roomX+1)*16;col++)
				{
					if(worldMap[col][row] == 'G')
						terrainGrid[cellX][cellY] = new TerrainGrass();
					else if(worldMap[col][row] == 'M')
						terrainGrid[cellX][cellY] = new TerrainMountain();
					else if(worldMap[col][row] == 'W')
						terrainGrid[cellX][cellY] = new TerrainWater();
					else if(worldMap[col][row] == 'C')
						terrainGrid[cellX][cellY] = new TerrainCaveEntrance();
					else //these are the "voids" after the line in the worldmap.txt ended
						terrainGrid[cellX][cellY] = new TerrainVoid(); 
					cellX++;
				}
				cellY++;
			}
		}
		catch(IOException e)
		{
		}
		
		mapRenderView.updateMapRenderView(terrainGrid);
	}
	
	public boolean isWalkable(int cellX, int cellY)
	{
		return terrainGrid[cellX][cellY].isWalkable();
	}
	public void typeOfTerrain(int cellX, int cellY) // test/debug function
	{
		System.out.println(terrainGrid[cellX][cellY].getClass());		
	}
	
	private char[][] readMap(String mapName)
	{
		
		BufferedReader reader = null;
		//read number of lines and length of longest line, place in rows/cols
		int[] rowsAndColumns = countRowsAndColumns(mapName);
		char[][] worldMap = new char[rowsAndColumns[1]][rowsAndColumns[0]]; 
		try
		{
			reader = new BufferedReader(new FileReader(mapName));
			String line = reader.readLine();
			int r=0;
			while(line != null)
			{
				for(int c=0;c<(line.length() + line.length() % 16);c++) //make sure we read the "void" after the line ends
				{
					try
					{
						worldMap[c][r] = line.charAt(c);
					}
					catch(Exception e)
					{
						worldMap[c][r] = 'V'; // V for void, this turns out to be necessary
					}
				}
				line = reader.readLine();
				r++;
			}
			reader.close();
		}
		catch(IOException e)
		{
		}
		return worldMap;
	}
	
	public int[] countRowsAndColumns(String filename) 
	{
	    BufferedReader reader = null;
	    int[] rowsAndColumns = new int[2];
	    try
	    {
	    	reader = new BufferedReader(new FileReader(filename));
	    	String line = reader.readLine();
	    	
	    	while(line != null)
	    	{
	    		rowsAndColumns[0]++; //count each row
	    		if(line.length() > rowsAndColumns[1])
	    			rowsAndColumns[1] = line.length(); //only keep the longest line, its the maximum line length
	    		line = reader.readLine();
	    	}
	    	reader.close();
	    }
	    catch(IOException e)
	    {
	    }
		//make sure both rows and columns are a multiple of 16 (enables half rooms, which are good for... something...) it solves a potential bug
	    rowsAndColumns[0] += 16 % rowsAndColumns[0];
	    rowsAndColumns[1] += 16 % rowsAndColumns[1];
	    /* Another solution, the one above is cleaner/better, but does it ALWAYS work?
	     * while(rowsAndColumns[0] % 16 != 0)
	    	rowsAndColumns[0]++;
	    while(rowsAndColumns[1] % 16 != 0)
	    	rowsAndColumns[1]++;*/
	    
	    return rowsAndColumns;
	}
}