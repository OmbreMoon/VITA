package net.ombremoon.vita.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ombremoon.vita.VitaMod;
import net.ombremoon.vita.common.VitaDamageType;

import java.util.concurrent.CompletableFuture;

public class VitaDamageTypeTagsProvider extends TagsProvider<DamageType> {
    protected VitaDamageTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, Registries.DAMAGE_TYPE, pLookupProvider, VitaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
                .add(VitaDamageType.FAILURE);
    }
}
