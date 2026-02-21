package fr.paladium.palaforgeutils.lib.resource;

import java.io.File;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class FileResourceLocation extends ResourceLocation {
  private final File file;
  
  public File getFile() {
    return this.file;
  }
  
  public FileResourceLocation(@NonNull File file) {
    super("file", file.getAbsolutePath());
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    this.file = file;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\resource\FileResourceLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */