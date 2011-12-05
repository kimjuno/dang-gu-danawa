package DangGuDANAWA.Render;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import DangGuDANAWA.Activity.MainUIActivity;
import DangGuDANAWA.Activity.R;
import DangGuDANAWA.Data.BilliardsData;
import DangGuDANAWA.Object3D.Interface.Object3D;
import DangGuDANAWA.Object3D.Utils.ObjectFactory;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MainUIRenderer implements GLSurfaceView.Renderer {

	// Wall Const
	public static final int WALL_UP = 0;
	public static final int WALL_DOWN = 1;
	public static final int WALL_LEFT = 2;
	public static final int WALL_RIGHT = 3;
	public static final int WALL_FORWARD = 4;
	public static final int WALL_BACKWARD = 5;
	public static final int WALL_NUMBER = 6;
	public static final int ROOM_SIZE = 2;
	public static final int TEXTURE_NUMBER = 7;

	// 3D Const
	public static final float RADIAN = 0.017453f;
	public static final float MAX_3D_COORDINATE_X = 3.90f;
	public static final float MAX_3D_COORDINATE_Y = 22.40f;
	public static final float MIN_3D_COORDINATE_X = 0.02f;
	public static final float MIN_3D_COORDINATE_Y = 14.83f;

	// View Const
	public static final int VIEW_FORWARD = 0;
	public static final int VIEW_BACKWARD = 1;
	public static final int VIEW_LEFT = 2;
	public static final int VIEW_RIGHT = 4;

	// Menu Animation Const
	public static final int FRAME_RATE = 15; // Normal Speed 30
	public static final float MENU_CAMERA_POSITION = 270.0f;
	public static final float MENU_FREE_POSITION = 0.0f;
	public static final float MENU_EXIT_POSITION = 115.0f;

	public boolean mViewMode = false;
	public float mDepth;
	public float mAngleX;
	public float mAngleY;
	public float mAngleZ;
	public float mSpeedX;
	public float mAngleVertical;
	public float mRotateShotAngle;

	private int mViewState;
	FloatBuffer mVertexBuffer;
	public int mLineVertexSize;

	// 3D Obejct
	private Object3D mBilliard;
	private Object3D mWindows;
	private Object3D mWall;
	private Object3D mPlants;
	private Object3D mCamera;
	private Object3D mCue;
	private Object3D mDoor;
	private Object3D mBall;
	private Object3D mSingleCue;

	// 공 관련
	public Vector2[] mBallBody;
	private Vector2[] mPreviousBodyPosition;

	public float mPreviousTime;
	public float mFixTime;

	private int mActivityState;
	private int mMenuSelectAnimationFrame;
	private float mPreviousAngle;
	private int mCurrent_Ball_Count;

	private MainUIActivity mContext;
	public Vector2 mWhiteBallPosition;
	private ByteBuffer mIndexBuffer;
	private int[] mTextureID;

	private boolean mInitialize;

	public void setMenuAnimation() {
		mMenuSelectAnimationFrame = FRAME_RATE;
	}

	public boolean getMenuAnimation() {
		if (mMenuSelectAnimationFrame != 0)
			return true;
		else
			return false;
	}

	public MainUIRenderer(MainUIActivity _Context) {
		this.mContext = _Context;

		this.mBilliard = ObjectFactory.createBilliard();
		this.mWall = ObjectFactory.createWall();
		this.mWindows = ObjectFactory.createWindows();
		this.mPlants = ObjectFactory.createPlants();
		this.mCamera = ObjectFactory.createCamera();
		this.mCue = ObjectFactory.createCue();
		this.mDoor = ObjectFactory.createDoor();
		this.mBall = ObjectFactory.createBall();
		this.mSingleCue = ObjectFactory.createSingleCue();

		this.mBallBody = new Vector2[3];
		this.mPreviousBodyPosition = new Vector2[3];
		this.mLineVertexSize = 0;
		for (int i = 0; i < 3; i++)
			this.mPreviousBodyPosition[i] = new Vector2();

		this.mPreviousTime = 0.0f;
		this.mFixTime = 0.0f;
		this.mAngleX = 50.0f;
		this.mAngleY = 0.0f;
		this.mAngleZ = 0.0f;
		this.mDepth = 0.5f;
		this.mSpeedX = 7.0f; // 8.55f 원래
		this.mAngleVertical = 0.0f;
		this.mTextureID = new int[TEXTURE_NUMBER];
		this.mRotateShotAngle = 0.0f;
		this.mPreviousAngle = 0.0f;
		this.mMenuSelectAnimationFrame = 1;
		this.mViewState = VIEW_FORWARD;
		this.mActivityState = MainUIActivity.MODE_MENU;
		this.mInitialize = false;
		this.mCurrent_Ball_Count = 0;
	}

	public void setBallBody(int _Index, Vector2 _Body, int current_Ball_Count) {
		this.mBallBody[_Index] = _Body;
		this.mCurrent_Ball_Count = current_Ball_Count;
	}

	public void onDrawFrame(GL10 gl) {
		// 기본 설정

		this.mActivityState = mContext.getState();

		// if (mActivityState == MainUIActivity.MODE_FREE_PLAY)
		// checkEndPlaying();

		// gl.glDisable(GL_DITHER);
		// gl.glTexEnvx(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -3.0f);

		// 시야 조정
		setView(gl);
		gl.glScalef(0.08f, 0.08f, 0.08f);
		setupLight(gl);

		// drawExtendLine(gl);
		drawBallAndCue(gl);
		drawBilliard(gl);

		// 벽그리기
		drawWall(gl);

		if (mActivityState == MainUIActivity.MODE_MENU
				&& mMenuSelectAnimationFrame != 0 && mSpeedX == 0.0f)
			menuSelectAnimation();

		calculateViewAngle();
		// if (mActivityState == MainUIActivity.MODE_FREE_PLAY)
		// setPreviousPosition();
	}

	public void setSimulation() {
		mRotateShotAngle = BilliardsData.shotAngle;
	}

	private float linear(float v0, float v1, int _Frame) {
		return v0 + _Frame * 1.0f / FRAME_RATE * (v1 - v0);
	}

	private void menuSelectAnimation() {
		if (!mInitialize) {
			mInitialize = true;
			mContext.appearLogoAndMenu();
		}
		int menuState = mContext.mMenuSelectState;

		mMenuSelectAnimationFrame--;

		if (this.mPreviousAngle == 0.0f) {
			if (this.mAngleX == MENU_FREE_POSITION
					&& menuState == MainUIActivity.MENU_SELECT_REAL)
				this.mPreviousAngle = 360.0f - MENU_FREE_POSITION;
			else if (this.mAngleX == MENU_CAMERA_POSITION
					&& menuState == MainUIActivity.MENU_SELECT_FREE)
				this.mPreviousAngle = -360.0f + MENU_CAMERA_POSITION;
			else
				this.mPreviousAngle = this.mAngleX;
		}

		switch (menuState) {
		case MainUIActivity.MENU_SELECT_REAL:
			this.mAngleX = linear(mPreviousAngle, MENU_CAMERA_POSITION,
					FRAME_RATE - this.mMenuSelectAnimationFrame);
			break;
		case MainUIActivity.MENU_SELECT_FREE:
			this.mAngleX = linear(mPreviousAngle, MENU_FREE_POSITION,
					FRAME_RATE - this.mMenuSelectAnimationFrame);
			break;
		case MainUIActivity.MENU_SELECT_EXIT:
			this.mAngleX = linear(mPreviousAngle, MENU_EXIT_POSITION,
					FRAME_RATE - this.mMenuSelectAnimationFrame);
			break;
		}

		if (this.mMenuSelectAnimationFrame == 0)
			this.mPreviousAngle = 0.0f;
	}

	// 연장선 그리기
	// public void drawExtendLine(GL10 gl) {
	//
	// if (mContext.getState() != MainUIActivity.MODE_FREE_READY)
	// return;
	//
	// PhysicsWorld physicsWorld = mContext.getPhysicsWorld();
	// Iterable<Body> bodies = physicsWorld.getBodies();
	// for (Body body : bodies) {
	// if (body.getType() != BodyType.DynamicBody)
	// continue;
	//
	// if (body == mBallBody[MainUIActivity.BALL_WHITE]) {
	//
	// FloatBuffer mVertexBuffer;
	// ByteBuffer mIndexBuffer;
	// FloatBuffer mColorBuffer;
	//
	// gl.glPushMatrix();
	// {
	// gl.glTranslatef(0.0f, -19.9f, 0.0f);
	// gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
	// gl.glScalef(0.5f, 0.5f, 0.5f);
	// gl.glTranslatef(0.0f, 0.0f, 18.5f);
	//
	// gl.glTranslatef(-13.3f, -123.5f, 1.0f);
	// gl.glScalef(6.74f, 6.64f, 6.65f);
	// this.mWhiteBallPosition = body.getPosition();
	//
	// float vertex[] = { 0.02f, 0.0f, 0f, 3.90f, 22.40f, 0f };
	// vertex[0] = mWhiteBallPosition.x;
	// vertex[1] = mWhiteBallPosition.y;
	// // 계산식 투입!
	// /**
	// * @연장선구하는식 (x1 , y1 - (tanX * (x1 - x)) ) or ( ?? ) RADIAN
	// **/
	//
	// vertex[4] = mWhiteBallPosition.y
	// + ((float) Math.tan((mRotateShotAngle + 90.0f) * RADIAN) *
	// (MAX_3D_COORDINATE_X - mWhiteBallPosition.x));
	// if (vertex[4] > MAX_3D_COORDINATE_Y) {
	// vertex[3] = mWhiteBallPosition.x
	// + ((float) Math.cos((mRotateShotAngle + 90.0f) * RADIAN)
	// / (float) Math.sin((mRotateShotAngle + 90.0f) * RADIAN) *
	// (MAX_3D_COORDINATE_Y - mWhiteBallPosition.y));
	// vertex[4] = MAX_3D_COORDINATE_Y;
	// } else if (vertex[4] < MIN_3D_COORDINATE_Y) {
	// vertex[3] = mWhiteBallPosition.x
	// - ((float) Math.cos((mRotateShotAngle + 90.0f) * RADIAN)
	// / (float) Math.sin((mRotateShotAngle + 90.0f) * RADIAN) *
	// (mWhiteBallPosition.y - MIN_3D_COORDINATE_Y));
	// vertex[4] = MIN_3D_COORDINATE_Y;
	// }
	//
	// if ((float) Math.cos((mRotateShotAngle + 90.0f) * RADIAN) <
	// MIN_3D_COORDINATE_X) {
	// vertex[4] = mWhiteBallPosition.y
	// - ((float) Math.tan((mRotateShotAngle + 90.0f) * RADIAN) *
	// (mWhiteBallPosition.x));
	// vertex[3] = MIN_3D_COORDINATE_X;
	//
	// if (vertex[4] > MAX_3D_COORDINATE_Y) {
	// vertex[3] = mWhiteBallPosition.x
	//
	// + ((float) Math.cos((mRotateShotAngle + 90.0f) * RADIAN)
	// / (float) Math.sin((mRotateShotAngle + 90.0f) * RADIAN) *
	// (MAX_3D_COORDINATE_Y - mWhiteBallPosition.y));
	// vertex[4] = MAX_3D_COORDINATE_Y;
	// } else if (vertex[4] < MIN_3D_COORDINATE_Y) {
	// vertex[3] = mWhiteBallPosition.x
	// - ((float) Math.cos((mRotateShotAngle + 90.0f) * RADIAN)
	// / (float) Math.sin((mRotateShotAngle + 90.0f) * RADIAN) *
	// (mWhiteBallPosition.y - MIN_3D_COORDINATE_Y));
	// vertex[4] = MIN_3D_COORDINATE_Y;
	// }
	// }
	// if(mRotateShotAngle > -3.0f && mRotateShotAngle < 3.0f)
	// vertex[4] = MAX_3D_COORDINATE_Y;
	// else if(mRotateShotAngle > 177.0f && mRotateShotAngle < 183.0f)
	// vertex[4] = MIN_3D_COORDINATE_Y;
	//
	// byte valueFace[] = { 0, 1 };
	// float valueColor[] = { 0.2f, 1.0f, 0.7f, 0.7f, 1.0f, 1.0f, 1.0f, 0.7f };
	//
	// ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length * 4);
	// vbb.order(ByteOrder.nativeOrder());
	// mVertexBuffer = vbb.asFloatBuffer();
	// mVertexBuffer.put(vertex);
	// mVertexBuffer.position(0);
	//
	// mIndexBuffer = ByteBuffer.allocateDirect(valueFace.length);
	// mIndexBuffer.put(valueFace);
	// mIndexBuffer.position(0);
	//
	// ByteBuffer cbb = ByteBuffer.allocateDirect(valueColor.length * 4);
	// cbb.order(ByteOrder.nativeOrder());
	// mColorBuffer = cbb.asFloatBuffer();
	// mColorBuffer.put(valueColor);
	// mColorBuffer.position(0);
	//
	// gl.glEnable(GL10.GL_BLEND);
	// gl.glDisable(GL10.GL_CULL_FACE);
	// gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
	// gl.glFrontFace(GL10.GL_CCW);
	// gl.glDisable(GL10.GL_TEXTURE_2D);
	// gl.glLineWidth(2.5f);
	// gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
	// gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
	// gl.glDrawElements(GL10.GL_LINE_STRIP, 2, GL10.GL_UNSIGNED_BYTE,
	// mIndexBuffer);
	// gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	// gl.glDisable(GL10.GL_BLEND);
	// gl.glEnable(GL10.GL_CULL_FACE);
	// }
	// gl.glPopMatrix();
	// break;
	// }
	// }
	// }

	public void calculateViewAngle() {
		if (this.mSpeedX > -0.2f && this.mSpeedX < 0.2f) {
			this.mSpeedX = 0.0f;
		} else {
			this.mSpeedX = this.mSpeedX * 0.97f;
		}

		this.mAngleX += this.mSpeedX;
		this.mAngleZ += this.mSpeedX;
	}

	public void setView(GL10 gl) {
		switch (mActivityState) {
		case MainUIActivity.MODE_MENU:
		case MainUIActivity.MODE_SIMULATION_READY:
		case MainUIActivity.MODE_SIMULATION_PLAY:
			if (!mViewMode) {
				gl.glTranslatef(0, 0, mDepth * 1.5f);
				gl.glScalef(1.0f + mDepth / 2.0f, 1.0f + mDepth / 2.0f,
						1.0f + mDepth / 2.0f);
				gl.glRotatef(mAngleX, 0, 1, 0);
				gl.glRotatef(mAngleY, 1, 0, 0);
			} else {
				gl.glTranslatef(0, 0, 2.4f);
				gl.glRotatef(mAngleZ, 0, 0, 1f);
				gl.glRotatef(90.0f, 1, 0, 0);
			}
			break;
		case MainUIActivity.MODE_FREE_INITIAL:
		case MainUIActivity.MODE_FREE_READY:
		case MainUIActivity.MODE_FREE_PLAY:
			gl.glTranslatef(0.0f, 0.0f, 2.4f);
			gl.glRotatef(mAngleVertical, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			break;
		}

		if (mAngleX > 360.f)
			mAngleX = 0.0f;
		else if (mAngleX < -1.0f)
			mAngleX = 359.0f;

		if (mAngleX <= 45.0f || mAngleX >= 315.0f) {
			mViewState = VIEW_FORWARD;
		} else if (mAngleX <= 135.0f && mAngleX >= 45.0f) {
			mViewState = VIEW_RIGHT;
		} else if (mAngleX <= 225.0f && mAngleX >= 135.0f) {
			mViewState = VIEW_BACKWARD;
		} else {
			mViewState = VIEW_LEFT;
		}

	}

	// 당구대 그리기
	public void drawBilliard(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -20.0f, 0.0f);
		gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		this.mBilliard.draw(gl);
		gl.glPopMatrix();

	}

	// 볼과 큐 그리기
	public void drawBallAndCue(GL10 gl) {
		// 당구대 비율 맞추기 테스트
		gl.glPushMatrix();
		{
			gl.glTranslatef(0.0f, -19.9f, 0.0f);
			gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
			gl.glScalef(0.5f, 0.5f, 0.5f);
			gl.glTranslatef(0.0f, 0.0f, 18.5f);

			switch (mActivityState) {

			case MainUIActivity.MODE_FREE_INITIAL:
				if (mContext.mCurrent_Ball_Count < 3) {
					gl.glPushMatrix();
					{
						// 놓아야 할 공 그리기
						gl.glTranslatef(-15.0f, 25.0f, 5.0f);
						gl.glScalef(12.0f, 12.0f, 12.0f);
						this.mBall.draw(gl, mContext.mCurrent_Ball_Count);
					}
					gl.glPopMatrix();
				}
				drawBall(gl);				
				break;

			case MainUIActivity.MODE_SIMULATION_READY:
			case MainUIActivity.MODE_FREE_READY:
				drawSingleCue(gl);				
			case MainUIActivity.MODE_SIMULATION_PLAY:
			case MainUIActivity.MODE_FREE_PLAY:
			case MainUIActivity.MODE_MENU:
				drawBall(gl);
				drawLine(gl);
				break;
			}
		}
		gl.glPopMatrix();
	}

	public void makeLineVertex() {

		float temp[] = new float[mContext.mLineResult.size() * 3];
		byte tempByte[] = new byte[mContext.mLineResult.size()];
		
		for (int index = 0; index < mContext.mLineResult.size(); index++) {
			temp[index * 3] = mContext.mLineResult.get(index).x;
			temp[index * 3 + 1] = -mContext.mLineResult.get(index).y;
			temp[index * 3 + 2] = 0.0f;
			tempByte[index] = (byte)(index);
//			tempByte[index * 3 + 1] = (byte)(index * 3 + 1);
//			tempByte[index * 3 + 2] = (byte)(index * 3 + 2);
			
		}		
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(temp.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asFloatBuffer();
		mVertexBuffer.put(temp);
		mVertexBuffer.position(0);
		
		//mLineVertexSize = valueFace.length;
		
		
		mIndexBuffer = ByteBuffer.allocateDirect(tempByte.length);
		mIndexBuffer.put(tempByte);
		mIndexBuffer.position(0);
		
		mLineVertexSize = mContext.mLineResult.size();				
	}

	public void drawLine(GL10 gl) {
		if (mLineVertexSize != 0) {
			
			gl.glPushMatrix();
			{				
								
				gl.glPointSize(2.0f);
				gl.glScalef(6.74f, 6.64f, 6.65f);
				//gl.glTranslatef(0.0f, 0.0f, 1.0f);
				//gl.glScalef(0.0225f, 0.0225f, 0.0225f);
				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);								
				gl.glFrontFace(GL10.GL_CCW);
				gl.glDisable(GL10.GL_TEXTURE_2D);				
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
				gl.glDrawElements(GL10.GL_POINTS, mLineVertexSize,
						GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
				gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
				
			}
			gl.glPopMatrix();
			
		}
	}

	// 위치 저장
	// public void setPreviousPosition() {
	// if (mPreviousTime + 1000.0f > android.os.SystemClock.uptimeMillis())
	// return;
	//
	// mFixTime = android.os.SystemClock.uptimeMillis();
	// mPreviousTime = android.os.SystemClock.uptimeMillis();
	// PhysicsWorld temp = mContext.getPhysicsWorld();
	// Iterable<Body> bodies = temp.getBodies();
	// int index = 0;
	//
	// for (Body body : bodies) {
	// if (body.getType() != BodyType.DynamicBody)
	// continue;
	// mPreviousBodyPosition[index].x = body.getPosition().x;
	// mPreviousBodyPosition[index].y = body.getPosition().y;
	// index++;
	// }
	// }

	// 공이 모두 멈췄는지 확인
	// public void checkEndPlaying() {
	// if (mFixTime + 500.0f > android.os.SystemClock.uptimeMillis())
	// return;
	//
	// PhysicsWorld temp = mContext.getPhysicsWorld();
	// Iterable<Body> bodies = temp.getBodies();
	// int index = 0;
	// for (Body body : bodies) {
	// if (body.getType() != BodyType.DynamicBody)
	// continue;
	//
	// if (Math.abs(mPreviousBodyPosition[index].x - body.getPosition().x) >
	// 0.05f
	// || Math.abs(mPreviousBodyPosition[index].y - body.getPosition().y) >
	// 0.05f) {
	// return;
	// }
	// index++;
	// }
	// mContext.setState(MainUIActivity.MODE_FREE_READY);
	// }

	public Vector2 calculateVector(float power) {
		float x = (float) (Math.cos((this.mRotateShotAngle + 90.0f) * RADIAN) * power);
		float y = (float) (Math.sin((this.mRotateShotAngle + 90.0f) * RADIAN) * power);
		return new Vector2(x, y);
	}

	private void drawSingleCue(GL10 gl) {
		// PhysicsWorld temp = mContext.getPhysicsWorld();
		// Iterable<Body> bodies = temp.getBodies();
		// 큐 그리기 ^-^)
		// for (Body body : bodies) {
		// if (body.getType() != BodyType.DynamicBody)
		// continue;

		if (mBallBody[MainUIActivity.BALL_WHITE] != null) {
			gl.glPushMatrix();
			{
				this.mWhiteBallPosition = mBallBody[MainUIActivity.BALL_WHITE];
				// gl.glTranslatef(-13.3f, -123.5f, 1.0f);

				// gl.glScalef(12.0f, 12.0f, 12.0f);
				gl.glScalef(6.74f, 6.64f, 6.65f);
				gl.glTranslatef(mWhiteBallPosition.x, -mWhiteBallPosition.y,
						0.0f);
				gl.glRotatef(mRotateShotAngle, 0.0f, 0.0f, 1.0f);
				// 큐 좌우 조절
				gl.glTranslatef(mContext.getSpinAmount() / 500.0f - 0.1f, 0.0f,
						0.0f);
				this.mSingleCue.draw(gl);
			}
			gl.glPopMatrix();
		}
		// }
	}

	private void drawBall(GL10 gl) {
		// PhysicsWorld physicsWorld = mContext.getPhysicsWorld();
		// Iterable<Body> bodies = physicsWorld.getBodies();

		// for (Body body : bodies) {
		for (int index = 0; index < mContext.mCurrent_Ball_Count; index++) {

			gl.glPushMatrix();
			{
				// gl.glTranslatef(-13.3f, -123.5f, 1.0f);
				// gl.glTranslatef(-15.0f, 25.0f, 5.0f);
				gl.glScalef(6.74f, 6.64f, 6.65f);
				this.mWhiteBallPosition = mBallBody[index];
				// gl.glTranslatef(mWhiteBallPosition.x, mWhiteBallPosition.y,
				// 0.0f);
				gl.glTranslatef(mBallBody[index].x, -mBallBody[index].y, 0.0f);
				// switch(index)
				// {
				// case 1:
				// gl.glTranslatef(-1.9f, -3.8f, 0.0f);
				// break;
				// case 2:
				// gl.glTranslatef(1.9f, 3.8f, 0.0f);
				// break;
				// case 0:
				// gl.glTranslatef(0.0f, 0.0f, 0.0f);
				// break;
				// }

				

				// for (int i = 0; i < 3; i++)
				// if (body == mBallBody[i])
				this.mBall.draw(gl, index);
			}
			gl.glPopMatrix();
		}
	}

	private void drawWall(GL10 gl) {
		int state = mContext.getState();
		gl.glPushMatrix();
		{
			// gl.glBindTexture(GL10.GL_TEXTURE_2D, texturesBuffer.get(0));
			gl.glTranslatef(0.0f, -20.0f, 0.0f);

			for (int i = -ROOM_SIZE; i < ROOM_SIZE; i++) {
				for (int j = -ROOM_SIZE; j < ROOM_SIZE; j++) {
					gl.glPushMatrix();
					{
						gl.glTranslatef(20.0f * i, 0.0f, 20.0f * j);
						gl.glPushMatrix();
						{
							// 바닥
							gl.glBindTexture(GL10.GL_TEXTURE_2D,
									mTextureID[WALL_DOWN]);

							gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
							mWall.draw(gl);

							// 천장
							if (state < MainUIActivity.MODE_FREE_INITIAL) {

								gl.glBindTexture(GL10.GL_TEXTURE_2D,
										mTextureID[WALL_LEFT]);
								gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
								gl.glTranslatef(0.0f, 40.0f, 0.0f);
								gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
								mWall.draw(gl);
							}
						}
						gl.glPopMatrix();
						gl.glBindTexture(GL10.GL_TEXTURE_2D,
								mTextureID[WALL_LEFT]);
						if (state < MainUIActivity.MODE_FREE_INITIAL) {
							gl.glPushMatrix();
							{
								gl.glPushMatrix();
								{
									switch (i) {
									case ROOM_SIZE - 1:
										// 오른쪽 벽
										if (mViewState != VIEW_LEFT) {
											if (j == ROOM_SIZE - 1) {
												gl.glPushMatrix();
												{
													gl.glTranslatef(18.0f,
															0.0f, 0.0f);
													gl.glRotatef(-90.0f, 1.0f,
															0.0f, 0.0f);
													gl.glRotatef(-90.0f, 0.0f,
															0.0f, 1.0f);
													if (mMenuSelectAnimationFrame == 0
															&& state == MainUIActivity.MODE_MENU)
														gl.glDisable(GL10.GL_FOG);
													mDoor.draw(gl);
													gl.glEnable(GL10.GL_FOG);
												}
												gl.glPopMatrix();
											}
											gl.glRotatef(-90.0f, 0.0f, 1.0f,
													0.0f);
											gl.glTranslatef(0.0f, 20.0f, -20.0f);
											mWall.draw(gl);
										}
										break;
									case -ROOM_SIZE:
										// 왼쪽 벽
										// TV
										if (mViewState != VIEW_RIGHT) {
											if (j == ROOM_SIZE - 2) {
												// gl.glBindTexture(GL10.GL_TEXTURE_2D,
												// 0);
												gl.glPushMatrix();
												{
													gl.glDisable(GL10.GL_TEXTURE_2D);
													// gl.glTranslatef(0.0f,
													// 0.0f, -10.0f);
													gl.glRotatef(-90.0f, 1.0f,
															0.0f, 0.0f);
													gl.glRotatef(90.0f, 0.0f,
															0.0f, 1.0f);
													gl.glScalef(0.5f, 0.5f,
															0.5f);
													if (mMenuSelectAnimationFrame == 0
															&& state == MainUIActivity.MODE_MENU)
														gl.glDisable(GL10.GL_FOG);
													mCamera.draw(gl);
													gl.glEnable(GL10.GL_FOG);
												}
												gl.glPopMatrix();
											}

											gl.glRotatef(90.0f, 0.0f, 1.0f,
													0.0f);
											gl.glTranslatef(0.0f, 20.0f, -20.0f);
											mWall.draw(gl);
										}
										break;
									}
								}
								gl.glPopMatrix();

								gl.glPushMatrix();
								{
									switch (j) {
									case ROOM_SIZE - 1:
										if (mViewState != VIEW_FORWARD) {
											// 뒤쪽 벽
											gl.glPushMatrix();
											{
												// gl.glBindTexture(GL10.GL_TEXTURE_2D,
												// 0);
												gl.glTranslatef(0.0f, 10.0f,
														19.5f);
												gl.glRotatef(-90.0f, 1.0f,
														0.0f, 0.0f);
												gl.glRotatef(180.0f, 0.0f,
														0.0f, 1.0f);

												mWindows.draw(gl);
											}
											gl.glPopMatrix();

											gl.glRotatef(180.0f, 0.0f, 1.0f,
													0.0f);
											gl.glTranslatef(0.0f, 20.0f, -20.0f);
											mWall.draw(gl);
										}
										break;
									case -ROOM_SIZE:
										// 앞쪽 벽
										// 식물 그리기
										if (mViewState != VIEW_BACKWARD) {
											if (i == -ROOM_SIZE) {
												if (mViewState != VIEW_RIGHT) {
													gl.glPushMatrix();
													{
														gl.glTranslatef(10.0f,
																0.0f, 0.0f);
														gl.glRotatef(-90.0f,
																1.0f, 0.0f,
																0.0f);
														gl.glRotatef(180.0f,
																0.0f, 0.0f,
																1.0f);
														mPlants.draw(gl);
													}
													gl.glPopMatrix();
												}
											} else if (i == 0) {
												// 큐 대
												gl.glPushMatrix();
												{
													gl.glTranslatef(0.0f, 0.0f,
															-15.0f);
													gl.glRotatef(-90.0f, 1.0f,
															0.0f, 0.0f);
													if (mMenuSelectAnimationFrame == 0
															&& state == MainUIActivity.MODE_MENU)
														gl.glDisable(GL10.GL_FOG);
													mCue.draw(gl);
													gl.glEnable(GL10.GL_FOG);
												}
												gl.glPopMatrix();
											}
											gl.glTranslatef(0.0f, 20.0f, -20.0f);
											mWall.draw(gl);
										}
										break;
									}
								}
								gl.glPopMatrix();
							}
							gl.glPopMatrix();
						}
					}
					gl.glPopMatrix();
				}
			}
		}
		gl.glPopMatrix();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 텍스쳐 관련
		gl.glEnable(GL10.GL_TEXTURE_2D);
		// LoadTextures(gl,texturesBuffer);

		gl.glViewport(0, 0, width, height); /*
											 * * Set our projection matrix. This
											 * doesn't have to be done * each
											 * time we draw, but usually a new
											 * projection needs to * be set when
											 * the viewport is resized.
											 */
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

		int[] textures = new int[3];
		gl.glGenTextures(3, textures, 0);

		InputStream is = mContext.getResources().openRawResource(
				R.drawable.wall2);
		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// Ignore.
			}
		}

		mTextureID[WALL_LEFT] = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID[WALL_LEFT]);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		bitmap.recycle();

		// int[] textures1 = new int[1];
		// gl.glGenTextures(1, textures1, 0);

		InputStream is1 = mContext.getResources().openRawResource(
				R.drawable.stone);
		Bitmap bitmap1;
		try {
			bitmap1 = BitmapFactory.decodeStream(is1);
		} finally {
			try {
				is1.close();
			} catch (IOException e) {
				// Ignore.
			}
		}

		mTextureID[WALL_DOWN] = textures[1];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID[WALL_DOWN]);

		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap1, 0);
		bitmap1.recycle();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0, 0, 0, 0);

		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

		// blending
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);

	}

	float testLight = 0.0f;

	public void setupLight(GL10 gl) {
		// source
		float fLightPos[] = { 0.0f, 30.0f, 0.0f, 0.1f };
		// float fLightEndPos[] = {0.0f, 0.0f, 0.0f};
		float fNoLight[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float fLowLight[] = { 0.3f, 0.3f, 0.3f, 1.0f };
		float fShiniessLight[] = { 60.0f, 60.0f, 60.0f, 1.0f };
		// float fBrightLight[] = { 0.3f, 0.3f, 0.3f, 1.0f };
		float fBrightLight[] = { 0.8f, 0.8f, 0.8f, 0.8f };

		// fLightPos[1] = testLight += 0.1f;
		// for(int i = 0 ; i < 3; i++)
		// fBrightLight[i] = testLight += 0.001f;

		gl.glEnable(GL10.GL_NORMALIZE);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);

		// Setup 조명
		// gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, fNoLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, fLightPos, 0);
		// gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, fLightEndPos,
		// 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, fLowLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, fBrightLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, fNoLight, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SHININESS, fShiniessLight, 0);

		// fLightPos[0]

		// gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, fLightPos, 0);
		// gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPOT_DIRECTION, fLightEndPos,
		// 0);
		// gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, fLowLight, 0);
		// gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, fBrightLight, 0);
		// gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, fNoLight, 0);

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		// gl.glEnable(GL10.GL_LIGHT1);

		// 재질 조명 설정
		gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, fBrightLight, 0);
		FloatBuffer fogBuffer;
		float fogColor[] = { 0.0f, 0.0f, 0.0f, 0.8f };// 먼저 안개 색 지정
		ByteBuffer vbb = ByteBuffer.allocateDirect(fogColor.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		fogBuffer = vbb.asFloatBuffer();
		fogBuffer.put(fogColor);
		fogBuffer.position(0);
		// gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); //배경색도 안개색에 맞춘다.
		gl.glFogf(GL10.GL_FOG_MODE, GL10.GL_LINEAR); //

		gl.glFogfv(GL10.GL_FOG_COLOR, fogBuffer); // LINEAR모드의 안개 색 지정
		gl.glFogf(GL10.GL_FOG_DENSITY, 0.3f); // 안개의 밀도 지정.GL_EXP,GL_EXP2만 사용
		gl.glHint(GL10.GL_FOG_HINT, GL10.GL_FASTEST); // 멋있게 해달라구 요구
		gl.glFogf(GL10.GL_FOG_START, 0.5f); // LINEAR에서만 적용 안개가 보이는 Z값
		gl.glFogf(GL10.GL_FOG_END, 10.0f); // 안개가 들이워져서 물체가 보이지 않는 Z값
		gl.glEnable(GL10.GL_FOG); // 안개 사용

		// 블랜딩
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
	}

}