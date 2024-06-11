package superscary.cyclopedia;

import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyclopediaServer extends CyclopediaBase {

    static Logger logger = LoggerFactory.getLogger(CyclopediaServer.class);

    public CyclopediaServer (IEventBus eventBus) {
        super(eventBus);
        logger.info("[{}] Server starting.", Cyclopedia.NAME);
    }

    @Override
    public @Nullable Level getClientLevel () {
        return null;
    }

}
