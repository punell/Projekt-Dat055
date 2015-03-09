package inventory;

import items.Item;
import items.ItemEquipment;

import java.io.Serializable;
import java.util.LinkedList;

import gameLayer.PlayerModel;

/**
 * Controller for the inventory-package, handles the communication between parts. Creates IventoryModel and InventoryView.
 * 
 * @author Robin Punell
 * @version 2015-03-09
 *
 */
public class InventoryController implements Serializable
{
	private InventoryModel invModel;
	private InventoryView invView;
	
	
	/**
	 * Constructor - Creates a InventoryModel and a InventoryView.
	 * @param pM PlayerModel
	 */
	public InventoryController(PlayerModel pM)
	{
		invModel = new InventoryModel();
		invView = new InventoryView(invModel, pM);
	}
	
	/**
	 * Shows the inventory
	 */
	public void show()
	{
		invView.updateInventoryView();
		invView.setVisible(true);
	}
	
	/**
	 * Adds an item to the inventory
	 * @param item Item to be added
	 */
	public void put(Item item)
	{
		invModel.put(item);
	}
	
	/**
	 * Get wanted item
	 * @param itemName Name of the wanted item
	 * @return Wanted item
	 */
	public Item get(String itemName)
	{
		return invModel.get(itemName);
	}
	
	/**
	 * Get item from specified slot
	 * @param slot Specified slot
	 * @return Item in slot
	 */
	public Item getEquipped(String slot)
	{
		return invModel.getEquipped(slot);
	}

	/**
	 * Gets a list of currently equipped items
	 * @return List of item
	 */
	public LinkedList<ItemEquipment> checkEquipment() 
	{
		return invModel.checkEquipment();
	}
	/**
	 * Gets a list of items in the backpack
	 * @return List of item in backpack
	 */
	public LinkedList<Item> checkBackpack()
	{
		return invModel.checkBackpack();
	}
	/**
	 * Creates at new inventory from saved file.
	 * @param pM PlayerModel object
	 */
	public void restoreFromLoad(PlayerModel pM)
	{
		invView = new InventoryView(invModel, pM);
	}
}
