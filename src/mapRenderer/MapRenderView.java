package mapRenderer;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class MapRenderView extends JPanel 
{
	/** MapRenderView displays the gameworld
	 * 
	 */
	private MapRenderModel mapModel;
	public MapRenderView(MapRenderModel mRM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		mapModel = mRM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(18,32));
		updateMapRenderView();
	}
	
	public void updateMapRenderView()
	{
		removeAll(); // remove to allow a refill
		TerrainTile[][] terrainGrid = mapModel.getUpdatedMap();
		for(int row=0; row<18;row++)
			for(int col=0; col<32;col++)
				add(terrainGrid[col][row]);
	}
	
}
