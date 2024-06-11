package superscary.cyclopedia;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import superscary.cyclopedia.manager.PluginManager;

@Mod(CyclopediaMod.MODID)
public class CyclopediaMod {

    public static final String MODID = "cyclopedia";
    private static Logger logger = LoggerFactory.getLogger(CyclopediaMod.class);

    public CyclopediaMod (IEventBus modEventBus, ModContainer modContainer) {
        getLogger().info("{} is loading...", MODID);
        getLogger().info("Finding plugins");
        modEventBus.addListener(PluginManager::init);
    }

    public static Logger getLogger () {
        return logger;
    }

}
