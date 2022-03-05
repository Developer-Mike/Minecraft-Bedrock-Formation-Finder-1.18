package com.mike.extracted;

import java.util.function.LongFunction;

public enum RandomProvider {
    XOROSHIRO(Xoroshiro128PlusPlusRandom::new);

    private final LongFunction<AbstractRandom> provider;

    private RandomProvider(LongFunction<AbstractRandom> provider) {
        this.provider = provider;
    }

    public AbstractRandom create(long seed) {
        return this.provider.apply(seed);
    }
}