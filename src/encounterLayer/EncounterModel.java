package encounterLayer;

import java.awt.Image;
import java.util.HashMap;
import java.util.LinkedList;



/**
 * Holds the encounters different methods and functions. Holds the important player and monster-stats for the encounter.
 * @author Robin Punell
 * @version 2015-03-09
 */
public class EncounterModel 
{
	private HashMap<String, Integer> playerStats;
	private HashMap<String, Integer> monsterStats;
	private MonsterModel monsterModel;
	private int playerHealth;
	private int baseArmor;
	private int monsterLevel;
	private boolean playerDead;
	
	
	/**
	 * Constructor - Blank
	 */
	public EncounterModel()
	{	
	}
	
	
	/**
	 * Player attack
	 */
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
	
	/**
	 * Monster attack
	 */
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
	
	/**
	 * Player block
	 */
	public void playerBlock()
	{
		int x = playerStats.get("armor")+baseArmor;
		playerStats.put("armor", x);
	}
	
	
	/**
	 * Sets the players stats and sets base armor.
	 * @param h	Health of the player
	 * @param s Stats of the player
	 */
	public void setPlayerStats(int h, HashMap<String, Integer> s)
	{
		playerHealth = h;
		playerStats = s;
		baseArmor = playerStats.get("armor");
	}
	
	/**
	 * Sets the level and creates the monster
	 * @param level The level of the monster
	 */
	public void setMonsterLevel(int level)
	{
		monsterLevel = level;
		monsterModel = new MonsterModel(monsterLevel);
		monsterStats = monsterModel.getStats();
	}

	/**
	 * Creates a string of the playerstats.
	 * @return String of playerstats
	 */
	public String playerString()
	{
		return "Health: "+playerHealth+" Armor: "+playerStats.get("armor")+" Damage: "+playerStats.get("damage");
	}
	
	/**
	 * Creates a string of the monstersats
	 * @return String of monsterstats
	 */
	public String monsterStringStats()
	{
		return "Health: "+monsterStats.get("health")+" Armor: "+monsterStats.get("armor");
	}
	
	/**
	 * Creates a string of the monsters name and level
	 * @return Monster name and level
	 */
	public String monsterStringName()
	{
		return ""+monsterModel.getName()+" - Level "+monsterModel.getLevel();
	}
	
	/**
	 * Loads the monster image
	 * @return Monster image
	 */
	public Image loadMonsterImage()
	{
		return monsterModel.loadMonsterImage();
	}
	
	/**
	 * Checks if the monster is dead
	 * @return Monster-status
	 */
	public boolean monsterDead()
	{
		return monsterModel.monsterDead();
	}
	
	/**
	 * Checks if the player is dead
	 * @return Player-status
	 */
	public boolean playerDead()
	{
		return playerDead;
	}
	/**
	 * Gets player health
	 * @return Player health
	 */
	public int getPlayerHP()
	{
		return playerHealth;
	}
	
	/**
	 * Gets the scaling for the monster image
	 * @return Scaling
	 */
	public int getScale()
	{
		return monsterModel.getScale();
	}
}