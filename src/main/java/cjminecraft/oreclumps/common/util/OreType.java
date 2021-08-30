package cjminecraft.oreclumps.common.util;

public enum OreType {
    IRON("iron"),
    GOLD("gold"),
    COPPER("copper");

    private final String name;

    OreType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
}
