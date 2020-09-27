package me.andrew.libraryb;

import android.util.Log;

import me.andrew.libraryc.ICInterface;
import me.andrew.moduleinjector.ModuleInjector;

public class LibBImpl implements IBInterface {

    @Override
    public void testb() {
        Log.i("LibBImpl", "B is called");
        ModuleInjector.get(ICInterface.class).testc();
    }
}
