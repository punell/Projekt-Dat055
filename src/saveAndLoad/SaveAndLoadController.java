package saveAndLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SaveAndLoadController 
{
	private SaveGame fromLoad;
	private String savePath;
	public SaveAndLoadController()
	{
		Path currentRelativePath = Paths.get("");
		savePath = currentRelativePath.toAbsolutePath().toString() + "/save/";
		try
		{
			File folder = new File(savePath);
			folder.mkdir();
		}
		catch(Exception e){}
		
	}
	
	public boolean save(SaveGame packageForSave)
	{
		try
		{
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream(savePath+"quicksave.sav"));
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
			ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream(savePath+filename+".sav"));
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
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream(savePath+"quicksave.sav"));
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
			ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream(savePath+savename));
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
