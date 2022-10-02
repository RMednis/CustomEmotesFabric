package mods.mednis.id.lv.customemotes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mods.mednis.id.lv.customemotes.emotes.EmoteRegistry;

public class FabricEmotesMod implements ClientModInitializer {
    private final Logger LOGGER = LogManager.getLogger("customemeotes.ChatEmotesMod");

    public void onInitializeClient() {
        this.LOGGER.info("onInitializeClient(): Loading emotes...");
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener((IdentifiableResourceReloadListener) new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("customemotes", "emotes");
            }

            public void reload(ResourceManager manager) {
                EmoteRegistry.getInstance().init();
            }
        });
        this.LOGGER.info("onInitializeClient(): Emotes loaded.");
    }
}
