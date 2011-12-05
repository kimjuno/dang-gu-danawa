package DangGuDANAWA.Render;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.util.Debug;

import android.opengl.GLSurfaceView;

public class PhysicWorldRenderer implements GLSurfaceView.Renderer {
		// ===========================================================
		// Fields
		// ===========================================================
		private int mSurfaceWidth;
		private int mSurfaceHeight;

		public int getSurfaceWidth() {
			return mSurfaceWidth;
		}

		public int getSurfaceHeight() {
			return mSurfaceHeight;
		}

		private final Engine mEngine;

		// ===========================================================
		// Constructors
		// ===========================================================

		public PhysicWorldRenderer(final Engine _pEngine) {
			this.mEngine = _pEngine;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public void onSurfaceChanged(final GL10 _pGL, final int _pWidth, final int _pHeight) {
			Debug.d("onSurfaceChanged: pWidth=" + _pWidth + "  pHeight=" + _pHeight);
			this.mEngine.setSurfaceSize(_pWidth, _pHeight);
			_pGL.glViewport(0, 0, _pWidth, _pHeight);
			_pGL.glLoadIdentity();
			mSurfaceWidth = _pWidth;
			mSurfaceHeight = _pHeight;
		}

		@Override
		public void onSurfaceCreated(final GL10 _pGL, final EGLConfig _pConfig) {
			Debug.d("onSurfaceCreated");
			GLHelper.reset(_pGL);

			GLHelper.setPerspectiveCorrectionHintFastest(_pGL);
			GLHelper.setShadeModelFlat(_pGL);

			GLHelper.disableLightning(_pGL);
			GLHelper.disableDither(_pGL);
			GLHelper.disableDepthTest(_pGL);
			GLHelper.disableMultisample(_pGL);

			GLHelper.enableBlend(_pGL);
			GLHelper.enableTextures(_pGL);
			GLHelper.enableTexCoordArray(_pGL);
			GLHelper.enableVertexArray(_pGL);

			GLHelper.enableCulling(_pGL);
			_pGL.glFrontFace(GL10.GL_CCW);
			_pGL.glCullFace(GL10.GL_BACK);

			GLHelper.enableExtensions(_pGL, this.mEngine.getEngineOptions().getRenderOptions());
		}

		@Override
		public void onDrawFrame(final GL10 _pGL) {
			try {
				this.mEngine.onDrawFrame(_pGL);
			} catch (final InterruptedException e) {
				Debug.e("GLThread interrupted!", e);
			}
		}
	}