package ua.strelchenko.whitespaceandroid;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by bowli on 26-Sep-17.
 */

public class Level extends Activity {
    public static final String TAG = "Level";

    ActivityManager activityManager = null;
    ConfigurationInfo configurationInfo = null;
    boolean supportsEs31;

    private GLSurfaceView glSurfaceView;
    /**
     * Keeps track of whether Level's glSurfaceView is in a valid state.
     */
    private boolean rendererSet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "#onCreate() started.");
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);

        // Determine the OpenGL ES support.
        if (configurationInfo == null) {
            activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            configurationInfo = activityManager.getDeviceConfigurationInfo();
            supportsEs31 = configurationInfo.reqGlEsVersion >= 0x00030001
                    || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP // if this is an emulator build. Emulator build has to support OpenGL ES 3.1
                    && (Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")));
        }

        // Init the GLSurfaceView.
        if (supportsEs31) {
            glSurfaceView.setEGLContextClientVersion(3); // should work for ES 3.1
            glSurfaceView.setRenderer(new LevelRenderer());
            rendererSet = true;
        } else {
            Toast.makeText(this, "This device doesn't support Opengl ES 3.1.", Toast.LENGTH_SHORT).show();
            return;
        }

        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }
}
