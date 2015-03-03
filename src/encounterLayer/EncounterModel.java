package encounterLayer;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;


public class EncounterModel 
{
	private HashMap<String, Integer> playerStats;	
	private HashMap<String, Integer> monsterStats;
	private MonsterModel monsterModel;
	
	
	
	public EncounterModel(int monsternr)
	{
		monsterModel = new MonsterModel(monsternr);
		//playerStats = mainControl.getStats(); 		//Vad är enklaste sättet att få in playerStats?
		monsterStats = monsterModel.getStats();
		
	}
	
	
	public void attack()
	{
			
	}
	
	public void block()
	{
		
	}
	
	

	// For menu/dialogue
	public int getMonsterHealth(){return monsterStats.get("health");}
	public int getMonsterArmor(){return monsterStats.get("armor");}
	public int getPlayerHealth(){return playerStats.get("health");}
	public int getPlayerArmor(){return playerStats.get("armor");}
	
	public Image loadMonsterImage() throws IOException{
		return monsterModel.loadMonsterImage();
	}
	
	
}
