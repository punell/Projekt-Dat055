package mapRenderer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**MapFactory reads map-files and constructs 2D char arrays used by
 * MapRenderModel.
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class MapFactory 
{
	public MapFactory()
	{
		
	}
	
	/**Reads parameter map-file and constructs a 2D char array
	 * @param mapName
	 * @return The 2D char array
	 */
	public char[][] readMap(String mapName) 
	{
		BufferedReader reader = null;
		//read number of lines and length of longest line, place in rows/cols
		int[] rowsAndColumns = countRowsAndColumns(mapName);
		char[][] worldMap = new char[rowsAndColumns[1]][rowsAndColumns[0]]; 
		try
		{
			InputStream filepath = getClass().getClassLoader().getResourceAsStream(mapName);
			InputStreamReader streamReader = new InputStreamReader(filepath);
			reader = new BufferedReader(streamReader);
			String line = reader.readLine();
			int r=0;
			while(line != null)
			{
				line = line.replaceAll(",",""); //replace all commas ',' with nothing, effectively deleting them
				for(int c=0;c<(line.length() + line.length() % 32);c++)
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
	
	/**Counts the rows and columns of the map. This is so readMap() can
	 * create correctly sized 2D char arrays
	 * @param filename
	 * @return int-array containing amount of rows and columns
	 */
	private int[] countRowsAndColumns(String filename) 
	{
	    BufferedReader reader = null;
	    int[] rowsAndColumns = new int[2];
	    try
	    {
	    	InputStream filepath = getClass().getClassLoader().getResourceAsStream(filename);
			InputStreamReader streamReader = new InputStreamReader(filepath);
			reader = new BufferedReader(streamReader);
	    	String line = reader.readLine();
	    	
	    	while(line != null)
	    	{
	    		line = line.replaceAll(",",""); //strip all commas
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
		//Make sure rows is a multiple of 18, and cols is multiple of 32, to allow for half-rooms
	    rowsAndColumns[0] += 18 % rowsAndColumns[0];
	    rowsAndColumns[1] += 32 % rowsAndColumns[1];
	    
	    return rowsAndColumns;
	}
}
