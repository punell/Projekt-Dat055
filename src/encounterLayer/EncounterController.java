package encounterLayer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;



/**
 * Holds methods for input and output of the encounter. Creates encounterModel and encounterView. Extends Observable.
 * @author Robin Punell
 * @version 2015-03-09
 *
 */
public class EncounterController extends Observable
{

	private EncounterModel encounterModel;
	private EncounterView encounterView;
	

	/**
	 * Constructor - Creates EncounterModel and EncounterView. Attach the created EncounterModel object in the EncounterView
	 * @param screenResolutionWidth	The screen resolution width of the screen
	 * @param screenResolutionHeight The screen resolution height of the screen
	 */
	public EncounterController(int screenResolutionWidth, int screenResolutionHeight)
	{

		encounterModel = new EncounterModel();		
		encounterView = new EncounterView(encounterModel, screenResolutionWidth, screenResolutionHeight);
			
	}
	/**
	 * Executes the wanted action based on input. Updates the view to show updated stats etc.
	 * @param input Wanted action
	 */
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
	
	/**
	 * Gets the view from the EncounterView-class
	 * @return The view of the encounter
	 */
	public EncounterView getView()
	{
		return encounterView;
	}
	
	/**
	 * Sets the players stats
	 * @param h	Health
	 * @param s	Stats
	 */
	public void setPlayerStats(int h, HashMap<String, Integer> s)
	{
		encounterModel.setPlayerStats(h, s);
	}
	
	/**
	 * Sets the level of the wanted monster or boss
	 * @param level Level of monster. Level 99 creates Dracula
	 */
	public void setMonsterLevel(int level) 
	{
			encounterModel.setMonsterLevel(level);	
	}
	
	/**
	 * Sets the start-screen of the encounterView
	 */
	public void setView()
	{
		encounterView.clear();
		encounterView.setConOp();
		encounterView.setStats();
		encounterView.setPlayerImage();
		encounterView.setMonsterImage();
	}
}
