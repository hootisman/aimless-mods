package hootisman.aimlessmods;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import hootisman.aimlessmods.init.ModBlocks;
import hootisman.aimlessmods.init.ModCreativeTabs;
import hootisman.aimlessmods.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AimlessMods.MODID)
public class AimlessMods
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "aimlessmods";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AimlessMods(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(ModCreativeTabs::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
