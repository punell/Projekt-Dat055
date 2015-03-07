package encounterLayer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EncounterView extends JPanel
{
	private EncounterModel encounterModel;
	private Image background;
	private Image playerImage;
	private ImageIcon playerIcon;
	private Image monsterImage;
	private ImageIcon monsterIcon;
	private int sRW;
	private int sRH;
	private int scale;
	
	public EncounterView(EncounterModel eM, int screenResolutionWidth, int screenResolutionHeight) 
	{
		super();
		encounterModel = eM;
		sRW = screenResolutionWidth;
		sRH = screenResolutionHeight;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout(2,2));	
		loadBackground();
			
	}
	
	
	public void clear()
	{
		removeAll();
		revalidate();
	}
	
	public void setConOp()
	{
		JLabel commandLabel = new JLabel("<html><font color='red'>Press A to ATTACK<br>Press B to BLOCK<br>Press R to RUN</font></html>", SwingConstants.CENTER);		//Fyll i tangent-alternativen för encounter
		commandLabel.setFont(new Font("Verdana",1,20));
		add(commandLabel); 
	}
	
	public void setStats()
	{
		JLabel hpLabel = new JLabel("<html><font color='red'>"+ "Player<br>"+ encounterModel.playerString() + "<br><br>"+encounterModel.monsterStringName()+"<br>"+encounterModel.monsterStringStats()+"</font></html>"); //Skall visa hälsa (player och monster)
		hpLabel.setFont(new Font("Verdana",1,20));
		add(hpLabel);
	}
	
	public void setPlayerImage()
	{
		try {
			playerImage = ImageIO.read(new File("player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerImage = playerImage.getScaledInstance(sRW/3, sRH/3, Image.SCALE_DEFAULT);
		playerIcon = new ImageIcon(playerImage); 
		JLabel playerLabel = new JLabel(playerIcon);
		add(playerLabel);
	}
	
	public void setMonsterImage()
	{
		monsterImage = encounterModel.loadMonsterImage();
		scale = encounterModel.getScale();
		monsterImage = monsterImage.getScaledInstance(sRW/scale, sRH/scale, Image.SCALE_DEFAULT);
		monsterIcon = new ImageIcon(monsterImage); 
		JLabel monsterLabel = new JLabel(monsterIcon);
		add(monsterLabel);
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
		g.drawImage(background, 0, 0, sRW, sRH, null);
	}
}