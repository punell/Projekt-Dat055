package encounterLayer;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;



/**
 * This class creates a monster.
 * @author Robin Punell
 * @version 2015-03-09
 */
public class MonsterModel 
{
	private HashMap<String, Integer> stats;
	private int number;
	private String name;
	private String level;
	private Image monsterImage;
	private boolean monsterDead;
	private int scale;
	
	/**
	 * Constructor - Creates different monsters based on input.
	 * @param monsterLevel Tells the constructor which type of monster it should create. (Dracula == 99)
	 */
	public MonsterModel(int monsterLevel)
	{
		int health;
		int damage;
		int armor;
		
		number = monsterLevel; 
		monsterDead = false;
		if(number == 99){ 	//DARCULA
			health = 500;
			damage = 50;
			armor = 500;
			name = "Dracula";
			level = "Boss";
		}
		else{						//Generates monsterStats based on input 
		health = (number*10);
		damage = (number*10);
		armor = (number*5);
		level = "" + number;
		name = "Vampire";			//Can generate different names if expanded

		}
		stats = new HashMap<>();
		stats.put("health", health);
		stats.put("damage", damage);
		stats.put("armor", armor);
	}
	
	
	
	/**
	 * Returns the name of the monster
	 * @return This monsters name 
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns a HashMap of the monsters health, damage and armor.
	 * @return	This monsters stats. 
	 */
	public HashMap<String, Integer> getStats()
	{
		return stats;
	}
	
	/**
	 * Returns the level of this monster.
	 * @return This monsters level.
	 */
	public String getLevel()
	{
		return level;
	}
	
	/**
	 * Loads the image for this monster. Sets the correct image based on the level of the monster or boss.
	 * Sets the right scaling for each image.
	 * 
	 * @return This monsters loaded image.
	 */
	public Image loadMonsterImage()
	{
		String monsterFile;
		switch(number)
		{
			case 99: monsterFile = "resource/textures/dracula.png"; scale = 2; break; // Scale sets the right scaling to each image.
			default: monsterFile = "resource/textures/monster.png"; scale = 3; break;
		}
		try {
			monsterImage = ImageIO.read(getClass().getClassLoader().getResource(monsterFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monsterImage;
	}
	
	/**
	 * Returns the wanted scaling for the monster image.
	 * @return Scaling
	 */
	public int getScale()
	{
		return scale;
	}
	
	/**
	 * Returns this monsters living-status (false=alive. true = dead).
	 * @return False if alive, True if dead.
	 */
	public boolean monsterDead()
	{
		return monsterDead;
	}
	
	/**
	 * Sets this monsters living-status (false=alive. true = dead)
	 * @param x False if alive, True if dead.
	 */
	public void setMonsterDead(boolean x)
	{
		monsterDead = x;
	}
	
}	
