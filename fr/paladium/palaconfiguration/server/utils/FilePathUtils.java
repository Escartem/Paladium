package fr.paladium.palaconfiguration.server.utils;

import java.io.File;
import lombok.NonNull;

public class FilePathUtils {
  @NonNull
  public static String normalize(@NonNull File file) {
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    return normalize(file.getAbsolutePath());
  }
  
  @NonNull
  public static String normalize(@NonNull String path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    String normalized = path.replace(File.separator, "/").replace("\\", "/").replace("//", "/").replace(":/", ":");
    if (normalized.startsWith("/"))
      return normalized.substring(1); 
    if (normalized.endsWith("/"))
      return normalized.substring(0, normalized.length() - 1); 
    return normalized;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\serve\\utils\FilePathUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */