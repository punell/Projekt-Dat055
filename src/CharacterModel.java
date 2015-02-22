import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;


public class CharacterModel implements Serializable
{
	private HashMap<String, String[]> itemConsumableSet;
	private HashMap<String, String[]> itemEquipmentSet;
	private LinkedList<CellProperties> cellGridItems;
	
	public CharacterModel()
	{
		
		itemConsumableSet = new HashMap<>();
		itemEquipmentSet = new HashMap<>();
		populateItemSet();
		cellGridItems = new LinkedList<>();
		readMapItems();
		
	}
	
	
	// Everything about the CellGrid below
	public LinkedList<CellProperties> getItemList()
	{
		return cellGridItems;
	}
	
	public void removeItemFromMap(int[] playerCoords, String playerArea) 
	{
		// Must remove items from the list, otherwise they respawn after reloading the game
		Iterator<CellProperties> it = cellGridItems.iterator();
		while(it.hasNext())
		{
			CellProperties element = (CellProperties) it.next();
			if(Arrays.equals(element.coords, playerCoords) && 
					element.area.equals(playerArea))
			{
				it.remove();
			}
		}
	}
	
	private void populateItemSet()
	{
		
		try
		{
			
			HashMap<String, String[]> currentHashMap = itemConsumableSet;
			BufferedReader reader = new BufferedReader(new FileReader("itemDesignations.txt"));
			String line = reader.readLine();
			String hashKey;
			int keyIndex;
			String[] hashValues;
			
			while(line != null)
			{
				
				while(line.charAt(0) == '#')
				{
					if(line.contains("Equipment"))
						currentHashMap = itemEquipmentSet;
					line = reader.readLine();
				}
				keyIndex = line.indexOf(',');
				hashKey = line.substring(0, keyIndex);
				
				hashValues = line.split(", ");
				currentHashMap.put(hashKey, hashValues);
				line = reader.readLine();
			}
			reader.close();
		}
		catch(IOException e)
		{
		}
	}
	private void readMapItems()
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader("itemmap.txt"));
			String line = reader.readLine();
			String[] splittedLine;
			while(line != null)
			{
				while(line.charAt(0) == '#')
					line = reader.readLine();
				splittedLine = line.split(", |:"); //split on "comma space" OR "colon"
				Item item;
				Image itemImage;
				String itemName = splittedLine[5];
				if(itemConsumableSet.containsKey(itemName))
				{
					String filename = itemConsumableSet.get(itemName)[3];
					itemImage = ImageIO.read(new File("textures/"+filename));
					item = new ItemConsumable(itemConsumableSet.get(itemName), itemImage);
				}
				
				else //(itemEquipmentSet.containsKey(splittedLine[5])) everything that isn't consumable is EQ...
				{
					String filename = itemConsumableSet.get(itemName)[4];
					itemImage = ImageIO.read(new File("textures/"+filename));
					item = new ItemEquipment(itemEquipmentSet.get(itemName), itemImage);
				}
				
				cellGridItems.add(new CellProperties(splittedLine, item));
				line = reader.readLine();
			}
			reader.close();
			
		} 
		catch (IOException e) 
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
}
