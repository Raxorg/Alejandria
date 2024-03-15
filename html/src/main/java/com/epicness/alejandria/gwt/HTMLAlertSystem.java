package com.epicness.alejandria.gwt;

import com.epicness.alejandria.interfacing.AlertSystem;

public class HTMLAlertSystem implements AlertSystem {

    @Override
    public void alert(String message) {
        nativeAlert(message);
    }

    public native void nativeAlert(String message) /*-{
        $wnd.alert(message);
    }-*/;
}