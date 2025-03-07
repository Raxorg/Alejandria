package com.epicness.alejandria.gwt;

import com.epicness.alejandria.interfacing.ModulePicker;
import com.google.gwt.user.client.Window;

public class HTMLModulePicker implements ModulePicker {

    @Override
    public String getModule() {
        return Window.Location.getHash().replace("#", "");
    }

    @Override
    public void setModule(String showcase) {
        updateHashNative(showcase);
    }

    public static native void updateHashNative(String example) /*-{
        $wnd.location.replace("#" + example);
    }-*/;
}