package superscary.cyclopedia.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.event.entity.living.MobDespawnEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import superscary.cyclopedia.Cyclopedia;
import superscary.cyclopedia.api.event.PermanentEventSubscriptions;

import java.util.HashSet;
import java.util.Set;

public class StartManager {

    static Logger logger = LoggerFactory.getLogger(StartManager.class);
    private static final Set<Class<? extends Event>> requiredEvents = Set.of(RecipesUpdatedEvent.class, MobDespawnEvent.class);

    private enum State {
        DISABLED, ENABLED, STARTED
    }

    private final Set<Class<? extends Event>> observedEvents = new HashSet<>();
    private final Runnable startRunnable;
    private final Runnable stopRunnable;
    private State state = State.DISABLED;

    public StartManager (Runnable startRunnable, Runnable stopRunnable) {
        this.startRunnable = startRunnable;
        this.stopRunnable = stopRunnable;
    }

    public void register (PermanentEventSubscriptions subscriptions) {
        requiredEvents.forEach(eventClass -> subscriptions.register(eventClass, this::onEvent));
        subscriptions.register(ClientPlayerNetworkEvent.LoggingIn.class, event -> {
            if (event.getPlayer() != null) {
                logger.info("[{}] Registering {}", Cyclopedia.NAME, event.getClass());
                if (this.state == State.DISABLED) changeState(State.ENABLED);
            }
        });
        subscriptions.register(ClientPlayerNetworkEvent.LoggingOut.class, event -> {
            if (event.getPlayer() != null) {
                logger.info("[{}] Registering {}", Cyclopedia.NAME, event.getClass());
                changeState(State.DISABLED);
            }
        });
        subscriptions.register(ScreenEvent.Init.Pre.class, event -> {
            if (this.state != State.STARTED) {
                Screen screen = event.getScreen();
                Minecraft minecraft = screen.getMinecraft();
                if (screen instanceof AbstractContainerScreen && minecraft.player != null) {
                    var missing = requiredEvents.stream().filter(e -> !observedEvents.contains(e)).sorted().toList();
                    logger.error("[{}] Cannot start.", Cyclopedia.NAME);
                    changeState(State.DISABLED);
                    changeState(State.ENABLED);
                    changeState(State.STARTED);
                }
            }
        });
    }

    private <T extends Event> void onEvent (T event) {
        if (this.state == State.DISABLED) return;
        logger.info("[{}] Registering {}", Cyclopedia.NAME, event.getClass());
        Class<? extends Event> eventClass = event.getClass();
        if (requiredEvents.contains(eventClass) && observedEvents.add(eventClass) && observedEvents.containsAll(requiredEvents)) {
            if (this.state == State.STARTED) {
                restart();
            } else {
                changeState(State.STARTED);
            }
        }
    }

    private void restart () {
        if (this.state != State.STARTED) return;
        changeState(State.DISABLED);
        changeState(State.ENABLED);
        changeState(State.STARTED);
    }

    private void changeState (State newState) {
        logger.info("[{}] Old state: {} -> New state: {}", Cyclopedia.NAME, this.state, newState);
        switch (newState) {
            case DISABLED -> {
                if (this.state == State.STARTED) {
                    this.stopRunnable.run();
                }
            }
            case ENABLED -> {
                if (this.state != State.DISABLED) {
                    throw new IllegalStateException("Cannot transition from " + this.state + " to " + newState);
                }
            }
            case STARTED -> {
                if (this.state != State.ENABLED) {
                    throw new IllegalStateException("Cannot transition from " + this.state + " to " + newState);
                }
                this.startRunnable.run();
            }
        }
        this.state = newState;
    }

}
