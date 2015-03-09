package mapRenderer;
import java.io.IOException;


/**MapController handles communication between the mapRenderer-package
 * and the MainController
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class MapController 
{
	private MapRenderModel mapModel;
	private MapRenderView mapView;
	private int screenWidth;
	private int screenHeight;
	
	/**Constructor
	 * @param screenResolutionWidth The width of the screen
	 * @param screenResolutionHeight The height of the screen
	 */
	public MapController(int screenResolutionWidth, int screenResolutionHeight)
	{
		screenWidth = screenResolutionWidth;
		screenHeight = screenResolutionHeight;
		mapModel = new MapRenderModel(screenWidth, screenHeight);
		mapView = new MapRenderView(mapModel, screenWidth, screenHeight);
	}
	
	/**@return The current MapRenderView-object
	 */
	public MapRenderView getView()
	{
		return mapView;
	}
	
	/**Sets the current area to parameter
	 * @param area name of area
	 */
	public void setCurrentArea(String area)
	{
		mapModel.setCurrentArea(area);
	}
	/**@return the current area
	 */
	public String getCurrentArea()
	{
		return mapModel.getCurrentArea();
	}
	/**Tells the map to display the parameter location
	 * @param coords coordinates
	 */
	public void updateMapRenderView(int[] coords)
	{	
			mapModel.updateMapRenderModel(coords[0], coords[1]);
			mapView.updateMapRenderView();
	}
	/**Checks if parameter location is a link to another area
	 * @param playerCoords coordinates
	 * @return true/false
	 */
	public boolean isLink(int[] playerCoords)
	{
		return mapModel.isLink(playerCoords[0], playerCoords[1]);
	}
	/**Checks where parameter location links to
	 * @param playerCoords coordinates
	 * @return name of the area the location links to
	 */
	public String linksTo(int[] playerCoords)
	{
		return mapModel.linksTo(playerCoords[0], playerCoords[1]);
	}
	
	/**Checks if parameter location is walkable by the player
	 * @param playerCoords coordinates
	 * @return true/false
	 */
	public boolean isWalkable(int[] playerCoords)
	{
		return mapModel.isWalkable(playerCoords[0], playerCoords[1]);
	}
	
	/**Gets the chance of encountering a monster at parameter location
	 * @param playerCoords coordinates
	 * @return chance of encounter
	 */
	public int encounterChance(int[] playerCoords)
	{
		return mapModel.encounterChance(playerCoords[0], playerCoords[1]);
	}
	
	/**Used to restore the game to an earlier saved state
	 * @param area name of area
	 * @param coords coordinates
	 */
	public void restoreFromLoad(String area, int[] coords)
	{
		setCurrentArea(area);
		updateMapRenderView(coords);
	}
}
