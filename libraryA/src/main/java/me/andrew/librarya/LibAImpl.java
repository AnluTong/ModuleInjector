package me.andrew.librarya;

import android.util.Log;

import me.andrew.libraryb.IBInterface;

import me.andrew.moduleinjector.ModuleInjector;

public class LibAImpl implements IAInterface {

    @Override
    public void testA() {
        Log.i("LibAImpl", "A is called");
        ModuleInjector.get(IBInterface.class).testb();
    }
}
