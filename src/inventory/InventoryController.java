package inventory;

import java.util.LinkedList;

import gameLayer.Item;
import gameLayer.ItemEquipment;

public class InventoryController 
{
	private InventoryModel invModel;
	private InventoryView invView;
	private String inventoryType;
	public InventoryController(String inventoryType)
	{
		this.inventoryType = inventoryType;
		invModel = new InventoryModel(inventoryType);
		invView = new InventoryView(invModel, inventoryType);
	}
	
	public void show()
	{
		invView.updateInventoryView();
		//invView.pack();
		invView.setVisible(true);
	}
	
	public void put(Item item)
	{
		invModel.put(item);
	}
	
	public Item get(String itemName)
	{
		return invModel.get(itemName);
	}
	
	public Item getEquipped(String slot)
	{
		return invModel.getEquipped(slot);
	}

	public LinkedList<ItemEquipment> checkContents() 
	{
		return invModel.checkContents();
	}
}
