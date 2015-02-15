import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame
{
	/** MainView creates the JFrame everything hangs on and also
	 *  displays the different view-objects in the JFrames different layers
	 *  It also listens for input
	 */
	public MainView(String title) throws IOException
	{
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
	}
	
	
	

}
