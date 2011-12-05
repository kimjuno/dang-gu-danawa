package DangGuDANAWA.Object3D.Object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

import DangGuDANAWA.Object3D.Interface.Object3D;

public class Ball implements Object3D{
	private int size[];
	private int objectNumber;

	public Ball() {
		setValue();
		setVertex0();
		setFace0();
		setNormal0();
		setColor0();
		setColor1();
		setColor2();
	}

	public void draw(GL10 gl, int variable) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glScalef(0.0225f, 0.0225f, 0.0225f);
		gl.glFrontFace(GL10.GL_CCW);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer[0]);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer[variable]);
		gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer[0]);
		gl.glDrawElements(GL10.GL_TRIANGLES, size[0], GL10.GL_UNSIGNED_BYTE, mIndexBuffer[0]);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

	}

	private FloatBuffer[] mVertexBuffer;
	private FloatBuffer[] mColorBuffer;
	private FloatBuffer[] mNormalBuffer;
	private ByteBuffer[] mIndexBuffer;

	public void setValue() {
		objectNumber = 1;
		size = new int[objectNumber];
		mVertexBuffer = new FloatBuffer[objectNumber];
		mIndexBuffer = new ByteBuffer[objectNumber];
		mNormalBuffer = new FloatBuffer[objectNumber];
		mColorBuffer = new FloatBuffer[3];
	}

	public void setVertex0() {
		float valueVertex[] = { 0f, -0f, 5f, 0f, 1.9f, 4.6f, -0.7f, 1.8f, 4.6f, -1.4f, 1.3f, 4.6f, -1.8f, 0.7f, 4.6f,
				-1.9f, -0f, 4.6f, -1.8f, -0.8f, 4.6f, -1.4f, -1.4f, 4.6f, -0.7f, -1.8f, 4.6f, 0f, -1.9f, 4.6f, 0.7f,
				-1.8f, 4.6f, 1.4f, -1.4f, 4.6f, 1.8f, -0.8f, 4.6f, 1.9f, -0f, 4.6f, 1.8f, 0.7f, 4.6f, 1.4f, 1.3f, 4.6f,
				0.7f, 1.8f, 4.6f, 0f, 3.5f, 3.6f, -1.4f, 3.3f, 3.6f, -2.5f, 2.5f, 3.6f, -3.3f, 1.3f, 3.6f, -3.6f, -0f,
				3.6f, -3.3f, -1.4f, 3.6f, -2.5f, -2.5f, 3.6f, -1.4f, -3.3f, 3.6f, 0f, -3.6f, 3.6f, 1.4f, -3.3f, 3.6f,
				2.5f, -2.5f, 3.6f, 3.3f, -1.4f, 3.6f, 3.6f, -0f, 3.6f, 3.3f, 1.3f, 3.6f, 2.5f, 2.5f, 3.6f, 1.4f, 3.3f,
				3.6f, 0f, 4.6f, 1.9f, -1.8f, 4.3f, 1.9f, -3.3f, 3.3f, 1.9f, -4.3f, 1.8f, 1.9f, -4.6f, -0f, 1.9f, -4.3f,
				-1.8f, 1.9f, -3.3f, -3.3f, 1.9f, -1.8f, -4.3f, 1.9f, 0f, -4.7f, 1.9f, 1.8f, -4.3f, 1.9f, 3.3f, -3.3f,
				1.9f, 4.3f, -1.8f, 1.9f, 4.6f, -0f, 1.9f, 4.3f, 1.8f, 1.9f, 3.3f, 3.3f, 1.9f, 1.8f, 4.3f, 1.9f, 0f, 5f,
				0f, -1.9f, 4.6f, 0f, -3.6f, 3.5f, 0f, -4.6f, 1.9f, 0f, -5f, -0f, 0f, -4.6f, -1.9f, 0f, -3.6f, -3.6f,
				0f, -1.9f, -4.7f, 0f, 0f, -5.1f, 0f, 1.9f, -4.7f, 0f, 3.6f, -3.6f, 0f, 4.6f, -1.9f, 0f, 5f, -0f, 0f,
				4.6f, 1.9f, 0f, 3.6f, 3.5f, 0f, 1.9f, 4.6f, 0f, 0f, 4.6f, -1.9f, -1.8f, 4.3f, -1.9f, -3.3f, 3.3f,
				-1.9f, -4.3f, 1.8f, -1.9f, -4.6f, -0f, -1.9f, -4.3f, -1.8f, -1.9f, -3.3f, -3.3f, -1.9f, -1.8f, -4.3f,
				-1.9f, 0f, -4.7f, -1.9f, 1.8f, -4.3f, -1.9f, 3.3f, -3.3f, -1.9f, 4.3f, -1.8f, -1.9f, 4.6f, -0f, -1.9f,
				4.3f, 1.8f, -1.9f, 3.3f, 3.3f, -1.9f, 1.8f, 4.3f, -1.9f, 0f, 3.5f, -3.6f, -1.4f, 3.3f, -3.6f, -2.5f,
				2.5f, -3.6f, -3.3f, 1.3f, -3.6f, -3.6f, -0f, -3.6f, -3.3f, -1.4f, -3.6f, -2.5f, -2.5f, -3.6f, -1.4f,
				-3.3f, -3.6f, 0f, -3.6f, -3.6f, 1.4f, -3.3f, -3.6f, 2.5f, -2.5f, -3.6f, 3.3f, -1.4f, -3.6f, 3.6f, -0f,
				-3.6f, 3.3f, 1.3f, -3.6f, 2.5f, 2.5f, -3.6f, 1.4f, 3.3f, -3.6f, 0f, 1.9f, -4.6f, -0.7f, 1.8f, -4.6f,
				-1.4f, 1.3f, -4.6f, -1.8f, 0.7f, -4.6f, -1.9f, -0f, -4.6f, -1.8f, -0.8f, -4.6f, -1.4f, -1.4f, -4.6f,
				-0.7f, -1.8f, -4.6f, 0f, -1.9f, -4.6f, 0.7f, -1.8f, -4.6f, 1.4f, -1.4f, -4.6f, 1.8f, -0.8f, -4.6f,
				1.9f, -0f, -4.6f, 1.8f, 0.7f, -4.6f, 1.4f, 1.3f, -4.6f, 0.7f, 1.8f, -4.6f, 0f, -0f, -5f };
		ByteBuffer vbb = ByteBuffer.allocateDirect(valueVertex.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer[0] = vbb.asFloatBuffer();
		mVertexBuffer[0].put(valueVertex);
		mVertexBuffer[0].position(0);
	}

	public void setFace0() {
		byte valueFace[] = { (byte) 0, (byte) 1, (byte) 2, (byte) 0, (byte) 2, (byte) 3, (byte) 0, (byte) 3, (byte) 4,
				(byte) 0, (byte) 4, (byte) 5, (byte) 0, (byte) 5, (byte) 6, (byte) 0, (byte) 6, (byte) 7, (byte) 0,
				(byte) 7, (byte) 8, (byte) 0, (byte) 8, (byte) 9, (byte) 0, (byte) 9, (byte) 10, (byte) 0, (byte) 10,
				(byte) 11, (byte) 0, (byte) 11, (byte) 12, (byte) 0, (byte) 12, (byte) 13, (byte) 0, (byte) 13,
				(byte) 14, (byte) 0, (byte) 14, (byte) 15, (byte) 0, (byte) 15, (byte) 16, (byte) 0, (byte) 16,
				(byte) 1, (byte) 18, (byte) 2, (byte) 1, (byte) 1, (byte) 17, (byte) 18, (byte) 19, (byte) 3, (byte) 2,
				(byte) 2, (byte) 18, (byte) 19, (byte) 20, (byte) 4, (byte) 3, (byte) 3, (byte) 19, (byte) 20,
				(byte) 21, (byte) 5, (byte) 4, (byte) 4, (byte) 20, (byte) 21, (byte) 22, (byte) 6, (byte) 5, (byte) 5,
				(byte) 21, (byte) 22, (byte) 23, (byte) 7, (byte) 6, (byte) 6, (byte) 22, (byte) 23, (byte) 24,
				(byte) 8, (byte) 7, (byte) 7, (byte) 23, (byte) 24, (byte) 25, (byte) 9, (byte) 8, (byte) 8, (byte) 24,
				(byte) 25, (byte) 26, (byte) 10, (byte) 9, (byte) 9, (byte) 25, (byte) 26, (byte) 27, (byte) 11,
				(byte) 10, (byte) 10, (byte) 26, (byte) 27, (byte) 28, (byte) 12, (byte) 11, (byte) 11, (byte) 27,
				(byte) 28, (byte) 29, (byte) 13, (byte) 12, (byte) 12, (byte) 28, (byte) 29, (byte) 30, (byte) 14,
				(byte) 13, (byte) 13, (byte) 29, (byte) 30, (byte) 31, (byte) 15, (byte) 14, (byte) 14, (byte) 30,
				(byte) 31, (byte) 32, (byte) 16, (byte) 15, (byte) 15, (byte) 31, (byte) 32, (byte) 17, (byte) 1,
				(byte) 16, (byte) 16, (byte) 32, (byte) 17, (byte) 34, (byte) 18, (byte) 17, (byte) 17, (byte) 33,
				(byte) 34, (byte) 35, (byte) 19, (byte) 18, (byte) 18, (byte) 34, (byte) 35, (byte) 36, (byte) 20,
				(byte) 19, (byte) 19, (byte) 35, (byte) 36, (byte) 37, (byte) 21, (byte) 20, (byte) 20, (byte) 36,
				(byte) 37, (byte) 38, (byte) 22, (byte) 21, (byte) 21, (byte) 37, (byte) 38, (byte) 39, (byte) 23,
				(byte) 22, (byte) 22, (byte) 38, (byte) 39, (byte) 40, (byte) 24, (byte) 23, (byte) 23, (byte) 39,
				(byte) 40, (byte) 41, (byte) 25, (byte) 24, (byte) 24, (byte) 40, (byte) 41, (byte) 42, (byte) 26,
				(byte) 25, (byte) 25, (byte) 41, (byte) 42, (byte) 43, (byte) 27, (byte) 26, (byte) 26, (byte) 42,
				(byte) 43, (byte) 44, (byte) 28, (byte) 27, (byte) 27, (byte) 43, (byte) 44, (byte) 45, (byte) 29,
				(byte) 28, (byte) 28, (byte) 44, (byte) 45, (byte) 46, (byte) 30, (byte) 29, (byte) 29, (byte) 45,
				(byte) 46, (byte) 47, (byte) 31, (byte) 30, (byte) 30, (byte) 46, (byte) 47, (byte) 48, (byte) 32,
				(byte) 31, (byte) 31, (byte) 47, (byte) 48, (byte) 33, (byte) 17, (byte) 32, (byte) 32, (byte) 48,
				(byte) 33, (byte) 50, (byte) 34, (byte) 33, (byte) 33, (byte) 49, (byte) 50, (byte) 51, (byte) 35,
				(byte) 34, (byte) 34, (byte) 50, (byte) 51, (byte) 52, (byte) 36, (byte) 35, (byte) 35, (byte) 51,
				(byte) 52, (byte) 53, (byte) 37, (byte) 36, (byte) 36, (byte) 52, (byte) 53, (byte) 54, (byte) 38,
				(byte) 37, (byte) 37, (byte) 53, (byte) 54, (byte) 55, (byte) 39, (byte) 38, (byte) 38, (byte) 54,
				(byte) 55, (byte) 56, (byte) 40, (byte) 39, (byte) 39, (byte) 55, (byte) 56, (byte) 57, (byte) 41,
				(byte) 40, (byte) 40, (byte) 56, (byte) 57, (byte) 58, (byte) 42, (byte) 41, (byte) 41, (byte) 57,
				(byte) 58, (byte) 59, (byte) 43, (byte) 42, (byte) 42, (byte) 58, (byte) 59, (byte) 60, (byte) 44,
				(byte) 43, (byte) 43, (byte) 59, (byte) 60, (byte) 61, (byte) 45, (byte) 44, (byte) 44, (byte) 60,
				(byte) 61, (byte) 62, (byte) 46, (byte) 45, (byte) 45, (byte) 61, (byte) 62, (byte) 63, (byte) 47,
				(byte) 46, (byte) 46, (byte) 62, (byte) 63, (byte) 64, (byte) 48, (byte) 47, (byte) 47, (byte) 63,
				(byte) 64, (byte) 49, (byte) 33, (byte) 48, (byte) 48, (byte) 64, (byte) 49, (byte) 66, (byte) 50,
				(byte) 49, (byte) 49, (byte) 65, (byte) 66, (byte) 67, (byte) 51, (byte) 50, (byte) 50, (byte) 66,
				(byte) 67, (byte) 68, (byte) 52, (byte) 51, (byte) 51, (byte) 67, (byte) 68, (byte) 69, (byte) 53,
				(byte) 52, (byte) 52, (byte) 68, (byte) 69, (byte) 70, (byte) 54, (byte) 53, (byte) 53, (byte) 69,
				(byte) 70, (byte) 71, (byte) 55, (byte) 54, (byte) 54, (byte) 70, (byte) 71, (byte) 72, (byte) 56,
				(byte) 55, (byte) 55, (byte) 71, (byte) 72, (byte) 73, (byte) 57, (byte) 56, (byte) 56, (byte) 72,
				(byte) 73, (byte) 74, (byte) 58, (byte) 57, (byte) 57, (byte) 73, (byte) 74, (byte) 75, (byte) 59,
				(byte) 58, (byte) 58, (byte) 74, (byte) 75, (byte) 76, (byte) 60, (byte) 59, (byte) 59, (byte) 75,
				(byte) 76, (byte) 77, (byte) 61, (byte) 60, (byte) 60, (byte) 76, (byte) 77, (byte) 78, (byte) 62,
				(byte) 61, (byte) 61, (byte) 77, (byte) 78, (byte) 79, (byte) 63, (byte) 62, (byte) 62, (byte) 78,
				(byte) 79, (byte) 80, (byte) 64, (byte) 63, (byte) 63, (byte) 79, (byte) 80, (byte) 65, (byte) 49,
				(byte) 64, (byte) 64, (byte) 80, (byte) 65, (byte) 82, (byte) 66, (byte) 65, (byte) 65, (byte) 81,
				(byte) 82, (byte) 83, (byte) 67, (byte) 66, (byte) 66, (byte) 82, (byte) 83, (byte) 84, (byte) 68,
				(byte) 67, (byte) 67, (byte) 83, (byte) 84, (byte) 85, (byte) 69, (byte) 68, (byte) 68, (byte) 84,
				(byte) 85, (byte) 86, (byte) 70, (byte) 69, (byte) 69, (byte) 85, (byte) 86, (byte) 87, (byte) 71,
				(byte) 70, (byte) 70, (byte) 86, (byte) 87, (byte) 88, (byte) 72, (byte) 71, (byte) 71, (byte) 87,
				(byte) 88, (byte) 89, (byte) 73, (byte) 72, (byte) 72, (byte) 88, (byte) 89, (byte) 90, (byte) 74,
				(byte) 73, (byte) 73, (byte) 89, (byte) 90, (byte) 91, (byte) 75, (byte) 74, (byte) 74, (byte) 90,
				(byte) 91, (byte) 92, (byte) 76, (byte) 75, (byte) 75, (byte) 91, (byte) 92, (byte) 93, (byte) 77,
				(byte) 76, (byte) 76, (byte) 92, (byte) 93, (byte) 94, (byte) 78, (byte) 77, (byte) 77, (byte) 93,
				(byte) 94, (byte) 95, (byte) 79, (byte) 78, (byte) 78, (byte) 94, (byte) 95, (byte) 96, (byte) 80,
				(byte) 79, (byte) 79, (byte) 95, (byte) 96, (byte) 81, (byte) 65, (byte) 80, (byte) 80, (byte) 96,
				(byte) 81, (byte) 98, (byte) 82, (byte) 81, (byte) 81, (byte) 97, (byte) 98, (byte) 99, (byte) 83,
				(byte) 82, (byte) 82, (byte) 98, (byte) 99, (byte) 100, (byte) 84, (byte) 83, (byte) 83, (byte) 99,
				(byte) 100, (byte) 101, (byte) 85, (byte) 84, (byte) 84, (byte) 100, (byte) 101, (byte) 102, (byte) 86,
				(byte) 85, (byte) 85, (byte) 101, (byte) 102, (byte) 103, (byte) 87, (byte) 86, (byte) 86, (byte) 102,
				(byte) 103, (byte) 104, (byte) 88, (byte) 87, (byte) 87, (byte) 103, (byte) 104, (byte) 105, (byte) 89,
				(byte) 88, (byte) 88, (byte) 104, (byte) 105, (byte) 106, (byte) 90, (byte) 89, (byte) 89, (byte) 105,
				(byte) 106, (byte) 107, (byte) 91, (byte) 90, (byte) 90, (byte) 106, (byte) 107, (byte) 108, (byte) 92,
				(byte) 91, (byte) 91, (byte) 107, (byte) 108, (byte) 109, (byte) 93, (byte) 92, (byte) 92, (byte) 108,
				(byte) 109, (byte) 110, (byte) 94, (byte) 93, (byte) 93, (byte) 109, (byte) 110, (byte) 111, (byte) 95,
				(byte) 94, (byte) 94, (byte) 110, (byte) 111, (byte) 112, (byte) 96, (byte) 95, (byte) 95, (byte) 111,
				(byte) 112, (byte) 97, (byte) 81, (byte) 96, (byte) 96, (byte) 112, (byte) 97, (byte) 113, (byte) 98,
				(byte) 97, (byte) 113, (byte) 99, (byte) 98, (byte) 113, (byte) 100, (byte) 99, (byte) 113, (byte) 101,
				(byte) 100, (byte) 113, (byte) 102, (byte) 101, (byte) 113, (byte) 103, (byte) 102, (byte) 113,
				(byte) 104, (byte) 103, (byte) 113, (byte) 105, (byte) 104, (byte) 113, (byte) 106, (byte) 105,
				(byte) 113, (byte) 107, (byte) 106, (byte) 113, (byte) 108, (byte) 107, (byte) 113, (byte) 109,
				(byte) 108, (byte) 113, (byte) 110, (byte) 109, (byte) 113, (byte) 111, (byte) 110, (byte) 113,
				(byte) 112, (byte) 111, (byte) 113, (byte) 97, (byte) 112 };
		size[0] = valueFace.length;

		mIndexBuffer[0] = ByteBuffer.allocateDirect(valueFace.length);
		mIndexBuffer[0].put(valueFace);
		mIndexBuffer[0].position(0);
	}

	public void setNormal0() {
		float valueNormal[] = { 0f, -0.002117f, 15.6539f, -0.075498f, 1.9973f, 4.49299f, -0.840553f, 1.75659f,
				4.50358f, -1.48958f, 1.30645f, 4.49672f, -1.84628f, 0.632641f, 4.50864f, -1.95642f, -0.098945f,
				4.51525f, -1.80008f, -0.825194f, 4.47911f, -1.30377f, -1.48521f, 4.4878f, -0.570923f, -1.85479f,
				4.5217f, 0.111267f, -1.95008f, 4.5184f, 0.789993f, -1.81769f, 4.4863f, 1.43062f, -1.38868f, 4.47365f,
				1.84465f, -0.608889f, 4.50984f, 1.95333f, 0.132618f, 4.51606f, 1.80003f, 0.813551f, 4.49001f, 1.3525f,
				1.43298f, 4.50385f, 0.606183f, 1.85897f, 4.49921f, -0.064426f, 4.09354f, 4.13836f, -1.54265f, 3.70685f,
				4.14324f, -2.94405f, 2.75351f, 4.18941f, -3.78412f, 1.51123f, 4.11368f, -4.0549f, -0.041238f, 4.09288f,
				-3.68646f, -1.63207f, 4.14794f, -2.85378f, -2.93924f, 4.13641f, -1.5506f, -3.7495f, 4.13141f,
				0.109446f, -4.00589f, 4.16493f, 1.63245f, -3.66877f, 4.16001f, 2.90196f, -2.86768f, 4.1411f, 3.78143f,
				-1.5514f, 4.0783f, 4.05133f, 0.091551f, 4.10226f, 3.69482f, 1.60725f, 4.17628f, 2.86143f, 2.86884f,
				4.15714f, 1.44917f, 3.74128f, 4.13713f, -0.082731f, 5.40135f, 2.17099f, -1.97539f, 5.00344f, 2.08302f,
				-3.86079f, 3.73547f, 2.14018f, -4.98147f, 1.98015f, 2.1502f, -5.4312f, -0.010954f, 2.09864f, -5.02658f,
				-1.99589f, 2.09524f, -3.73451f, -3.83705f, 2.25017f, -2.10696f, -4.94139f, 2.21345f, 0.02369f,
				-5.34796f, 2.21395f, 2.11859f, -4.95107f, 2.21767f, 3.86561f, -3.70356f, 2.20644f, 4.97746f, -2.01743f,
				2.1211f, 5.43706f, 0.023806f, 2.09405f, 4.99884f, 2.02657f, 2.09539f, 3.75848f, 3.81949f, 2.15608f,
				2.01743f, 4.97746f, 2.1211f, 0f, 5.77931f, 0f, -2.18646f, 5.37472f, 0.029852f, -4.10563f, 4.06445f,
				0.000284f, -5.38898f, 2.15884f, -0.01567f, -5.77931f, 0f, 0f, -5.41193f, -2.11094f, -0.008287f,
				-4.11077f, -3.99332f, -0.038438f, -2.22147f, -5.3266f, 0.010547f, 0f, -5.7419f, 0f, 2.22147f, -5.3266f,
				-0.010547f, 4.11077f, -3.99332f, 0.038438f, 5.41193f, -2.11094f, 0.008287f, 5.77931f, 0f, 0f, 5.38898f,
				2.15884f, 0.01567f, 4.10563f, 4.06445f, -0.000284f, 2.18646f, 5.37472f, -0.029852f, 0.082731f,
				5.40135f, -2.17099f, -2.01743f, 4.97746f, -2.1211f, -3.75848f, 3.81949f, -2.15608f, -4.99884f,
				2.02657f, -2.09539f, -5.43706f, 0.023806f, -2.09405f, -4.97746f, -2.01743f, -2.1211f, -3.86561f,
				-3.70356f, -2.20644f, -2.11859f, -4.95108f, -2.21767f, -0.02369f, -5.34796f, -2.21395f, 2.10696f,
				-4.94139f, -2.21345f, 3.73451f, -3.83705f, -2.25017f, 5.02658f, -1.99589f, -2.09524f, 5.4312f,
				-0.010954f, -2.09864f, 4.98147f, 1.98015f, -2.1502f, 3.86079f, 3.73547f, -2.14018f, 1.97539f, 5.00344f,
				-2.08302f, 0.064426f, 4.09354f, -4.13836f, -1.44917f, 3.74127f, -4.13713f, -2.86143f, 2.86884f,
				-4.15714f, -3.69482f, 1.60725f, -4.17628f, -4.05133f, 0.091551f, -4.10226f, -3.78143f, -1.5514f,
				-4.0783f, -2.90196f, -2.86768f, -4.1411f, -1.63245f, -3.66877f, -4.16001f, -0.109446f, -4.00589f,
				-4.16493f, 1.5506f, -3.74951f, -4.13141f, 2.85378f, -2.93924f, -4.13641f, 3.68646f, -1.63207f,
				-4.14794f, 4.05491f, -0.041238f, -4.09288f, 3.78412f, 1.51123f, -4.11368f, 2.94405f, 2.75351f,
				-4.18941f, 1.54265f, 3.70685f, -4.14324f, 0.075498f, 1.9973f, -4.49299f, -0.606183f, 1.85897f,
				-4.49921f, -1.3525f, 1.43298f, -4.50385f, -1.80003f, 0.813551f, -4.49001f, -1.95333f, 0.132618f,
				-4.51606f, -1.84465f, -0.608889f, -4.50984f, -1.43062f, -1.38868f, -4.47365f, -0.789993f, -1.81769f,
				-4.4863f, -0.111267f, -1.95008f, -4.5184f, 0.570923f, -1.85479f, -4.5217f, 1.30377f, -1.48521f,
				-4.4878f, 1.80008f, -0.825194f, -4.47911f, 1.95642f, -0.098945f, -4.51525f, 1.84628f, 0.632641f,
				-4.50864f, 1.48958f, 1.30645f, -4.49672f, 0.840553f, 1.75659f, -4.50358f, 0f, -0.002117f, -15.6539f };

		ByteBuffer nbb = ByteBuffer.allocateDirect(valueNormal.length * 4);
		nbb.order(ByteOrder.nativeOrder());
		mNormalBuffer[0] = nbb.asFloatBuffer();
		mNormalBuffer[0].put(valueNormal);
		mNormalBuffer[0].position(0);
	}

	public void setColor0() {
		float valueColor[] = { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[0] = cbb.asFloatBuffer();
		mColorBuffer[0].put(valueColor);
		mColorBuffer[0].position(0);
	}

	public void setColor1() {
		float valueColor[] = { 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f };
		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[1] = cbb.asFloatBuffer();
		mColorBuffer[1].put(valueColor);
		mColorBuffer[1].position(0);
	}

	public void setColor2() {
		float valueColor[] = { 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f };
		

		ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer[2] = cbb.asFloatBuffer();
		mColorBuffer[2].put(valueColor);
		mColorBuffer[2].position(0);
	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}
}
