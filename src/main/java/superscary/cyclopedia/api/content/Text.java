package superscary.cyclopedia.api.content;

import net.minecraft.network.chat.MutableComponent;

public class Text {

    private final MutableComponent component;

    public Text (MutableComponent component) {
        this.component = component;
    }

    public MutableComponent getComponent () {
        return component;
    }

}
