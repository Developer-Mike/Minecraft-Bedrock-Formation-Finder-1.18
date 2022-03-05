package com.mike.extracted;

public class GaussianGenerator {
    public final AbstractRandom baseRandom;
    private double nextNextGaussian;
    private boolean hasNextGaussian;

    public GaussianGenerator(AbstractRandom baseRandom) {
        this.baseRandom = baseRandom;
    }

    public void reset() {
        this.hasNextGaussian = false;
    }

    public double next() {
        double e;
        double d;
        double f;
        if (this.hasNextGaussian) {
            this.hasNextGaussian = false;
            return this.nextNextGaussian;
        }
        do {
            d = 2.0 * this.baseRandom.nextDouble() - 1.0;
            e = 2.0 * this.baseRandom.nextDouble() - 1.0;
        } while ((f = MathHelper.square(d) + MathHelper.square(e)) >= 1.0 || f == 0.0);
        double g = Math.sqrt(-2.0 * Math.log(f) / f);
        this.nextNextGaussian = e * g;
        this.hasNextGaussian = true;
        return d * g;
    }
}
