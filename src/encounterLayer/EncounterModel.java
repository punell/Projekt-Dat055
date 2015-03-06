package encounterLayer;

import java.awt.Image;
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
	
	
	public void playerAttack()
	{
		int x = monsterStats.get("armor") - playerStats.get("damage");
		if(x<=0)
		{
			monsterStats.put("armor", 0);
			int y = monsterStats.get("health") + x;
			
			if(y<=0)
			{
				monsterModel.setMonsterDead(true); //Monster dead
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
			playerHealth += x;
			
			if(playerHealth<=0)
			{
				playerDead = true; //Player dead
			}
			//else{playerStats.put("health", y);}
		}
		else{playerStats.put("armor", x);}	
	}
	
	public void playerBlock()
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
		return "Health: "+playerHealth+" Armor: "+playerStats.get("armor")+" Damage: "+playerStats.get("damage");
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
	
	public boolean monsterDead()
	{
		return monsterModel.monsterDead();
	}
	
	public boolean playerDead()
	{
		return playerDead;
	}
	public int getPlayerHP()
	{
		return playerHealth;
	}
	
	public int getScale()
	{
		return monsterModel.getScale();
	}
}