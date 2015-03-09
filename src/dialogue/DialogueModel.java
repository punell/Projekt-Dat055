package dialogue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * Reads from a textfile, links these lines of text to specific keys in a HashMap, then sends these to DialogueView which displays the text on the screen.
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 */
public class DialogueModel
{
	private HashMap<String,String> map;
	
	/**
	 * The constructor creates a HashMap and calls for the readDialogue method
	 */
	public DialogueModel()
	{
		map = new HashMap<>();
		readDialogue();
	} 
	
	/**
	 * Reads text from a text file and put it a HashMap with a unique key and value for each line. 
	 */
	private void readDialogue()
	{
		
		try
		{
			InputStream filepath = getClass().getClassLoader().getResourceAsStream("resource/textfiles/dialogue.txt");
			InputStreamReader streamReader = new InputStreamReader(filepath);
			BufferedReader reader = new BufferedReader(streamReader);
			String line = reader.readLine();
			String hashKey;
			String hashValue;
			
			while(line != null)
			{
				hashKey = line;
				line = reader.readLine();
				hashValue = line;
				map.put(hashKey, hashValue);
				line = reader.readLine();
			}
			reader.close();
		}
		
		catch(IOException e)
		{}
	}
	
	/**
	 * @return Returns the HashMap
	 */
	public HashMap<String, String> getMap()
	{
		return map;
	}
	
}
