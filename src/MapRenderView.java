import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;


public class MapRenderView extends JPanel 
{
	/** MapRenderView displays the gameworld
	 * 
	 */
	public MapRenderView(int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(16,16));
	}
	
	public void updateMapRenderView(TerrainTile[][] terrainGrid)
	{
		removeAll(); // remove to allow a refill
		for(int row=0; row<16;row++)
			for(int col=0; col<16;col++)
				add(terrainGrid[col][row]);
	}
	
}
