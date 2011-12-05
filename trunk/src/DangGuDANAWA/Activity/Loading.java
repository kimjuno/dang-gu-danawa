package DangGuDANAWA.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class Loading extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		setContentView(R.layout.loading);
		initialize();
	}

	private void initialize() {
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				Intent intent = new Intent();
				intent.setClass(Loading.this, CameraConfiguration.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.fade);
				finish();
			}
		};

		handler.sendEmptyMessageDelayed(0, 2000); // ms Delay
	}

	// 로고 화면중 Back Button 무효화
	@Override
	public void onBackPressed() {
	}

}