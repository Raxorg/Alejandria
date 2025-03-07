package com.epicness.alejandria.showcase.logic;

public class URLHandler extends ShowcaseLogicHandler {

    @Override
    protected void init() {
        String potentialShowcaseName = game.getShowcasePicker().getModule();
        for (int i = 0; i < logic.getHandlers().size(); i++) {
            if (potentialShowcaseName.equals(logic.getHandlers().get(i).getClass().getName())) {
                get(ShowcaseHandler.class).changeModule(i);
                break;
            }
        }
    }

    public void updateURL(String moduleName) {
        game.getShowcasePicker().setModule(moduleName);
    }
}