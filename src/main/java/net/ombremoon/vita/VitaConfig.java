package net.ombremoon.vita;

import net.minecraftforge.common.ForgeConfigSpec;

public class VitaConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_TIME;

    static {
        BUILDER.push("Violence Is The Answer (VITA) Configs");

        MAX_TIME = BUILDER.comment("Time (in ticks) until timer runs out").define("Max Time", 6000);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
