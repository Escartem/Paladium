package fr.paladium.palarpg.module.dungeon.client.ui.loading;

import net.minecraft.util.ResourceLocation;

public enum DungeonLoadingState {
  SEARCHING("Recherche du donjon", new ResourceLocation("palarpg", "textures/gui/loading/search.gif"), new ResourceLocation("palarpg", "textures/gui/loading/search.png"), 3000L),
  BUILDING("Construction des salles", new ResourceLocation("palarpg", "textures/gui/loading/build.gif"), new ResourceLocation("palarpg", "textures/gui/loading/build.png"), 3000L),
  SPAWNING("Apparition des mobs", new ResourceLocation("palarpg", "textures/gui/loading/spawn.gif"), new ResourceLocation("palarpg", "textures/gui/loading/spawn.png"), 3000L);
  
  DungeonLoadingState(String title, ResourceLocation gif, ResourceLocation png, long duration) {
    this.title = title;
    this.gif = gif;
    this.png = png;
    this.duration = duration;
  }
  
  private final String title;
  
  private final ResourceLocation gif;
  
  private final ResourceLocation png;
  
  private final long duration;
  
  public String getTitle() {
    return this.title;
  }
  
  public ResourceLocation getGif() {
    return this.gif;
  }
  
  public ResourceLocation getPng() {
    return this.png;
  }
  
  public long getDuration() {
    return this.duration;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\loading\UIDungeonLoading$DungeonLoadingState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */