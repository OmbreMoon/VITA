package net.ombremoon.vita.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.ombremoon.vita.VitaConfig;
import net.ombremoon.vita.capability.DeathTimerCapabilityProvider;
import net.ombremoon.vita.common.network.ClientboundTimerSyncMessage;
import net.ombremoon.vita.common.network.VitaMessages;

public class DeathTimerUtil {
    public static final int MAX_TIME = VitaConfig.MAX_TIME.get();

    public static void increaseTime(Player player, int deathTime) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
                cap.increaseTimer(deathTime, false);
                VitaMessages.sendToPlayer(new ClientboundTimerSyncMessage(cap.getTimer()), serverPlayer);
            });
        }
    }

    public static void decreaseTime(Player player, int deathTime) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
                cap.decreaseTimer(deathTime, false);
                VitaMessages.sendToPlayer(new ClientboundTimerSyncMessage(cap.getTimer()), serverPlayer);
            });
        }
    }

    public static void setTime(Player player, int deathTime) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
                cap.setTimer(deathTime);
                VitaMessages.sendToPlayer(new ClientboundTimerSyncMessage(cap.getTimer()), serverPlayer);
            });
        }
    }

    public static int getTime(Player player) {
        int vitalForce = 0;
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            vitalForce = serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).orElseThrow(NullPointerException::new).getTimer();
        }
        return vitalForce;
    }
}
