package net.ombremoon.vita;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.ombremoon.vita.common.network.VitaMessages;
import org.slf4j.Logger;

@Mod(VitaMod.MOD_ID)
public class VitaMod {
    public static final String MOD_ID = "vita";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VitaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VitaConfig.SPEC, "vita-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        VitaMessages.register();
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(VitaMod.MOD_ID, name);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
