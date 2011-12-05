package DangGuDANAWA.Object3D.Object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

import DangGuDANAWA.Object3D.Interface.Object3D;

public class SingleCue implements Object3D{
	private int size[];
	private int objectNumber;

	public SingleCue() {
		setValue();
		setVertex0();
		setFace0();
		setNormal0();
		setColor0();
		setVertex1();
		setFace1();
		setNormal1();
		setColor1();
		setVertex2();
		setFace2();
		setNormal2();
		setColor2();
		setVertex3();
		setFace3();
		setNormal3();
		setColor3();
	}

	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glScalef(0.1f, 0.1f, 0.1f);
		gl.glFrontFace(GL10.GL_CCW);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		for (int i = 0; i < objectNumber; i++) {
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer[i]);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer[i]);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer[i]);
			gl.glDrawElements(GL10.GL_TRIANGLES, size[i], GL10.GL_UNSIGNED_BYTE, mIndexBuffer[i]);
		}
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}

	private FloatBuffer[] mVertexBuffer;
	private FloatBuffer[] mColorBuffer;
	private FloatBuffer[] mNormalBuffer;
	private ByteBuffer[] mIndexBuffer;

	public void setValue() {
		objectNumber = 4;
		size = new int[objectNumber];
		mVertexBuffer = new FloatBuffer[objectNumber];
		mIndexBuffer = new ByteBuffer[objectNumber];
		mNormalBuffer = new FloatBuffer[objectNumber];
		mColorBuffer = new FloatBuffer[objectNumber];
	}

	public void setVertex0() {
		float valueVertex[] = { 0.200012f, -6.4018f, 1.60038f, 0.200012f, -7.1018f, 1.70038f, 0.200012f, -7.1018f,
				1.70038f, 0.200012f, -6.4018f, 1.50038f, 0.200012f, -6.4018f, 1.60038f, 0.200012f, -7.1018f, 1.70038f,
				0.100012f, -6.5018f, 1.20038f, 0.100012f, -7.2018f, 1.30038f, 0.100012f, -7.2018f, 1.30038f, 0.100012f,
				-6.5018f, 1.20038f, 1.2207e-005f, -6.4018f, 1.20038f, -0.0999878f, -7.2018f, 1.40038f, 1.2207e-005f,
				-7.2018f, 1.40038f, -0.0999878f, -6.4018f, 1.30038f, -0.0999878f, -7.2018f, 1.50038f, -0.0999878f,
				-6.4018f, 1.30038f, 0.200012f, -6.5018f, 1.20038f, 0.200012f, -7.2018f, 1.40038f, 0.200012f, -7.2018f,
				1.40038f, 0.200012f, -6.5018f, 1.20038f, 1.2207e-005f, -7.2018f, 1.40038f, 1.2207e-005f, -6.5018f,
				1.20038f, 0.200012f, -7.2018f, 1.30038f, 0.200012f, -6.5018f, 1.20038f, -0.0999878f, -6.4018f,
				1.40038f, -0.0999878f, -7.2018f, 1.50038f, 0.300012f, -7.2018f, 1.40038f, 0.300012f, -6.4018f,
				1.30038f, 0.100012f, -6.4018f, 1.60038f, 0.100012f, -7.1018f, 1.70038f, 0.100012f, -6.4018f, 1.60038f,
				0.100012f, -7.1018f, 1.70038f, 1.2207e-005f, -6.4018f, 1.60038f, 1.2207e-005f, -7.1018f, 1.70038f,
				1.2207e-005f, -6.4018f, 1.50038f, 1.2207e-005f, -7.1018f, 1.70038f, -0.0999878f, -7.1018f, 1.60038f,
				-0.0999878f, -6.4018f, 1.50038f, -0.0999878f, -7.1018f, 1.60038f, -0.0999878f, -6.4018f, 1.40038f,
				0.300012f, -7.2018f, 1.50038f, 0.300012f, -6.4018f, 1.30038f, 0.300012f, -7.2018f, 1.50038f, 0.300012f,
				-6.4018f, 1.40038f, 0.300012f, -7.1018f, 1.60038f, 0.300012f, -6.4018f, 1.40038f, 0.300012f, -7.1018f,
				1.60038f, 0.300012f, -6.4018f, 1.50038f };
		ByteBuffer vbb = ByteBuffer.allocateDirect(valueVertex.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer[0] = vbb.asFloatBuffer();
		mVertexBuffer[0].put(valueVertex);
		mVertexBuffer[0].position(0);
	}

	public void setVertex1() {
		float valueVertex[] = { -0.499988f, -36.1018f, 7.80038f, 1.2207e-005f, -7.3018f, 1.40038f, -0.499988f,
				-36.2018f, 7.70038f, -0.0999878f, -7.3018f, 1.50038f, -0.599988f, -36.1018f, 8.00038f, -0.0999878f,
				-7.3018f, 1.50038f, -0.599988f, -36.1018f, 8.20038f, -0.0999878f, -7.2018f, 1.60038f, -0.599988f,
				-36.1018f, 8.10038f, -0.0999878f, -7.2018f, 1.60038f, 0.300012f, -7.2018f, 1.60038f, 0.300012f,
				-36.1018f, 8.10038f, 0.300012f, -7.3018f, 1.60038f, 0.300012f, -36.1018f, 8.20038f, 0.200012f,
				-7.3018f, 1.40038f, 0.300012f, -36.1018f, 7.80038f, 0.200012f, -36.2018f, 7.70038f, 0.300012f,
				-7.3018f, 1.50038f, 0.200012f, -7.2018f, 1.80038f, -0.0999878f, -36.0018f, 8.60038f, 1.2207e-005f,
				-36.0018f, 8.60038f, 0.100012f, -7.2018f, 1.80038f, -0.399988f, -36.2018f, 7.70038f, 1.2207e-005f,
				-7.3018f, 1.40038f, 1.2207e-005f, -7.2018f, 1.70038f, -0.499988f, -36.0018f, 8.40038f, -0.399988f,
				-36.0018f, 8.50038f, 1.2207e-005f, -7.2018f, 1.70038f, 0.300012f, -7.2018f, 1.70038f, 0.300012f,
				-36.0018f, 8.30038f, 0.100012f, -7.3018f, 1.30038f, 1.2207e-005f, -36.2018f, 7.60038f, -0.0999878f,
				-36.2018f, 7.60038f, 0.200012f, -7.3018f, 1.40038f, -0.299988f, -36.0018f, 8.60038f, 0.100012f,
				-7.2018f, 1.80038f, 0.200012f, -7.2018f, 1.70038f, 0.200012f, -36.0018f, 8.40038f, -0.499988f,
				-36.0018f, 8.30038f, -0.0999878f, -7.2018f, 1.70038f, 0.300012f, -36.1018f, 8.00038f, 0.300012f,
				-7.3018f, 1.50038f, 0.100012f, -36.0018f, 8.50038f, 0.200012f, -7.2018f, 1.70038f, -0.299988f,
				-36.2018f, 7.60038f, 0.100012f, -7.3018f, 1.40038f, 0.200012f, -7.3018f, 1.40038f, 0.100012f,
				-36.2018f, 7.70038f };
		ByteBuffer vbb = ByteBuffer.allocateDirect(valueVertex.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer[1] = vbb.asFloatBuffer();
		mVertexBuffer[1].put(valueVertex);
		mVertexBuffer[1].position(0);
	}

	public void setVertex2() {
		float valueVertex[] = { 0.300012f, -36.9018f, 8.10038f, 0.300012f, -36.8018f, 8.40038f, 0.300012f, -36.8018f,
				8.20038f, 0.300012f, -36.8018f, 8.50038f, 0.300012f, -36.9018f, 8.00038f, 0.200012f, -36.9018f,
				7.90038f, 0.200012f, -36.8018f, 8.60038f, 0.100012f, -36.9018f, 7.80038f, 0.100012f, -36.7018f,
				8.70038f, 1.2207e-005f, -36.7018f, 8.70038f, 1.2207e-005f, -36.9018f, 7.80038f, -0.0999878f, -36.9018f,
				7.80038f, -0.0999878f, -36.7018f, 8.70038f, -0.299988f, -36.9018f, 7.80038f, -0.299988f, -36.7018f,
				8.70038f, -0.399988f, -36.9018f, 7.80038f, -0.399988f, -36.7018f, 8.70038f, -0.499988f, -36.7018f,
				8.60038f, -0.499988f, -36.9018f, 7.90038f, -0.499988f, -36.8018f, 8.50038f, -0.499988f, -36.9018f,
				8.00038f, -0.599988f, -36.9018f, 8.10038f, -0.599988f, -36.8018f, 8.40038f, -0.599988f, -36.8018f,
				8.20038f, 0.200012f, -36.0018f, 8.40038f, 0.300012f, -36.8018f, 8.50038f, 0.300012f, -36.0018f,
				8.30038f, 0.200012f, -36.8018f, 8.60038f, 0.300012f, -36.8018f, 8.40038f, 0.300012f, -36.1018f,
				8.20038f, 0.300012f, -36.8018f, 8.20038f, 0.300012f, -36.1018f, 8.10038f, 0.300012f, -36.9018f,
				8.10038f, 0.300012f, -36.1018f, 8.00038f, 0.300012f, -36.9018f, 8.00038f, 0.300012f, -36.1018f,
				7.80038f, 0.200012f, -36.2018f, 7.70038f, 0.200012f, -36.9018f, 7.90038f, 0.100012f, -36.2018f,
				7.70038f, 0.100012f, -36.9018f, 7.80038f, 1.2207e-005f, -36.9018f, 7.80038f, 1.2207e-005f, -36.2018f,
				7.60038f, -0.0999878f, -36.2018f, 7.60038f, -0.0999878f, -36.9018f, 7.80038f, -0.299988f, -36.2018f,
				7.60038f, -0.299988f, -36.9018f, 7.80038f, -0.599988f, -36.8018f, 8.20038f, -0.599988f, -36.1018f,
				8.00038f, -0.599988f, -36.9018f, 8.10038f, -0.599988f, -36.1018f, 8.10038f, -0.599988f, -36.8018f,
				8.40038f, -0.599988f, -36.1018f, 8.20038f, -0.499988f, -36.8018f, 8.50038f, -0.499988f, -36.0018f,
				8.30038f, -0.499988f, -36.7018f, 8.60038f, -0.499988f, -36.0018f, 8.40038f, -0.399988f, -36.0018f,
				8.50038f, -0.399988f, -36.7018f, 8.70038f, -0.299988f, -36.7018f, 8.70038f, -0.299988f, -36.0018f,
				8.60038f, -0.0999878f, -36.0018f, 8.60038f, -0.0999878f, -36.7018f, 8.70038f, 0.100012f, -36.0018f,
				8.50038f, 1.2207e-005f, -36.7018f, 8.70038f, 0.100012f, -36.7018f, 8.70038f, 1.2207e-005f, -36.0018f,
				8.60038f, -0.399988f, -36.2018f, 7.70038f, -0.399988f, -36.9018f, 7.80038f, -0.499988f, -36.9018f,
				7.90038f, -0.499988f, -36.2018f, 7.70038f, -0.499988f, -36.1018f, 7.80038f, -0.499988f, -36.9018f,
				8.00038f, 0.300012f, -37.6018f, 8.50038f, 0.200012f, -37.5018f, 8.70038f, 0.100012f, -37.7018f,
				8.00038f, 1.2207e-005f, -37.7018f, 7.90038f, -0.599988f, -37.5018f, 8.50038f, -0.599988f, -37.6018f,
				8.40038f, 0.200012f, -37.7018f, 8.10038f, -0.599988f, -37.6018f, 8.30038f, -0.499988f, -37.5018f,
				8.80038f, -0.399988f, -37.5018f, 8.80038f, -0.499988f, -37.5018f, 8.70038f, -0.299988f, -37.5018f,
				8.90038f, -0.0999878f, -37.5018f, 8.90038f, 1.2207e-005f, -37.5018f, 8.90038f, 0.100012f, -37.5018f,
				8.80038f, 0.200012f, -37.5018f, 8.80038f, 0.300012f, -37.6018f, 8.40038f, 0.300012f, -37.6018f,
				8.30038f, 0.200012f, -37.6018f, 8.20038f, -0.0999878f, -37.7018f, 7.90038f, -0.499988f, -37.6018f,
				8.20038f, -0.499988f, -37.7018f, 8.10038f, -0.299988f, -37.7018f, 7.90038f, -0.399988f, -37.7018f,
				8.00038f };
		ByteBuffer vbb = ByteBuffer.allocateDirect(valueVertex.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer[2] = vbb.asFloatBuffer();
		mVertexBuffer[2].put(valueVertex);
		mVertexBuffer[2].position(0);
	}

	public void setVertex3() {
		float valueVertex[] = { -0.599988f, -37.5018f, 8.50038f, -1.09999f, -58.4018f, 13.1004f, -0.599988f, -37.6018f,
				8.40038f, -0.999988f, -58.4018f, 13.3004f, 0.400012f, -58.5018f, 12.9004f, 0.200012f, -37.6018f,
				8.20038f, 0.300012f, -58.5018f, 12.7004f, 0.300012f, -37.6018f, 8.30038f, 0.400012f, -58.4018f,
				13.1004f, 0.300012f, -37.6018f, 8.40038f, 0.400012f, -58.4018f, 13.3004f, 0.300012f, -37.6018f,
				8.50038f, 0.200012f, -37.5018f, 8.70038f, 0.300012f, -58.3018f, 13.5004f, 0.200012f, -37.5018f,
				8.80038f, 0.200012f, -58.3018f, 13.7004f, 0.100012f, -37.5018f, 8.80038f, 0.100012f, -58.3018f,
				13.8004f, 1.2207e-005f, -37.5018f, 8.90038f, -0.0999878f, -58.2018f, 13.9004f, -0.0999878f, -37.5018f,
				8.90038f, -0.299988f, -58.2018f, 13.9004f, -0.299988f, -37.5018f, 8.90038f, -0.499988f, -58.2018f,
				13.9004f, -0.399988f, -37.5018f, 8.80038f, -0.699988f, -58.2018f, 13.8004f, -0.599988f, -37.6018f,
				8.30038f, -1.09999f, -58.5018f, 12.9004f, -0.499988f, -37.6018f, 8.20038f, -0.999988f, -58.5018f,
				12.7004f, -0.499988f, -37.7018f, 8.10038f, -0.899988f, -58.5018f, 12.5004f, -0.399988f, -37.7018f,
				8.00038f, -0.699988f, -58.6018f, 12.4004f, -0.299988f, -37.7018f, 7.90038f, -0.499988f, -58.6018f,
				12.3004f, -0.0999878f, -37.7018f, 7.90038f, -0.299988f, -58.6018f, 12.3004f, 1.2207e-005f, -37.7018f,
				7.90038f, -0.0999878f, -58.6018f, 12.3004f, 0.100012f, -37.7018f, 8.00038f, 0.100012f, -58.6018f,
				12.4004f, 0.200012f, -58.5018f, 12.5004f, 0.200012f, -37.7018f, 8.10038f, -0.499988f, -37.5018f,
				8.80038f, -0.899988f, -58.3018f, 13.7004f, -0.999988f, -58.3018f, 13.5004f, -0.499988f, -37.5018f,
				8.70038f };
		ByteBuffer vbb = ByteBuffer.allocateDirect(valueVertex.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer[3] = vbb.asFloatBuffer();
		mVertexBuffer[3].put(valueVertex);
		mVertexBuffer[3].position(0);
	}

	public void setFace0() {
		byte valueFace[] = { (byte) 0, (byte) 1, (byte) 2, (byte) 1, (byte) 0, (byte) 3, (byte) 4, (byte) 2, (byte) 5,
				(byte) 2, (byte) 4, (byte) 0, (byte) 6, (byte) 7, (byte) 8, (byte) 7, (byte) 6, (byte) 9, (byte) 10,
				(byte) 11, (byte) 12, (byte) 11, (byte) 10, (byte) 13, (byte) 13, (byte) 14, (byte) 11, (byte) 14,
				(byte) 13, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 17, (byte) 16, (byte) 19, (byte) 9,
				(byte) 20, (byte) 7, (byte) 20, (byte) 9, (byte) 21, (byte) 19, (byte) 22, (byte) 17, (byte) 22,
				(byte) 19, (byte) 23, (byte) 24, (byte) 14, (byte) 15, (byte) 14, (byte) 24, (byte) 25, (byte) 23,
				(byte) 8, (byte) 22, (byte) 8, (byte) 23, (byte) 6, (byte) 21, (byte) 12, (byte) 20, (byte) 12,
				(byte) 21, (byte) 10, (byte) 26, (byte) 16, (byte) 18, (byte) 16, (byte) 26, (byte) 27, (byte) 28,
				(byte) 5, (byte) 29, (byte) 5, (byte) 28, (byte) 4, (byte) 30, (byte) 29, (byte) 31, (byte) 29,
				(byte) 30, (byte) 28, (byte) 32, (byte) 31, (byte) 33, (byte) 31, (byte) 32, (byte) 30, (byte) 34,
				(byte) 33, (byte) 35, (byte) 33, (byte) 34, (byte) 32, (byte) 34, (byte) 36, (byte) 37, (byte) 36,
				(byte) 34, (byte) 35, (byte) 37, (byte) 38, (byte) 39, (byte) 38, (byte) 37, (byte) 36, (byte) 39,
				(byte) 25, (byte) 24, (byte) 25, (byte) 39, (byte) 38, (byte) 40, (byte) 27, (byte) 26, (byte) 27,
				(byte) 40, (byte) 41, (byte) 42, (byte) 41, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44,
				(byte) 43, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 45, (byte) 44, (byte) 45,
				(byte) 46, (byte) 47, (byte) 1, (byte) 47, (byte) 46, (byte) 47, (byte) 1, (byte) 3 };
		size[0] = valueFace.length;

		mIndexBuffer[0] = ByteBuffer.allocateDirect(valueFace.length);
		mIndexBuffer[0].put(valueFace);
		mIndexBuffer[0].position(0);
	}

	public void setFace1() {
		byte valueFace[] = { (byte) 0, (byte) 1, (byte) 2, (byte) 1, (byte) 0, (byte) 3, (byte) 4, (byte) 3, (byte) 0,
				(byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 7, (byte) 6, (byte) 9, (byte) 10,
				(byte) 11, (byte) 12, (byte) 11, (byte) 10, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 15,
				(byte) 14, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 19, (byte) 18, (byte) 21, (byte) 1,
				(byte) 22, (byte) 2, (byte) 22, (byte) 1, (byte) 23, (byte) 8, (byte) 5, (byte) 4, (byte) 5, (byte) 8,
				(byte) 7, (byte) 24, (byte) 25, (byte) 26, (byte) 25, (byte) 24, (byte) 27, (byte) 28, (byte) 13,
				(byte) 10, (byte) 13, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 31, (byte) 30,
				(byte) 33, (byte) 21, (byte) 34, (byte) 19, (byte) 34, (byte) 21, (byte) 35, (byte) 36, (byte) 29,
				(byte) 28, (byte) 29, (byte) 36, (byte) 37, (byte) 38, (byte) 27, (byte) 39, (byte) 27, (byte) 38,
				(byte) 25, (byte) 17, (byte) 40, (byte) 15, (byte) 40, (byte) 17, (byte) 41, (byte) 35, (byte) 26,
				(byte) 34, (byte) 26, (byte) 35, (byte) 24, (byte) 36, (byte) 42, (byte) 37, (byte) 42, (byte) 36,
				(byte) 43, (byte) 43, (byte) 20, (byte) 42, (byte) 20, (byte) 43, (byte) 18, (byte) 41, (byte) 11,
				(byte) 40, (byte) 11, (byte) 41, (byte) 12, (byte) 23, (byte) 44, (byte) 22, (byte) 44, (byte) 23,
				(byte) 45, (byte) 46, (byte) 16, (byte) 47, (byte) 16, (byte) 46, (byte) 14, (byte) 45, (byte) 32,
				(byte) 44, (byte) 32, (byte) 45, (byte) 30, (byte) 6, (byte) 39, (byte) 9, (byte) 39, (byte) 6,
				(byte) 38, (byte) 33, (byte) 47, (byte) 31, (byte) 47, (byte) 33, (byte) 46 };
		size[1] = valueFace.length;

		mIndexBuffer[1] = ByteBuffer.allocateDirect(valueFace.length);
		mIndexBuffer[1].put(valueFace);
		mIndexBuffer[1].position(0);
	}

	public void setFace2() {
		byte valueFace[] = { (byte) 0, (byte) 1, (byte) 2, (byte) 1, (byte) 0, (byte) 3, (byte) 3, (byte) 0, (byte) 4,
				(byte) 3, (byte) 4, (byte) 5, (byte) 3, (byte) 5, (byte) 6, (byte) 6, (byte) 5, (byte) 7, (byte) 6,
				(byte) 7, (byte) 8, (byte) 8, (byte) 7, (byte) 9, (byte) 9, (byte) 7, (byte) 10, (byte) 9, (byte) 10,
				(byte) 11, (byte) 9, (byte) 11, (byte) 12, (byte) 12, (byte) 11, (byte) 13, (byte) 12, (byte) 13,
				(byte) 14, (byte) 14, (byte) 13, (byte) 15, (byte) 14, (byte) 15, (byte) 16, (byte) 16, (byte) 15,
				(byte) 17, (byte) 17, (byte) 15, (byte) 18, (byte) 17, (byte) 18, (byte) 19, (byte) 19, (byte) 18,
				(byte) 20, (byte) 19, (byte) 20, (byte) 21, (byte) 19, (byte) 21, (byte) 22, (byte) 22, (byte) 21,
				(byte) 23, (byte) 24, (byte) 25, (byte) 26, (byte) 25, (byte) 24, (byte) 27, (byte) 26, (byte) 28,
				(byte) 29, (byte) 28, (byte) 26, (byte) 25, (byte) 29, (byte) 30, (byte) 31, (byte) 30, (byte) 29,
				(byte) 28, (byte) 31, (byte) 32, (byte) 33, (byte) 32, (byte) 31, (byte) 30, (byte) 33, (byte) 34,
				(byte) 35, (byte) 34, (byte) 33, (byte) 32, (byte) 36, (byte) 34, (byte) 37, (byte) 34, (byte) 36,
				(byte) 35, (byte) 38, (byte) 37, (byte) 39, (byte) 37, (byte) 38, (byte) 36, (byte) 40, (byte) 38,
				(byte) 39, (byte) 38, (byte) 40, (byte) 41, (byte) 42, (byte) 40, (byte) 43, (byte) 40, (byte) 42,
				(byte) 41, (byte) 44, (byte) 43, (byte) 45, (byte) 43, (byte) 44, (byte) 42, (byte) 46, (byte) 47,
				(byte) 48, (byte) 47, (byte) 46, (byte) 49, (byte) 50, (byte) 49, (byte) 46, (byte) 49, (byte) 50,
				(byte) 51, (byte) 52, (byte) 51, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 53,
				(byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 54, (byte) 57, (byte) 54, (byte) 56,
				(byte) 55, (byte) 58, (byte) 56, (byte) 57, (byte) 56, (byte) 58, (byte) 59, (byte) 60, (byte) 58,
				(byte) 61, (byte) 58, (byte) 60, (byte) 59, (byte) 62, (byte) 63, (byte) 64, (byte) 63, (byte) 62,
				(byte) 65, (byte) 27, (byte) 62, (byte) 64, (byte) 62, (byte) 27, (byte) 24, (byte) 66, (byte) 45,
				(byte) 67, (byte) 45, (byte) 66, (byte) 44, (byte) 68, (byte) 66, (byte) 67, (byte) 66, (byte) 68,
				(byte) 69, (byte) 68, (byte) 70, (byte) 69, (byte) 70, (byte) 68, (byte) 71, (byte) 48, (byte) 70,
				(byte) 71, (byte) 70, (byte) 48, (byte) 47, (byte) 65, (byte) 61, (byte) 63, (byte) 61, (byte) 65,
				(byte) 60, (byte) 25, (byte) 72, (byte) 28, (byte) 72, (byte) 25, (byte) 73, (byte) 40, (byte) 74,
				(byte) 75, (byte) 74, (byte) 40, (byte) 39, (byte) 76, (byte) 46, (byte) 77, (byte) 46, (byte) 76,
				(byte) 50, (byte) 39, (byte) 78, (byte) 74, (byte) 78, (byte) 39, (byte) 37, (byte) 77, (byte) 48,
				(byte) 79, (byte) 48, (byte) 77, (byte) 46, (byte) 57, (byte) 80, (byte) 81, (byte) 80, (byte) 57,
				(byte) 54, (byte) 82, (byte) 50, (byte) 76, (byte) 50, (byte) 82, (byte) 52, (byte) 58, (byte) 81,
				(byte) 83, (byte) 81, (byte) 58, (byte) 57, (byte) 63, (byte) 84, (byte) 85, (byte) 84, (byte) 63,
				(byte) 61, (byte) 27, (byte) 86, (byte) 87, (byte) 86, (byte) 27, (byte) 64, (byte) 27, (byte) 73,
				(byte) 25, (byte) 73, (byte) 27, (byte) 87, (byte) 28, (byte) 88, (byte) 30, (byte) 88, (byte) 28,
				(byte) 72, (byte) 30, (byte) 89, (byte) 32, (byte) 89, (byte) 30, (byte) 88, (byte) 32, (byte) 90,
				(byte) 34, (byte) 90, (byte) 32, (byte) 89, (byte) 34, (byte) 78, (byte) 37, (byte) 78, (byte) 34,
				(byte) 90, (byte) 43, (byte) 75, (byte) 91, (byte) 75, (byte) 43, (byte) 40, (byte) 92, (byte) 68,
				(byte) 93, (byte) 68, (byte) 92, (byte) 71, (byte) 79, (byte) 71, (byte) 92, (byte) 71, (byte) 79,
				(byte) 48, (byte) 67, (byte) 94, (byte) 95, (byte) 94, (byte) 67, (byte) 45, (byte) 45, (byte) 91,
				(byte) 94, (byte) 91, (byte) 45, (byte) 43, (byte) 68, (byte) 95, (byte) 93, (byte) 95, (byte) 68,
				(byte) 67, (byte) 64, (byte) 85, (byte) 86, (byte) 85, (byte) 64, (byte) 63, (byte) 80, (byte) 52,
				(byte) 82, (byte) 52, (byte) 80, (byte) 54, (byte) 61, (byte) 83, (byte) 84, (byte) 83, (byte) 61,
				(byte) 58 };
		size[2] = valueFace.length;

		mIndexBuffer[2] = ByteBuffer.allocateDirect(valueFace.length);
		mIndexBuffer[2].put(valueFace);
		mIndexBuffer[2].position(0);
	}

	public void setFace3() {
		byte valueFace[] = { (byte) 0, (byte) 1, (byte) 2, (byte) 1, (byte) 0, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
				(byte) 5, (byte) 4, (byte) 7, (byte) 8, (byte) 7, (byte) 4, (byte) 7, (byte) 8, (byte) 9, (byte) 10,
				(byte) 9, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 10, (byte) 12, (byte) 11, (byte) 12,
				(byte) 10, (byte) 13, (byte) 13, (byte) 14, (byte) 12, (byte) 14, (byte) 13, (byte) 15, (byte) 16,
				(byte) 15, (byte) 17, (byte) 15, (byte) 16, (byte) 14, (byte) 18, (byte) 17, (byte) 19, (byte) 17,
				(byte) 18, (byte) 16, (byte) 20, (byte) 19, (byte) 21, (byte) 19, (byte) 20, (byte) 18, (byte) 22,
				(byte) 21, (byte) 23, (byte) 21, (byte) 22, (byte) 20, (byte) 24, (byte) 23, (byte) 25, (byte) 23,
				(byte) 24, (byte) 22, (byte) 26, (byte) 1, (byte) 27, (byte) 1, (byte) 26, (byte) 2, (byte) 28,
				(byte) 27, (byte) 29, (byte) 27, (byte) 28, (byte) 26, (byte) 30, (byte) 29, (byte) 31, (byte) 29,
				(byte) 30, (byte) 28, (byte) 32, (byte) 31, (byte) 33, (byte) 31, (byte) 32, (byte) 30, (byte) 34,
				(byte) 33, (byte) 35, (byte) 33, (byte) 34, (byte) 32, (byte) 36, (byte) 35, (byte) 37, (byte) 35,
				(byte) 36, (byte) 34, (byte) 38, (byte) 37, (byte) 39, (byte) 37, (byte) 38, (byte) 36, (byte) 40,
				(byte) 39, (byte) 41, (byte) 39, (byte) 40, (byte) 38, (byte) 42, (byte) 40, (byte) 41, (byte) 40,
				(byte) 42, (byte) 43, (byte) 6, (byte) 43, (byte) 42, (byte) 43, (byte) 6, (byte) 5, (byte) 44,
				(byte) 25, (byte) 45, (byte) 25, (byte) 44, (byte) 24, (byte) 44, (byte) 46, (byte) 47, (byte) 46,
				(byte) 44, (byte) 45, (byte) 47, (byte) 3, (byte) 0, (byte) 3, (byte) 47, (byte) 46 };
		size[3] = valueFace.length;

		mIndexBuffer[3] = ByteBuffer.allocateDirect(valueFace.length);
		mIndexBuffer[3].put(valueFace);
		mIndexBuffer[3].position(0);
	}

	public void setNormal0() {
		float valueNormal[] = { -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -1f, -0.274721f, -0.961523f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, 1.70436f, 0.33058f, 1.6745f, 1.70436f, 0.33058f, 1.6745f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -1.74484f, 0.357481f, 1.62361f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, 1f, -0.282842f, -1.9799f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -1.70353f, -0.375225f, -1.66505f };

		ByteBuffer nbb = ByteBuffer.allocateDirect(valueNormal.length * 4);
		nbb.order(ByteOrder.nativeOrder());
		mNormalBuffer[0] = nbb.asFloatBuffer();
		mNormalBuffer[0].put(valueNormal);
		mNormalBuffer[0].position(0);
	}

	public void setNormal1() {
		float valueNormal[] = { -2.59078f, -0.214256f, -1.1596f, -2147.48f, -2147.48f, -2147.48f, -0.999798f,
				-0.198789f, -0.991255f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2.9995f,
				0.048778f, -0.014121f, -2147.48f, -2147.48f, -2147.48f, 3f, 0f, 0f, 4f, 0f, 0f, 2f, 0f, 0f, 3f, 0f, 0f,
				-2147.48f, -2147.48f, -2147.48f, 2.46439f, -0.289884f, -1.32736f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, 0.999975f, 0.452641f, 1.94648f, 0f, 0.689378f, 2.91972f, 1.69771f,
				0.385167f, 1.67097f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2.08894f,
				0.522232f, 2.08893f, -2147.48f, -2147.48f, -2147.48f, 2f, 0.223376f, 0.974732f, 1.69773f, 0.385695f,
				1.67246f, -0.301381f, -0.360776f, -1.67565f, 1.39719f, -0.522402f, -2.37424f, -0.999976f, -0.41583f,
				-1.95481f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, 1.39546f, 0.324638f, 1.39546f, -1.77074f, 0.393171f,
				1.59319f, -1.77074f, 0.393111f, 1.59319f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -0.699851f, -0.562396f,
				-2.65536f, -0.999976f, -0.4126f, -1.95551f, -2147.48f, -2147.48f, -2147.48f, -2147.48f, -2147.48f,
				-2147.48f };

		ByteBuffer nbb = ByteBuffer.allocateDirect(valueNormal.length * 4);
		nbb.order(ByteOrder.nativeOrder());
		mNormalBuffer[1] = nbb.asFloatBuffer();
		mNormalBuffer[1].put(valueNormal);
		mNormalBuffer[1].position(0);
	}

	public void setNormal2() {
		float valueNormal[] = { -1f, 0f, 0f, -2f, 0f, 0f, -1f, 0f, 0f, 0.052422f, 1.94244f, -0.332485f, 1.19245f,
				0.962248f, -0.192454f, 0.192453f, 2.92264f, -0.472516f, 0.604699f, 2.73788f, -0.452837f, 0.744731f,
				3.71006f, -0.746669f, 0.6047f, 1.75367f, -0.389706f, 0f, 3.90475f, -0.867725f, 0f, 1.95237f,
				-0.433862f, 0f, 2.92856f, -0.650794f, 0f, 2.92856f, -0.650794f, 0f, 2.92856f, -0.650794f, 0f, 2.92856f,
				-0.650794f, -0.052907f, 3.83354f, -0.91077f, 0.211999f, 1.93018f, -0.428931f, -1.05291f, 1.88117f,
				-0.476907f, -2.26491f, 0.927172f, -0.264907f, -1.89094f, 1.86678f, -0.493971f, -1.19245f, 0.962248f,
				-0.192454f, 1.10906f, 1.86678f, -0.493971f, 1.30152f, 0.904529f, -0.301517f, 1f, 0f, 0f, 2.08893f,
				0.522234f, 2.08893f, 4.99057f, 0.448659f, 2.54336f, 2.69631f, 0.174078f, 0.696312f, 4.61847f,
				0.978915f, 4.32446f, 6f, 0f, 0f, 3f, 0f, 0f, 6f, 0f, 0f, 3f, 0f, 0f, 5.68305f, -0.339451f, -0.693101f,
				3f, 0f, 0f, 6.13876f, -0.777595f, -1.89686f, 1.77152f, -0.154302f, -0.617211f, 1.46462f, -0.627052f,
				-2.27184f, 2.78925f, -0.921411f, -3.75078f, 1.39663f, -0.714676f, -3.3481f, 2.09615f, -0.832616f,
				-4.05624f, 1.39746f, -1.34351f, -6.27288f, 0.693102f, -0.47275f, -1.65463f, 0f, -0.824163f, -2.88457f,
				0f, -1.19627f, -5.8614f, -0.693102f, -0.747472f, -2.61615f, -0.693102f, -0.986276f, -5.62141f, -6f, 0f,
				0f, -2.89303f, -0.055814f, -0.446516f, -5.28245f, -0.42792f, -1.83593f, -3f, 0f, 0f, -5.27882f,
				0.459815f, 1.83251f, -2.46462f, 0.352332f, 1.31032f, -5.15772f, 0.55036f, 2.00342f, -2.77152f,
				0.154303f, 0.617213f, -5.08251f, 0.570137f, 2.08252f, -1.6931f, 0.198029f, 0.693101f, -2.08973f,
				0.771282f, 3.05126f, -1.38941f, 0.894898f, 4.3355f, -1.39984f, 1.19871f, 6.30368f, -0.703527f,
				0.241923f, 1.69347f, 0f, 0.424261f, 2.96985f, 0f, 1.15187f, 5.88028f, 2.17732f, 0.722075f, 2.96607f,
				0.703527f, 1.24425f, 5.56543f, 2.22668f, 0.860837f, 3.90283f, 0.703527f, 0.383343f, 2.68342f,
				-1.39663f, -0.714676f, -3.3481f, -2.09615f, -0.714116f, -4.07837f, -6.09615f, -0.72338f, -3.05767f,
				-1f, -0.274721f, -0.961523f, -3.58934f, -0.229891f, -1.14283f, -4.08252f, -0.570136f, -2.08251f,
				2.89443f, 0f, 0.447213f, 2.59795f, 0.100502f, 1.15074f, 1.40067f, -0.504658f, -2.37081f, 0.70436f,
				-0.336114f, -2.68892f, -2.89261f, 0.063757f, 0.446304f, -3f, 0f, 0f, 3.07681f, -0.631997f, -1.97916f,
				-2.38621f, -0.396059f, -1.3862f, -2.69631f, 0.298113f, 1.68859f, -0.696308f, 0.422148f, 2.68087f,
				-2.58571f, 0.261786f, 1.13941f, -0.696308f, 0.659149f, 2.6366f, 0f, 0.727606f, 2.91043f, 0.704358f,
				0.573117f, 2.64465f, 1.4492f, 0.445527f, 2.32797f, 1f, 0.27472f, 0.961524f, 3f, 0f, 0f, 2.6931f,
				-0.198029f, -0.693101f, 2.67093f, -0.449215f, -0.583334f, 0f, -0.372104f, -2.97683f, -2.6931f,
				-0.198029f, -0.693102f, -1.69631f, -0.174077f, -0.696309f, -0.69631f, -0.422147f, -2.68087f, -2.08893f,
				-0.522233f, -2.08893f };

		ByteBuffer nbb = ByteBuffer.allocateDirect(valueNormal.length * 4);
		nbb.order(ByteOrder.nativeOrder());
		mNormalBuffer[2] = nbb.asFloatBuffer();
		mNormalBuffer[2].put(valueNormal);
		mNormalBuffer[2].position(0);
	}

	public void setNormal3() {
		float valueNormal[] = { 2.77576f, -0.257553f, -0.868474f, 3.88711f, -0.184153f, -0.413664f, 1.99933f,
				-0.043631f, 0.0196f, 2.77586f, -0.259498f, -0.877301f, -2.5897f, 0.237826f, 1.14654f, -2.5897f,
				0.238199f, 1.14832f, -2.7811f, 0.181868f, 0.894495f, -2.69903f, 0.141395f, 0.701205f, -2.99996f,
				-0.013925f, 0.002155f, -2.99996f, -0.014422f, 0f, -3.81768f, -0.205488f, -0.810915f, -1.90884f,
				-0.102744f, -0.405458f, -2.81769f, -0.200681f, -0.810915f, -2.79835f, -0.207517f, -0.850209f,
				-1.88949f, -0.338881f, -1.41811f, -1.58661f, -0.501647f, -2.11521f, -1.39421f, -0.564448f, -2.36757f,
				-1.9102f, -0.533915f, -2.22743f, -1.21309f, -0.601135f, -2.50237f, -0.515987f, -0.668354f, -2.77731f,
				0f, -0.70438f, -2.91614f, 0f, -0.70438f, -2.91614f, 0.696206f, -0.64448f, -2.6403f, 1.13274f, -0.6269f,
				-2.54133f, 1.13274f, -0.6269f, -2.54133f, 0.954488f, -0.657576f, -2.67546f, 2.69982f, 0.091821f,
				0.711171f, 2.59204f, 0.190536f, 1.15714f, 2.59194f, 0.192404f, 1.16604f, 2.7833f, 0.132177f, 0.911523f,
				2.59203f, 0.192236f, 1.16621f, 2.09997f, 0.382434f, 1.99112f, 1.90861f, 0.442661f, 2.24564f, 1.64778f,
				0.488902f, 2.42464f, 1.14023f, 0.524392f, 2.55861f, 0.439826f, 0.593003f, 2.83676f, 0f, 0.618031f,
				2.93565f, 0f, 0.618031f, 2.93565f, -0.699051f, 0.562534f, 2.65615f, -1.13854f, 0.541572f, 2.55658f,
				-2.60199f, 0.616387f, 2.90859f, -1.20361f, 0.317939f, 1.51021f, -2.3539f, 0.375007f, 1.77578f,
				-2.58976f, 0.238179f, 1.14849f, 2.40545f, -0.586038f, -2.24628f, 1.40574f, -0.327213f, -1.27423f,
				2.8872f, -0.16722f, -0.433119f, 2.8875f, -0.164553f, -0.433264f };

		ByteBuffer nbb = ByteBuffer.allocateDirect(valueNormal.length * 4);
		nbb.order(ByteOrder.nativeOrder());
		mNormalBuffer[3] = nbb.asFloatBuffer();
		mNormalBuffer[3].put(valueNormal);
		mNormalBuffer[3].position(0);
	}

	public void setColor0() {
		float valueColor[] = { 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[0] = cbb.asFloatBuffer();
		mColorBuffer[0].put(valueColor);
		mColorBuffer[0].position(0);
	}

	public void setColor1() {
		float valueColor[] = { 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f, 0.699999f, 0.5f, 1.0f, 0.899999f,
				0.699999f, 0.5f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[1] = cbb.asFloatBuffer();
		mColorBuffer[1].put(valueColor);
		mColorBuffer[1].position(0);
	}

	public void setColor2() {
		float valueColor[] = { 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[2] = cbb.asFloatBuffer();
		mColorBuffer[2].put(valueColor);
		mColorBuffer[2].position(0);
	}

	public void setColor3() {
		float valueColor[] = { 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f,
				1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f,
				0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f,
				0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f,
				0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f, 0f, 0f, 0f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[3] = cbb.asFloatBuffer();
		mColorBuffer[3].put(valueColor);
		mColorBuffer[3].position(0);
	}

	@Override
	public void draw(GL10 gl, int variable) {
		// TODO Auto-generated method stub
		
	}
}
