package superscary.cyclopedia;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Cyclopedia {

    String MODID = "cyclopedia";
    String NAME = "Cyclopedia";
    Logger logger = LoggerFactory.getLogger(Cyclopedia.class);

    static Cyclopedia instance () {
        return CyclopediaBase.INSTANCE;
    }

    static ResourceLocation getResource (String name) {
        return new ResourceLocation(MODID, name);
    }

    @Nullable
    Level getClientLevel ();

    @Nullable
    MinecraftServer getCurrentServer ();

}
