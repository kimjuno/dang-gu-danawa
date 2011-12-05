package DangGuDANAWA.Object3D.Utils;
import DangGuDANAWA.Object3D.Object.Ball;
import DangGuDANAWA.Object3D.Object.Billiard;
import DangGuDANAWA.Object3D.Object.Camera;
import DangGuDANAWA.Object3D.Object.Cue;
import DangGuDANAWA.Object3D.Object.Door;
import DangGuDANAWA.Object3D.Object.Plants;
import DangGuDANAWA.Object3D.Object.SingleCue;
import DangGuDANAWA.Object3D.Object.Tv;
import DangGuDANAWA.Object3D.Object.Wall;
import DangGuDANAWA.Object3D.Object.Windows;

public class ObjectFactory {
	
	static public Camera createCamera()
	{
		return new Camera();
	}	
	
	static public Ball createBall()
	{
		return new Ball();
	}
	
	static public Billiard createBilliard()
	{
		return new Billiard();
	}
	
	static public Cue createCue()
	{
		return new Cue();
	}
	
	static public Door createDoor()
	{
		return new Door();
	}
	
	static public Plants createPlants()
	{
		return new Plants();
	}
	
	static public SingleCue createSingleCue()
	{
		return new SingleCue();
	}
	
	static public Tv createTv()
	{
		return new Tv();
	}
	
	static public Wall createWall()
	{
		return new Wall();
	}
	
	static public Windows createWindows()
	{
		return new Windows();
	}
}