import java.awt.GridLayout;
import javax.swing.JPanel;

public class CharacterView extends JPanel
{
	/** Controls the display of the player character as well as things such as
	 *  monsters, chests, non-player characters
	 */
	private Cell[][] cellGrid;
	public CharacterView(int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(16,16));
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
	}
	
	public void updateCellGrid(int x, int y, boolean isVisible)
	{
		// just turn on/off showing the playerCharacter-picture in this cell
		cellGrid[x][y].showPlayerCharacter(isVisible); 
	}
}
