package fr.paladium.palarpg.module.equipment.common.loader.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Objects;

public class TextureFile {
  private final String name;
  
  private final String texture;
  
  private final String mcmeta;
  
  private final boolean exists;
  
  private final boolean mcmetaExists;
  
  public String getName() {
    return this.name;
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public String getMcmeta() {
    return this.mcmeta;
  }
  
  public boolean isExists() {
    return this.exists;
  }
  
  public boolean isMcmetaExists() {
    return this.mcmetaExists;
  }
  
  public TextureFile(File texture) {
    try {
      File mcmetaFile = new File(texture.getParentFile(), texture.getName() + ".mcmeta");
      this.name = texture.getName();
      this.texture = Base64.getEncoder().encodeToString(Files.readAllBytes(texture.toPath()));
      this.exists = texture.exists();
      this.mcmetaExists = mcmetaFile.exists();
      if (this.mcmetaExists) {
        this.mcmeta = Base64.getEncoder().encodeToString(Files.readAllBytes(mcmetaFile.toPath()));
      } else {
        this.mcmeta = null;
      } 
    } catch (IOException e) {
      throw new RuntimeException("Unable to read texture file: " + texture, e);
    } 
  }
  
  public boolean exists() {
    return this.exists;
  }
  
  public boolean isAnimated() {
    return this.mcmetaExists;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.texture });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    TextureFile other = (TextureFile)obj;
    String otherTexture = other.texture;
    if (this.texture == null && otherTexture == null)
      return true; 
    if ((this.texture == null && otherTexture != null) || (this.texture != null && otherTexture == null))
      return false; 
    return this.texture.equals(otherTexture);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loade\\util\TextureFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */