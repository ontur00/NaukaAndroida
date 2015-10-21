package com.example.hipokryta.testlistview.MyOpenGL;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.hipokryta.testlistview.DebugHelper.DebugHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLStroboscobe extends Activity {

    private GLSurfaceView glSurfaceView;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;

        disableTitle();

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new MyOpenGLRender());
        setContentView(glSurfaceView);
    }

    private void disableTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private class MyOpenGLRender implements GLSurfaceView.Renderer {
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            DebugHelper.showLog("onSurfaceCreated");
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int i, int i1) {
            DebugHelper.showAlertDialog(activity, "surfaceChanged");
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            float red = randomFloat();
            float green = randomFloat();
            float blue = randomFloat();

            gl10.glClearColor(red, green, blue, 1.0f);
            gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
        }
    }

    private float randomFloat() {
        // Generate a random "top bit". Is it set?
        while (Math.random() >= 0.5) {
            // Generate the rest of the random bits. Are they zero?
            // If so, then we've generated 2^n, and dividing by 2^n gives us 1.
            if (Math.random() == 0) { return (float)1.0; }
            // If not, generate a new random number.
        }
        // If the top bits are not set, just divide by 2^n.
        return (float)Math.random();
    }
}
