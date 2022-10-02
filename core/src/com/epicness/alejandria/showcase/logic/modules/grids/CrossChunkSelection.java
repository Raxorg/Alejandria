package com.epicness.alejandria.showcase.logic.modules.grids;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.grids.CrossChunkSelectionDrawable;

public class CrossChunkSelection extends Module<CrossChunkSelectionDrawable> {

    public CrossChunkSelection() {
        super("Chunking", "This demonstrates how to select neighboring cells even from different chunks");
    }

    @Override
    public CrossChunkSelectionDrawable setup() {
        return new CrossChunkSelectionDrawable();
    }
}