package encounterLayer;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


public class EncounterModel 
{
	private HashMap<String, Integer> playerStats;
	private HashMap<String, Integer> monsterStats;
	private MonsterModel monsterModel;
	private int playerHealth;
	private LinkedList backPack;
	private int baseArmor;
	private int monsterLevel;
	private boolean playerDead;
	
	
	public EncounterModel()
	{
		
	}
	
	
	public void attack()
	{
		int x = monsterStats.get("armor") - playerStats.get("damage");
		if(x<=0)
		{
			monsterStats.put("armor", 0);
			int y = monsterStats.get("health") + x;
			
			if(y<=0)
			{
				//Monster dead
			}
			else{monsterStats.put("health", y);}
		}
		else{monsterStats.put("armor", x);}	
	}
	
	public void monsterAttack()
	{
		int x = playerStats.get("armor") - monsterStats.get("damage");
		if(x<=0)
		{
			playerStats.put("armor", 0);
			int y = playerHealth + x;
			
			if(y<=0)
			{
				playerDead = true; //Player dead
			}
			else{playerStats.put("health", y);}
		}
		else{playerStats.put("armor", x);}	
	}
	
	public void block()
	{
		int x = playerStats.get("armor")+baseArmor;
		playerStats.put("armor", x);
	}
	
	public void setPlayerStats(int health, HashMap<String, Integer> stats, LinkedList bp)
	{
		playerHealth = health;
		playerStats = stats;
		backPack = bp;
		baseArmor = playerStats.get("armor");
	}
	
	public void setMonsterLevel(int level)
	{
		monsterLevel = level;
		monsterModel = new MonsterModel(monsterLevel);
		monsterStats = monsterModel.getStats();
	}

	public String playerString()
	{
		return "Health: "+playerHealth+" Armor: "+playerStats.get("armor");
	}
	
	public String monsterStringStats()
	{
		return "Health: "+monsterStats.get("health")+" Armor: "+monsterStats.get("armor");
	}
	
	public String monsterStringName()
	{
		return ""+monsterModel.getName()+" - Level "+monsterModel.getLevel();
	}
	
	public Image loadMonsterImage()
	{
		return monsterModel.loadMonsterImage();
	}
}