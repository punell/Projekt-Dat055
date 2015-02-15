import java.io.IOException;


public class MapController 
{
	private MapRenderModel mapModel;
	private MapRenderView mapView;
	public MapController(int screenResolutionWidth, int screenResolutionHeight)
	{
		mapModel = new MapRenderModel();
		mapView = new MapRenderView(mapModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	public MapRenderView getView() //MainView uses this
	{
		return mapView;
	}
	
	public void updateView(int[] coords)
	{
		int[] cellCoords = { coords[0], coords[1] };
		int[] roomCoords = { coords[2], coords[3] };
		int[] currentRoom = mapModel.getCurrentRoom();
		
		if(roomCoords[0] != currentRoom[0] || roomCoords[1] != currentRoom[1]) //checks if we have ventured beyond the room-borders
		{
			mapModel.updateMapRenderModel(roomCoords[0], roomCoords[1], "overworld");
			mapView.updateMapRenderView();
		}
			
		else if(mapModel.isLink(cellCoords[0], cellCoords[1]))
		{
			mapModel.updateMapRenderModel(roomCoords[0], roomCoords[1], mapModel.linksTo(cellCoords[0], cellCoords[1])); //checks if tile is link
			mapView.updateMapRenderView();
		}
	}
	
	public boolean isWalkable(int[] playerCoords)
	{
		return mapModel.isWalkable(playerCoords[0], playerCoords[1]);
	}
}
