import gameLayer.CharacterModel;
import gameLayer.PlayerModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveAndLoadController 
{
	private PlayerModel player;
	private CharacterModel charModel;
	public SaveAndLoadController()
	{
		
	}
	
	public boolean saveGame(PlayerModel player, CharacterModel charModel)
	{
		try
		{
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("savefile.sav"));
			saveStream.writeObject(player);
			saveStream.writeObject(charModel);
			saveStream.close();
			System.out.println("Saving Game...");
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public CharacterModel getCharModel()
	{
		return charModel;
	}
	
	public PlayerModel getPlayer()
	{
		return player;
	}
	
	public void loadGame()
	{
		try
		{
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream("savefile.sav"));
			player = (PlayerModel)loadStream.readObject();
			charModel = (CharacterModel)loadStream.readObject();
			loadStream.close();
			System.out.println("Loading Game...");
			
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		
	}
}
