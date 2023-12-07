package net.ombremoon.vita.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IDeathCapability extends INBTSerializable<CompoundTag> {

    int increaseTimer(int inc, boolean sim);

    int decreaseTimer(int dec, boolean sim);

    void setTimer(int energy);

    int getTimer();

    int getMaxTime();
}
