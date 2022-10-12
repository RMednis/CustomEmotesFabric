package mods.mednis.id.lv.customemotes.render;

import mods.mednis.id.lv.customemotes.emotes.Emote;

public class RenderEmote {
  private final Emote emote;
  
  private final float x;
  
  private final float y;
  
  public RenderEmote(Emote emote, float x, float y) {
    this.emote = emote;
    this.x = x;
    this.y = y;
  }
  
  public Emote getEmote() {
    return this.emote;
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getY() {
    return this.y;
  }
}