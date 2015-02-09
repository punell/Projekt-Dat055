import javax.swing.*;

import java.awt.BorderLayout;
import java.io.IOException;

public class MainView extends JFrame
{
	public int screenResolutionWidth = 1920;
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
		setContentPane(mapRenderLayer);
		
		characterLayer = new CharacterView(screenResolutionWidth, screenResolutionHeight);
		characterLayer.setOpaque(false);
		setGlassPane(characterLayer);
		getGlassPane().setVisible(true);
		
		controllerListener = new Controller(characterLayer, mapRenderLayer);
		addKeyListener(controllerListener);
		
		
	}
	
	public static void main(String[] args) throws IOException 
	{
		MainView app = new MainView("spelet v0.11");
		app.pack();
		app.setVisible(true);

	}

}
