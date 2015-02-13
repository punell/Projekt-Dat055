import java.io.IOException;


public class TerrainGrass extends TerrainType 
{	
	public TerrainGrass() throws IOException 
	{
		//These all follow the same pattern (boolean walkable, String filename) (add more in future versions)
		// Actually allows for easy integration of things such as "climb-able" or "swim-able"  
		super(true, false, "grass.jpg");
	}

}
