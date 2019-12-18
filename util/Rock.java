package util;

public enum Rock {

    CLAY(new short[]{6705}, "Clay"),
    COPPER(new short[]{4645, 4510}, "Copper ore"),
    TIN(new short[]{53}, "Tin ore"),
    IRON(new short[]{2576}, "Iron ore"),
    SILVER(new short[]{74}, "Silver"),
    COAL(new short[]{10508}, "Coal"),
    GOLD(new short[]{8885}, "Gold"),
    MITHRIL(new short[]{-22239}, "Mithril ore"),
    ADAMANTITE(new short[]{21662}, "Adamantite ore"),
    RUNITE(new short[]{-31437}, "Runite ore");

    public String ORE;
    public short[] COLOURS;

    Rock(final short[] COLOURS, final String ORE) {
        this.COLOURS = COLOURS;
        this.ORE = ORE;
    }
}
