import java.awt.GridLayout;
import javax.swing.JPanel;

public class CharacterView extends JPanel
{
	/** Controls the display of the player character as well as things such as
	 *  monsters, chests, non-player characters
	 */
	private PlayerModel playerModel;
	private Cell[][] cellGrid;
	public CharacterView(PlayerModel pM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		playerModel = pM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(16,16));
		setOpaque(false); //to allow for transparent placement on MainViews glasspane
		int cellX = 0;
		int cellY = 0;
		cellGrid = new Cell[16][16]; // same size as the Map, this could potentially be based on screen-resolution
		for(int i=0;i<16*16;i++)
		{
			// create new cell and put it both in the cellGrid and in the JPanel
			add(cellGrid[cellX][cellY] = new Cell(cellX, cellY)); 
			
			if(cellX < 15)
				cellX++;
			else
			{
				cellX = 0;
				cellY++;
			}
		}
		updateCharacterView("player", true);
	}
	
	public void updateCharacterView(String character, boolean isVisible) //supports different characters/things via the string
	{
		// just turn on/off showing the playerCharacter-picture in this cell
		if(character.equals("player"))
		{
			int[] coords = playerModel.getPlayerCoords();
			cellGrid[coords[0]][coords[1]].showPlayerCharacter(isVisible); 
		}
	}
}
