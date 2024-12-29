package com.epicness.alejandria.gwt;

import com.epicness.alejandria.interfacing.ShowcasePicker;
import com.google.gwt.user.client.Window;

public class HTMLShowcasePicker implements ShowcasePicker {
    @Override
    public void setShowcase(String showcase) {
        updateHashNative(showcase);
    }

    @Override
    public String getShowcase() {
        return Window.Location.getHash().replace("#", "");
    }

    public static native void updateHashNative(String example) /*-{
        $wnd.location.replace("#" + example);
    }-*/;
}
