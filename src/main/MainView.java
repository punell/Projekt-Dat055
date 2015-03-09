package main;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/** Main window of the game
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class MainView extends JFrame
{
	
	/**MainView creates the JFrame everything hangs on and also
	 *  displays the different view-objects in the JFrames different layers
	 *  It also listens for input
	 * @param title Title of the frame
	 * @param screenResolutionWidth The width of the screen
	 * @param screenResolutionHeight The height of the screen
	 * @throws IOException may throw IOException
	 */
	public MainView(String title, int screenResolutionWidth, int screenResolutionHeight) throws IOException
	{
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(screenResolutionWidth, screenResolutionHeight); //Does this or the one below work best?
		//setState(Frame.NORMAL); //Maximizes the frame
		setLayout(new BorderLayout());		
	}
	
	
	

}
