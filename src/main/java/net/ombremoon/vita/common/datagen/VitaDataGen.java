package net.ombremoon.vita.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ombremoon.vita.VitaMod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = VitaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VitaDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        CompletableFuture<HolderLookup.Provider> lookupProviderWithOwn = lookupProvider.thenApply(provider ->
                DatapackRegistriesProvider.BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), provider));

        generator.addProvider(event.includeServer(), new DatapackRegistriesProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new VitaDamageTypeTagsProvider(packOutput, lookupProviderWithOwn, existingFileHelper));
    }
}

