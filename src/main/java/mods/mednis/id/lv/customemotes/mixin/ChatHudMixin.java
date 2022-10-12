package mods.mednis.id.lv.customemotes.mixin;

import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.OrderedText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import mods.mednis.id.lv.customemotes.render.EmoteRenderHelper;
import mods.mednis.id.lv.customemotes.render.RenderEmote;
import mods.mednis.id.lv.customemotes.text.TextReaderVisitor;

@Mixin({net.minecraft.client.gui.hud.ChatHud.class})
public abstract class ChatHudMixin {
  private final Logger MOD_LOGGER = LogManager.getLogger("customemotes.mixin.ChatHudMixin");
  
  @Shadow
  @Final
  private MinecraftClient client;
  
  @Redirect(method = {"render"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/OrderedText;FFI)I"))
  private int drawWithShadow(TextRenderer textRenderer, MatrixStack matrices, OrderedText text, float x, float y, int color) {
    TextReaderVisitor textReaderVisitor = new TextReaderVisitor();
    text.accept((CharacterVisitor) textReaderVisitor);
    float emoteSize = textRenderer.getWidth("  ");
    float emoteAlpha = (color >> 24 & 0xFF) / 255.0F;
    List<RenderEmote> renderEmoteList = EmoteRenderHelper.extractEmotes(textReaderVisitor, textRenderer, x, y);
    matrices.translate(0.0D, -0.5D, 0.0D);
    for (RenderEmote renderEmote : renderEmoteList)
      EmoteRenderHelper.drawEmote(matrices, renderEmote, emoteSize, emoteAlpha, 1.05F, 1.5F); 
    matrices.translate(0.0D, 0.5D, 0.0D);
    return textRenderer.draw(matrices, textReaderVisitor.getOrderedText(), x, y, color);
  }
}

