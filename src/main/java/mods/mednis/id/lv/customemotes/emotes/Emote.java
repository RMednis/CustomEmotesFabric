package mods.mednis.id.lv.customemotes.emotes;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;

public class Emote {
  private static int currEmoteId = 0;
  
  private final int id;
  
  private final String name;
  
  private final String filename;
  
  private int width;
  
  private int height;
  
  private final int frameCount;
  
  private final int frameTimeMs;
  
  public Emote(String name, String filename) throws IOException {
    this(name, filename, 0);
  }
  
  public Emote(String name, String filename, int frameTimeMs) throws IOException {
    this.id = currEmoteId++;
    this.name = name;
    this.filename = filename;
    this.frameTimeMs = frameTimeMs;
    Resource resource = MinecraftClient.getInstance().getResourceManager().getResourceOrThrow(getTextureIdentifier());
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
        } // TODO: There may be issues with resource loading exceptions!
      } catch (FileNotFoundException exception) {
          throw new IOException(exception.getMessage());
    }
  }

  public int getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }

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