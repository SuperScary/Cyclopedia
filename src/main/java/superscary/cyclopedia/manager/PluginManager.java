package superscary.cyclopedia.manager;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;

public class PluginManager {

    public static void init (IEventBus eventBus) {
        eventBus.addListener((RegisterEvent event) -> {

        });
    }

}
