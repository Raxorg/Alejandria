package com.epicness.alejandria.android;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.epicness.alejandria.R;

/**
 * Runs libGDX in a fragment! implementation 'androidx.appcompat:appcompat:1.6.1' was needed in the build.gradle file
 * Implementing AndroidFragmentApplication.Callbacks is mandatory
 */
public class FragmentLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment);

        getSupportFragmentManager().beginTransaction()
            .add(R.id.frameLayout, new libGDXFragment())
            .commit();
    }

    @Override
    public void exit() {
    }
}