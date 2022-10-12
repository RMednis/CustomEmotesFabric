package mods.mednis.id.lv.customemotes.mixin;

import java.util.Collection;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.network.ClientCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import mods.mednis.id.lv.customemotes.emotes.EmoteRegistry;

@Mixin({ChatInputSuggestor.class})
public class CommandSuggestorMixin {
  @Redirect(method = {"refresh"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientCommandSource;getChatSuggestions()Ljava/util/Collection;"))
  private Collection<String> breakRenderedChatMessageLines(ClientCommandSource clientCommandSource) {
    Collection<String> suggestions = EmoteRegistry.getInstance().getEmoteSuggestions();
    suggestions.addAll(clientCommandSource.getPlayerNames());
    return suggestions;
  }
}