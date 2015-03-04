package mapRenderer;
import java.io.IOException;


public class MapController 
{
	private MapRenderModel mapModel;
	private MapRenderView mapView;
	public MapController(int screenResolutionWidth, int screenResolutionHeight)
	{
		mapModel = new MapRenderModel(screenResolutionWidth, screenResolutionHeight);
		mapView = new MapRenderView(mapModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	public MapRenderView getView() //MainView uses this
	{
		return mapView;
	}
	
	public void setCurrentArea(String area)
	{
		mapModel.setCurrentArea(area);
	}
	public String getCurrentArea()
	{
		return mapModel.getCurrentArea();
	}
	public void updateMapRenderView(int[] coords)
	{	
			mapModel.updateMapRenderModel(coords[0], coords[1]);
			mapView.updateMapRenderView();
	}
	public boolean isLink(int[] playerCoords)
	{
		return mapModel.isLink(playerCoords[0], playerCoords[1]);
	}
	public String linksTo(int[] playerCoords)
	{
		return mapModel.linksTo(playerCoords[0], playerCoords[1]);
	}
	
	public boolean isWalkable(int[] playerCoords)
	{
		return mapModel.isWalkable(playerCoords[0], playerCoords[1]);
	}
	
	public int encounterChance(int[] playerCoords)
	{
		return mapModel.encounterChance(playerCoords[0], playerCoords[1]);
	}
	
	public void restoreFromLoad(String area, int[] coords)
	{
		setCurrentArea(area);
		updateMapRenderView(coords);
	}
}
