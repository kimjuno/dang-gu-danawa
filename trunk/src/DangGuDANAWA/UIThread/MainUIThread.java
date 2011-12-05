package DangGuDANAWA.UIThread;

import DangGuDANAWA.Activity.MainUIActivity;
import DangGuDANAWA.Activity.R;
import android.os.SystemClock;
import android.widget.ImageView;

public class MainUIThread extends Thread {

	public static final int MAIN_LOGO_APPEAR = 4;
	public static final int MAIN_LOGO_DISAPPEAR = 5;

	private ImageView mImageViewMain;
	private ImageView mImageViewSub;
	private int mAlpha;
	private int mState;

	public MainUIThread(ImageView _ImageViewMain, ImageView _ImageViewSub, int _State) {
		this.mImageViewMain = _ImageViewMain;
		this.mImageViewSub = _ImageViewSub;
		this.mState = _State;
	}

	@Override
	public void run() {
		super.run();

		switch (mState) {
		case MAIN_LOGO_APPEAR:
			for (mAlpha = 0; mAlpha < 254; mAlpha++) {
				mImageViewMain.post(new Runnable() {
					public void run() {
						mImageViewMain.setAlpha(mAlpha);
						mImageViewSub.setAlpha(mAlpha);
					}
				});
				// Delay
				SystemClock.sleep(4);
			}
			break;
			
		case MAIN_LOGO_DISAPPEAR:
			for (mAlpha = 255; mAlpha > 1; mAlpha -= 2) {
				mImageViewMain.post(new Runnable() {
					public void run() {
						mImageViewMain.setAlpha(mAlpha);
						mImageViewSub.setAlpha(mAlpha);
					}
				});
				// Delay
				SystemClock.sleep(3);
			}
			break;

		default:
			for (mAlpha = 255; mAlpha > 1; mAlpha -= 2) {

				mImageViewSub.post(new Runnable() {
					public void run() {
						mImageViewSub.setAlpha(mAlpha);
					}
				});
				// Delay
				SystemClock.sleep(1);
			}

			mImageViewSub.post(new Runnable() {
				public void run() {
					switch (mState) {
					case MainUIActivity.MENU_SELECT_REAL:
						mImageViewSub.setImageResource(R.drawable.realmode);
						break;
					case MainUIActivity.MENU_SELECT_FREE:
						mImageViewSub.setImageResource(R.drawable.freemode);
						break;
					case MainUIActivity.MENU_SELECT_EXIT:
						mImageViewSub.setImageResource(R.drawable.exit);
						break;
					}
				}
			});
			SystemClock.sleep(500);

			for (mAlpha = 0; mAlpha < 254; mAlpha += 2) {
				mImageViewSub.post(new Runnable() {
					public void run() {
						mImageViewSub.setAlpha(mAlpha);
					}
				});
				// Delay
				SystemClock.sleep(2);
			}

			try {
				finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			break;
		}

	}
}