package saveAndLoad;
import java.io.Serializable;

import gameLayer.CharacterModel;
import gameLayer.PlayerModel;


/**
 * Is a type of list which contains a CharacterModel object and a PlayerModel object, this is to make the communication between the SaveAndLoad-Package and the gameLayer-Package easier.
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 *
 */
public class SaveGame implements Serializable
{
	private PlayerModel player;
	private CharacterModel charModel;
	/**
	 * This is the constructor
	 * @param pM The player model
	 * @param cM The character model
	 */
	public SaveGame(PlayerModel pM, CharacterModel cM)
	{
		player = pM;
		charModel = cM;
	}
	
	/**
	 * @return Returns the player
	 */
	public PlayerModel getPlayer()
	{
		return player;
	}
	/**
	 * @return Returns the character model
	 */
	public CharacterModel getCharModel()
	{
		return charModel;
	}

}
