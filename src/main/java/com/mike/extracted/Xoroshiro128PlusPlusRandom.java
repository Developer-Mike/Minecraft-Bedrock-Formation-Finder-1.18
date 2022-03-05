package com.mike.extracted;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Longs;

public class Xoroshiro128PlusPlusRandom implements AbstractRandom {
    private static final float FLOAT_MULTIPLIER = 5.9604645E-8f;
    private static final double DOUBLE_MULTIPLIER = (double) 1.110223E-16f;
    private Xoroshiro128PlusPlusRandomImpl implementation;
    private final GaussianGenerator gaussianGenerator = new GaussianGenerator(this);

    public Xoroshiro128PlusPlusRandom(long seed) {
        this.implementation = new Xoroshiro128PlusPlusRandomImpl(RandomSeed.createXoroshiroSeed(seed));
    }

    public Xoroshiro128PlusPlusRandom(long seedLo, long seedHi) {
        this.implementation = new Xoroshiro128PlusPlusRandomImpl(seedLo, seedHi);
    }

    @Override
    public AbstractRandom derive() {
        return new Xoroshiro128PlusPlusRandom(this.implementation.next(), this.implementation.next());
    }

    @Override
    public com.mike.extracted.RandomDeriver createRandomDeriver() {
        return new RandomDeriver(this.implementation.next(), this.implementation.next());
    }

    @Override
    public void setSeed(long l) {
        this.implementation = new Xoroshiro128PlusPlusRandomImpl(RandomSeed.createXoroshiroSeed(l));
        this.gaussianGenerator.reset();
    }

    @Override
    public int nextInt() {
        return (int) this.implementation.next();
    }

    @Override
    public int nextInt(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        }
        long l = Integer.toUnsignedLong(this.nextInt());
        long m = l * (long) i;
        long n = m & 0xFFFFFFFFL;
        if (n < (long) i) {
            int j = Integer.remainderUnsigned(~i + 1, i);
            while (n < (long) j) {
                l = Integer.toUnsignedLong(this.nextInt());
                m = l * (long) i;
                n = m & 0xFFFFFFFFL;
            }
        }
        long o = m >> 32;
        return (int) o;
    }

    @Override
    public long nextLong() {
        return this.implementation.next();
    }

    @Override
    public boolean nextBoolean() {
        return (this.implementation.next() & 1L) != 0L;
    }

    @Override
    public float nextFloat() {
        return (float) this.next(24) * 5.9604645E-8f;
    }

    @Override
    public double nextDouble() {
        return (double) this.next(53) * (double) 1.110223E-16f;
    }

    @Override
    public double nextGaussian() {
        return this.gaussianGenerator.next();
    }

    @Override
    public void skip(int count) {
        for (int i = 0; i < count; ++i) {
            this.implementation.next();
        }
    }

    private long next(int bits) {
        return this.implementation.next() >>> 64 - bits;
    }

    public static class RandomDeriver implements com.mike.extracted.RandomDeriver {
        private static final HashFunction MD5_HASHER = Hashing.md5();
        private final long seedLo;
        private final long seedHi;

        public RandomDeriver(long seedLo, long seedHi) {
            this.seedLo = seedLo;
            this.seedHi = seedHi;
        }

        @Override
        public AbstractRandom createRandom(int x, int y, int z) {
            long l = MathHelper.hashCode(x, y, z);
            long m = l ^ this.seedLo;
            return new Xoroshiro128PlusPlusRandom(m, this.seedHi);
        }

        @Override
        public AbstractRandom createRandom(String string) {
            byte[] bs = MD5_HASHER.hashString(string, Charsets.UTF_8).asBytes();
            long l = Longs.fromBytes(bs[0], bs[1], bs[2], bs[3], bs[4], bs[5], bs[6], bs[7]);
            long m = Longs.fromBytes(bs[8], bs[9], bs[10], bs[11], bs[12], bs[13], bs[14], bs[15]);
            return new Xoroshiro128PlusPlusRandom(l ^ this.seedLo, m ^ this.seedHi);
        }
    }
}
