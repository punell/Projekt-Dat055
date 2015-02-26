package saveAndLoad;
import java.io.Serializable;

import gameLayer.CharacterModel;
import gameLayer.PlayerModel;


public class SaveGame implements Serializable
{
	private PlayerModel player;
	private CharacterModel charModel;
	public SaveGame(PlayerModel pM, CharacterModel cM)
	{
		player = pM;
		charModel = cM;
	}
	
	public PlayerModel getPlayer()
	{
		return player;
	}
	public CharacterModel getCharModel()
	{
		return charModel;
	}

}
