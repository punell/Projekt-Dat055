import java.awt.GridLayout;
import javax.swing.JPanel;

public class CharacterView extends JPanel
{
	private Cell transparentCell;
	private Cell[][] cellGrid;
	public CharacterView(int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(16,16));
		int cellX = 0;
		int cellY = 0;
		cellGrid = new Cell[16][16];
		for(int i=0;i<16*16;i++)
		{
			transparentCell = new Cell(cellX, cellY);
			cellGrid[cellX][cellY] = transparentCell;
			add(transparentCell);
			
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
		transparentCell = cellGrid[x][y];
		transparentCell.showPlayerCharacter(isVisible);
	}
}
