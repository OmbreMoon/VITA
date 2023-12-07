package net.ombremoon.vita.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class DeathTimerCapabilityHandler implements IDeathCapability {
    private int deathTime;
    private final int maxTime;
    private static final String VITA_KEY = "vita";

    public DeathTimerCapabilityHandler(int maxTime) {
        this(maxTime, maxTime);
    }

    public DeathTimerCapabilityHandler(int maxTime, int deathTime) {
        this.maxTime = maxTime;
        this.deathTime = deathTime;
    }

    @Override
    public int increaseTimer(int inc, boolean sim) {
        int timerIncreased = Math.min(maxTime - deathTime, inc);
        if (!sim)
            deathTime += timerIncreased;
        return timerIncreased;
    }

    @Override
    public int decreaseTimer(int dec, boolean sim) {
        int timerDecreased = Math.min(deathTime, dec);
        if (!sim)
            deathTime -= timerDecreased;
        return timerDecreased;
    }

    @Override
    public void setTimer(int energy) {
        this.deathTime = Mth.clamp(deathTime, 0, maxTime);
    }

    @Override
    public int getTimer() {
        return this.deathTime;
    }

    @Override
    public int getMaxTime() {
        return this.maxTime;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt(VITA_KEY, this.deathTime);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.deathTime = nbt.getInt(VITA_KEY);
    }
}
