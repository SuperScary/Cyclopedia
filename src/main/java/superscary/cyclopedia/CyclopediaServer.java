package superscary.cyclopedia;

import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class CyclopediaServer extends CyclopediaBase {

    static Logger logger = Cyclopedia.logger;

    public CyclopediaServer (IEventBus eventBus) {
        super(eventBus);
        logger.info("{} server starting.", Cyclopedia.NAME);
    }

    @Override
    public @Nullable Level getClientLevel () {
        return null;
    }

}
