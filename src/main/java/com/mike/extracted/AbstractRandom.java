package com.mike.extracted;

public interface AbstractRandom {
    public AbstractRandom derive();

    public RandomDeriver createRandomDeriver();

    public void setSeed(long var1);

    public int nextInt();

    public int nextInt(int var1);

    default public int nextBetween(int min, int max) {
        return this.nextInt(max - min + 1) + min;
    }

    public long nextLong();

    public boolean nextBoolean();

    public float nextFloat();

    public double nextDouble();

    public double nextGaussian();

    default public void skip(int count) {
        for (int i = 0; i < count; ++i) {
            this.nextInt();
        }
    }
}
