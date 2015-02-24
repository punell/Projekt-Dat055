package gameLayer;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CharacterView extends JPanel
{
	/** Controls the display of the player character as well as things such as
	 *  monsters, chests, non-player characters
	 */
	private CharacterModel charModel;
	private PlayerModel playerModel;
	private Cell[][] cellGrid;
	private Image playerCharacterImage;
	private ImageIcon playerCharacterIcon;
	private int[] previousPlayerCoords;
	private LinkedList<CellProperties> cellGridItems;
	private int screenWidth;
	private int screenHeight;
	
	public CharacterView(CharacterModel cM, PlayerModel pM, int screenWidth, int screenHeight)
	{
		super();
		charModel = cM;
		playerModel = pM;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setSize(screenWidth, screenHeight);
		setLayout(new GridLayout(18,32));
		setOpaque(false); //to allow for transparent placement on MainViews glasspane
		
		cellGrid = new Cell[32][18]; //same size as the map
		fillCellGrid();
		cellGridItems = new LinkedList<>();
		previousPlayerCoords = playerModel.getPlayerCoords('c');
		
		updatePlayerPosition();
		updateCellGrid();
	}
	
	public void updatePlayerPosition() //this method either updates everything = easy, or it can have a parameter to decide what to update...
	{
		cellGrid[previousPlayerCoords[0]][previousPlayerCoords[1]].showPlayerCharacter(false); //turn off the old icon at the old coords
		previousPlayerCoords = playerModel.getPlayerCoords('c'); //get the new coords
		cellGrid[previousPlayerCoords[0]][previousPlayerCoords[1]].showPlayerCharacter(true); //turn on the old icon at the new coords
	}
	
	public Item getCellContents(int[] cellCoords)
	{
		int cellX = cellCoords[0];
		int cellY = cellCoords[1];
		charModel.removeItemFromMap(playerModel.getPlayerCoords('a'), playerModel.getPlayerArea());
		return cellGrid[cellX][cellY].pickUpContents();
	}
	
	public void updateCellGrid()
	{
		int cellX, cellY;
		// First delete items from old list
		for(CellProperties cell : cellGridItems)
		{
			cellX = cell.cellCoords[0];
			cellY = cell.cellCoords[1];
			cellGrid[cellX][cellY].setContents(null);
		}
		
		cellGridItems = charModel.getItemList();
		// Then place items from new list
		for(CellProperties cell : cellGridItems)
		{
			if(cell.area.equals(playerModel.getPlayerArea()) &&
					Arrays.equals(cell.roomCoords, playerModel.getPlayerCoords('r')))
			{
				cellX = cell.cellCoords[0];
				cellY = cell.cellCoords[1];
				cellGrid[cellX][cellY].setContents(cell.item);
			}
		}
	}
	
	public void updateCharacterViewModels(CharacterModel cM, PlayerModel pM)
	{
		charModel = cM;
		playerModel = pM;
	}
	
	private void fillCellGrid()
	{
		int cellX = 0;
		int cellY = 0;
		try
		{
			playerCharacterImage = ImageIO.read(new File("textures/playerCharacter.png"));
		}
		catch(IOException e)
		{
		}
		playerCharacterImage = playerCharacterImage.getScaledInstance(screenWidth/32, screenHeight/18, Image.SCALE_DEFAULT);
		playerCharacterIcon = new ImageIcon(playerCharacterImage);
		for(int i=0;i<576;i++) //32 x 18 = 576
		{
			// create new cell and put it both in the cellGrid and in the JPanel
			add(cellGrid[cellX][cellY] = new Cell(playerCharacterIcon)); 
			
			if(cellX < 31) 
				cellX++;
			else
			{
				cellX = 0;
				cellY++;
			}
		}
	}	
}
