package com.codeday.thoughts;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Orang on 11/8/2015.
 */
public class Thought {
    String text;
    Bitmap image;

    public Thought(String str, Bitmap bitmap) {
        text = str;
        image = bitmap;
    }
}
