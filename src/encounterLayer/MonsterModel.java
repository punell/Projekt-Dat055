package encounterLayer;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class MonsterModel 
{
	private HashMap<String, Integer> stats;
	private int number;
	private int health;
	private int damage;
	private int armor;
	private String name;
	private String level;
	private Image monsterImage;
	private boolean monsterDead;
	private String monsterFile;
	private int scale;
	
	public MonsterModel(int monsterLevel)
	{
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
	
	public String getName()
	{
		return name;
	}
	
	public HashMap<String, Integer> getStats()
	{
		return stats;
	}
	
	public String getLevel()
	{
		return level;
	}
	
	public Image loadMonsterImage() //monsternumber == 99 -> Dracula-image, possible more images?
	{
		switch(number)
		{
			case 99: monsterFile = "dracula.png"; scale = 2; break; // Sets right scaling to each image.
			default: monsterFile = "monster.png"; scale = 3; break;
		}
		try {
			monsterImage = ImageIO.read(new File(monsterFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return monsterImage;
	}
	
	public int getScale()
	{
		return scale;
	}
	
	public boolean monsterDead()
	{
		return monsterDead;
	}
	public void setMonsterDead(boolean x)
	{
		monsterDead = x;
	}
	
}	