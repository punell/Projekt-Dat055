import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CharacterView extends JPanel
{
	/** Controls the display of the player character as well as things such as
	 *  monsters, chests, non-player characters
	 */
	private PlayerModel playerModel;
	private Cell[][] cellGrid;
	private Image playerCharacterImage;
	private ImageIcon playerCharacterIcon;
	private int[] previousPlayerCoords;
	public CharacterView(PlayerModel pM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		playerModel = pM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(18,32));
		setOpaque(false); //to allow for transparent placement on MainViews glasspane
		
		cellGrid = new Cell[32][18]; //same size as the map
		fillCellGrid();
		previousPlayerCoords = playerModel.getPlayerCoords('c');
		
		updateCharacterView();
	}
	
	public void updateCharacterView() //this method either updates everything = easy, or it can have a parameter to decide what to update...
	{
		cellGrid[previousPlayerCoords[0]][previousPlayerCoords[1]].showPlayerCharacter(false); //turn off the old icon at the old coords
		previousPlayerCoords = playerModel.getPlayerCoords('c'); //get the new coords
		cellGrid[previousPlayerCoords[0]][previousPlayerCoords[1]].showPlayerCharacter(true); //turn on the old icon at the new coords
	}
	
	public void updateCharacterViewPlayerModel(PlayerModel player)
	{
		playerModel = player;
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
		playerCharacterImage = playerCharacterImage.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
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
