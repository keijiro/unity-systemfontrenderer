package jp.radiumsoftware.systemfontrenderer.plugin;

import android.util.Log;

import java.util.Map;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class TextBuffer {
    private Map<Integer, TextEntry> mEntryMap = new HashMap<Integer, TextEntry>();

    public void setEntry(int id, String text, int width, int height, int textSize, int textureId) {
        TextEntry entry = mEntryMap.get(id);

        if (entry == null) {
            entry = new TextEntry(text, width, height, textSize);
            entry.setTextureId(textureId);
            mEntryMap.put(id, entry);
        } else {
            entry.setTextureId(textureId);
        }
    }

    public void removeEntry(int id) {
        mEntryMap.remove(id);
    }

    public void update(GL10 gl) {
        for (TextEntry entry : mEntryMap.values()) {
            entry.update(gl);
        }
    }

    public void invalidateAll() {
        for (TextEntry entry : mEntryMap.values()) {
            entry.invalidate();
        }
    }
}
