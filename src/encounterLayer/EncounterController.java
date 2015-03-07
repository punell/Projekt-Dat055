package encounterLayer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;



public class EncounterController extends Observable
{

	private EncounterModel encounterModel;
	private EncounterView encounterView;
	

	public EncounterController(int screenResolutionWidth, int screenResolutionHeight)
	{

		encounterModel = new EncounterModel();		
		encounterView = new EncounterView(encounterModel, screenResolutionWidth, screenResolutionHeight);
			
	}
	public void input(String input)
	{
		boolean validAction = false;
		boolean run = false;
		switch(input)
		{
			case "attack":  encounterModel.playerAttack(); 
							validAction = true;
							break;

			case "block":	encounterModel.playerBlock(); 
							validAction = true;
							break;
			
			case "run": 	run = true;
							validAction = true;
							break;
		}
		
		if(encounterModel.monsterDead())
		{
			setChanged();
			notifyObservers(encounterModel.getPlayerHP()); 	//monster dead 
			validAction = false;
		} 
		if(validAction == true)
		{	
			encounterModel.monsterAttack();
		}
		
		if(encounterModel.playerDead())
			{				
				setChanged();
				notifyObservers(encounterModel.getPlayerHP()); //player dead
			} 
		if(run == true)
		{
			setChanged();
			notifyObservers(encounterModel.getPlayerHP());
		}
		
		setView();
	}
	
	public EncounterView getView()
	{
		return encounterView;
	}
	
	public void setPlayerStats(int h, HashMap<String, Integer> s, LinkedList bp)
	{
		encounterModel.setPlayerStats(h, s, bp);
	}
	
	public void setMonsterLevel(int level) //Monster level-intput (dracula = 99)
	{
			encounterModel.setMonsterLevel(level);	
	}
	
	public void setView()
	{
		encounterView.clear();
		encounterView.setConOp();
		encounterView.setStats();
		encounterView.setPlayerImage();
		encounterView.setMonsterImage();
	}
	

}
