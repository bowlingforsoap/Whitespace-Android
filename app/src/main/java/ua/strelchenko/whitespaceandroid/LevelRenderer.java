package ua.strelchenko.whitespaceandroid;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES31.*;

/**
 * Created by bowli on 27-Sep-17.
 */

class LevelRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        glClearColor(1f, 0f, 0f, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // Set OpenGL Viewport to fill the whole screen.
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
