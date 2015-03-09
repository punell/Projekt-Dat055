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


/**
 * Controls the reading and writing of the SaveGame object to files
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 */
public class SaveAndLoadController 
{
	private SaveGame fromLoad;
	private String savePath;
	/**
	 * The constructor 
	 */
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
	
	/**
	 * Saves the game as a "quicksave"
	 * @param packageForSave The game that's going to be saved 
	 * @return Return on success
	 */
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
	
	/**
	 * Saves the game with a name of players own choosing
	 * @param packageForSave The game that's going to be saved
	 * @param filename The name of the save file
	 * @return Return on success
	 */
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
	
	/**
	 * Loads a saved game from a quicksave file
	 * @return The quicksaved game
	 */
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
	
	/**
	 * Loads a saved game from a specific file
	 * @param savename The name of the saved file
	 * @return Returns the saved game
	 */
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
