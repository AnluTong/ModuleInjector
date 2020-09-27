package me.andrew.moduleinjector.tester;

import android.app.Application;

import me.andrew.librarya.IAInterface;
import me.andrew.librarya.LibAImpl;
import me.andrew.libraryb.IBInterface;
import me.andrew.libraryb.LibBImpl;
import me.andrew.libraryc.ICInterface;
import me.andrew.libraryc.LibCImpl;
import me.andrew.moduleinjector.ModuleInjector;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ModuleInjector.install(IAInterface.class, new LibAImpl());
        ModuleInjector.install(IBInterface.class, new LibBImpl());
        ModuleInjector.install(ICInterface.class, new LibCImpl());
    }
}
