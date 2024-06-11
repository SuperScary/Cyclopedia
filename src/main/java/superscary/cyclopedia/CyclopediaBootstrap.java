package superscary.cyclopedia;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import superscary.cyclopedia.api.event.PermanentEventSubscriptions;

@Mod(Cyclopedia.MODID)
public class CyclopediaBootstrap {

    public CyclopediaBootstrap (IEventBus modEventBus) {
        IEventBus eventBus = NeoForge.EVENT_BUS;
        PermanentEventSubscriptions subscriptions = new PermanentEventSubscriptions(eventBus, modEventBus);
        switch (FMLEnvironment.dist) {
            case CLIENT -> new CyclopediaClient(eventBus, subscriptions);
            case DEDICATED_SERVER -> new CyclopediaServer(eventBus);
        }
    }

}
