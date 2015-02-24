package encounterLayer;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EncounterView extends JPanel
{
	private EncounterModel encounterModel;
	private Image background;
	private Image playerImage;
	private ImageIcon playerIcon;
	private Image monsterImage;
	private ImageIcon monsterIcon;
	
	
	
	public EncounterView(EncounterModel eM, int screenResolutionWidth, int screenResolutionHeight) throws IOException
	{
		super();
		encounterModel = eM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(2,2));	
		loadBackground();
		
		JLabel emptyLabel = new JLabel("");
		add(emptyLabel);
		
		JLabel emptyLabel2 = new JLabel("");
		add(emptyLabel2);
		
		playerImage = ImageIO.read(new File("player.png"));
		playerImage = playerImage.getScaledInstance(screenResolutionWidth/3, screenResolutionHeight/3, Image.SCALE_DEFAULT);
		playerIcon = new ImageIcon(playerImage); 
		JLabel playerLabel = new JLabel(playerIcon);
		add(playerLabel);
		
		//ex: monsterImage = loadMonsterImage();
		monsterImage = ImageIO.read(new File("monster.png"));
		monsterImage = monsterImage.getScaledInstance(screenResolutionWidth/3, screenResolutionHeight/3, Image.SCALE_DEFAULT);
		monsterIcon = new ImageIcon(monsterImage); 
		JLabel monsterLabel = new JLabel(monsterIcon);
		add(monsterLabel);
			
	}
	
	public Image loadPlayerImage(){
		
		return encounterModel.getPlayerImage();
		
	}
	public Image loadMonsterImage(){
		
		return encounterModel.getMonsterImage();	
		
	}
	public Image loadBackground(){
		
		try {
			background = ImageIO.read(new File("eBG1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return background;
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
		
}
