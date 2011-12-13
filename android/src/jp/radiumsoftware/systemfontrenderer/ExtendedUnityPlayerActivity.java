package jp.radiumsoftware.systemfontrenderer;

import jp.radiumsoftware.systemfontrenderer.plugin.TextBuffer;

import com.unity3d.player.UnityPlayerActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.view.ViewGroup.LayoutParams;
import android.graphics.PixelFormat;

public class ExtendedUnityPlayerActivity extends UnityPlayerActivity {
    private TextBuffer mTextBuffer;

    public void setTextEntry(int id, String text, int width, int height, int textSize, int textureId) {
        mTextBuffer.setEntry(id, text, width, height, textSize, textureId);
    }

    public void removeTextEntry(int id) {
        mTextBuffer.removeEntry(id);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextBuffer = new TextBuffer();

        ExtendedUnityPlayer player = new ExtendedUnityPlayer(this, mTextBuffer);
        player.init(0, false);
        
        GLSurfaceView mainView = new GLSurfaceView(getApplication());
        mainView.setZOrderMediaOverlay(true);
        mainView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        mainView.setRenderer(player);
        mainView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
         
        FrameLayout layout = new FrameLayout(getApplicationContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.addView(mainView);
        setContentView(layout);
    }
}
