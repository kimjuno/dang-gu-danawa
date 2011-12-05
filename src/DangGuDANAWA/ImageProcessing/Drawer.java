package DangGuDANAWA.ImageProcessing;

import DangGuDANAWA.Activity.CameraConfiguration;
import DangGuDANAWA.Algorithm.BiliardsController;
import DangGuDANAWA.Data.BilliardsData;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Drawer extends View {
	public OpenCV opencv = new OpenCV();
	public byte[] data = null;
	public Size previewSize;
	public BiliardsController m_controller;
	public Button btnTest;
	private boolean m_bDrawFlag = true;
	public void setM_bDrawFlag(boolean m_bDrawFlag) {
		this.m_bDrawFlag = m_bDrawFlag;
	}
	public Drawer(Context context) {
		super(context);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (data != null)
		{			
			Log.i("Camera", "data != null OK");
			if(m_bDrawFlag)
			{
				m_controller = new BiliardsController();
				Log.i("Camera", "m_bDrawFlag OK");
				float ptdata[] = new float[6];
				
				ptdata = opencv.setbyteSourceImage(data, previewSize.width, previewSize.height);
				CameraConfiguration cameraIsFaile = (CameraConfiguration )getContext();
				Log.i("Camera", "ptdata : " + ptdata);
				if(ptdata[0] == -0.5)
				{
					Log.i("Camera", "ptdata[0] == -0.5");
					Toast.makeText(this.getContext(), "영상인식 실패!! 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
					cameraIsFaile.InvisibleButton();
					cameraIsFaile.getmPreview().RestartPreview();
					return;
				}
				
								
				cameraIsFaile.VisibleButton();
				Log.i("june", "OKOKOK" + ptdata[0]);
				m_controller.ChangePosition(ptdata);
				m_controller.FindRoadAlgorithm();
				
				if(m_controller.m_arrResult.size() == 0)
				{
					Toast.makeText(this.getContext(), "길을 하나도 찾지 못했습니다.", Toast.LENGTH_SHORT).show();
					cameraIsFaile.InvisibleButton();
					cameraIsFaile.getmPreview().RestartPreview();
					return;
				}
				else
				{
					m_controller.m_Cue.setAngle(m_controller.m_arrResult.get(m_controller.m_nResultCount).Angle);
					m_controller.m_Cue.setPower(m_controller.m_arrResult.get(m_controller.m_nResultCount).Power);
				}
			}

			byte temp[] = opencv.getSourceImage(m_controller.getDrawRoad(), !m_bDrawFlag);
			
			canvas.drawBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length), 0, 0, null);
		} 
		else {
			Log.i("null", "null");
		}
	}
	
	/**
	 * @author 웅렬
	 * data를 3D 시뮬을 위해 전역 Data로 넘겨줌
	 */
	public void setResultData()
	{
		for(int i=0; i<3; i++)
		{
			BilliardsData.positionX[i] = m_controller.m_Balls[i].getX();
			BilliardsData.positionY[i] = m_controller.m_Balls[i].getY();
		}
		BilliardsData.shotAngle = m_controller.m_Cue.getAngle();
		BilliardsData.shotSpin = m_controller.m_Cue.getrX();
		BilliardsData.shotPower = m_controller.m_Cue.getPower();
		BilliardsData.simulation = true;
	}
}
