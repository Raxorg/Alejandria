package com.epicness.alejandria.showcase.modules.fun;

import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.fun.Laser;

public class Lasers extends Module<LasersDrawable> {

    private Laser laser1;

    public Lasers() {
        super("Lasers", "Some colorful lasers made out of 3 segments of 2 sprites each \"color and glow\"\n\n" +
            "they're built so they don't look stretched when you change their lengths");
    }

    @Override
    protected LasersDrawable setup() {
        drawable = new LasersDrawable(
            assets.getBeamStart(), assets.getBeamMid(), assets.getBeamEnd(),
            assets.getBeamStartTop(), assets.getBeamMidTop(), assets.getBeamEndTop()
        );
        laser1 = drawable.getLaser1();
        return drawable;
    }

    @Override
    public void update(float delta) {
        laser1.rotate(45f * delta);
    }
}