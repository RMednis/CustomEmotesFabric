package mods.mednis.id.lv.customemotes.text;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.text.*;

public class TextReaderVisitor implements CharacterVisitor {
  private final List<TextPart> textParts = new ArrayList<>();
  
  public boolean accept(int val, Style style, int currCharInt) {
    this.textParts.add(new TextPart(style, (char)currCharInt));
    return true;
  }
  
  public void replaceBetween(int beginIndex, int endIndex, String text, Style style) {
    deleteBetween(beginIndex, endIndex);
    insertAt(beginIndex, text, style);
  }
  
  public void deleteBetween(int beginIndex, int endIndex) {
    if (endIndex > beginIndex) {
      this.textParts.subList(beginIndex, endIndex).clear();
    }
  }
  
  public void insertAt(int index, String text, Style style) {
    for (int i = 0; i < text.length(); i++)
      this.textParts.add(index + i, new TextPart(style, text.charAt(i))); 
  }
  
  public OrderedText getOrderedText() {

    MutableText text = Text.empty();

    for (TextPart textPart : this.textParts)
      text.append(Text.literal(Character.toString(textPart.getChr())).setStyle(textPart.getStyle()));

    return text.asOrderedText();
  }
  
  public String getString() {
    StringBuilder sb = new StringBuilder();
    for (TextPart textPart : this.textParts)
      sb.append(textPart.getChr()); 
    return sb.toString();
  }
}