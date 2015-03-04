package dialogue;

public class DialogueController 
{
	private DialogueModel dialogueModel;
	private DialogueView dialogueView;
	public DialogueController(int screenResolutionWidth, int screenResolutionHeight)
	{
		dialogueModel = new DialogueModel();
		dialogueView = new DialogueView(dialogueModel, screenResolutionWidth, screenResolutionHeight);
	}
}
