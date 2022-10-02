package mods.mednis.id.lv.customemotes.emotes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;

public class Emote {
  private static int currEmoteId = 0;
  
  private int id;
  
  private String name;
  
  private String filename;
  
  private int width;
  
  private int height;
  
  private int frameCount;
  
  private int frameTimeMs;
  
  public Emote(String name, String filename) throws IOException {
    this(name, filename, 0);
  }
  
  public Emote(String name, String filename, int frameTimeMs) throws IOException {
    this.id = currEmoteId++;
    this.name = name;
    this.filename = filename;
    this.frameTimeMs = frameTimeMs;
    Resource resource = MinecraftClient.getInstance().getResourceManager().getResource(getTextureIdentifier());
    try {
      Resource resource2 = MinecraftClient.getInstance().getResourceManager().getResource(getTextureIdentifier());
      try {
        BufferedImage bufferedImage = ImageIO.read(resource.getInputStream());
        if (bufferedImage == null)
          throw new IOException("Failed to load image: " + filename); 
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        if (isAnimated()) {
          this.frameCount = this.height / this.width;
          this.height = this.width;
        } else {
          this.frameCount = 1;
        } 
        if (resource2 != null)
          resource2.close(); 
      } catch (Throwable throwable) {
        if (resource2 != null)
          try {
            resource2.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
      if (resource != null)
        resource.close(); 
    } catch (Throwable throwable) {
      if (resource != null)
        try {
          resource.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }

  public String getSmallName(){ return this.name.toLowerCase();}

  public int getWidth() {
    return this.width;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public int getFrameCount() {
    return this.frameCount;
  }
  
  public int getFrameTimeMs() {
    return this.frameTimeMs;
  }
  
  public boolean isAnimated() {
    return (this.frameTimeMs > 0);
  }
  
  public int getSheetWidth() {
    return this.width;
  }
  
  public int getSheetHeight() {
    return this.height * this.frameCount;
  }
  
  public Identifier getTextureIdentifier() {
    return new Identifier("customemotes:emotes/" + this.filename);
  }
}