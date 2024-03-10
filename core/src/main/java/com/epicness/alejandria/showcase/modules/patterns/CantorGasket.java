package com.epicness.alejandria.showcase.modules.patterns;

import com.epicness.alejandria.showcase.logic.Module;

public class CantorGasket extends Module<CantorGasketDrawable> {

    public CantorGasket() {
        super("Cantor Gasket", "This gasket pertains to Cantor apparently");
    }

    @Override
    protected CantorGasketDrawable setup() {
        return new CantorGasketDrawable();
    }
}