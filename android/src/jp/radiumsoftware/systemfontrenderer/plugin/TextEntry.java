package jp.radiumsoftware.systemfontrenderer.plugin;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLUtils;
import android.util.Log;

import javax.microedition.khronos.opengles.GL10;

public class TextEntry {
    private String mText;
    private int mTextureId;
    private int mWidth;
    private int mHeight;
    private int mTextSize;
    private boolean mReady;

    public TextEntry(String text, int width, int height, int textSize) {
        mText = text;
        mTextureId = -1;
        mWidth = width;
        mHeight = height;
        mTextSize = textSize;
        mReady = false;
        Log.d("TextEntry", "Entry:" + text);
    }

    public void setTextureId(int id) {
        if (mTextureId != id) {
            mTextureId = id;
            mReady = false;
            Log.d("TextEntry", "Texture ID changed (" + Integer.toString(id) + ")");
        }
    }

    public void invalidate() {
        mTextureId = -1;
        mReady = false;
    }

    public void update(GL10 gl) {
        if (!mReady && mTextureId >= 0) {
            buildTexture(gl);
            mReady = true;
        }
    }

    public void buildTexture(GL10 gl) {
        // 対応するビットマップを生成。
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        // テキスト描画設定。
        Paint textPaint = new Paint();
        textPaint.setTextSize(mTextSize);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xff, 0xff, 0xff, 0xff);
        // キャンバスを使って一行づつ描画。
        Canvas canvas = new Canvas(bitmap);
        int y = mTextSize;
        for (String line : mText.split("\n")) {
            canvas.drawText(line, 0, y, textPaint);
            y += mTextSize;
        }
        // テクスチャの入れ替え。
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        // ビットマップはここで破棄。
        bitmap.recycle();
        Log.d("TextEntry", "Texture built:" + mText);
    }
}
