package mods.mednis.id.lv.customemotes.text;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.LiteralText;
import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.OrderedText;

public class TextReaderVisitor implements CharacterVisitor {
  private List<TextPart> textParts = new ArrayList<>();
  
  public boolean accept(int val, Style style, int currCharInt) {
    this.textParts.add(new TextPart(style, (char)currCharInt));
    return true;
  }
  
  public void replaceBetween(int beginIndex, int endIndex, String text, Style style) {
    deleteBetween(beginIndex, endIndex);
    insertAt(beginIndex, text, style);
  }
  
  public void deleteBetween(int beginIndex, int endIndex) {
    for (int i = endIndex - 1; i >= beginIndex; i--)
      this.textParts.remove(i); 
  }
  
  public void insertAt(int index, String text, Style style) {
    for (int i = 0; i < text.length(); i++)
      this.textParts.add(index + i, new TextPart(style, text.charAt(i))); 
  }
  
  public OrderedText getOrderedText() {
    LiteralText literalText = new LiteralText("");
    for (TextPart textPart : this.textParts)
      literalText.append((new LiteralText(Character.toString(textPart.getChr()))).setStyle(textPart.getStyle()));
    return literalText.asOrderedText();
  }
  
  public String getString() {
    StringBuilder sb = new StringBuilder();
    for (TextPart textPart : this.textParts)
      sb.append(textPart.getChr()); 
    return sb.toString();
  }
}