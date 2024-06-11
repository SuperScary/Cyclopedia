package superscary.cyclopedia;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import superscary.cyclopedia.api.IEncyclopediaPlugin;
import superscary.cyclopedia.manager.PluginManager;

import java.util.List;

public class CyclopediaClient extends CyclopediaBase {

    static Logger logger = Cyclopedia.logger;

    public CyclopediaClient (IEventBus eventBus) {
        super(eventBus);
        logger.info("{} client starting.", Cyclopedia.NAME);
        List<IEncyclopediaPlugin> plugins = PluginManager.findPlugins();
        for (IEncyclopediaPlugin plugin : plugins) logger.info("Found {}. Loading...", plugin.getPluginId().getNamespace());
    }

    @Override
    public @Nullable Level getClientLevel () {
        return Minecraft.getInstance().level;
    }

}
