package net.ombremoon.vita.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.ombremoon.vita.capability.DeathTimerCapabilityProvider;
import net.ombremoon.vita.common.network.ClientboundTimerSyncMessage;
import net.ombremoon.vita.common.network.VitaMessages;

public class DeathTimerUtil {
    public static final int MAX_TIME = 6000;

/*    public static void giveSeidrEnergy(Player player, int energyValue) {
        PlayerSEEvent.SEChange event = new PlayerSEEvent.SEChange(player, energyValue);
        if ( MinecraftForge.EVENT_BUS.post(event)) return;
        energyValue = event.getAmount();
        increaseEnergy(player, energyValue);
    }

    public static void takeSeidrEnergy(Player player, int energyValue) {
        PlayerSEEvent.SEChange event = new PlayerSEEvent.SEChange(player, energyValue);
        if ( MinecraftForge.EVENT_BUS.post(event)) return;
        energyValue = event.getAmount();

        decreaseEnergy(player, energyValue);
    }

    public static void setSeidrEnergy(Player player, int energyValue) {
        PlayerSEEvent.SEChange event = new PlayerSEEvent.SEChange(player, energyValue);
        if ( MinecraftForge.EVENT_BUS.post(event)) return;
        energyValue = event.getAmount();

        setEnergy(player, energyValue);
    }*/

    public static void increaseEnergy(Player player, int deathTime) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
                cap.increaseTimer(deathTime, false);
                VitaMessages.sendToPlayer(new ClientboundTimerSyncMessage(cap.getTimer()), serverPlayer);
            });
        }
    }

    public static void decreaseEnergy(Player player, int deathTime) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
                cap.decreaseTimer(deathTime, false);
                VitaMessages.sendToPlayer(new ClientboundTimerSyncMessage(cap.getTimer()), serverPlayer);
            });
        }
    }

    public static void setEnergy(Player player, int deathTime) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
                cap.setTimer(deathTime);
                VitaMessages.sendToPlayer(new ClientboundTimerSyncMessage(cap.getTimer()), serverPlayer);
            });
        }
    }

    public static int getEnergy(Player player) {
        int vitalForce = 0;
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            vitalForce = serverPlayer.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).orElseThrow(NullPointerException::new).getTimer();
        }
        return vitalForce;
    }
}
