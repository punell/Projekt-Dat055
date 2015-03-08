package dialogue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class DialogueModel
{
	private HashMap<String,String> map;
	
	public DialogueModel()
	{
		map = new HashMap<>();
		readDialogue();
	} 
	
	private void readDialogue()
	{
		
		try
		{
			
			BufferedReader reader = new BufferedReader(new FileReader("text-resources/dialogue.txt"));
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
	
	public HashMap<String, String> getMap()
	{
		return map;
	}
	
}
