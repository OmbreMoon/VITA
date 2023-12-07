package net.ombremoon.vita.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.ombremoon.vita.client.TimerSync;

import java.util.function.Supplier;

public class ClientboundTimerSyncMessage {
    private final int deathTime;

    public ClientboundTimerSyncMessage(int deathTime) {
        this.deathTime = deathTime;
    }

    public ClientboundTimerSyncMessage(FriendlyByteBuf buf) {
        this.deathTime = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(deathTime);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            TimerSync.setPlayerDeathTime(deathTime);
        });
        return true;
    }
}
