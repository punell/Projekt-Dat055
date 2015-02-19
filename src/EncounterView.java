import java.awt.GridLayout;
import javax.swing.JPanel;

public class EncounterView extends JPanel
{
	private EncounterModel encounterModel;
	
	public EncounterView(EncounterModel eM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		encounterModel = eM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(2,2));	
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
}

// http://www.coderanch.com/how-to/java/BackgroundImageOnJPanel