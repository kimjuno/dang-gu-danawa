package DangGuDANAWA.Object3D.Object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

import DangGuDANAWA.Object3D.Interface.Object3D;

public class Wall implements Object3D{
	private int size[];
	private int objectNumber;

	public Wall() {
		setValue();
		setVertex0();
		setFace0();
		setNormal0();
		setColor0();
		setTexture0();
	}

	public void draw(GL10 gl) {		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		for (int i = 0; i < objectNumber; i++) {
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer[i]);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer[i]);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer[i]);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTexBuffer[i]);
			gl.glDrawElements(GL10.GL_TRIANGLES, size[i],
					GL10.GL_UNSIGNED_BYTE, mIndexBuffer[i]);
		}
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);		
	}

	private FloatBuffer[] mVertexBuffer;
	private FloatBuffer[] mColorBuffer;
	private FloatBuffer[] mNormalBuffer;
	private ByteBuffer[] mIndexBuffer;
	private FloatBuffer[] mTexBuffer;
	private int[] mTextureExist;

	public void setValue() {
		objectNumber = 1;
		size = new int[objectNumber];
		mVertexBuffer = new FloatBuffer[objectNumber];
		mIndexBuffer = new ByteBuffer[objectNumber];
		mNormalBuffer = new FloatBuffer[objectNumber];
		mColorBuffer = new FloatBuffer[objectNumber];
		mTexBuffer = new FloatBuffer[objectNumber];
		mTextureExist = new int[objectNumber];
		mTextureExist[0] = 0;
	}

	public void setVertex0() {
		float valueVertex[] = { -20.0f, 20.0f, -2.13623e-005f, 
				20.0f, 20.0f, -2.13623e-005f, 
				20.0f, -20.0f, -2.13623e-005f,
				-20.0f, -20.0f, -2.13623e-005f };
		ByteBuffer vbb = ByteBuffer.allocateDirect(valueVertex.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer[0] = vbb.asFloatBuffer();
		mVertexBuffer[0].put(valueVertex);
		mVertexBuffer[0].position(0);
	}

	public void setFace0() {
		byte valueFace[] = { (byte) 2, (byte) 0, (byte) 3, (byte) 0, (byte) 2,
				(byte) 1 };
		size[0] = valueFace.length;

		mIndexBuffer[0] = ByteBuffer.allocateDirect(valueFace.length);
		mIndexBuffer[0].put(valueFace);
		mIndexBuffer[0].position(0);
	}

	public void setNormal0() {
		float valueNormal[] = { 0f, 0f, 2f, 0f, 0f, 2f, 0f, 0f, 2f, 0f,
				0f, 2f };

		ByteBuffer nbb = ByteBuffer.allocateDirect(valueNormal.length * 4);
		nbb.order(ByteOrder.nativeOrder());
		mNormalBuffer[0] = nbb.asFloatBuffer();
		mNormalBuffer[0].put(valueNormal);
		mNormalBuffer[0].position(0);
	}

	public void setColor0() {
		float valueColor[] = { 0.899999f, 0.899999f, 0.8f, 1.0f, 0.899999f,
				0.899999f, 0.8f, 1.0f, 0.899999f, 0.899999f, 0.8f, 1.0f,
				0.899999f, 0.899999f, 0.8f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[0] = cbb.asFloatBuffer();
		mColorBuffer[0].put(valueColor);
		mColorBuffer[0].position(0);
	}

	public void setTexture0() {
		float valueTexture[] = { -1f, -1f, -1f, 1f, 1f, 1f, 1f, -1f};

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueTexture.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mTexBuffer[0] = cbb.asFloatBuffer();
		mTexBuffer[0].put(valueTexture);
		mTexBuffer[0].position(0);
	}

	@Override
	public void draw(GL10 gl, int variable) {
		// TODO Auto-generated method stub
		
	}
}
