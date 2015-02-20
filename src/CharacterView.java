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
	public CharacterView(PlayerModel pM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		playerModel = pM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		//setLayout(new GridLayout(16,16));
		setLayout(new GridLayout(18,32));
		setOpaque(false); //to allow for transparent placement on MainViews glasspane

		//cellGrid = new Cell[16][16]; // same size as the Map, this could potentially be based on screen-resolution
		cellGrid = new Cell[32][18];
		fillCellGrid();
		
		updateCharacterView("player", true);
	}
	
	public void updateCharacterView(String character, boolean isVisible) //supports different characters/things via the string
	{
		// just turn on/off showing the playerCharacter-picture in this cell
		if(character.equals("player"))
		{
			int[] coords = playerModel.getPlayerCoords('c');
			cellGrid[coords[0]][coords[1]].showPlayerCharacter(isVisible); 
		}
	}
	
	private void fillCellGrid()
	{
		int cellX = 0;
		int cellY = 0;
		try
		{
			playerCharacterImage = ImageIO.read(new File("playerCharacter.png"));
		}
		catch(IOException e)
		{
		}
		playerCharacterImage = playerCharacterImage.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
		playerCharacterIcon = new ImageIcon(playerCharacterImage);
		for(int i=0;i<576;i++) //32 x 18 = 576
		{
			// create new cell and put it both in the cellGrid and in the JPanel
			add(cellGrid[cellX][cellY] = new Cell(cellX, cellY, playerCharacterIcon)); 
			
			if(cellX < 31) //15 for old style
				cellX++;
			else
			{
				cellX = 0;
				cellY++;
			}
		}
	}
}
