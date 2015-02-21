import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveAndLoadController 
{
	public SaveAndLoadController()
	{
		
	}
	
	public boolean saveGame(PlayerModel player)
	{
		try
		{
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("savefile.sav"));
			saveStream.writeObject(player);
			saveStream.close();
			System.out.println("Saving Game...");
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public PlayerModel loadGame()
	{
		try
		{
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream("savefile.sav"));
			PlayerModel player = (PlayerModel)loadStream.readObject();
			loadStream.close();
			System.out.println("Loading Game...");
			return player;
		}
		catch(IOException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}
}
