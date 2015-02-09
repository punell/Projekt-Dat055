import javax.swing.*;

import java.awt.BorderLayout;
import java.io.IOException;

public class MainView extends JFrame
{
	/** MainView creates the JFrame everything hangs on and also
	 *  displays the different view-objects in the JFrames different layers
	 *  It also listens for input
	 */
	
	public int screenResolutionWidth = 1920; //future versions might allow for changes based on the resolution
	public int screenResolutionHeight = 1080;
	private MapRenderView mapRenderLayer;
	private CharacterView characterLayer;
	private Controller controllerListener;
	private MainView(String title) throws IOException
	{
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		mapRenderLayer = new MapRenderView(screenResolutionWidth, screenResolutionHeight); 
		setContentPane(mapRenderLayer); //contentPane is the bottom layer of the JFrame
		
		characterLayer = new CharacterView(screenResolutionWidth, screenResolutionHeight);
		characterLayer.setOpaque(false);
		setGlassPane(characterLayer); //glassPane is a transparent layer on top of the other layers
		getGlassPane().setVisible(true);
		
		controllerListener = new Controller(characterLayer, mapRenderLayer);
		addKeyListener(controllerListener); //this allows the JFrame to listen for keyboard input
		
		
	}
	
	public static void main(String[] args) throws IOException 
	{
		MainView app = new MainView("spelet v0.12 (now with comments!)");
		app.pack();
		app.setVisible(true);

	}

}
