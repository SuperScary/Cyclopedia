package superscary.cyclopedia;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Cyclopedia.MODID)
public class CyclopediaBootstrap {

    public CyclopediaBootstrap (IEventBus eventBus) {
        switch (FMLEnvironment.dist) {
            case CLIENT -> new CyclopediaClient(eventBus);
            case DEDICATED_SERVER -> new CyclopediaServer(eventBus);
        }
    }

}
