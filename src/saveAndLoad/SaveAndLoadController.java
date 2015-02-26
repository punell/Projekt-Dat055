package saveAndLoad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveAndLoadController 
{
	private SaveGame fromLoad;
	public SaveAndLoadController()
	{
		
	}
	
	public boolean save(SaveGame packageForSave)
	{
		try
		{
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("savefile.sav"));
			saveStream.writeObject(packageForSave);
			saveStream.close();
			System.out.println("Saving Game...");
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public SaveGame load()
	{
		try
		{
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream("savefile.sav"));
			fromLoad = (SaveGame)loadStream.readObject();
			loadStream.close();
			System.out.println("Loading Game...");
			return fromLoad;
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
