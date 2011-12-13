package jp.radiumsoftware.systemfontrenderer;

import jp.radiumsoftware.systemfontrenderer.plugin.TextBuffer;

import com.unity3d.player.UnityPlayer;

import android.content.Context;
import android.content.ContextWrapper;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ExtendedUnityPlayer extends UnityPlayer implements GLSurfaceView.Renderer {
    private TextBuffer mTextBuffer;

    public ExtendedUnityPlayer(Context context, TextBuffer textBuffer) {
        super((ContextWrapper)context);
        mTextBuffer = textBuffer;
    }
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig glConfig) {
        super.onSurfaceCreated(gl, glConfig);
        mTextBuffer.invalidateAll();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        mTextBuffer.update(gl);
        super.onDrawFrame(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        mTextBuffer.invalidateAll();
    }
}
