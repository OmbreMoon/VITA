package net.ombremoon.vita.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.ombremoon.vita.VitaMod;
import net.ombremoon.vita.common.VitaDamageType;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class VitaDamageTypeProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, VitaDamageType::bootstrap);

    public VitaDamageTypeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(VitaMod.MOD_ID));
    }
}
