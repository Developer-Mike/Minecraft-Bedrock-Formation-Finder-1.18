package com.mike;

public class BedrockBlock {
    int x;
    int y;
    int z;
    boolean shouldBeBedrock;

    // Example 1,0,2:1
    public BedrockBlock(String arg) {
        String[] coordinates = arg.split(":")[0].split(",");
        x = Integer.parseInt(coordinates[0]);
        y = Integer.parseInt(coordinates[1]);
        z = Integer.parseInt(coordinates[2]);

        shouldBeBedrock = Integer.parseInt(arg.split(":")[1]) == 1;
    }

    @Override
    public String toString() {
        return "BedrockBlock{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", shouldBeBedrock=" + shouldBeBedrock +
                '}';
    }
}
