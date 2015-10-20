package com.example.hipokryta.testlistview.MyOpenGL;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.example.hipokryta.testlistview.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyOpenGLActivity extends Activity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disableTitle();

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new MyOpenGLRender());
        setContentView(R.layout.activity_my_open_gl);
    }

    private void disableTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private class MyOpenGLRender implements GLSurfaceView.Renderer {
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int i, int i1) {
//            Log
        }

        @Override
        public void onDrawFrame(GL10 gl10) {

        }
    }
}
