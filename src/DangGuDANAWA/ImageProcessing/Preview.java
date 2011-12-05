package DangGuDANAWA.ImageProcessing;

import java.io.IOException;

import DangGuDANAWA.Activity.CameraConfiguration;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Preview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private Drawer mDrawer;
	private boolean click = false; 
	private CameraConfiguration cameraActivity;
	
	public Preview(Context context, Drawer drawer) {
		super(context);
		// SurfaceHolder.Callback을 설정함으로써 Surface가 생성/소멸되었음을
		// 알 수 있습니다.
		cameraActivity = (CameraConfiguration) context;
		this.mDrawer = drawer;
		this.mHolder = getHolder();
		this.mHolder.addCallback(this);
		this.mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void SnapShot()
	{
		click = true;
		Log.i("Camera", "Shot OK" + click);
	}
	
	public void RestartPreview()
	{
		cameraActivity.InvisibleButton();
		mCamera.startPreview();
		click = false;
		mDrawer.data = null;			
		mDrawer.invalidate();
		mCamera.setPreviewCallback(mPreveListner); 
		Log.i("Camera", "Restart OK");
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		// Surface가 생성되었다면, 카메라의 인스턴스를 받아온 후 카메라의
		// Preview 를 표시할 위치를 설정합니다.
		mCamera = Camera.open();
		try 
		{
			mCamera.setPreviewDisplay(holder);
			mCamera.setPreviewCallback(mPreveListner); 
		} 
		catch (IOException exception) 
		{
			mCamera.release();
			mCamera = null;
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// 다른 화면으로 돌아가면, Surface가 소멸됩니다. 따라서 카메라의 Preview도
		// 중지해야 합니다. 카메라는 공유할 수 있는 자원이 아니기에, 사용하지 않을
		// 경우 -액티비티가 일시정지 상태가 된 경우 등 - 자원을 반환해야합니다.
		mCamera.setPreviewCallback(null);
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	};

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// 표시할 영역의 크기를 알았으므로 해당 크기로 Preview를 시작합니다.
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPreviewSize(w, h);
		mCamera.setParameters(parameters);
		mCamera.startPreview();
	};

	private Camera.PreviewCallback mPreveListner = new Camera.PreviewCallback() {
		public void onPreviewFrame(byte[] data, Camera camera) {
			Size previewSize = camera.getParameters().getPreviewSize();
			Log.i("Camera", "PreviewCallback : " + click);
			if(click)
			{
				mCamera.stopPreview();
				click = false;
				mDrawer.previewSize = previewSize;
				mDrawer.data = data;
				mDrawer.invalidate();
				Log.i("Camera", "PreviewCallback OK");
			}
		}
	};
}
