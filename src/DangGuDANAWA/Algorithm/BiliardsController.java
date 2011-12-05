package DangGuDANAWA.Algorithm;

import java.util.Vector;

import DangGuDANAWA.Algorithm.MainView.DrawThread;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class BiliardsController {
	private final static int NO_OF_BALLS = 3;	
	private final static int MAX_POWER = 16;		
	private final static int MASS = 1;	
	private final static float MODULUS = 0.9f; 
	private final static float R_MODULUS= 0.5f;
	private final static float BALLSIZE = 6.55f;		//1.41
	private final static float R_FRICTION = 0.2f;
	private final static float V_FRICTION = 0.1f;
	private final static int FRICTIONCNT = 3;	
	private final static int IMAGEBALL_FRAME = 100;
	
	float sin_array[] = {0.0f, 0.0175f, 0.0349f, 0.0523f, 0.0698f, 0.0872f, 0.1045f, 0.1219f, 0.1392f, 0.1564f, 0.1736f, 0.1908f, 0.2079f, 0.2250f, 0.2419f, 0.2588f, 0.2756f, 0.2924f, 0.3090f, 0.3256f, 0.3420f, 0.3584f, 0.3746f, 0.3907f, 0.4067f, 0.4226f, 0.4384f, 0.4540f, 0.4695f, 0.4848f, 0.5000f, 0.5150f, 0.5299f, 0.5446f, 0.5592f, 0.5736f, 0.5878f, 0.6018f, 0.6157f, 0.6293f, 0.6428f, 0.6561f, 0.6691f, 0.6820f, 0.6947f, 0.7071f, 0.7193f, 0.7314f, 0.7431f, 0.7547f, 0.7660f, 0.7771f, 0.7880f, 0.7986f, 0.8090f, 0.8192f, 0.8290f, 0.8387f, 0.8480f, 0.8572f, 0.8660f, 0.8746f, 0.8829f, 0.8910f, 0.8988f, 0.9063f, 0.9135f, 0.9205f,
			0.9272f, 0.9336f, 0.9397f, 0.9455f, 0.9511f, 0.9563f, 0.9613f, 0.9659f, 0.9703f, 0.9744f, 0.9781f, 0.9816f, 0.9848f, 0.9877f, 0.9903f, 0.9925f, 0.9945f, 0.9962f, 0.9976f, 0.9986f, 0.9994f, 0.9998f, 1.0000f, 0.9998f, 0.9994f, 0.9986f, 0.9976f, 0.9962f, 0.9945f, 0.9925f, 0.9903f, 0.9877f, 0.9848f, 0.9816f, 0.9781f, 0.9744f, 0.9703f, 0.9659f, 0.9613f, 0.9563f, 0.9511f, 0.9455f, 0.9397f, 0.9336f, 0.9272f, 0.9205f, 0.9135f, 0.9063f, 0.8988f, 0.8910f, 0.8829f, 0.8746f, 0.8660f, 0.8572f, 0.8480f, 0.8387f, 0.8290f, 0.8192f, 0.8090f, 0.7986f, 0.7880f, 0.7771f, 0.7660f, 0.7547f, 0.7431f, 0.7314f, 0.7193f, 0.7071f,
			0.6947f, 0.6820f, 0.6691f, 0.6561f, 0.6428f, 0.6293f, 0.6157f, 0.6018f, 0.5878f, 0.5736f, 0.5592f, 0.5446f, 0.5299f, 0.5150f, 0.5000f, 0.4848f, 0.4695f, 0.4540f, 0.4384f, 0.4226f, 0.4067f, 0.3907f, 0.3746f, 0.3584f, 0.3420f, 0.3256f, 0.3090f, 0.2924f, 0.2756f, 0.2588f, 0.2419f, 0.2250f, 0.2079f, 0.1908f, 0.1736f, 0.1564f, 0.1392f, 0.1219f, 0.1045f, 0.0872f, 0.0698f, 0.0523f, 0.0349f, 0.0175f, 0.0f, -0.0174f, -0.0348f, -0.0522f, -0.0697f, -0.0871f, -0.1045f, -0.1218f, -0.1391f, -0.1563f, -0.1735f, -0.1907f, -0.2078f, -0.2249f, -0.2418f, -0.2587f, -0.2755f, -0.2923f, -0.3089f, -0.3255f, -0.3419f,
			-0.3583f, -0.3745f, -0.3906f, -0.4066f, -0.4225f, -0.4383f, -0.4539f, -0.4694f, -0.4847f, -0.4999f, -0.5149f, -0.5298f, -0.5445f, -0.5591f, -0.5735f, -0.5877f, -0.6017f, -0.6156f, -0.6292f, -0.6427f, -0.6560f, -0.6690f, -0.6819f, -0.6946f, -0.7070f, -0.7192f, -0.7313f, -0.7430f, -0.7546f, -0.7659f, -0.7770f, -0.7879f, -0.7985f, -0.8089f, -0.8191f, -0.8289f, -0.8386f, -0.8479f, -0.8571f, -0.8659f, -0.8745f, -0.8828f, -0.8909f, -0.8987f, -0.9062f, -0.9134f, -0.9204f, -0.9271f, -0.9335f, -0.9396f, -0.9454f, -0.9510f, -0.9562f, -0.9612f, -0.9658f, -0.9702f, -0.9743f, -0.9780f, -0.9815f, -0.9847f, -0.9876f, -0.9902f, -0.9924f, -0.9944f, -0.9961f, -0.9975f, -0.9985f, -0.9993f, -0.9997f, -0.9999f, -0.9997f, -0.9993f, -0.9985f, -0.9975f, -0.9961f, 
			-0.9944f, -0.9924f, -0.9902f, -0.9876f, -0.9847f, -0.9815f, -0.9780f, -0.9743f, -0.9702f, -0.9658f, -0.9612f, -0.9562f, -0.9510f, -0.9454f, -0.9396f, -0.9335f, -0.9271f, -0.9204f, -0.9134f, -0.9062f, -0.8987f, -0.8909f, -0.8828f, -0.8745f, -0.8659f, -0.8571f, -0.8479f, -0.8386f, -0.8289f, -0.8191f, -0.8089f, -0.7985f, -0.7879f, -0.7770f, -0.7659f, -0.7546f, -0.7430f, -0.7313f, -0.7192f, -0.7070f, -0.6946f, -0.6819f, -0.6690f, -0.6560f, -0.6427f, 
			-0.6292f, -0.6156f, -0.6017f, -0.5877f, -0.5735f, -0.5591f, -0.5445f, -0.5298f, -0.5149f, -0.4999f, -0.4847f, -0.4694f, -0.4539f, -0.4383f, -0.4225f, -0.4066f, -0.3906f, -0.3745f, -0.3583f, -0.3419f, -0.3255f, -0.3089f, -0.2923f, -0.2755f, -0.2587f, -0.2418f, -0.2249f, -0.2078f, -0.1907f, -0.1735f, -0.1563f, -0.1391f, -0.1218f, -0.1044f, -0.0871f, -0.0697f, -0.0522f, -0.0348f, -0.0174f};

	float cos_array[] = {1.0f, 0.9998f, 0.9994f, 0.9986f, 0.9976f, 0.9962f, 0.9945f, 0.9925f, 0.9903f, 0.9877f, 0.9848f, 0.9816f, 0.9781f, 0.9744f, 0.9703f, 0.9659f, 0.9613f, 0.9563f, 0.9511f, 0.9455f, 0.9397f, 0.9336f, 0.9272f, 0.9205f, 0.9135f, 0.9063f, 0.8988f, 0.8910f, 0.8829f, 0.8746f, 0.8660f, 0.8572f, 0.8480f, 0.8387f, 0.8290f, 0.8192f, 0.8090f, 0.7986f, 0.7880f, 0.7771f, 0.7660f, 0.7547f, 0.7431f, 0.7314f, 0.7193f, 0.7071f, 0.6947f, 0.6820f, 0.6691f, 0.6561f, 0.6428f, 0.6293f, 0.6157f, 0.6018f, 0.5878f, 0.5736f, 0.5592f, 0.5446f, 0.5299f, 0.5150f, 0.5000f, 0.4848f, 0.4695f, 0.4540f, 0.4384f, 0.4226f, 0.4067f, 0.3907f, 0.3746f, 0.3584f, 0.3420f, 0.3256f, 0.3090f, 0.2924f, 0.2756f, 0.2588f, 0.2419f, 0.2250f, 0.2079f, 0.1908f, 0.1736f, 0.1564f, 0.1392f, 0.1219f, 0.1045f,
			0.0872f, 0.0698f, 0.0523f, 0.0349f, 0.0175f, 0.0000f, -0.0174f, -0.0348f, -0.0522f, -0.0697f, -0.0871f, -0.1044f, -0.1218f, -0.1391f, -0.1563f, -0.1735f, -0.1907f, -0.2078f, -0.2249f, -0.2418f, -0.2587f, -0.2755f, -0.2923f, -0.3089f, -0.3255f, -0.3419f, -0.3583f, -0.3745f, -0.3906f, -0.4066f, -0.4225f, -0.4383f, -0.4539f, -0.4694f, -0.4847f, -0.4999f, -0.5149f, -0.5298f, -0.5445f, -0.5591f, -0.5735f, -0.5877f, -0.6017f, -0.6156f, -0.6292f, -0.6427f, -0.6560f, -0.6690f, -0.6819f, -0.6946f, -0.7070f, -0.7192f, -0.7313f, -0.7430f, -0.7546f, -0.7659f, -0.7770f, -0.7879f, -0.7985f, -0.8089f, -0.8191f, -0.8289f, -0.8386f, -0.8479f, -0.8571f, -0.8659f, -0.8745f, -0.8828f, -0.8909f, -0.8987f, -0.9062f, -0.9134f, -0.9204f, -0.9271f, -0.9335f, -0.9396f, 
			-0.9454f, -0.9510f, -0.9562f, -0.9612f, -0.9658f, -0.9702f, -0.9743f, -0.9780f, -0.9815f, -0.9847f, -0.9876f, -0.9902f, -0.9924f, -0.9944f, -0.9961f, -0.9975f, -0.9985f, -0.9993f, -0.9997f, -0.9999f, -0.9997f, -0.9993f, -0.9985f, -0.9975f, -0.9961f, -0.9944f, -0.9924f, -0.9902f, -0.9876f, -0.9847f, -0.9815f, -0.9780f, -0.9743f, -0.9702f, -0.9658f, -0.9612f, -0.9562f, -0.9510f, -0.9454f, -0.9396f, -0.9335f, -0.9271f, -0.9204f, -0.9134f, -0.9062f, -0.8987f, -0.8909f, -0.8828f, -0.8745f, -0.8659f, -0.8571f, -0.8479f, -0.8386f, -0.8289f, -0.8191f, -0.8089f, -0.7985f, -0.7879f, -0.7770f, -0.7659f, -0.7546f, -0.7430f, -0.7313f, -0.7192f, -0.7070f, -0.6946f, -0.6819f, -0.6690f, -0.6560f, -0.6427f, -0.6292f, -0.6156f, -0.6017f, -0.5877f, -0.5735f, 
			-0.5591f, -0.5445f, -0.5298f, -0.5149f, -0.4999f, -0.4847f, -0.4694f, -0.4539f, -0.4383f, -0.4225f, -0.4066f, -0.3906f, -0.3745f, -0.3583f, -0.3419f, -0.3255f, -0.3089f, -0.2923f, -0.2755f, -0.2587f, -0.2418f, -0.2249f, -0.2078f, -0.1907f, -0.1735f, -0.1563f, -0.1391f, -0.1218f, -0.1044f, -0.0871f, -0.0697f, -0.0522f, -0.0348f, -0.0175f, 0.0000f, 0.0175f, 0.0349f, 0.0523f, 0.0698f, 0.0872f, 0.1045f, 0.1219f, 0.1392f, 0.1564f, 0.1736f, 0.1908f, 0.2079f,
			0.2250f, 0.2419f, 0.2588f, 0.2756f, 0.2924f, 0.3090f, 0.3256f, 0.3420f, 0.3584f, 0.3746f, 0.3907f, 0.4067f, 0.4226f, 0.4384f, 0.4540f, 0.4695f, 0.4848f, 0.5000f, 0.5150f, 0.5299f, 0.5446f, 0.5592f, 0.5736f, 0.5878f, 0.6018f, 0.6157f, 0.6293f, 0.6428f, 0.6561f, 0.6691f, 0.6820f, 0.6947f, 0.7071f, 0.7193f, 0.7314f, 0.7431f, 0.7547f, 0.7660f, 0.7771f, 0.7880f, 0.7986f, 0.8090f, 0.8192f, 0.8290f, 0.8387f, 0.8480f, 0.8572f, 0.8660f, 0.8746f, 0.8829f, 0.8910f, 0.8988f, 0.9063f, 0.9135f, 0.9205f, 0.9272f, 0.9336f, 0.9397f, 0.9455f, 0.9511f, 0.9563f, 0.9613f, 0.9659f, 0.9703f, 0.9744f, 0.9781f, 0.9816f, 0.9848f, 0.9877f, 0.9903f, 0.9925f, 0.9945f, 0.9962f, 0.9976f, 0.9986f, 0.9994f, 0.9998f};
	private int m_nFrictionCnt;
	public boolean m_bSpaceKey;
	private boolean m_bFontKey;
	public boolean m_bStoppedAllBalls;
	public boolean m_bShowWay;
	public boolean m_bImageFriction;
	private Rect m_PanSize;
	
	
	public BallEntity m_Balls[];
	public CueEntity m_Cue;
	public DrawThread m_Drawthd;
	
	private float m_BallAngle[];
	private ResultEntity m_tmpResult;
	public Vector<ResultEntity> m_arrResult;
	private boolean m_bRedBall;
	private boolean m_bYellowBall;
	
	public BiliardsController() {
		m_bRedBall = false;
		m_bYellowBall = false;
		m_BallAngle = new float[2];
		m_arrResult = new Vector<ResultEntity>();
		m_Cue = new CueEntity(); 
		m_bSpaceKey = true;				// 공 치는 스페이스바
		m_bFontKey  = false;			// 디버그용 숫자 확인 키
		m_nFrictionCnt = 5;				// 마찰	 계수 계산용
		m_Cue.setAngle(0.0f);			// 기본 큐의 각도
		m_Cue.setPower(16);				// 기본 큐의 파워
		m_Cue.setrX(0.0f);				// 기본 큐의 당점X
		m_Cue.setrY(0.0f);				// 기본 큐의 당점Y
		m_bStoppedAllBalls = true;		// 모든 공들이 멈추어 있는가
		m_bShowWay = false;				// 길 보여주기 
		m_bImageFriction = false;		// 길 보여주기 모드에서 마찰력의 작성
		m_PanSize = new Rect(0, 0, 122, 244);	// 당구대의 크기를 초기화
		InitBalls();
	}
	public BiliardsController(DrawThread drawThread) {
		m_BallAngle = new float[2];
		m_arrResult = new Vector<ResultEntity>();
		m_Cue = new CueEntity(); 
		m_bSpaceKey = true;				// 공 치는 스페이스바
		m_bFontKey  = false;			// 디버그용 숫자 확인 키
		m_nFrictionCnt = 5;				// 마찰	 계수 계산용
		m_Cue.setAngle(0.0f);			// 기본 큐의 각도
		m_Cue.setPower(16);				// 기본 큐의 파워
		m_Cue.setrX(0.0f);				// 기본 큐의 당점X
		m_Cue.setrY(0.0f);				// 기본 큐의 당점Y
		m_bStoppedAllBalls = true;		// 모든 공들이 멈추어 있는가
		m_bShowWay = false;				// 길 보여주기 
		m_bImageFriction = false;		// 길 보여주기 모드에서 마찰력의 작성
		m_Drawthd = drawThread;			// 그리기를 위한 변수 초기화
		m_PanSize = drawThread.PanSize;	// 당구대의 크기를 초기화
		InitBalls();
	}
	
	// 공 움직이는 함수 
	void MoveBalls()
	{
		//MovePointOfBall();
		Log.i("moveX_Y", "vx = "+m_Balls[0].getVx()+" vy = "+m_Balls[0].getVy());
		CollisionEffect();
		if(m_bImageFriction)
			FrictionEffect();
		CushionEffect();

		int nStoppedBallCnt = 0;
		
		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			float x = m_Balls[i].getX();
			float y = m_Balls[i].getY();
			float vx = m_Balls[i].getVx();
			float vy = m_Balls[i].getVy();
		
			m_Balls[i].setX(x += vx);	
			m_Balls[i].setY(y += vy);
			
			if(x <= 0)
				m_Balls[i].setX(0);
			else if(x + BALLSIZE >= m_PanSize.right)
				m_Balls[i].setX( m_PanSize.right - BALLSIZE );
			
			if(y <= 0)
				m_Balls[i].setY( 0 );
			else if(y + BALLSIZE >= m_PanSize.bottom)
				m_Balls[i].setY( m_PanSize.bottom - BALLSIZE );

//			if(Math.abs(vx) <= 1.0f)
//				m_Balls[i].setVx(0.0f);
//			if(Math.abs(vy) <= 1.0f)
//				m_Balls[i].setVy(0.0f);
			
			if(vx <= 0)
				nStoppedBallCnt++;
			if(vy <= 0)
				nStoppedBallCnt++;
		}	

		if(nStoppedBallCnt/2 == NO_OF_BALLS)
			m_bStoppedAllBalls = true;
		else
			m_bStoppedAllBalls = false;
	}


	// 충돌 체크 함수 
	boolean CheckCollision(BallEntity waterBall, BallEntity redBall)
	{
		// 공이 멈추었는지 확인
		if(waterBall.getVx() == 0 && waterBall.getVy() == 0 && redBall.getVx() == 0 && redBall.getVy() == 0)
			return false;
		// 공의 충돌 확인
		if( 
				(redBall.getX() - waterBall.getX() ) * ( redBall.getX() - waterBall.getX() )  
				+ ( redBall.getY() - waterBall.getY() ) * ( redBall.getY() - waterBall.getY() )  
				<= BALLSIZE*BALLSIZE
				)
		{
			// 공의 충돌 확인!!
			if( waterBall.index == 1 )
			{
				if(redBall.index == 2)
				{
					m_tmpResult.ResultCushion.add(Cushion.REDBALL);
					m_bRedBall = true;
				}
				else if(redBall.index == 3)
				{
					m_tmpResult.ResultCushion.add(Cushion.YELLOWBALL);
					m_bYellowBall = true;
				}
			}
			if( waterBall.index == 2)
				if(redBall.index == 3)
					m_tmpResult.ResultCushion.add(Cushion.KISS);
			
			return true;
		}
		return false;
	}

	
	// 공의 초기화 함수
	void InitBalls()
	{
		m_Balls = new BallEntity[NO_OF_BALLS];
		for(int i=0; i<NO_OF_BALLS; i++)
			m_Balls[i] = new BallEntity();
		
		
		m_Balls[0].setX( (m_PanSize.right/2 - BALLSIZE/2) );
		m_Balls[0].setY( (m_PanSize.bottom - m_PanSize.bottom/3) );
		m_Balls[0].setPx( BALLSIZE / 2.0f );
		m_Balls[0].setPy( BALLSIZE / 2.0f );
		m_Balls[0].VisiblePoint = true;
		m_Balls[0].setVx( 0.0f ); 
		m_Balls[0].setVy( 0.0f );
		m_Balls[0].setRx(0.0f);
		m_Balls[0].setRy(0.0f);
		m_Balls[0].index = 1;
		m_Balls[0].color = 1;

		m_Balls[1].setX( (m_PanSize.right/2 - BALLSIZE/2) );
		m_Balls[1].setY( (m_PanSize.bottom/3) );
		m_Balls[1].setPx( BALLSIZE / 2.0f );
		m_Balls[1].setPy( BALLSIZE / 2.0f);
		m_Balls[1].VisiblePoint = true;
		m_Balls[1].setVx( 0.0f ); 
		m_Balls[1].setVy( 0.0f );
		m_Balls[1].setRx(0.0f);
		m_Balls[1].setRy(0.0f);
		m_Balls[1].index = 2;
		m_Balls[1].color = 1;
		
		//m_Balls[2].setX( (m_PanSize.right/2 - BALLSIZE/2) + 20 );
		//m_Balls[2].setY( (m_PanSize.bottom - m_PanSize.bottom/3) );
		
		m_Balls[2].setX( m_PanSize.left + 40 );
		m_Balls[2].setY( m_PanSize.bottom - 40 );
		m_Balls[2].setPx( BALLSIZE / 2 );
		m_Balls[2].setPy( BALLSIZE / 2 );
		m_Balls[2].VisiblePoint = true;
		m_Balls[2].setVx( 0.0f ); 
		m_Balls[2].setVy( 0.0f );
		m_Balls[2].setRx(0.0f);
		m_Balls[2].setRy(0.0f);
		m_Balls[2].index = 3;
		m_Balls[2].color = 1;
	}
	
	private void FindAngle()
	{
		//rotation=(float) (Math.atan( m_Balls[1].getY()-m_Balls[0].getY() / (m_Balls[1].getX()-m_Balls[0].getX()) ) * 180 / Math.PI);
		for(int i=1; i < NO_OF_BALLS; i++)
			m_BallAngle[i-1] = (float) (Math.atan2(m_Balls[i].getY()-m_Balls[0].getY(), m_Balls[i].getX()-m_Balls[0].getX()) * 180.0f / Math.PI) + 180.0f;
			
		//m_Cue.setAngle( rotation + 180.0f );
	}


	// 충돌 효과 함수 
	void CollisionEffect()
	{
		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			for(int j = i + 1 ; j < NO_OF_BALLS ; j++)
			{
				if(CheckCollision(m_Balls[i],m_Balls[j]))
				{
					Collision_VelocityEffecter(m_Balls[i], m_Balls[j]);		// 충돌시 속도의 변화적용 함수
					Collision_RotationEffecter(m_Balls[i], m_Balls[j]);		// 충돌시 회전력의 변화적용 함수
					
					if(m_Balls[i].getVx() * m_Balls[i].getVx() * 3 < m_Balls[i].getRx() * m_Balls[i].getRx() )
					{
						m_Balls[i].setRx( m_Balls[i].getRx()/2 );
						m_Balls[i].setVx( m_Balls[i].getRx() );
					}
					
					if(m_Balls[i].getVy() * m_Balls[i].getVy() * 3 < m_Balls[i].getRy() * m_Balls[i].getRy() )
					{
						m_Balls[i].setRy( m_Balls[i].getRy()/2 );
						m_Balls[i].setVy( m_Balls[i].getRy() );
					}

					if(m_Balls[j].getVx() * m_Balls[j].getVx() * 3 < m_Balls[i].getRx() * m_Balls[i].getRx() )
					{
						m_Balls[j].setRx( m_Balls[j].getRx()/2 );
						m_Balls[j].setVx( m_Balls[j].getRx() );
					}
					
					if(m_Balls[j].getVy() * m_Balls[j].getVy() * 3 < m_Balls[i].getRy() * m_Balls[i].getRy() )
					{
						m_Balls[j].setRy( m_Balls[j].getRy()/2 );
						m_Balls[j].setVy( m_Balls[j].getRy());
					}
				}
			}
		}

	}

	// 마찰력 적용 함수
	void FrictionEffect()
	{
		float s;
		float costheta, sintheta;

		if(m_nFrictionCnt==0)
		{
			m_nFrictionCnt=FRICTIONCNT;
		}
		else
		{
			m_nFrictionCnt--;
			return;
		}

		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			float vx = m_Balls[i].getVx();
			float vy = m_Balls[i].getVy();
			float rx = m_Balls[i].getRx();
			float ry = m_Balls[i].getRy();
			
			if(!(vx == 0 && vy == 0))
			{
				s = (float) Math.sqrt( (float)(vx * vx) + (vy * vy) );
				
				if(s!=0){
					costheta = vx / s;
					sintheta = vy / s;
					
					s = s - V_FRICTION;
					
					if(s<=0)
					{
						m_Balls[i].setVx(0.0f);
						m_Balls[i].setVy(0.0f);
					}
					else
					{	
						m_Balls[i].setVx( s * costheta );	
						m_Balls[i].setVy( s * sintheta );
					}
				}
			}

			if(!(rx == 0 && ry == 0))
			{

				s = (float) Math.sqrt( (rx * rx ) + (ry * ry ) );
				
				if(s!=0)
				{
					costheta = rx / s;
					sintheta = ry / s;
					
					s = s - R_FRICTION;
					
					if(s<=0)
					{
						m_Balls[i].setRx(0);
						m_Balls[i].setRy(0);
					}
					else
					{	
						m_Balls[i].setRx( s * costheta );	
						m_Balls[i].setRy( s * sintheta );
					}
				}
			}
		}
	}

	// 공을 치는 함수 
	void ShotBall()
	{
		float vx=0, vy=0, rx=0, ry=0;
		float radianAngle = getRadian(m_Cue.getAngle());
		
		vx = (float) ( - ( m_Cue.getPower() * Math.cos(radianAngle) ) );
		vy = (float) ( - ( m_Cue.getPower() * Math.sin(radianAngle) ) );

		ry = (float) ( -( m_Cue.getrX() * Math.cos(radianAngle) - m_Cue.getrY() * Math.sin(radianAngle)) * m_Cue.getPower());
		rx = (float) (  ( m_Cue.getrX() * Math.sin(radianAngle) + m_Cue.getrY() * Math.cos(radianAngle)) * m_Cue.getPower());
		Log.i("angle","rx = "+rx +"  ry = "+ry);
		
		m_Balls[0].setVx( vx );
		m_Balls[0].setVy( vy );

		m_Balls[0].setRx( rx );
		m_Balls[0].setRy( ry );
	}

	
	// 공 그리기 함수
	void DrawBalls(Canvas canvas)
	{
		float x,y;
		Paint pnt = new Paint();
		pnt.setAntiAlias(true);

		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
		// Draw Ball
			x = m_Balls[i].getX();		
			y = m_Balls[i].getY();		
			
			if(m_Balls[i].color == 1)
			{
				if(i == 0)
					pnt.setColor(Color.WHITE);
				else if( i == 1)
					pnt.setColor(Color.RED);
				else
					pnt.setColor(Color.YELLOW);
			}
			else
			{
				pnt.setColor(Color.GRAY);
				m_Balls[i].color--;
			}
			//canvas.drawCircle(x , y , BALLSIZE, pnt);
			canvas.drawOval(new RectF(x, y, x+BALLSIZE, y+BALLSIZE), pnt);

		// Draw Text
			if(m_bFontKey)
			{
				pnt.setColor(Color.GREEN);
				String str = "" + m_Balls[i].getVx() +"," + m_Balls[i].getVy();
				canvas.drawText(str, x+5, y+14, pnt);
				str = "" + m_Balls[i].getRx() + "," + m_Balls[i].getRy();
				canvas.drawText(str, x+5, y+32, pnt);
			}

		// Draw Point
			if(m_Balls[i].VisiblePoint)
			{
				pnt.setColor(Color.BLACK);

				x  = m_Balls[i].getX();		
				x += m_Balls[i].getPx();	
				
				y  = m_Balls[i].getY();		
				y += m_Balls[i].getPy();		
				
				canvas.drawOval(new RectF(x-1, y-1, x+1, y+1), pnt);
			}
		}
	}


	// 큐 그리기 함수
	void DrawCue(Canvas canvas)
	{
		Paint  pnt = new Paint();
				
		m_Cue.setBeginX( (float) ((BALLSIZE/2+(m_Cue.getPower()*2)) * Math.cos(getRadian(m_Cue.getAngle()))) );
		m_Cue.setBeginY( (float) ((BALLSIZE/2+(m_Cue.getPower()*2)) * Math.sin(getRadian(m_Cue.getAngle()))) ) ;
		m_Cue.setEndX( (float) ((BALLSIZE/2+(m_Cue.getPower()*2)+20) * Math.cos(getRadian(m_Cue.getAngle()))) ) ;
		m_Cue.setEndY( (float) ((BALLSIZE/2+(m_Cue.getPower()*2)+20) * Math.sin(getRadian(m_Cue.getAngle()))) ) ;
		
		m_Cue.setBeginX(m_Cue.getBeginX() + m_Balls[0].getX());
		m_Cue.setBeginY(m_Cue.getBeginY() + m_Balls[0].getY());
		m_Cue.setEndX(m_Cue.getEndX() + m_Balls[0].getX());
		m_Cue.setEndY(m_Cue.getEndY() + m_Balls[0].getY());
		
		float movex = m_Cue.getBeginX();
		float movey = m_Cue.getBeginY();
		float linex = m_Cue.getEndX();
		float liney = m_Cue.getEndY();
		
		pnt.setColor(Color.YELLOW);
		canvas.drawLine(movex + BALLSIZE/2 , movey + BALLSIZE/2, linex + BALLSIZE/2 , liney + BALLSIZE/2, pnt);
	}
	
	// 공을 치는 당점을 그리는 함수 
	void DrawShotPoint(Canvas canvas)
	{
		RectF m_FullSize;
		m_FullSize = new RectF(0, 0, m_Drawthd.Width, m_Drawthd.Height);
		Paint pnt = new Paint();
		pnt.setAntiAlias(true);
		pnt.setColor(Color.LTGRAY);
		
		// Draw Back
		canvas.drawRect(m_FullSize.right*2/3, m_FullSize.bottom - m_FullSize.right*1/3, m_FullSize.right, m_FullSize.bottom, pnt);
		
		pnt.setColor(Color.WHITE);
		
		pnt.setStrokeWidth(1);
		pnt.setStyle(Paint.Style.FILL);
		canvas.drawOval(new RectF(m_FullSize.right*2/3, m_FullSize.bottom - m_FullSize.right*1/3, m_FullSize.right, m_FullSize.bottom), pnt);
		
		pnt.setColor(Color.BLACK);
		pnt.setStyle(Paint.Style.STROKE);
		canvas.drawOval(new RectF(m_FullSize.right*2/3, m_FullSize.bottom - m_FullSize.right*1/3, m_FullSize.right, m_FullSize.bottom), pnt);
		
		canvas.drawLine(m_FullSize.right*5/6, m_FullSize.bottom - m_FullSize.right*1/3, m_FullSize.right*5/6, m_FullSize.bottom, pnt);
		canvas.drawLine(m_FullSize.right*2/3, m_FullSize.bottom - m_FullSize.right*1/6, m_FullSize.right, m_FullSize.bottom - m_FullSize.right*1/6, pnt);

	// Draw Point
		pnt.setStyle(Paint.Style.FILL);
		canvas.drawOval(new RectF(m_FullSize.right*5/6-5 + m_Cue.getrX()*6 , m_FullSize.bottom-m_FullSize.right*1/6-5 + m_Cue.getrY()*6,
					   m_FullSize.right*5/6+5 + m_Cue.getrX()*6 , m_FullSize.bottom-m_FullSize.right*1/6+5 + m_Cue.getrY()*6), pnt);
	}
	
	
	// 공의 당점 움직여서 구르는것 처럼 보여주는 함수
	void MovePointOfBall()
	{
		int i;
		float x = 0,y = 0;
		float radius = BALLSIZE/2;

		for(i = 0 ; i < NO_OF_BALLS ; i++)
		{
			float px = m_Balls[i].getPx(); 
			float py = m_Balls[i].getPy();
			float rx = m_Balls[i].getRx();
			float ry = m_Balls[i].getRy();
			
			x  = px;
			x += rx;
			x -= radius;

			y  = py;
			y += ry;
			y -= radius;
			
			if( x*x + y*y > (radius-2)*(radius-2) )
			{
				m_Balls[i].VisiblePoint = !m_Balls[i].VisiblePoint;
			}
			
			if(m_Balls[i].VisiblePoint)
			{
				m_Balls[i].setPx( px += rx );
				m_Balls[i].setPy( py += ry );
			}
			else
			{
				m_Balls[i].setPx( px -= rx );
				m_Balls[i].setPy( py -= ry );
			}
		}
	}
	

	// 충돌시 속도 변화 함수
	void Collision_VelocityEffecter(BallEntity waterBall, BallEntity redBall)
	{

		float vaxp,vayp,vbxp,vbyp,vay,vax,vbx,vby,costheta,sintheta;
		
		if( (redBall.getX() - waterBall.getX()) * (redBall.getX() - waterBall.getX()) 
				+ (redBall.getY() - waterBall.getY()) * (redBall.getY() - waterBall.getY()) < BALLSIZE*BALLSIZE )
		{ 
			waterBall.setX(waterBall.getX() - waterBall.getVx());
			waterBall.setY(waterBall.getY() - waterBall.getVy());
			redBall.setX(redBall.getX() - redBall.getVx());
			redBall.setY(redBall.getY() - redBall.getVy());
		}
		
		waterBall.color = 4;
		redBall.color = 4;
		
		vax = waterBall.getVx();	
		vay = waterBall.getVy();
		vbx = redBall.getVx();	
		vby = redBall.getVy();
		
	   	costheta = (waterBall.getX() - redBall.getX())/(BALLSIZE);		
		sintheta = (waterBall.getY() - redBall.getY())/(BALLSIZE);      
		
		
		vaxp = ( MASS*(1.0f-MODULUS)*(vax*costheta+vay*sintheta) + (1.0f+MODULUS)*MASS*(vbx*costheta+vby*sintheta) ) / (MASS+MASS);
		vayp = ( MASS*(1.0f-MODULUS)*(vax*(-sintheta)+vay*costheta) + (1.0f+MODULUS)*MASS*(vax*(-sintheta)+vay*costheta) ) / (MASS+MASS);
		
		vbxp = ( (1.0f+MODULUS)*MASS*(vax*costheta+vay*sintheta) + (1.0f-MODULUS)*MASS*(vbx*costheta+vby*sintheta) ) /(MASS+MASS);
		vbyp = ( (1.0f+MODULUS)*MASS*(vbx*(-sintheta)+vby*costheta) + (1.0f-MODULUS)*MASS*(vbx*(-sintheta)+vby*costheta) ) /(MASS+MASS);
		
		//vax = vaxp/1.0f*costheta+vayp/1.0f*(-sintheta);
		vax = vaxp*costheta+vayp*(-sintheta);
		vay = vaxp*sintheta+vayp*costheta;
		vbx = vbxp*costheta+vbyp*(-sintheta);	
		vby = vbxp*sintheta+vbyp*costheta;
		
		waterBall.setVx(vax);	
		waterBall.setVy(vay);
		redBall.setVx(vbx);
		redBall.setVy(vby);
		
		waterBall.setX(waterBall.getX() + waterBall.getVx());	
		waterBall.setY(waterBall.getY() + waterBall.getVy());
		redBall.setX(redBall.getX() + redBall.getVx());	
		redBall.setY(redBall.getY() + redBall.getVy());
	}



	// 충돌시 회전 변화 함수
	void Collision_RotationEffecter(BallEntity waterBall, BallEntity redBall)
	{
		float a_rx,a_ry,b_rx,b_ry;

		waterBall.setRx(waterBall.getRx() * R_MODULUS);
		waterBall.setRy(waterBall.getRy() * R_MODULUS);
		redBall.setRx(redBall.getRx() * R_MODULUS);
		redBall.setRy(redBall.getRy() * R_MODULUS);

		a_rx = waterBall.getRx();
		a_ry = waterBall.getRy();;
		b_rx = redBall.getRx();
		b_ry = redBall.getRy();

		waterBall.setRx( waterBall.getRx() + (-b_rx / 2) );
		waterBall.setRy( waterBall.getRy() + (-b_ry / 2) );
		redBall.setRx( redBall.getRx() + (-a_rx / 2) );
		redBall.setRy( redBall.getRy() + (-a_ry / 2) );
	}


	// 쿠션의 효과 함수
	void CushionEffect()
	{
		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			if(m_Balls[i].getX() <= m_PanSize.left) 
			{	
				m_Balls[i].setVx(m_Balls[i].getVx() * -MODULUS); 
			}
			if(m_Balls[i].getY() <= m_PanSize.top) 
			{	
				m_Balls[i].setVy(m_Balls[i].getVy() * -MODULUS); 
			}
			if(m_Balls[i].getX()+BALLSIZE >= m_PanSize.right  ) 
			{
				m_Balls[i].setVx(m_Balls[i].getVx() * -MODULUS); 
			}
			if(m_Balls[i].getY()+BALLSIZE >= m_PanSize.bottom )
			{	
				m_Balls[i].setVy(m_Balls[i].getVy() * -MODULUS); 
			}

			if(m_Balls[i].getX() + m_Balls[i].getVx() <= m_PanSize.left) 
			{
				m_Balls[i].setVx(m_Balls[i].getVx() * -MODULUS); 

				m_Balls[i].setVx(m_Balls[i].getVx() - m_Balls[i].getRx( )/ 2.0f);

				m_Balls[i].setRx(m_Balls[i].getRx()* R_MODULUS); 
				m_Balls[i].setRy(m_Balls[i].getRy()* R_MODULUS); 
			}
		
			if( m_Balls[i].getX()+BALLSIZE + m_Balls[i].getVx() >= m_PanSize.right ) 
			{
				m_Balls[i].setVx(m_Balls[i].getVx() * -MODULUS); 
				
				m_Balls[i].setVx(m_Balls[i].getVx() - m_Balls[i].getRx() / 2.0f);
				
				m_Balls[i].setRx(m_Balls[i].getRx() * R_MODULUS); 
				m_Balls[i].setRy(m_Balls[i].getRy() * R_MODULUS); 
			}
			
			if(m_Balls[i].getY() + m_Balls[i].getVy() <= m_PanSize.top) 
			{
				m_Balls[i].setVy(m_Balls[i].getVy() * -MODULUS); 
			
				if(m_Balls[i].getVx() >=0)
					m_Balls[i].setVy(m_Balls[i].getVy() - m_Balls[i].getRx()/2.0f);
				else
					m_Balls[i].setVy(m_Balls[i].getVy() + m_Balls[i].getRx()/2.0f);

				m_Balls[i].setRx(m_Balls[i].getRx() * R_MODULUS); 
				m_Balls[i].setRy(m_Balls[i].getRy() * R_MODULUS); 
			}
					
			if(m_Balls[i].getY()+BALLSIZE + m_Balls[i].getVy() >= m_PanSize.bottom ) 
			{
				m_Balls[i].setVy(m_Balls[i].getVy() * -MODULUS); 
				
				if(m_Balls[i].getVx() >=0)
					m_Balls[i].setVy(m_Balls[i].getVy() + m_Balls[i].getRx()/2.0f);
				else
					m_Balls[i].setVy(m_Balls[i].getVy() - m_Balls[i].getRx()/2.0f);

				m_Balls[i].setRx(m_Balls[i].getRx() * R_MODULUS); 
				m_Balls[i].setRy(m_Balls[i].getRy() * R_MODULUS); 
			}
		}
	}
	
	float getRadian(float angle)
	{
		return (float) (angle * Math.PI/180.0f);
	}
	 
	//길을 만드는 함수
	void DrawImageBall(Canvas canvas)
	{
		BallEntity imageBall[] = new BallEntity[NO_OF_BALLS];
		float vx=0, vy=0, rx=0, ry=0;
		float drawX, drawY; 
		float Radianangle = getRadian(m_Cue.getAngle());
		Paint pnt = new Paint();
		pnt.setAntiAlias(true);
		
		for(int j = 0 ; j < NO_OF_BALLS ; j++)
		{
			imageBall[j] = new BallEntity();
			imageBall[j].setX(m_Balls[j].getX());
			imageBall[j].setY(m_Balls[j].getY());	
			imageBall[j].setVx(m_Balls[j].getVx());
			imageBall[j].setVy(m_Balls[j].getVy());
			imageBall[j].setRx(m_Balls[j].getRx());
			imageBall[j].setRy(m_Balls[j].getRy());
//			imageBall[j].setPx(m_Balls[j].getPx());
//			imageBall[j].setPy(m_Balls[j].getPy());
			imageBall[j].index = m_Balls[j].index;
		}

		vx = (float) ( -(m_Cue.getPower() * Math.cos(Radianangle)) );
		vy = (float) ( -(m_Cue.getPower() * Math.sin(Radianangle)) );
		Log.i("math", "angle="+m_Cue.getAngle() + " int="+cos_array[(int) m_Cue.getAngle()]+" float="+Math.cos(Radianangle) );
		ry = (float) ( -( m_Cue.getrX() * Math.cos(Radianangle) - m_Cue.getrY() * Math.sin(Radianangle) ) * m_Cue.getPower() );
		rx = (float) ( ( m_Cue.getrX() * Math.sin(Radianangle) + m_Cue.getrY() * Math.cos(Radianangle) ) * m_Cue.getPower() );
		
		imageBall[0].setVx(vx);
		imageBall[0].setVy(vy);
		imageBall[0].setRx(rx);
		imageBall[0].setRy(ry);

		for(int j = 0 ; j < IMAGEBALL_FRAME ; j++)
		{
			pnt.setColor(Color.RED);
			pnt.setStrokeWidth(1);
			float tempX, tempY;
			drawX = imageBall[0].getX();
			drawY = imageBall[0].getY();
			
			tempX = drawX;
			tempY = drawY;
			
			MoveImageBalls(imageBall);
			
			drawX = imageBall[0].getX();		
			drawY =	imageBall[0].getY();
			
			canvas.drawLine( tempX + BALLSIZE/2.0f, tempY + BALLSIZE/2.0f, drawX + BALLSIZE/2.0f , drawY + BALLSIZE/2.0f ,pnt);
		}
	}
	
	// 공의 이동경로볼때 미리 공을 움직여 보는 함수
	void MoveImageBalls(BallEntity imageBall[])
	{
		ImageBallCollisionEffect(imageBall);

		if(false)//m_bImageFriction)
			ImageBallFrictionEffect(imageBall);

		ImageCushionEffect(imageBall);
		
		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			imageBall[i].setX(imageBall[i].getX() + imageBall[i].getVx());	
			imageBall[i].setY(imageBall[i].getY() + imageBall[i].getVy());
			
			if(imageBall[i].getX() <= 0)
				imageBall[i].setX(0);
			else if(imageBall[i].getX()+BALLSIZE >= m_PanSize.right)
				imageBall[i].setX( m_PanSize.right - BALLSIZE );
			
			if(imageBall[i].getY() <= 0)
				imageBall[i].setY(0);
			else if(imageBall[i].getY()+BALLSIZE >= m_PanSize.bottom)
				imageBall[i].setY( m_PanSize.bottom - BALLSIZE );
		}
	}
	
	// 미리 보기 함수에서 충돌효과 처리 함수
	void ImageBallCollisionEffect(BallEntity imageBall[])
	{
		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			for(int j = i+1 ; j < NO_OF_BALLS ; j++)
			{
				if(CheckCollision(imageBall[i],imageBall[j]))
				{
					Collision_VelocityEffecter(imageBall[i], imageBall[j]);
					Collision_RotationEffecter(imageBall[i], imageBall[j]);

					if(imageBall[i].getVx() * imageBall[i].getVx() * 3.0f < imageBall[i].getRx() * imageBall[i].getRx() )
					{
						imageBall[i].setRx(imageBall[i].getRx() / 2.0f);
						imageBall[i].setVx(imageBall[i].getRx());
					}
					
					if(imageBall[i].getVy() * imageBall[i].getVy() * 3.0f < imageBall[i].getRy() * imageBall[i].getRy() )
					{
						imageBall[i].setRy(imageBall[i].getRy() / 2.0f) ;
						imageBall[i].setVy(imageBall[i].getRy());
					}

					if(imageBall[j].getVx() * imageBall[j].getVx() * 3.0f < imageBall[i].getRx() * imageBall[i].getRx() )
					{
						imageBall[j].setRx(imageBall[j].getRx() / 2.0f);
						imageBall[j].setVx(imageBall[j].getRx());
					}
					
					if(imageBall[j].getVy() * imageBall[j].getVy() * 3.0f < imageBall[i].getRy() * imageBall[i].getRy() )
					{
						imageBall[j].setRy(imageBall[j].getRy() / 2.0f);
						imageBall[j].setVy(imageBall[j].getRy());
					}
				}
			}
		}
	}
	
	// 미리보기에서 마찰효과 주기 함수
	void ImageBallFrictionEffect(BallEntity imageBall[])
	{
		float costheta, sintheta, s;

		if(m_nFrictionCnt==0)
		{
			m_nFrictionCnt=FRICTIONCNT;
		}
		else
		{
			m_nFrictionCnt--;
			return;
		}


		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			if(!(imageBall[i].getVx() == 0 && imageBall[i].getVy() == 0))
			{
				s = (float) Math.sqrt( (imageBall[i].getVx() * imageBall[i].getVx()) + (imageBall[i].getVy() * imageBall[i].getVy()) );
				
				if(s!=0){
					costheta = imageBall[i].getVx() / s;
					sintheta = imageBall[i].getVy() / s;
					
					s = s - V_FRICTION;
					
					if(s<=0)
					{
						imageBall[i].setVx(0);
						imageBall[i].setVy(0);
					}
					else
					{	
						imageBall[i].setVx( s * costheta );	
						imageBall[i].setVy( s * sintheta );
					}
				}
			}

			if(!(imageBall[i].getRx() == 0 && imageBall[i].getRy() == 0))
			{
				s = (float) Math.sqrt( (imageBall[i].getRx() * imageBall[i].getRx()) + (imageBall[i].getRy() * imageBall[i].getRy()) );
				
				if(s!=0)
				{
					costheta = imageBall[i].getRx() / s;
					sintheta = imageBall[i].getRy() / s;
					
					s = s - R_FRICTION;
					
					if(s<=0)
					{
						imageBall[i].setRx(0);
						imageBall[i].setRy(0);
					}
					else
					{	
						imageBall[i].setRx(s * costheta);	
						imageBall[i].setRy(s * sintheta);
					}
				}
			}
		}
	}

	// 미리 보기 의 쿠션 효과 함수
	void ImageCushionEffect(BallEntity imageBall[])
	{
		for(int i = 0 ; i < NO_OF_BALLS ; i++)
		{
			if(imageBall[i].getX() <= m_PanSize.left) 
			{
				imageBall[i].setVx(imageBall[i].getVx() * -MODULUS);
			}	
			if(imageBall[i].getY() <= m_PanSize.top) 
			{
				imageBall[i].setVy(imageBall[i].getVy() * -MODULUS);
			}
			if(imageBall[i].getX() + BALLSIZE >= m_PanSize.right  ) 
			{
				imageBall[i].setVx(imageBall[i].getVx() * -MODULUS);
			}
			if(imageBall[i].getY() + BALLSIZE >= m_PanSize.bottom ) 
			{
				imageBall[i].setVy(imageBall[i].getVy() * -MODULUS);
			}

			if(imageBall[i].getX() + imageBall[i].getVx() <= m_PanSize.left) 
			{
				if( i == 0 )
					m_tmpResult.ResultCushion.add(Cushion.CUSHION);
				
				imageBall[i].setVx(imageBall[i].getVx() * -MODULUS); 

				imageBall[i].setVx(imageBall[i].getVx() - imageBall[i].getRx()/2.0f);

				imageBall[i].setRx(imageBall[i].getRx() * R_MODULUS); 
				imageBall[i].setRy(imageBall[i].getRy() * R_MODULUS); 
			}
		
			if( imageBall[i].getX()+BALLSIZE + imageBall[i].getVx() >= m_PanSize.right ) 
			{
				if( i == 0 )
					m_tmpResult.ResultCushion.add(Cushion.CUSHION);
				
				imageBall[i].setVx(imageBall[i].getVx() * -MODULUS); 
				
				imageBall[i].setVx(imageBall[i].getVx() - imageBall[i].getRx()/2.0f);

				imageBall[i].setRx(imageBall[i].getRx() * R_MODULUS); 
				imageBall[i].setRy(imageBall[i].getRy() * R_MODULUS); 
			}
			
			if( imageBall[i].getY() + imageBall[i].getVy() <= m_PanSize.top) 
			{
				if( i == 0 )
					m_tmpResult.ResultCushion.add(Cushion.CUSHION);
				
				imageBall[i].setVy(imageBall[i].getVy() * -MODULUS); 
			
				if(imageBall[i].getVx() >= 0.0f)
					imageBall[i].setVy(imageBall[i].getVy() - imageBall[i].getRx()/2.0f);
				else
					imageBall[i].setVy(imageBall[i].getVy() + imageBall[i].getRx()/2.0f);

				imageBall[i].setRx(imageBall[i].getRx() * R_MODULUS); 
				imageBall[i].setRy(imageBall[i].getRy() * R_MODULUS); 
			}
					
			if( imageBall[i].getY()+BALLSIZE + imageBall[i].getVy() >= m_PanSize.bottom ) 
			{
				if( i == 0 )
					m_tmpResult.ResultCushion.add(Cushion.CUSHION);
				
				imageBall[i].setVy(imageBall[i].getVy() * -MODULUS); 
				
				if(imageBall[i].getVx() >= 0.0f)
					imageBall[i].setVy(imageBall[i].getVy() + imageBall[i].getRx()/2.0f);
				else
					imageBall[i].setVy(imageBall[i].getVy() - imageBall[i].getRx()/2.0f); // 변경전값 20.0f

				imageBall[i].setRx(imageBall[i].getRx() * R_MODULUS); 
				imageBall[i].setRy(imageBall[i].getRy() * R_MODULUS); 
			}
		}
	}
	
	public void FindRoadAlgorithm()
	{
		FindAngle();
		for(int i=0; i<2; i++)
		{
			float startAngle = m_BallAngle[i] - 5.0f;
			float EndAngle = m_BallAngle[i] + 5.0f;
			for( ; startAngle < EndAngle; startAngle+=0.1f)
			{
				for(int power=5; power<MAX_POWER; power++)
				{	
					BallEntity imageBall[] = new BallEntity[NO_OF_BALLS];
					m_tmpResult = new ResultEntity();
					float vx=0, vy=0, rx=0, ry=0;
					float radianAngle = getRadian(m_Cue.getAngle());

					for(int j = 0 ; j < NO_OF_BALLS ; j++)
					{
						imageBall[j] = new BallEntity();
						imageBall[j].setX(m_Balls[j].getX());
						imageBall[j].setY(m_Balls[j].getY());	
						imageBall[j].setVx(m_Balls[j].getVx());
						imageBall[j].setVy(m_Balls[j].getVy());
						imageBall[j].setRx(m_Balls[j].getRx());
						imageBall[j].setRy(m_Balls[j].getRy());
						imageBall[j].index = m_Balls[j].index;
					}

					m_Cue.setAngle(startAngle);
					m_Cue.setPower(power);
					//m_Cue.setrX(power);
					m_tmpResult.Angle = startAngle;
					m_tmpResult.Power = power;

					vx = (float) ( -(m_Cue.getPower() * Math.cos(radianAngle)) );
					vy = (float) ( -(m_Cue.getPower() * Math.sin(radianAngle)) );
					ry = (float) ( -( m_Cue.getrX() * Math.cos(radianAngle) - m_Cue.getrY() * Math.sin(radianAngle) ) * m_Cue.getPower() );
					rx = (float) ( ( m_Cue.getrX() * Math.sin(radianAngle) + m_Cue.getrY() * Math.cos(radianAngle) ) * m_Cue.getPower() );					

					imageBall[0].setVx(vx);
					imageBall[0].setVy(vy);
					imageBall[0].setRx(rx);
					imageBall[0].setRy(ry);

					for(int j = 0 ; j < IMAGEBALL_FRAME ; j++)
						MoveImageBalls(imageBall);

					if(CheckAlgorithm())
						m_arrResult.add(m_tmpResult);
					
					m_tmpResult.ResultCushion.removeAllElements();
					
				}
			}
		}
	}
	
	public int m_nResultCount=0;
	
	// 찾은 길의 좌표점을 넘겨주는 함수
	public float[] getDrawRoad()
	{
		BallEntity imageBall[] = new BallEntity[NO_OF_BALLS];
		float vx=0, vy=0, rx=0, ry=0;
		float radianAngle = getRadian(m_Cue.getAngle());
		float resultXY[] = new float[IMAGEBALL_FRAME * 2];
		
		for(int j = 0 ; j < NO_OF_BALLS ; j++)
		{
			imageBall[j] = new BallEntity();
			imageBall[j].setX(m_Balls[j].getX());
			imageBall[j].setY(m_Balls[j].getY());	
			imageBall[j].setVx(m_Balls[j].getVx());
			imageBall[j].setVy(m_Balls[j].getVy());
			imageBall[j].setRx(m_Balls[j].getRx());
			imageBall[j].setRy(m_Balls[j].getRy());
			imageBall[j].index = m_Balls[j].index;
		}

		vx = (float) ( -(m_Cue.getPower() * Math.cos(radianAngle)) );
		vy = (float) ( -(m_Cue.getPower() * Math.sin(radianAngle)) );
		ry = (float) ( -( m_Cue.getrX() * Math.cos(radianAngle) - m_Cue.getrY() * Math.sin(radianAngle) ) * m_Cue.getPower() );
		rx = (float) ( ( m_Cue.getrX() * Math.sin(radianAngle) + m_Cue.getrY() * Math.cos(radianAngle) ) * m_Cue.getPower() );					
		
		imageBall[0].setVx(vx);
		imageBall[0].setVy(vy); 
		imageBall[0].setRx(rx);
		imageBall[0].setRy(ry);
		
		m_Cue.setAngle(m_arrResult.get(m_nResultCount).Angle);
		m_Cue.setPower(m_arrResult.get(m_nResultCount).Power);
		
		for(int j = 0 ; j < IMAGEBALL_FRAME ; j++)
		{
			int index = j*2;
			Log.i("ball Position", imageBall[0].getX() + " - " + imageBall[0].getY());
			resultXY[index] = imageBall[0].getX();
			resultXY[index+1] = imageBall[0].getY();			
			if( m_bRedBall && m_bYellowBall)
				break;
			MoveImageBalls(imageBall);
		}
		m_bRedBall = false;
		m_bYellowBall = false;
		return resultXY;
	}
	
	private boolean CheckAlgorithm()
	{
		int state = 0;
		int size = m_tmpResult.ResultCushion.size();
		boolean bFlag = true;
		Cushion RedFlag = Cushion.REDBALL;
		Cushion YellowFlag = Cushion.YELLOWBALL;
		
		if( size > 7 )
			return false;
		
		for(int i=0; i<size; i++)
		{
			Cushion flag = m_tmpResult.ResultCushion.get(i);
			if( bFlag && (flag.equals(Cushion.REDBALL) || flag.equals(Cushion.YELLOWBALL)) )
			{
				if( flag.equals(Cushion.YELLOWBALL))
				{
					RedFlag = Cushion.YELLOWBALL;
					YellowFlag = Cushion.REDBALL;
				}
				bFlag = false;
			}
			
			if(state >= 5)
				break;
			
			if(flag.equals(RedFlag))
			{
				switch (state) {
				case 0:	state=1;			break;
				case 1:	
				case 2:	
				case 3:	
				case 4:	
				case 6:	state=6;			break;
				case 5:	state=5;			break;
				}
			}
			else if(flag.equals(YellowFlag))
			{
				switch (state) {
				case 0:	
				case 1:	
				case 2:	
				case 3:	
				case 6:	state=6;			break;
				case 4:
				case 5:	state=5;			break;
				
				}
			}
			else if(flag.equals(Cushion.KISS))
			{
				switch (state) {
				case 0:	
				case 1:	
				case 2:	
				case 3:	
				case 4:	
				case 6:	state=6;			break;
				case 5:	state=5;			break;					
				}
			}
			else
			{
				switch (state) {
				case 1:	state=2;			break;
				case 2:	state=3;			break;
				case 3:	
				case 4:	state=4;			break;
				case 5:	state=5;			break;
				case 0:	
				case 6:	state=6;			break;
				}
			}
		}
		if(state == 5)
			return true;
		else
			return false;
	}
	
	public void ChangePosition(float _pointArray[])
	{
		for(int i=0; i<6; i+=2)
		{
			m_Balls[i/2].setX(_pointArray[i]);
			m_Balls[i/2].setY(_pointArray[i+1]);
		}
	}
}
