package superscary.cyclopedia;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import superscary.cyclopedia.api.IEncyclopediaPlugin;
import superscary.cyclopedia.api.event.PermanentEventSubscriptions;
import superscary.cyclopedia.api.plugin.PluginStarter;
import superscary.cyclopedia.api.record.StarterData;
import superscary.cyclopedia.manager.PluginManager;
import superscary.cyclopedia.manager.StartManager;

import java.util.List;

public class CyclopediaClient extends CyclopediaBase {

    static Logger logger = LoggerFactory.getLogger(CyclopediaClient.class);

    private final PermanentEventSubscriptions subscriptions;

    public CyclopediaClient (IEventBus eventBus, PermanentEventSubscriptions subscriptions) {
        super(eventBus);
        logger.info("[{}] Client starting.", Cyclopedia.NAME);
        this.subscriptions = subscriptions;
        pluginLoader();
    }

    private void pluginLoader () {
        logger.info("[{}] Finding plugins...", Cyclopedia.NAME);
        List<IEncyclopediaPlugin> plugins = PluginManager.findPlugins();
        for (IEncyclopediaPlugin plugin : plugins) logger.info("[{}] Found {}. Loading...", Cyclopedia.NAME, plugin.getPluginId().getPath());
        StarterData starterData = new StarterData(plugins);
        PluginStarter starter = new PluginStarter(starterData);
        StartManager startManager = new StartManager(starter::start, starter::stop);
        startManager.register(subscriptions);
        logger.info("[{}] All plugins loaded.", Cyclopedia.NAME);
    }

    @Override
    public @Nullable Level getClientLevel () {
        return Minecraft.getInstance().level;
    }

}
