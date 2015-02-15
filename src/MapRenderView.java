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
		setLayout(new GridLayout(16,16));
		updateMapRenderView();
	}
	
	public void updateMapRenderView()
	{
		removeAll(); // remove to allow a refill
		TerrainTile[][] terrainGrid = mapModel.getUpdatedMap();
		for(int row=0; row<16;row++)
			for(int col=0; col<16;col++)
				add(terrainGrid[col][row]);
	}
	
}
