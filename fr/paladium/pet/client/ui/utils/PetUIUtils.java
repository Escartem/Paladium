package fr.paladium.pet.client.ui.utils;

import net.minecraft.util.ResourceLocation;

public class PetUIUtils {
  public static ResourceLocation getResourceFromTexturePath(String path) {
    ResourceLocation resource = null;
    if (path == null)
      return unknownResource(); 
    if (path.contains(":")) {
      String[] split = path.split(":");
      resource = new ResourceLocation(split[0], split[1]);
    } else {
      resource = new ResourceLocation(path);
    } 
    return resource;
  }
  
  public static ResourceLocation getGeckoAnimationFileLocation(String folder, String modelName) {
    return new ResourceLocation("palapet", "animations/" + folder + "/" + modelName + ".animation.json");
  }
  
  public static ResourceLocation getAdditionalDataFileLocation(String modelName) {
    return new ResourceLocation("palapet", "data/" + modelName + ".json");
  }
  
  public static ResourceLocation getGeckoModelLocation(String folder, String modelName) {
    return new ResourceLocation("palapet", "geo/" + folder + "/" + modelName + ".geo.json");
  }
  
  public static ResourceLocation getGeckoTextureLocation(String folder, String modelName) {
    return new ResourceLocation("palapet", "textures/" + folder + "/" + modelName + ".png");
  }
  
  public static ResourceLocation unknownResource() {
    return new ResourceLocation("palapet", "textures/ui/home/unknown_logo.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\PetUIUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */