package fr.paladium.palamod.modules.paladium.client;

import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.Vec3;

public class ImagePositionData {
  private final Vec3 pos1;
  
  private final Vec3 pos2;
  
  private final String url;
  
  private final Resource texture;
  
  public ImagePositionData(Vec3 pos1, Vec3 pos2, String url, Resource texture) {
    this.pos1 = pos1;
    this.pos2 = pos2;
    this.url = url;
    this.texture = texture;
  }
  
  public Vec3 getPos1() {
    return this.pos1;
  }
  
  public Vec3 getPos2() {
    return this.pos2;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public Resource getTexture() {
    return this.texture;
  }
  
  public ImagePositionData(Vec3 pos1, Vec3 pos2, String url) {
    this(pos1, pos2, url, Resource.of(url).nearest());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\ImagePositionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */