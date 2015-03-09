package dialogue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * @author
 * 
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
