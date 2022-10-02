package mods.mednis.id.lv.customemotes.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.text.Style;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import mods.mednis.id.lv.customemotes.emotes.Emote;
import mods.mednis.id.lv.customemotes.emotes.EmoteRegistry;
import mods.mednis.id.lv.customemotes.text.TextReaderVisitor;

public class EmoteRenderHelper {
  public static List<RenderEmote> extractEmotes(TextReaderVisitor textReaderVisitor, TextRenderer textRenderer, float renderX, float renderY) {
    List<RenderEmote> renderEmoteList = new ArrayList<>();
    Pattern emotePattern = Pattern.compile("(▏([0-9]+))");
    boolean emotesLeft = true;
    while (emotesLeft) {
      String textStr = textReaderVisitor.getString();
      Matcher emoteMatch = emotePattern.matcher(textStr);
      while (emotesLeft = emoteMatch.find()) {
        String emoteIdStr = emoteMatch.group(2);
        try {
          int emoteId = Integer.parseInt(emoteIdStr);
          Emote emote = EmoteRegistry.getInstance().getEmoteById(emoteId);
          int startPos = emoteMatch.start(1);
          int endPos = emoteMatch.end(1);
          if (emote != null) {
            float beforeTextWidth = textRenderer.getWidth(textStr.substring(0, startPos));
            renderEmoteList.add(new RenderEmote(emote, renderX + beforeTextWidth, renderY));
            textReaderVisitor.replaceBetween(startPos, endPos, "  ", Style.EMPTY);
            break;
          } 
        } catch (NumberFormatException numberFormatException) {}
      } 
    } 
    return renderEmoteList;
  }
  
  public static void drawEmote(MatrixStack matrices, RenderEmote renderEmote, float size, float alpha, float sizeMult, float maxWidthMult) {
    Emote emote = renderEmote.getEmote();
    float scaleX = emote.getWidth() / emote.getHeight() * sizeMult;
    float scaleY = sizeMult;
    if (scaleX > maxWidthMult) {
      scaleX = maxWidthMult;
      scaleY = maxWidthMult * emote.getHeight() / emote.getWidth();
    } 
    scaleX = Math.round(size * scaleX) / size;
    scaleY = Math.round(size * scaleY) / size;
    int x = (int)(renderEmote.getX() + size * (1.0F - scaleX) / 2.0F);
    int y = (int)(renderEmote.getY() + size * (1.0F - scaleY) / 2.0F);
    RenderSystem.setShaderTexture(0, emote.getTextureIdentifier());
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
    int frameNumber = 1;
    if (emote.isAnimated())
      frameNumber = (int)(System.currentTimeMillis() / emote.getFrameTimeMs() % emote.getFrameCount()); 
    DrawableHelper.drawTexture(matrices, x, y,
        
        Math.round(size * scaleX), 
        Math.round(size * scaleY), 0.0F, (emote
        
        .getHeight() * frameNumber), emote
        .getWidth(), emote
        .getHeight(), emote
        .getSheetWidth(), emote
        .getSheetHeight());
  }
}