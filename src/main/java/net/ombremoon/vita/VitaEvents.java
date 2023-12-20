package net.ombremoon.vita;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ombremoon.vita.capability.DeathTimerCapabilityProvider;
import net.ombremoon.vita.client.TimerOverlay;
import net.ombremoon.vita.common.VitaDamageType;
import net.ombremoon.vita.util.DeathTimerUtil;

@Mod.EventBusSubscriber(modid = VitaMod.MOD_ID)
public class VitaEvents {

    @SubscribeEvent
    public static void onAttackCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).isPresent()) {
                DeathTimerCapabilityProvider provider = new DeathTimerCapabilityProvider();
                event.addCapability(DeathTimerCapabilityProvider.VITA_LOCATION, provider);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        event.getEntity().getCapability(DeathTimerCapabilityProvider.VITA_CAPABILITY).ifPresent(cap -> {
            cap.setTimer(cap.getMaxTime());
        });
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.level().isClientSide) {
            if (player.isCreative() || player.isSpectator())
                return;

            if (!player.isAlive())
                return;

            DeathTimerUtil.decreaseTime(player, 1);
            if (DeathTimerUtil.getTime(player) <= 0) {
                DamageSource damageSource = new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(VitaDamageType.FAILURE));
                player.hurt(damageSource, Float.MAX_VALUE);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (!event.getEntity().level().isClientSide) {
            if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player) {
//                float i = 600;
                int i = 45;
                if (event.getEntity() instanceof Player || event.getEntity() instanceof Warden || event.getEntity() instanceof EnderDragon ||event.getEntity() instanceof WitherBoss) {
                    DeathTimerUtil.setTime(player, DeathTimerUtil.MAX_TIME);
                    return;
                }

                i *= event.getEntity().getHealth();

/*                if (event.getEntity() instanceof Monster) {
                    i = 900;
                }*/
                DeathTimerUtil.increaseTime(player, i);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = VitaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void registerGUIOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("vita", TimerOverlay.HUD_TIMER);
        }
    }
}
