package com.epicness.alejandria.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.epicness.alejandria.AlejandriaApp;

/**
 * We need to extend AndroidFragmentApplication and not use an anonymous class or else a weird error will show
 */
public class libGDXFragment extends AndroidFragmentApplication {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initializeForView(new AlejandriaApp());
    }
}