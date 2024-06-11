package superscary.cyclopedia.minecraft;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import superscary.cyclopedia.api.EncyclopediaPlugin;
import superscary.cyclopedia.api.IEncyclopediaPlugin;

@EncyclopediaPlugin
public class MinecraftPlugin implements IEncyclopediaPlugin {

    @Override
    public @NotNull ResourceLocation getPluginId () {
        return new ResourceLocation("minecraft");
    }

}
