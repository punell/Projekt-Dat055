
//Har ej tagit bort kommenteringen i MainController än, för att slippa git-problem. 

public class EncounterController 
{

	private EncounterModel encounterModel;
	private EncounterView encounterView;
	
	public EncounterController(int screenResolutionWidth, int screenResolutionHeight)
	{
		encounterModel = new EncounterModel();
		encounterView = new EncounterView(encounterModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	
	public EncounterView getView()
	{
		return encounterView;
	}
	
	
	
	
	
	
}