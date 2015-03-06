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
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("save/quicksave.sav"));
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
	
	public boolean save(SaveGame packageForSave, String filename)
	{
		
		try
		{	
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("save/"+filename+".sav"));
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
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream("save/quicksave.sav"));
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
	
	public SaveGame load(String savename)
	{
		try
		{
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream("save/"+savename));
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
