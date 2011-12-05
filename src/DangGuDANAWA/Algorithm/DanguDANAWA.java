package DangGuDANAWA.Algorithm;

import DangGuDANAWA.Activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;

public class DanguDANAWA extends Activity {
    /** Called when the activity is first created. */
    private Button btnCueShot;
    private Button btnReset;
    private Button btnAlgorChange;
    private Button btnFriction;
    
    private SeekBar seekPower;
    private SeekBar seekBiliardsPointX;
    private SeekBar seekBiliardsPointY;
    
    
    private MainView m_MainBoundry;
    private boolean m_bTouchDownFlag;
    private float m_prevX;
    private int Count;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MainView main = new MainView(this);
//        setContentView(main);
        setContentView(R.layout.main);
        m_MainBoundry = (MainView) findViewById(R.id.mainView);
        m_bTouchDownFlag = false;
        m_prevX = 0.0f;
        // 터치 리스너 등록
        m_MainBoundry.setOnTouchListener(m_TouchListener);
        
        // 버튼 초기화
        btnCueShot = (Button) findViewById(R.id.btnCueShot);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnAlgorChange = (Button) findViewById(R.id.btnAlgorChange);
        btnFriction = (Button) findViewById(R.id.btnFriction);
        // 시크바 초기화
        seekPower = (SeekBar) findViewById(R.id.seekPower);
        seekBiliardsPointX = (SeekBar) findViewById(R.id.seekPointX);
        seekBiliardsPointY = (SeekBar) findViewById(R.id.seekPointY);
        //버튼 클릭 리스너 등록
        btnCueShot.setOnClickListener(m_ClickListener);
        btnReset.setOnClickListener(m_ClickListener);
        btnAlgorChange.setOnClickListener(m_ClickListener);
        btnFriction.setOnClickListener(m_ClickListener);
        //시크바 리스너 등록
        seekPower.setOnSeekBarChangeListener(m_seekChangeListener);
        seekBiliardsPointX.setOnSeekBarChangeListener(m_seekChangeListener);
        seekBiliardsPointY.setOnSeekBarChangeListener(m_seekChangeListener);
        Count=0;
    }
	
	private OnTouchListener m_TouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN :
				m_bTouchDownFlag = true;
				m_prevX = event.getX();
				break;

			case MotionEvent.ACTION_MOVE :
				if(m_bTouchDownFlag)
				{
					BiliardsController Controller =  m_MainBoundry.m_Thread.getController();
					float x = event.getX();
					float result = m_prevX - x;
					float angle = Controller.m_Cue.getAngle() + (result/300.0f);
					
					if(angle > 360.0f)
						angle = angle - 360.0f;
					else if(angle < 0.0f)
						angle = 360.0f + angle;

					Controller.m_Cue.setAngle( angle );
				}
				break;
			case MotionEvent.ACTION_UP :
				m_bTouchDownFlag = false;
				m_prevX = 0.0f;
				break;
			}
			return true;
		}
	};
	
	private SeekBar.OnSeekBarChangeListener m_seekChangeListener = new SeekBar.OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			BiliardsController Controller =  m_MainBoundry.m_Thread.getController();
			switch (seekBar.getId()) {
			case R.id.seekPower:
				Controller.m_Cue.setPower((float)progress/10.0f);
				break;
			case R.id.seekPointX:
				Controller.m_Cue.setrX((float)progress/10.0f - 16.0f);
				break;
			case R.id.seekPointY:
				Controller.m_Cue.setrY((float)progress/10.0f - 16.0f);
				break;
			default:
				break;
			}
			
		}
	};
	
	private View.OnClickListener m_ClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			BiliardsController Controller =  m_MainBoundry.m_Thread.getController();
			switch (v.getId()) {
			case R.id.btnCueShot:
				if(Controller.m_bStoppedAllBalls)
				{
					Controller.ShotBall();
					Controller.m_bShowWay = false;
				}
				break;
			case R.id.btnReset:
				Controller.m_bShowWay = true;
				Controller.m_bStoppedAllBalls = true;
				//Controller.m_arrResultAngle.removeAllElements();
				//Controller.FindRoadAlgorithm();
				//Controller.FindAngle();
				break;
			case R.id.btnAlgorChange:
				if(Count == Controller.m_arrResult.size())
					Count = 0;
				Controller.m_Cue.setAngle(Controller.m_arrResult.get(Count++).Angle);
				break;
			case R.id.btnFriction:
				Controller.m_bImageFriction = !Controller.m_bImageFriction;
				break;
			}
			
		}
	};
}
