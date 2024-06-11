package superscary.cyclopedia.api.plugin;

import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import superscary.cyclopedia.Cyclopedia;
import superscary.cyclopedia.api.IEncyclopediaPlugin;
import superscary.cyclopedia.api.record.StarterData;

import java.util.List;

public class PluginStarter {

    static Logger logger = LoggerFactory.getLogger(PluginStarter.class);

    private final StarterData data;
    private final List<IEncyclopediaPlugin> plugins;

    public PluginStarter (StarterData data) {
        this.data = data;
        this.plugins = data.plugins();
    }

    public void start () {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) {
            logger.error("[{}] Failed to start {}, no level found.", Cyclopedia.NAME, Cyclopedia.NAME);
            return;
        }

    }

    public void stop () {
        logger.info("[{}] Stopping...", Cyclopedia.NAME);
        List<IEncyclopediaPlugin> plugins = this.plugins;
    }

}
