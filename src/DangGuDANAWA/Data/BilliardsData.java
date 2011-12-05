package DangGuDANAWA.Data;

public class BilliardsData {
	public static float[] positionX = new float[3];
	public static float[] positionY = new float[3];
	public static float shotAngle = 0.0f;
	public static float shotPower = 0.0f;
	public static float shotSpin = 0.0f;
	public static boolean simulation = false;
	
	static public void resetState()
	{
		for(int i = 0; i < 3; i++)
		{
			positionX[i] = 0.0f;
			positionY[i] = 0.0f;
		}
		shotAngle = 0.0f;
		shotPower = 0.0f;
		shotSpin = 0.0f;
		simulation = false;
	}
}