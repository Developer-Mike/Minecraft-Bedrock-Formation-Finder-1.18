package com.mike.extracted;

import com.mike.recreated.BlockPos;
import com.mike.recreated.Identifier;

public interface RandomDeriver {
    default public AbstractRandom createRandom(BlockPos pos) {
        return this.createRandom(pos.x, pos.y, pos.z);
    }

    default public AbstractRandom createRandom(Identifier id) {
        return this.createRandom(id.toString());
    }

    public AbstractRandom createRandom(String var1);

    public AbstractRandom createRandom(int var1, int var2, int var3);
}

