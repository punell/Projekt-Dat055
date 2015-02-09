import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;



public class MapRenderModel
{	
	private TerrainType[][] terrainGrid;
	private MapRenderView mapRenderView;
	private char[][] worldMap;
	
	public MapRenderModel(MapRenderView mapRenderView)
	{
		this.mapRenderView = mapRenderView;
		terrainGrid = new TerrainType[16][16];
		worldMap = readWorldMap();		
	}
	
	public void updateMapRenderModel(int roomX, int roomY) throws IOException
	{
		try
		{
			int cellY=0;
			for(int row=roomY*16;row<(roomY+1)*16;row++)
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
					else
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
	
	private char[][] readWorldMap()
	{
		String mapName = "worldmap.txt";
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
				
				for(int c=0;c<line.length();c++)
				{
					worldMap[c][r] = line.charAt(c);
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
	    		rowsAndColumns[0]++;
	    		if(line.length() > rowsAndColumns[1])
	    			rowsAndColumns[1] = line.length();
	    		line = reader.readLine();
	    	}
	    	reader.close();
	    }
	    catch(IOException e)
	    {
	    }
	    
	    return rowsAndColumns;
	}
}