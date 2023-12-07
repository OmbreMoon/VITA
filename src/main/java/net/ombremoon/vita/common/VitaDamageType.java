package net.ombremoon.vita.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.ombremoon.vita.VitaMod;

public class VitaDamageType {
    public static final ResourceKey<DamageType> FAILURE = register("failure");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, VitaMod.resourceLocation(name));
    }

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(FAILURE, new DamageType("failure", 0.1F));
    }}

