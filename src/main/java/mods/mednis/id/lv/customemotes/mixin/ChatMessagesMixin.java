package mods.mednis.id.lv.customemotes.mixin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.util.ChatMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import mods.mednis.id.lv.customemotes.emotes.Emote;
import mods.mednis.id.lv.customemotes.emotes.EmoteRegistry;

@Mixin({ChatMessages.class})
public abstract class ChatMessagesMixin {
  private static final Logger MOD_LOGGER = LogManager.getLogger("customemotes.mixin.ChatMessagesMixin");
  
  @ModifyVariable(method = {"getRenderedChatMessage"}, at = @At("HEAD"), ordinal = 0)
  private static String getRenderedChatMessageParam(String message) {
    Pattern emotePattern = Pattern.compile("(:([A-Za-z0-9_-]*):)");

    boolean emotesLeft = true;

    while (emotesLeft) {
      Matcher emoteMatch = emotePattern.matcher(message);
      while (emotesLeft = emoteMatch.find()) {
        String emoteName = emoteMatch.group(2);
        Emote emote = EmoteRegistry.getInstance().getEmoteByName(emoteName);
        if (emote != null) {
          message = message.replace(":"+emoteName+":", "▏"+emote.getId());
        }
      }
    }
    return message;
  }
}