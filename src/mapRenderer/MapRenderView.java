package mapRenderer;
import java.awt.GridLayout;

import javax.swing.JPanel;


/**MapRenderView displays the TerrainTilegrid that makes up the terrain
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class MapRenderView extends JPanel 
{
	private MapRenderModel mapModel;
	/**Constructor
	 * @param MapRenderModel-object
	 * @param screenResolutionWidth
	 * @param screenResolutionHeight
	 */
	public MapRenderView(MapRenderModel mRM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		mapModel = mRM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(18,32));
		updateMapRenderView();
	}
	
	/**Gets the latest TerrainTilegrid from the MapRenderModel
	 * and displays it.
	 */
	public void updateMapRenderView()
	{
		removeAll(); // remove to allow a refill
		TerrainTile[][] terrainGrid = mapModel.getUpdatedMap();
		for(int row=0; row<18;row++)
			for(int col=0; col<32;col++)
				add(terrainGrid[col][row]);
	}
	
}
