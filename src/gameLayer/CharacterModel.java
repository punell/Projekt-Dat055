package gameLayer;
import items.Item;
import items.ItemConsumable;
import items.ItemEquipment;
import items.ItemUnpickable;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;


/**CharacterModel maintains the cellgrid. It handles placement and removal
 * of Items from the map
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class CharacterModel implements Serializable
{
	private HashMap<String, String[]> itemConsumableSet;
	private HashMap<String, String[]> itemEquipmentSet;
	private HashMap<String, String[]> itemUnpickableSet;
	private LinkedList<CellProperties> cellGridItems;
	private int screenWidth;
	private int screenHeight;
	
	/**Constructor. Creates HashMaps for the different Item-types
	 * @param screenWidth The width of the screen
	 * @param screenHeight The height of the screen
	 */
	public CharacterModel(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		itemConsumableSet = new HashMap<>();
		itemEquipmentSet = new HashMap<>();
		itemUnpickableSet = new HashMap<>();
		populateItemSet();
		cellGridItems = new LinkedList<>();
		readMapItems();
		
	}
	
	
	/**
	 * @return A list of all Items currently in the world
	 * (not necessarily displayed)
	 */
	public LinkedList<CellProperties> getItemList()
	{
		return cellGridItems;
	}
	
	/**Removes an Item from the map at parameter location
	 * @param playerCoords coordinates
	 * @param playerArea area
	 */
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
	
	/**Creates all different types of Items, puts them in 
	 * different HashMaps depending on type
	 */
	private void populateItemSet()
	{
		
		try
		{
			
			HashMap<String, String[]> currentHashMap = itemConsumableSet;
			InputStream filepath = getClass().getClassLoader().getResourceAsStream("resource/textfiles/itemDesignations.txt");
			InputStreamReader streamReader = new InputStreamReader(filepath);
			BufferedReader reader = new BufferedReader(streamReader);
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
					if(line.contains("Signs"))
						currentHashMap = itemUnpickableSet;
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
	/**Reads the itemmap.txt file and creates Items for all locations specified
	 */
	private void readMapItems()
	{
		try 
		{
			InputStream filepath = getClass().getClassLoader().getResourceAsStream("resource/textfiles/itemmap.txt");
			InputStreamReader streamReader = new InputStreamReader(filepath);
			BufferedReader reader = new BufferedReader(streamReader);
			String line = reader.readLine();
			String[] splittedLine;
			Image itemImage=null;
			Image smallPotionImage = ImageIO.read(getClass().getClassLoader().getResource("resource/textures/smallhealthpot.png"));
			smallPotionImage = smallPotionImage.getScaledInstance(screenWidth/32,
										screenHeight/18, Image.SCALE_DEFAULT);
			
			Image bigPotionImage = ImageIO.read(getClass().getClassLoader().getResource("resource/textures/healthpot.png"));
			bigPotionImage = bigPotionImage.getScaledInstance(screenWidth/32,
										screenHeight/18, Image.SCALE_DEFAULT);
			
			Image signImage = ImageIO.read(getClass().getClassLoader().getResource("resource/textures/sign.png"));
			signImage = signImage.getScaledInstance(screenWidth/32,
					screenHeight/18, Image.SCALE_DEFAULT);
			
			while(line != null)
			{
				while(line.charAt(0) == '#')
					line = reader.readLine();
				splittedLine = line.split(", |:"); //split on "comma space" OR "colon"
				Item item;
				
				String itemName = splittedLine[5];
				if(itemConsumableSet.containsKey(itemName))
				{
					if(itemName.equals("Big Health Potion"))
					{
						item = new ItemConsumable(itemConsumableSet.get(itemName), bigPotionImage);
					}
					else if(itemName.equals("Small Health Potion"))
					{
						item = new ItemConsumable(itemConsumableSet.get(itemName), smallPotionImage);
					}
						
					else
					{
						String filename = itemConsumableSet.get(itemName)[3];
						itemImage = ImageIO.read(getClass().getClassLoader().getResource("resource/textures/"+filename));
						itemImage = itemImage.getScaledInstance(screenWidth/32, screenHeight/18, Image.SCALE_DEFAULT);
						item = new ItemConsumable(itemConsumableSet.get(itemName), itemImage);
					}
				}
				
				else if(itemEquipmentSet.containsKey(itemName)) // everything that isn't consumable is EQ...
				{
					String filename = itemEquipmentSet.get(itemName)[4];
					itemImage = ImageIO.read(getClass().getClassLoader().getResource("resource/textures/"+filename));
					itemImage = itemImage.getScaledInstance(screenWidth/32, screenHeight/18, Image.SCALE_DEFAULT);
					item = new ItemEquipment(itemEquipmentSet.get(itemName), itemImage);
				}
				
				else //(itemUnpickableSet.containsKey(itemName))
				{
					item = new ItemUnpickable(itemUnpickableSet.get(itemName), signImage);
				}
				
				cellGridItems.add(new CellProperties(splittedLine, item));
				line = reader.readLine();
			}
			reader.close();
			
		} 
		catch (IOException e) 
		{
			System.out.println(e);
			System.out.println(e.getLocalizedMessage());
		}
	}
}
