import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Controller extends KeyAdapter
{	
	/** Controller creates and keeps track of all the different Model-objects
	 *  in the game. It waits for keyboard-input and sends those inputs to the
	 *  correct object
	 */
	private MapRenderModel map;
	private PlayerModel player;
	
	public Controller(CharacterView cV, MapRenderView mRV) throws IOException
	{
		map = new MapRenderModel(mRV);
		player = new PlayerModel(cV, map);
	}
	
	public void keyPressed(KeyEvent e)
	{
		try
		{
			if(e.getKeyCode() == 37)
				player.move('W');
			else if(e.getKeyCode() == 38)
				player.move('N');
			else if(e.getKeyCode() == 39)
				player.move('E');
			else if(e.getKeyCode() == 40)
				player.move('S');			
		}
		catch(IOException ex)
		{
		}
	}
}
