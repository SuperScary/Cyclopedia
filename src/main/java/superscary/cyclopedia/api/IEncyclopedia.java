package superscary.cyclopedia.api;

import net.minecraft.resources.ResourceLocation;
import superscary.cyclopedia.api.content.page.CoverPage;
import superscary.cyclopedia.api.content.page.Page;

/**
 * Main interface for creating an encyclopedia.
 * Must also adopt {@link EncyclopediaPlugin} annotation.
 *
 * @since 1.0.0
 */
public interface IEncyclopedia {

    /**
     * Allows registration of a plugin. ID's must be unique.
     * Your mod id will work.
     * @return the plugin id.
     */
    ResourceLocation getPluginId ();

    /**
     * Registers associated pages with a plugin id
     * @param pages each page to register.
     */
    default void registerPages (Page... pages) {

    }

    /**
     * Registers the cover page
     * @param page the cover page.
     */
    default void registerCoverPage (CoverPage page) {

    }

}
