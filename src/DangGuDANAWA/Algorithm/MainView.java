package DangGuDANAWA.Algorithm;


import DangGuDANAWA.Activity.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class MainView extends SurfaceView implements SurfaceHolder.Callback{
	
	private SurfaceHolder m_Holder;
	public DrawThread m_Thread;
	private Bitmap m_bitmap;
	
	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		m_Holder = this.getHolder();
		m_Holder.addCallback(this);
		Resources res = this.getResources();
		BitmapDrawable bd = (BitmapDrawable) res.getDrawable(R.drawable.biliardpan);
		m_bitmap = bd.getBitmap();
	}
	
	public MainView(Context context) {
		super(context);
		m_Holder = this.getHolder();
		m_Holder.addCallback(this);
		Resources res = this.getResources();
		BitmapDrawable bd = (BitmapDrawable) res.getDrawable(R.drawable.biliardpan);
		m_bitmap = bd.getBitmap();
	}
	
	//표면 생성시 그리기 시작
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		m_Thread = new DrawThread(m_Holder);
		m_Thread.start();
		//m_Thread.drawDangu();
		
	}
	// 표면이 변경될 때 크기를 기록
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if(m_Thread != null)
			m_Thread.SizeChange(width, height);
	}
	
	//표면 파괴 시 그리기 중지
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		m_Thread.m_bExit = true;
		
	}
	/**
	 * 표면에 테스트를 위한 그리기를 담당하는 클래스
	 * @author 임충렬
	 */
	class DrawThread extends Thread {
		SurfaceHolder m_thdHolder;
		int Width, Height;
		public Rect PanSize;
		boolean m_bExit;
		private BiliardsController controller; 
		public BiliardsController getController() {
			return controller;
		}
		public Canvas canvas;
		public DrawThread(SurfaceHolder _Holder) {
			this.m_thdHolder = _Holder;
			this.m_bExit = false;
			PanSize = new Rect(11,11,151, 251);
		}
		

		/**
		 * @author 임충렬
		 * @param _Width	가로 크기
		 * @param _Height	세로 크기
		 * 표면의 크기가 변경되면 그 변경된 값으로 초기화 하는 함수
		 */
		public void SizeChange(int _Width, int _Height)
		{
			this.Width = _Width;
			this.Height = _Height;
			//this.Width = m_bitmap.getWidth();
			//this.Height = m_bitmap.getHeight();
			PanSize.set(25,25,m_bitmap.getWidth()-25, m_bitmap.getHeight()-25);	
		}
		public void drawDangu()
		{
			controller = new BiliardsController(this);
			synchronized (m_thdHolder) {
				canvas = m_thdHolder.lockCanvas();
				canvas.drawBitmap(m_bitmap, 0, 0, null);

				controller.DrawBalls(canvas);			//공을 그린다

				controller.DrawShotPoint(canvas); 		// 공의 당점을 보여준다.

				controller.DrawCue(canvas);			//치는 각을 보여줄 수 있는 큐를 그린다
				controller.DrawImageBall(canvas); 
				controller.MoveBalls();

				m_thdHolder.unlockCanvasAndPost(canvas);
			}
		}

		public void run() 
		{
			controller = new BiliardsController(this);
			controller.FindRoadAlgorithm();
			//controller.m_Cue.setAngle(controller.m_arrResult.get(0).Angle);
			while(m_bExit == false){
				synchronized (m_thdHolder) {
					canvas = m_thdHolder.lockCanvas();
					if (canvas == null) 
						break;
					canvas.drawBitmap(m_bitmap, 0, 0, null);
					
					controller.DrawBalls(canvas);			//공을 그린다
					
					controller.DrawShotPoint(canvas); 		// 공의 당점을 보여준다.

					if(controller.m_bShowWay)
					{
						controller.DrawCue(canvas);			//치는 각을 보여줄 수 있는 큐를 그린다
						controller.DrawImageBall(canvas); 
					}
					controller.MoveBalls();
					
					m_thdHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}