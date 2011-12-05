package DangGuDANAWA.Object3D.Interface;

import javax.microedition.khronos.opengles.GL10;

public interface Object3D{
	public void draw(GL10 gl);
	public void draw(GL10 gl, int variable);
	public void setValue();
}