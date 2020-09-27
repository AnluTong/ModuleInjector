package me.andrew.libraryc;

import android.util.Log;

public class LibCImpl implements ICInterface {
    @Override
    public void testc() {
        Log.i("LibCImpl", "C is called");
    }
}
