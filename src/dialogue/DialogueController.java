package dialogue;

/**
 * @author
 * 
 */
public class DialogueController 
{
	private DialogueModel dialogueModel;
	private DialogueView dialogueView;
	
	/**
	 * This constructor creates the DialougeModel and DialogueView classes
	 * @param screenResolutionWidth Sets the resolution
	 * @param screenResolutionHeight Sets the resolution 
	 */
	public DialogueController(int screenResolutionWidth, int screenResolutionHeight)
	{
		dialogueModel = new DialogueModel();
		dialogueView = new DialogueView(dialogueModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	/**
	 * Sets the dialogue window to visible and sends a string to the Text function
	 * @param text A key that's needed to show the right value in a HashMap
	 */
	public void show(String text)
	{
		dialogueView.Text(text);
		dialogueView.setVisible(true);
	}

}
