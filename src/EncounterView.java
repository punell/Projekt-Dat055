import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EncounterView extends JPanel
{
	private EncounterModel encounterModel;
	private Image background;
	
	public EncounterView(EncounterModel eM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		encounterModel = eM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(2,2));	
		try {
			background = ImageIO.read(new File("bground.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadPlayer()
	{
		
	}
	public void loadMonster()
	{
			
	}
	public void loadBackground()
	{
		
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
		
}

// http://www.coderanch.com/how-to/java/BackgroundImageOnJPanel