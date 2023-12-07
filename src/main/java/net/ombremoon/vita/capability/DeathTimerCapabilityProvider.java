package net.ombremoon.vita.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.ombremoon.vita.VitaConfig;
import net.ombremoon.vita.VitaMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DeathTimerCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<IDeathCapability> VITA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static final ResourceLocation VITA_LOCATION = VitaMod.resourceLocation("vita");

    private IDeathCapability deathCapability = new DeathTimerCapabilityHandler(VitaConfig.MAX_TIME.get());
    private final LazyOptional<IDeathCapability> optional = LazyOptional.of(() -> deathCapability);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return VITA_CAPABILITY.orEmpty(cap, this.optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.deathCapability.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.deathCapability.deserializeNBT(nbt);
    }
}
