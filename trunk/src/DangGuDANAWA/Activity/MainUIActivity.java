package DangGuDANAWA.Activity;

import java.util.Vector;

import DangGuDANAWA.Algorithm.BiliardsController;
import DangGuDANAWA.Data.BilliardsData;
import DangGuDANAWA.Object3D.Utils.HelpTextFreeMode;
import DangGuDANAWA.Object3D.Utils.HelpTextSimulation;
import DangGuDANAWA.Render.MainUIRenderer;
import DangGuDANAWA.UIThread.MainUIThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.math.Vector2;

public class MainUIActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

	public BiliardsController m_controller;
	// Physics World Const
	private static final int VIEW_WIDTH = 480;
	private static final int VIEW_HEIGHT = 720;
	private static final int BILLIARDS_WIDTH = 122;
	private static final int BILLIARDS_HEIGHT = 244;
	private static final float BALL_LINEAR_DAMPING = 0.35f;
	private static final float BALL_ANGULAR_DAMPING = 0.25f;
	private static final float BALL_PHYSICSWORLD_SCALE = 0.254688f;
	private static final int BALL_MAX_COUNT = 3;
	public static final int BALL_WHITE = 0;
	public static final int BALL_ORANGE = 1;
	public static final int BALL_RED = 2;

	// 3D View Const
	public static final int GROW = 0;
	public static final int SHRINK = 1;
	public static final float TIME_CONSTANT = 250.0f;
	public static final float POSSIBLE_POSITION_CONSTANT = 15.0f;

	// Menu Const
	public static final int MENU_SELECT_REAL = 0;
	public static final int MENU_SELECT_FREE = 1;
	public static final int MENU_SELECT_EXIT = 2;

	// Mode Const
	public static final int MODE_MENU = 0;
	public static final int MODE_SIMULATION_READY = 1;
	public static final int MODE_SIMULATION_PLAY = 2;
	public static final int MODE_FREE_INITIAL = 3;
	public static final int MODE_FREE_READY = 4;
	public static final int MODE_FREE_PLAY = 5;

	// ===========================================================
	// Fields
	// ===========================================================

	public int mCurrent_Ball_Count;

//	public AnimatedSprite mFace[];
	public Vector2 mBody[];
	public int mMenuSelectState;

	private int mState_Mode;

	private float xCur, yCur, xSec, ySec, distDelta, distCur, distPre = -1;
	private long mLastGestureTime;
	private int mTouchSlop;
	private float mTouchPreviousX;
	private float mTouchPreviousY;
	private float mPreviousTouchTime;

	// Menu Select State
	private boolean mMenuSelected;

	// SurfaceView
	//private GLSurfaceView mGlsurfaceviewPhysics;
	private GLSurfaceView mGlsurfaceviewMain;

	// OpenGL Render
	private MainUIRenderer mRendererRealWorld;
	//private PhysicWorldRenderer mRenderPhysicsWorld;

	// Image View
	private ImageView mImageViewMainLogo; // Main Logo
	private ImageView mImageViewBottomLogo; // Bottom Logo
	private ImageView mImageViewMenu; // Menu Name

	// 메인 Control
	private LinearLayout mModeContainer;
	private Button mButtonSiulmationMode;
	private Button mButtonFreeMode;
	private Button mButtonModeCancel;
	private Button mButtonModeSelect;
	private Button mButton_Variable_One;
	private Button mButton_Variable_Two;
	private Button mButton_Variable_Three;
	private Button mButton_Variable_Four;

	// Ball Control
	private LinearLayout mContainerBallControlMain;
	private LinearLayout mContainerBallControlSub;
	private TextView mTextViewBallControlSpin;
	private TextView mTextViewBallControlPower;
	private SeekBar mSeekBarBallControlSpin;
	private SeekBar mSeekBarBallControlPower;
	private Button mButtonBallControlConfirm;
	private int mSpinAmount;

	public Vector <Vector2> mLineResult;
	// PhysicsWorld
//	private PhysicsWorld mPhysicsWorld;
//	private Texture mTexture;
//	private TiledTextureRegion mCircleFaceTextureRegion;

	// Physics Wall
//	private Shape mShapeGroundWall;
//	private Shape mShapeRoofWall;
//	private Shape mShapeLeftWall;
//	private Shape mShapeRightWall;

	@Override
	protected void onCreate(final Bundle _pSavedInstanceState) {
		super.onCreate(_pSavedInstanceState);

		setContentView(R.layout.surface_view_overlay);
		this.mMenuSelected = false;
		this.mState_Mode = MODE_MENU;

		// SurfaceView
		//this.mGlsurfaceviewPhysics = (GLSurfaceView) findViewById(R.id.PhysicsGlsurfaceview);
		this.mGlsurfaceviewMain = (GLSurfaceView) findViewById(R.id.MainGlsurfaceview);
		//this.mRenderPhysicsWorld = new PhysicWorldRenderer(mEngine);
		//this.mGlsurfaceviewPhysics.setRenderer(this.mRenderPhysicsWorld);
		this.mRendererRealWorld = new MainUIRenderer(this);
		this.mGlsurfaceviewMain.setRenderer(this.mRendererRealWorld);

		this.mImageViewMainLogo = (ImageView) findViewById(R.id.imageView_mainlogo);
		this.mImageViewMenu = (ImageView) findViewById(R.id.imageView_menu);
		this.mImageViewBottomLogo = (ImageView) findViewById(R.id.imageView_bottomlogo);
		this.mImageViewMainLogo.setAlpha(0);
		this.mImageViewMenu.setAlpha(0);

		// Mode Change
		this.mModeContainer = (LinearLayout) findViewById(R.id.layout_ModeContainer);
		this.mButtonSiulmationMode = (Button) findViewById(R.id.button_SimulationMode);
		this.mButtonFreeMode = (Button) findViewById(R.id.button_FreeMode);
		this.mButtonModeCancel = (Button) findViewById(R.id.button_ModeSelectCancel);
		this.mButtonSiulmationMode.setOnClickListener(new ModeButtonListener());
		this.mButtonFreeMode.setOnClickListener(new ModeButtonListener());
		this.mButtonModeCancel.setOnClickListener(new ModeButtonListener());

		// Find Ball Control
		this.mContainerBallControlMain = (LinearLayout) findViewById(R.id.layout_BallControl_Main);
		this.mContainerBallControlSub = (LinearLayout) findViewById(R.id.layout_BallControl_Sub);
		this.mTextViewBallControlSpin = (TextView) findViewById(R.id.textView_BallControl_Spin);
		this.mTextViewBallControlPower = (TextView) findViewById(R.id.textView_BallControl_Power);
		this.mSeekBarBallControlSpin = (SeekBar) findViewById(R.id.seekbar_BallControl_Spin);
		this.mSeekBarBallControlPower = (SeekBar) findViewById(R.id.seekbar_BallControl_Power);
		this.mButtonBallControlConfirm = (Button) findViewById(R.id.button_BallControl_Confirm);
		this.mButtonBallControlConfirm.setOnClickListener(new FreeModeButtonListener());
		this.mSeekBarBallControlSpin.setOnSeekBarChangeListener(this);

		// FInd Sub Menu Control
		this.mButtonModeSelect = (Button) findViewById(R.id.button_ModeSelect);
		this.mButton_Variable_One = (Button) findViewById(R.id.button_Variable_One);
		this.mButton_Variable_Two = (Button) findViewById(R.id.button_Variable_Two);
		this.mButton_Variable_Three = (Button) findViewById(R.id.button_Variable_Three);
		this.mButton_Variable_Four = (Button) findViewById(R.id.button_Variable_Four);
		
		this.mButtonModeSelect .setOnClickListener(this);
		this.mButton_Variable_One.setOnClickListener(this);
		this.mButton_Variable_Two.setOnClickListener(this);
		this.mButton_Variable_Three.setOnClickListener(this);
		this.mButton_Variable_Four.setOnClickListener(this);

		this.mSpinAmount = 50;
		BilliardsData.simulation = false;
		this.mLineResult = new Vector <Vector2>();
//		// Initialize
//		mFace = new AnimatedSprite[3];
		mBody = new Vector2[3];
		for (int i = 0; i < BALL_MAX_COUNT; i++) {			
			mBody[i] = null;
		}
//		addBall((int) (Math.random() * 130.0f), (int) (Math.random() * 180.0f) + 420);
//		this.mCurrent_Ball_Count++;
//		addBall((int) (Math.random() * 130.0f), (int) (Math.random() * 180.0f) + 420);
//		this.mCurrent_Ball_Count++;
//		addBall((int) (Math.random() * 130.0f), (int) (Math.random() * 180.0f) + 420);
//		this.mCurrent_Ball_Count++;
		this.mButton_Variable_Three.setEnabled(false);
		this.mButton_Variable_Four.setEnabled(false);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (BilliardsData.simulation) {
			initializeSimulationMode();
		}
	}

	public synchronized void setVisibleBallControlContainer(boolean _State) {
		if (_State) {
			this.mContainerBallControlMain.setVisibility(View.VISIBLE);
			this.mContainerBallControlSub.setVisibility(View.VISIBLE);
			this.mTextViewBallControlSpin.setVisibility(View.VISIBLE);
			this.mTextViewBallControlPower.setVisibility(View.VISIBLE);
			this.mSeekBarBallControlSpin.setVisibility(View.VISIBLE);
			this.mSeekBarBallControlPower.setVisibility(View.VISIBLE);
			this.mButtonBallControlConfirm.setVisibility(View.VISIBLE);
		} else {
			this.mContainerBallControlMain.setVisibility(View.INVISIBLE);
			this.mContainerBallControlSub.setVisibility(View.INVISIBLE);
			this.mTextViewBallControlSpin.setVisibility(View.INVISIBLE);
			this.mTextViewBallControlPower.setVisibility(View.INVISIBLE);
			this.mSeekBarBallControlSpin.setVisibility(View.INVISIBLE);
			this.mSeekBarBallControlPower.setVisibility(View.INVISIBLE);
			this.mButtonBallControlConfirm.setVisibility(View.INVISIBLE);
		}
	}

	public void setTextVarialeButton(String str1, String str2, String str3, String str4) {
		this.mButton_Variable_One.setText(str1);
		this.mButton_Variable_Two.setText(str2);
		this.mButton_Variable_Three.setText(str3);		
		this.mButton_Variable_Four.setText(str4);
	}

	public void setVisibleVariableButton(boolean _State) {
		if (_State) {
			this.mImageViewBottomLogo.setVisibility(View.GONE);
			//this.mButtonModeSelect.setVisibility(View.VISIBLE);
			this.mButton_Variable_One.setVisibility(View.VISIBLE);
			this.mButton_Variable_Two.setVisibility(View.GONE);
			this.mButton_Variable_Three.setVisibility(View.VISIBLE);
			this.mButton_Variable_Four.setVisibility(View.VISIBLE);
		} else {
			this.mImageViewBottomLogo.setVisibility(View.VISIBLE);
			this.mButtonModeSelect.setVisibility(View.GONE);
			this.mButton_Variable_One.setVisibility(View.GONE);
			this.mButton_Variable_Two.setVisibility(View.GONE);
			this.mButton_Variable_Three.setVisibility(View.GONE);
			this.mButton_Variable_Four.setVisibility(View.GONE);
		}
	}

	public void actionVariableOneButton() {
		switch (this.mState_Mode) {
		case MainUIActivity.MODE_SIMULATION_READY:
			shotBall();
			this.mState_Mode = MODE_SIMULATION_PLAY;
			break;
		case MainUIActivity.MODE_SIMULATION_PLAY:
			break;
		case MainUIActivity.MODE_FREE_INITIAL:
		case MainUIActivity.MODE_FREE_READY:
		case MainUIActivity.MODE_FREE_PLAY:
			resetBall();
			mState_Mode = MODE_FREE_INITIAL;
			break;
		}
	}

	public void resetBall() {
		// Ball Reset
		//mGlsurfaceviewMain.onPause();

//		Iterable<Body> bodies = getPhysicsWorld().getBodies();
//		for (Body body : bodies) {
//			if (body.getType() == BodyType.DynamicBody)
//				getPhysicsWorld().destroyBody(body);
//		}
		m_controller = new BiliardsController();
		mCurrent_Ball_Count = BALL_WHITE;
		setVisibleBallControlContainer(false);
		this.mButton_Variable_Three.setEnabled(false);
		this.mButton_Variable_Four.setEnabled(false);
		this.mLineResult.clear();
		mRendererRealWorld.mLineVertexSize = 0;
		//mGlsurfaceviewMain.onResume();
	}

	public void actionVariableTwoButton() {
		switch (this.mState_Mode) {
		case MainUIActivity.MODE_SIMULATION_READY:
		case MainUIActivity.MODE_SIMULATION_PLAY:
			initializeSimulationMode();
			break;
		case MainUIActivity.MODE_FREE_INITIAL:
		case MainUIActivity.MODE_FREE_READY:
		case MainUIActivity.MODE_FREE_PLAY:
			setVisibleBallControlContainer(true);
			break;
		}
	}

	public void actionVariableThreeButton() {
		switch (this.mState_Mode) {
		// 저장 불러오기
		case MainUIActivity.MODE_SIMULATION_READY:
			break;
		case MainUIActivity.MODE_SIMULATION_PLAY:
			break;
		case MainUIActivity.MODE_FREE_INITIAL:
			break;
		case MainUIActivity.MODE_FREE_READY:
			// Shot Ball~!
			float ptdata[] = new float[6];
			float ratio = 32.10526f;
			ptdata[0] = (mBody[0].x + 1.9f) * ratio;
			ptdata[1] = (mBody[0].y + 3.8f) * ratio;
			ptdata[2] = (mBody[1].x + 1.9f) * ratio;
			ptdata[3] = (mBody[1].y + 3.8f) * ratio;
			ptdata[4] = (mBody[2].x + 1.9f) * ratio;
			ptdata[5] = (mBody[2].y + 3.8f) * ratio;
			
			m_controller.ChangePosition(ptdata);
			m_controller.FindRoadAlgorithm();
			
			if(m_controller.m_arrResult.size() == 0)
			{
				Toast.makeText(this, "길을 하나도 찾지 못했습니다.", Toast.LENGTH_SHORT).show();				
				return;
			}
			else
			{
				m_controller.m_Cue.setAngle(m_controller.m_arrResult.get(m_controller.m_nResultCount).Angle);
				m_controller.m_Cue.setPower(m_controller.m_arrResult.get(m_controller.m_nResultCount).Power);				
			}
									
			float tempResult[] = m_controller.getDrawRoad();
			mRendererRealWorld.mRotateShotAngle = 360 -  m_controller.m_arrResult.get(m_controller.m_nResultCount).Angle + 90;
			int size = m_controller.m_arrResult.size();
			if( ++(m_controller.m_nResultCount) == size && size != 0)
			{
				m_controller.m_nResultCount = 0;
			}
			float result[] = m_controller.getDrawRoad();
			
			boolean check = false;
			this.mLineResult.clear();
			float x = 0.0f;
			for(float vertex : result)
			{
				if(!check)
					x = vertex;
				else
				{
					//Log.i("ball Position", x + " - " + result[index]);
					mLineResult.add(new Vector2((x - 64f) / ratio, (vertex - 122f) / ratio));									
				}
				check = !check;
			}
			
			mRendererRealWorld.makeLineVertex();			
			this.mButton_Variable_Four.setEnabled(true);
			this.mButton_Variable_Three.setEnabled(false);
			break;
		case MainUIActivity.MODE_FREE_PLAY:
			break;
		}
	}

	public void shotBall() {
//		Iterable<Body> bodies = getPhysicsWorld().getBodies();
//		for (Body body : bodies) {
//			if (body != this.mRendererRealWorld.mBallBody[BALL_WHITE])
//				continue;
//			// - 가 왼쪽 +가 오른쪽 스핀
//			body.setAngularVelocity( (this.mSeekBarBallControlSpin.getProgress() - 50)/100.0f );
//			body.setLinearVelocity(this.mRendererRealWorld.calculateVector(this.mSeekBarBallControlPower.getProgress() / 2.5f));
//			this.mState_Mode = MODE_FREE_PLAY;
//			this.mRendererRealWorld.setPreviousPosition();
//			this.mRendererRealWorld.mPreviousTime = android.os.SystemClock.uptimeMillis();
//			setVisibleBallControlContainer(false);
//		}
	}

	public void actionHelpTextButton() {
		switch (mState_Mode) {
		case MainUIActivity.MODE_MENU:
		case MainUIActivity.MODE_SIMULATION_READY:
		case MainUIActivity.MODE_SIMULATION_PLAY:
			startActivity(new Intent(this, HelpTextSimulation.class));
			break;
		case MainUIActivity.MODE_FREE_INITIAL:
		case MainUIActivity.MODE_FREE_READY:
		case MainUIActivity.MODE_FREE_PLAY:
			startActivity(new Intent(this, HelpTextFreeMode.class));
			break;
		}
	}

	// 메인버튼 리스너
	@Override
	public void onClick(View _View) {
		if (_View.getId() == R.id.button_ModeSelect) {
			// 모드 선택화면 Visible
			mButtonSiulmationMode.setVisibility(View.VISIBLE);
			mButtonFreeMode.setVisibility(View.VISIBLE);
			mButtonModeCancel.setVisibility(View.VISIBLE);
			mModeContainer.setVisibility(View.VISIBLE);
		} else if (_View.getId() == R.id.button_Variable_One) {
			actionVariableOneButton();
		} else if (_View.getId() == R.id.button_Variable_Two) {
			actionVariableTwoButton();
		} else if (_View.getId() == R.id.button_Variable_Three) {
			actionVariableThreeButton();
		} else if (_View.getId() == R.id.button_Variable_Four) {
			actionVariableFourButton();
		}else if (_View.getId() == R.id.button_HelpText) {
			actionHelpTextButton();
		}
	}
	public void actionVariableFourButton()
	{
		mRendererRealWorld.mRotateShotAngle = 360 -  m_controller.m_arrResult.get(m_controller.m_nResultCount).Angle + 90;
		int size = m_controller.m_arrResult.size();
		if( ++(m_controller.m_nResultCount) == size && size != 0)
		{
			m_controller.m_nResultCount = 0;
		}
//		Toast.makeText(context, "redX:" + mDrawer.m_controller.m_Balls[1].getX() +
//				"redY:" + mDrawer.m_controller.m_Balls[1].getY() , Toast.LENGTH_SHORT).show();
//		float ptdata[] = new float[6];
		float ratio = 32.10526f;
//		ptdata[0] = (mBody[0].x + 1.9f) * ratio;
//		ptdata[1] = (mBody[0].y + 3.8f) * ratio;
//		ptdata[2] = (mBody[1].x + 1.9f) * ratio;
//		ptdata[3] = (mBody[1].y + 3.8f) * ratio;
//		ptdata[4] = (mBody[2].x + 1.9f) * ratio;
//		ptdata[5] = (mBody[2].y + 3.8f) * ratio;
		
//		m_controller.ChangePosition(ptdata);
//		m_controller.FindRoadAlgorithm();
		
//		if(m_controller.m_arrResult.size() == 0)
//		{
//			Toast.makeText(this, "길을 하나도 찾지 못했습니다.", Toast.LENGTH_SHORT).show();				
//			return;
//		}
//		else
//		{
				
//		}
		
//		m_controller.m_Cue.setAngle();
//		m_controller.m_Cue.setPower(m_controller.m_arrResult.get(m_controller.m_nResultCount).Power);
		
		float result[] = m_controller.getDrawRoad();
		
		boolean check = false;
		this.mLineResult.clear();
		float x = 0.0f;
		//for(float vertex : result)
		for(int index = 0; index < 200; index++)
		{
			if(!check)
				x = result[index];
			else
			{
				//Log.i("ball Position", x + " - " + result[index]);
				mLineResult.add(new Vector2((x - 64f) / ratio, (result[index] - 122f) / ratio));									
			}
			check = !check;
		}
		//Toast.makeText(this, "버택스 수 : " + mLineResult.size(), Toast.LENGTH_SHORT).show();
		mRendererRealWorld.makeLineVertex();			
	}

	public void addBall(float _x, float _y) {
		// 44, 517		
		//mGlsurfaceviewMain.onPause();
		MainUIActivity.this.addFace(_x, _y);
		//mGlsurfaceviewMain.onResume();
	}

	public void simulationMultiTouchGesture(MotionEvent _Event) {
		int action = _Event.getAction() & MotionEvent.ACTION_MASK;
		switch (action) {
		case MotionEvent.ACTION_MOVE:

			// point 1 coords
			this.xCur = _Event.getX(0);
			this.yCur = _Event.getY(0);

			// point 2 coords
			this.xSec = _Event.getX(1);
			this.ySec = _Event.getY(1);

			// distance between
			this.distCur = (float) Math.sqrt(Math.pow(this.xSec - this.xCur, 2) + Math.pow(this.ySec - this.yCur, 2));
			this.distDelta = this.distPre > -1 ? this.distCur - this.distPre : 0;

			long now = android.os.SystemClock.uptimeMillis();
			if (Math.abs(this.distDelta) > this.mTouchSlop) {
				this.mLastGestureTime = 0;

				int mode = this.distDelta > 0 ? GROW : (this.distCur == this.distPre ? 2 : SHRINK);

				switch (mode) {
				case GROW: // 확대
					if (this.mRendererRealWorld.mDepth <= 0.8f)
						this.mRendererRealWorld.mDepth += this.distCur / 20000.0f;
					break;
				case SHRINK: // 축소
					if (this.mRendererRealWorld.mDepth >= -0.05f)
						this.mRendererRealWorld.mDepth -= this.distCur / 20000.0f;
					break;
				}

				this.mLastGestureTime = now;
				this.distPre = this.distCur;
				return;
			}
			this.distPre = this.distCur;
			break;
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_UP:
			distPre = -1;
			mLastGestureTime = android.os.SystemClock.uptimeMillis();
			break;
		}
	}

	// 시뮬레이션 모드에서의 싱글 터치 이벤트
	public void simulationSingleTouchGesture(MotionEvent _Event) {
		if (mLastGestureTime + TIME_CONSTANT < android.os.SystemClock.uptimeMillis()) {
			float x = _Event.getX();
			float y = _Event.getY();
			switch (_Event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				float dx = x - mTouchPreviousX;
				mRendererRealWorld.mSpeedX += dx / 20;
				break;
			case MotionEvent.ACTION_DOWN:
				if (mPreviousTouchTime + TIME_CONSTANT > android.os.SystemClock.uptimeMillis()
						&& Math.abs(mTouchPreviousX - x) < POSSIBLE_POSITION_CONSTANT
						&& Math.abs(mTouchPreviousY - y) < POSSIBLE_POSITION_CONSTANT) {
					mRendererRealWorld.mViewMode = !mRendererRealWorld.mViewMode;
				} else
					mPreviousTouchTime = android.os.SystemClock.uptimeMillis();
				break;
			}
			mTouchPreviousX = x;
			mTouchPreviousY = y;
		}
	}

	// 터치 이벤트
	@Override
	public boolean onTouchEvent(MotionEvent _Event) {
		switch (mState_Mode) {
		case MainUIActivity.MODE_MENU:
			menuTouchGesture(_Event);
			break;
		case MainUIActivity.MODE_SIMULATION_READY:
		case MainUIActivity.MODE_SIMULATION_PLAY:
			if (_Event.getPointerCount() > 1) { // Multi Touch
				simulationMultiTouchGesture(_Event);
			} else { // Single Touch
				simulationSingleTouchGesture(_Event);
			}
			break;
		case MainUIActivity.MODE_FREE_INITIAL:
		case MainUIActivity.MODE_FREE_READY:
		case MainUIActivity.MODE_FREE_PLAY:
			if (_Event.getPointerCount() > 1) { // Multi Touch

			} else { // Single Touch
				freeSingleTouchGesture(_Event);
			}
			break;
		}

		return true;
	}

	private void initializeSimulationMode() {
		new MainUIThread(mImageViewMainLogo, mImageViewMenu, MainUIThread.MAIN_LOGO_DISAPPEAR).start();

		resetBall();
//		for (int i = 0; i < BALL_MAX_COUNT; i++) {
//			addBall(BilliardsData.positionX[i] + 2, VIEW_HEIGHT - 2 - BilliardsData.positionY[i]);
//			// addBall(100, 100 + VIEW_HEIGHT - BILLIARDS_HEIGHT - 2);
//			mCurrent_Ball_Count++;
//		}
		mRendererRealWorld.mRotateShotAngle = -BilliardsData.shotAngle + 90.0f;
		mRendererRealWorld.mViewMode = false;
		mSeekBarBallControlPower.setProgress((int) BilliardsData.shotPower * 7);
		mSeekBarBallControlSpin.setProgress( (int) (((BilliardsData.shotSpin * 3.125) + 50)/100.0f ) );
		setVisibleBallControlContainer(true);
		setVisibleVariableButton(true);
		setVisibleBallControlContainer(false);
		mState_Mode = MODE_SIMULATION_READY;
	}

	private void initializeFreeMode() {
		new MainUIThread(mImageViewMainLogo, mImageViewMenu, MainUIThread.MAIN_LOGO_DISAPPEAR).start();
		setVisibleVariableButton(true);
		mRendererRealWorld.mRotateShotAngle = 0.0f;
		resetBall();
		mState_Mode = MODE_FREE_INITIAL;
	}

	public void finishProgram() {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Exit");
		alert.setMessage("Really ?");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		alert.show();
	}

	@Override
	public void onBackPressed() {

		if (mState_Mode == MODE_MENU) {
			finishProgram();
			return;
		}
		appearLogoAndMenu();
		mContainerBallControlSub.setVisibility(View.GONE);
		setVisibleVariableButton(false);
		mRendererRealWorld.mAngleX = 0.0f;
		mState_Mode = MODE_MENU;
		mRendererRealWorld.mSpeedX = 0.0f;
		mRendererRealWorld.mViewMode = false;
		switch (mMenuSelectState) {
		case MENU_SELECT_REAL:
			mRendererRealWorld.mAngleX = MainUIRenderer.MENU_CAMERA_POSITION;
			break;
		case MENU_SELECT_FREE:
			mRendererRealWorld.mAngleX = MainUIRenderer.MENU_FREE_POSITION;
			break;
		case MENU_SELECT_EXIT:
			mRendererRealWorld.mAngleX = MainUIRenderer.MENU_EXIT_POSITION;
			break;
		}

	}

	private void menuTouchGesture(MotionEvent _Event) {

		if (this.mRendererRealWorld.getMenuAnimation())
			return;

		int action = _Event.getAction() & MotionEvent.ACTION_MASK;
		float x = _Event.getX();
		float y = _Event.getY();
//		int width = mRenderPhysicsWorld.getSurfaceWidth();
//		int height = mRenderPhysicsWorld.getSurfaceHeight();

		switch (action) {
		case MotionEvent.ACTION_MOVE:
			float dx = x - mTouchPreviousX;
			if (dx < -20.0f) {
				if (this.mMenuSelectState < 2) {
					this.mMenuSelectState++;
				} else {
					this.mMenuSelectState = MENU_SELECT_REAL;
				}
				this.mRendererRealWorld.setMenuAnimation();
				mMenuSelected = false;
				new MainUIThread(null, mImageViewMenu, mMenuSelectState).start();
			} else if (dx > 20.0f) {
				if (this.mMenuSelectState > 0) {
					this.mMenuSelectState--;
				} else {
					this.mMenuSelectState = MENU_SELECT_EXIT;
				}
				this.mRendererRealWorld.setMenuAnimation();
				mMenuSelected = false;
				new MainUIThread(null, mImageViewMenu, mMenuSelectState).start();
			}

			this.mTouchPreviousX = x;
			this.mTouchPreviousY = y;
			break;

		case MotionEvent.ACTION_DOWN:
			this.mTouchPreviousX = x;
			this.mTouchPreviousY = y;
//			if (width / 3 < x && width / 3 * 2 > x && height / 3 < y && height / 3 * 2 > y)
//				mMenuSelected = true;
			break;
		case MotionEvent.ACTION_UP:
			//if (width / 3 < x && width / 3 * 2 > x && height / 3 < y && height / 3 * 2 > y && mMenuSelected) {
				switch (mMenuSelectState) {
				case MENU_SELECT_REAL:
					startActivity(new Intent(this, Loading.class));
					overridePendingTransition(R.anim.fade, R.anim.fade);
					// finish();
					break;
				case MENU_SELECT_FREE:
					initializeFreeMode();
					break;
				case MENU_SELECT_EXIT:
					finishProgram();
					break;
				}
			//}
			mMenuSelected = false;
			break;
		}
	}

	// 자유 모드에서의 싱글 터치 이벤트
	private void freeSingleTouchGesture(MotionEvent _Event) {
		int action = _Event.getAction() & MotionEvent.ACTION_MASK;
		float x = _Event.getX();
		float y = _Event.getY();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
//			if (mState_Mode == MODE_FREE_READY) {
//				switch (_Event.getAction()) {
//				case MotionEvent.ACTION_MOVE:
//					float dx = x - mTouchPreviousX;
//					mRendererRealWorld.mRotateShotAngle += dx / 4;
//					break;
//				case MotionEvent.ACTION_DOWN:
//					break;
//				}
//				mTouchPreviousX = x;
//				mTouchPreviousY = y;
//			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_DOWN:
			// 터치 위한값 저장
			this.mTouchPreviousX = x;
			this.mTouchPreviousY = y;

			float xPosition = (_Event.getX() - 237.5f) / 72.36842f;// * 0.471429f;
			float yPosition = (_Event.getY() - 365f) / 67.10526f;// * 0.471429f);
			
			if (x < 100 || x > 375 || y < 110 || y > 620)
				return;
			
			for(int index = 0 ; index < mCurrent_Ball_Count; index++)
			{
				if(Math.abs(mBody[index].x - xPosition) < 0.1f && Math.abs(mBody[index].y - yPosition) < 0.1f)					
					return;
			}

			if (this.mCurrent_Ball_Count < BALL_MAX_COUNT) {				
				addBall(xPosition, yPosition);
				this.mCurrent_Ball_Count++;
				if (this.mCurrent_Ball_Count == BALL_MAX_COUNT) {
					//setVisibleBallControlContainer(true);
					mState_Mode = MODE_FREE_READY;
					this.mButton_Variable_Three.setEnabled(true);
					//this.mButton_Variable_Four.setEnabled(true);
				}
			}
		}
	}

	// Logo Appear
	public void appearLogoAndMenu() {
		new MainUIThread(mImageViewMainLogo, mImageViewMenu, MainUIThread.MAIN_LOGO_APPEAR).start();
	}

//	public synchronized PhysicsWorld getPhysicsWorld() {
//		return this.mPhysicsWorld;
//	}

	public synchronized int getState() {
		return this.mState_Mode;
	}

	public synchronized void setState(int _State) {
		this.mState_Mode = _State;
	}

	// ===========================================================
	// Physic Engine Setup
	// ===========================================================

//	public Engine onLoadEngine() {
//		final Camera camera = new Camera(0, 0, VIEW_WIDTH, VIEW_HEIGHT);
//
//		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(VIEW_WIDTH,
//				VIEW_HEIGHT), camera));
//	}
//
//	public void onLoadResources() {
//		super.onPause();
//
//		this.mTexture = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//		TextureRegionFactory.setAssetBasePath("gfx/");
//
//		this.mCircleFaceTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "empty.png", 0,
//				32, 2, 1); // 64x32
//
//		this.mEngine.getTextureManager().loadTexture(this.mTexture);
//
//	}
//
//	public Scene onLoadScene() {
//		this.mEngine.registerUpdateHandler(new FPSLogger());
//
//		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, 0), false);
//
//		final Scene scene = new Scene(2);
//		scene.setBackground(new ColorBackground(0.0f, 0.0f, 0.0f));
//
//		final Shape ground = new Rectangle(0, VIEW_HEIGHT - 2, VIEW_WIDTH, 200);
//		final Shape roof = new Rectangle(0, 0, VIEW_WIDTH, VIEW_HEIGHT - BILLIARDS_HEIGHT - 2);
//		final Shape left = new Rectangle(-200, -200, 202, VIEW_HEIGHT + 200);
//		final Shape right = new Rectangle(BILLIARDS_WIDTH + 2, 0, VIEW_WIDTH - BILLIARDS_WIDTH, VIEW_HEIGHT);
//
//		ground.setColor(0.0f, 0.0f, 0.0f);
//		roof.setColor(0.0f, 0.0f, 0.0f);
//		left.setColor(0.0f, 0.0f, 0.0f);
//		right.setColor(0.0f, 0.0f, 0.0f);
//
//		mShapeGroundWall = ground;
//		mShapeRoofWall = roof;
//		mShapeLeftWall = left;
//		mShapeRightWall = right;
//
//		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(1, 0.1f, 0.7f);
//		PhysicsFactory.createBoxBody(this.mPhysicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
//		PhysicsFactory.createBoxBody(this.mPhysicsWorld, roof, BodyType.StaticBody, wallFixtureDef);
//		PhysicsFactory.createBoxBody(this.mPhysicsWorld, left, BodyType.StaticBody, wallFixtureDef);
//		PhysicsFactory.createBoxBody(this.mPhysicsWorld, right, BodyType.StaticBody, wallFixtureDef);
//
//		scene.getBottomLayer().addEntity(ground);
//		scene.getBottomLayer().addEntity(roof);
//		scene.getBottomLayer().addEntity(left);
//		scene.getBottomLayer().addEntity(right);
//
//		scene.registerUpdateHandler(this.mPhysicsWorld);
//
//		// 충돌 체크 하여 벽에 달라 붙는 현상 방지...
//		scene.registerUpdateHandler(new IUpdateHandler() {
//
//			public void reset() {
//			}
//
//			@Override
//			public void onUpdate(final float pSecondsElapsed) {
//				for (int i = 0; i < BALL_MAX_COUNT; i++) {
//					if (mFace[i] == null || mBody[i] == null)
//						return;
//
//					if (mFace[i].collidesWith(mShapeGroundWall)) {
//						mBody[i].setLinearVelocity(new Vector2(mBody[i].getLinearVelocity().x, mBody[i]
//								.getLinearVelocity().y - 0.005f));
//					} else if (mFace[i].collidesWith(mShapeRoofWall)) {
//						mBody[i].setLinearVelocity(new Vector2(mBody[i].getLinearVelocity().x, mBody[i]
//								.getLinearVelocity().y + 0.005f));
//					} else if (mFace[i].collidesWith(mShapeRightWall)) {
//						mBody[i].setLinearVelocity(new Vector2(mBody[i].getLinearVelocity().x - 0.005f, mBody[i]
//								.getLinearVelocity().y));
//					} else if (mFace[i].collidesWith(mShapeLeftWall)) {
//						mBody[i].setLinearVelocity(new Vector2(mBody[i].getLinearVelocity().x + 0.005f, mBody[i]
//								.getLinearVelocity().y));
//					}
//				}
//			}
//		});
//		return scene;
//	}
//
	private void addFace(final float _pX, final float _pY) {
//		final Scene scene = this.mEngine.getScene();
//		final AnimatedSprite face;
//		final Body body;
//		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(1, 0.98f, 0.5f);

//		face = new AnimatedSprite(_pX, _pY, this.mCircleFaceTextureRegion);
//		face.setScale(BALL_PHYSICSWORLD_SCALE);
//		body = PhysicsFactory.createCircleBody(getPhysicsWorld(), face, BodyType.DynamicBody, objectFixtureDef);

//		body.setLinearDamping(BALL_LINEAR_DAMPING);
//		body.setAngularDamping(BALL_ANGULAR_DAMPING);
		mBody[mCurrent_Ball_Count] = new Vector2(_pX, _pY);
		mRendererRealWorld.setBallBody(this.mCurrent_Ball_Count, mBody[mCurrent_Ball_Count], mCurrent_Ball_Count);

		//getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(face, body, true, true, false, false));
		
		// face.animate(new long[] { 200, 200 }, 0, 1, true);
		// face.setUpdatePhysics(false);

//		scene.registerTouchArea(face);
//		scene.getTopLayer().addEntity(face);

		//mFace[mCurrent_Ball_Count] = face;
		//;
	}

//	public void onLoadComplete() {
//		// x = 0~ 130 y = 420 ~ 700
//		addBall((int) (Math.random() * 130.0f), (int) (Math.random() * 180.0f) + 420);
//		this.mCurrent_Ball_Count++;
//		addBall((int) (Math.random() * 130.0f), (int) (Math.random() * 180.0f) + 420);
//		this.mCurrent_Ball_Count++;
//		addBall((int) (Math.random() * 130.0f), (int) (Math.random() * 180.0f) + 420);
//		this.mCurrent_Ball_Count++;
//		// addBall(44,517);
//		startActivity(new Intent(this, Empty.class));
//	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	// 모드 변환 버튼의 리스너
	class FreeModeButtonListener implements Button.OnClickListener {

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		public void onClick(View v) {
			setVisibleBallControlContainer(false);
		}
	}

	// 모드 변환 버튼의 리스너
	class ModeButtonListener implements Button.OnClickListener {

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		public void onClick(View _View) {
			if (_View.getId() == R.id.button_SimulationMode) {
				mState_Mode = MODE_SIMULATION_READY;
				// if (!mSimulation)
				// mState_Mode = MODE_MAIN;

				setVisibleVariableButton(true);
				setVisibleBallControlContainer(false);
			} else if (_View.getId() == R.id.button_FreeMode) {
				mState_Mode = MODE_FREE_INITIAL;
				setVisibleVariableButton(true);
				mCurrent_Ball_Count = 0;
			}

			mButtonSiulmationMode.setVisibility(View.INVISIBLE);
			mButtonFreeMode.setVisibility(View.INVISIBLE);
			mButtonModeCancel.setVisibility(View.INVISIBLE);
			mModeContainer.setVisibility(View.INVISIBLE);
		}
	}

	public int getSpinAmount() {
		return this.mSpinAmount;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		if (seekBar.getId() == R.id.seekbar_BallControl_Spin) {
			this.mSpinAmount = progress;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}
}
