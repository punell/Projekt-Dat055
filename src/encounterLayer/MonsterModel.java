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
	
	public MonsterModel(int monsterLevel)
	{
		number = monsterLevel; 
		monsterDead = false;
		if(number == 99){ 	//DARCULA
			health = 50;
			damage = 50;
			armor = 50;
			name = "Dracula";
			level = "Boss";
		}
		else{					//Generates monsterStats based on input 
		health = (number*10);
		damage = (number*10);
		armor = (number*5);
		level = "" + number;
			if(number < 5){name = "Vampire";}
			else{name = "Werewolf";}
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
	
	public Image loadMonsterImage() 				//if monsternumber == 99 -> Dracula-image
	{
		try {
			monsterImage = ImageIO.read(new File("monster.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return monsterImage;
	}
	
	public boolean monsterDead()
	{
		return monsterDead;
	}
	
	
}	