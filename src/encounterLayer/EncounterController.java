package encounterLayer;
import java.io.IOException;



public class EncounterController 
{

	private EncounterModel encounterModel;
	private EncounterView encounterView;
	
	public EncounterController(int screenResolutionWidth, int screenResolutionHeight) throws IOException
	{
		encounterModel = new EncounterModel();
		encounterView = new EncounterView(encounterModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	
	public EncounterView getView()
	{
		return encounterView;
	}
	
	
	
	
}