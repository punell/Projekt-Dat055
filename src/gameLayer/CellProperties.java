package gameLayer;
import items.Item;

import java.io.Serializable;


/**CellProperties is basically a struct to allow for multiple values in a
 * single element (used in Lists)
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class CellProperties implements Serializable
{
	public int[] cellCoords;
	public int[] roomCoords;
	public int[] coords;
	public String area;
	public Item item;
	
	/**Constructor
	 * @param properties (coordinates and area)
	 * @param item Item to store
	 */
	public CellProperties(String[] properties, Item item)
	{
		cellCoords = new int[2];
		roomCoords = new int[2];
		coords = new int[4];

		cellCoords[0] = Integer.parseInt(properties[0]); 
		cellCoords[1] = Integer.parseInt(properties[1]);
		roomCoords[0] = Integer.parseInt(properties[2]); 
		roomCoords[1] = Integer.parseInt(properties[3]);
		coords[0] = cellCoords[0];
		coords[1] = cellCoords[1];
		coords[2] = roomCoords[0];
		coords[3] = roomCoords[1];
		
		area = properties[4];
		
		this.item = item;
	}
	
}
