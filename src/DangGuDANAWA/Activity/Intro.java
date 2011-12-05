package DangGuDANAWA.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ImageView;

public class Intro extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		final ImageView imageViewIntro = (ImageView) findViewById(R.id.imageView_intro);
		imageViewIntro.setAlpha(0);
		
		new Thread(new Runnable() {
			int alpha;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (alpha = 0; alpha < 255; alpha++) {
					imageViewIntro.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							imageViewIntro.setAlpha(alpha);
						}
					});
					SystemClock.sleep(8);
				}
				
				SystemClock.sleep(1000);
				
				for (alpha = 255; alpha > 0; alpha--) {
					imageViewIntro.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							imageViewIntro.setAlpha(alpha);
						}
					});
					SystemClock.sleep(8);
				}				
			}
		}).start();
		
		initialize();
	}

	private void initialize() {
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				Intent intent = new Intent();
				intent.setClass(Intro.this, MainUIActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.fade);
				finish();
			}
		};

		handler.sendEmptyMessageDelayed(0, 5000); // ms Delay
	}

	// Intro 중 Back Button 무효화
	@Override
	public void onBackPressed() {
	}

}