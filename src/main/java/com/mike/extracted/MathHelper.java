package com.mike.extracted;

public class MathHelper {
    public static double getLerpProgress(double value, double start, double end) {
        return (value - start) / (end - start);
    }

    public static double lerp(double delta, double start, double end) {
        return start + delta * (end - start);
    }

    public static double lerpFromProgress(double lerpValue, double lerpStart, double lerpEnd, double start, double end) {
        return MathHelper.lerp(MathHelper.getLerpProgress(lerpValue, lerpStart, lerpEnd), start, end);
    }

    public static double square(double n) {
        return n * n;
    }

    public static long hashCode(int x, int y, int z) {
        long l = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
        l = l * l * 42317861L + l * 11L;
        return l >> 16;
    }
}

