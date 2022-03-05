package com.mike.recreated;

public class Identifier {
    static final String DEFAULT_NAMESPACE = "minecraft";
    String id;

    public Identifier(String path) {
        this.id = DEFAULT_NAMESPACE + ":" + path;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
