package mods.mednis.id.lv.customemotes.text;

import net.minecraft.text.Style;

public class TextPart {
  private final Style style;
  
  private final char chr;
  
  public TextPart(Style style, char chr) {
    this.style = style;
    this.chr = chr;
  }
  
  public Style getStyle() {
    return this.style;
  }
  
  public char getChr() {
    return this.chr;
  }
}
