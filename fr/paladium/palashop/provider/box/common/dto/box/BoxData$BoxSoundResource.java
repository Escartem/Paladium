package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class BoxSoundResource implements BoxData.IBoxResource {
  private ResourceLocation idle;
  
  private ResourceLocation open;
  
  private ResourceLocation close;
  
  private ResourceLocation broadcast;
  
  public void setIdle(ResourceLocation idle) {
    this.idle = idle;
  }
  
  public void setOpen(ResourceLocation open) {
    this.open = open;
  }
  
  public void setClose(ResourceLocation close) {
    this.close = close;
  }
  
  public void setBroadcast(ResourceLocation broadcast) {
    this.broadcast = broadcast;
  }
  
  public ResourceLocation getIdle() {
    return this.idle;
  }
  
  public ResourceLocation getOpen() {
    return this.open;
  }
  
  public ResourceLocation getClose() {
    return this.close;
  }
  
  public ResourceLocation getBroadcast() {
    return this.broadcast;
  }
  
  public BoxData.BoxSoundFastResource getFast() {
    return this.fast;
  }
  
  public BoxData.BoxSoundSpinResource getSpin() {
    return this.spin;
  }
  
  public BoxData.BoxSoundActionResource getAction() {
    return this.action;
  }
  
  private final BoxData.BoxSoundFastResource fast = new BoxData.BoxSoundFastResource();
  
  private final BoxData.BoxSoundSpinResource spin = new BoxData.BoxSoundSpinResource();
  
  private final BoxData.BoxSoundActionResource action = new BoxData.BoxSoundActionResource();
  
  public void create(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    this.idle = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/idle.ogg");
    this.open = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/open.ogg");
    this.close = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/close.ogg");
    this.broadcast = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/broadcast.ogg");
    this.fast.create(assets);
    this.spin.create(assets);
    this.action.create(assets);
  }
  
  public void build() {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
      return; 
    if (this.idle == null)
      throw new IllegalStateException("Idle sound must be set before building BoxSoundResource"); 
    if (this.open == null)
      throw new IllegalStateException("Open sound must be set before building BoxSoundResource"); 
    if (this.close == null)
      throw new IllegalStateException("Close sound must be set before building BoxSoundResource"); 
    if (this.broadcast == null)
      throw new IllegalStateException("Broadcast sound must be set before building BoxSoundResource"); 
    if (!MCResource.of(this.idle).exists())
      throw new IllegalArgumentException("Idle sound file does not exist: " + this.idle); 
    if (!MCResource.of(this.open).exists())
      throw new IllegalArgumentException("Open sound file does not exist: " + this.open); 
    if (!MCResource.of(this.close).exists())
      throw new IllegalArgumentException("Close sound file does not exist: " + this.close); 
    if (!MCResource.of(this.broadcast).exists())
      throw new IllegalArgumentException("Broadcast sound file does not exist: " + this.broadcast); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData$BoxSoundResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */