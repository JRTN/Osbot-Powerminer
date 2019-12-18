package util;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public class Rocks extends MethodProvider {
    private static MethodProvider methodProvider;

    public static void setMethodProvider(MethodProvider provider) {
        methodProvider = provider;
    }

    public static boolean isType(final Rock type, final Entity rockEntity) {
        if(rockEntity.getDefinition() == null) {
            return false;
        }

        short[] colours = rockEntity.getDefinition().getModifiedModelColors();

        return Arrays.equals(colours, type.COLOURS);
    }

    public static Entity getClosestRock(Rock type, Area miningArea) {
        return methodProvider.getObjects().closest(obj ->
                isType(type, obj) && miningArea.contains(obj.getPosition())
        );
    }
}
