package encounterLayer;
import java.io.IOException;
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
		
		switch(input)
		{
			case "attack":  encounterModel.playerAttack(); break;

			case "block":	encounterModel.playerBlock(); break;
			
			case "run": break;
							
		}
		System.out.println(encounterModel.getPlayerHP());
		if(encounterModel.monsterDead())
		{
			setChanged();
			notifyObservers(encounterModel.getPlayerHP()); 	//monster dead 
		} 
		encounterModel.monsterAttack();
		
		if(encounterModel.playerDead())
		{				
			

			setChanged();
			notifyObservers(encounterModel.getPlayerHP()); //player dead
		} 
		setView();
		
		
		
	}
	
	public EncounterView getView()
	{
		return encounterView;
	}
	
	public void setPlayerStats(int health, HashMap<String, Integer> stats, LinkedList bp)
	{
		encounterModel.setPlayerStats(health, stats, bp);
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
