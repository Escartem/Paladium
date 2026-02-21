package fr.paladium.pet.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.util.ResourceLocation;

public class FileLoaderUtils {
  public static List<ResourceLocation> getResources(String domain, String folder, String extension, File modDirectory) {
    List<ResourceLocation> files = new ArrayList<>();
    enumerateFiles(modDirectory, folder, name -> name.endsWith(extension), files, domain);
    return files;
  }
  
  private static void enumerateFiles(File parent, String folder, Predicate<String> predicate, List<ResourceLocation> locations, String domain) {
    File folderFile = new File(parent, folder);
    if (!folderFile.exists() || !folderFile.isDirectory())
      return; 
    File[] files = folderFile.listFiles();
    if (files == null)
      return; 
    for (File file : files) {
      if (file.isFile() && predicate.test(file.getName())) {
        locations.add(new ResourceLocation(domain, folder + "/" + file.getName()));
      } else if (file.isDirectory()) {
        enumerateFiles(file, folder + "/" + file.getName(), predicate, locations, domain);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\FileLoaderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */