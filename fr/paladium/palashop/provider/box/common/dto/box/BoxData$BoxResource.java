package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;

public class BoxResource implements BoxData.IBoxResource {
  private ResourceLocation model;
  
  private ResourceLocation animation;
  
  private ResourceLocation texture;
  
  private ResourceLocation thumbnail;
  
  private ResourceLocation item;
  
  public ResourceLocation getModel() {
    return this.model;
  }
  
  public ResourceLocation getAnimation() {
    return this.animation;
  }
  
  public ResourceLocation getTexture() {
    return this.texture;
  }
  
  public ResourceLocation getThumbnail() {
    return this.thumbnail;
  }
  
  public ResourceLocation getItem() {
    return this.item;
  }
  
  public BoxData.BoxSoundResource getSound() {
    return this.sound;
  }
  
  public BoxData.BoxOverlayOpeningResource getOpeningOverlay() {
    return this.openingOverlay;
  }
  
  public BoxData.BoxOverlayWaitingResource getWaitingOverlay() {
    return this.waitingOverlay;
  }
  
  public LindwormModel<IAnimatable> getLindwormModel() {
    return this.lindwormModel;
  }
  
  public Resource getThumbnailResource() {
    return this.thumbnailResource;
  }
  
  public Resource getItemResource() {
    return this.itemResource;
  }
  
  private final BoxData.BoxSoundResource sound = new BoxData.BoxSoundResource();
  
  private final BoxData.BoxOverlayOpeningResource openingOverlay = new BoxData.BoxOverlayOpeningResource();
  
  private final BoxData.BoxOverlayWaitingResource waitingOverlay = new BoxData.BoxOverlayWaitingResource();
  
  @SideOnly(Side.CLIENT)
  private transient LindwormModel<IAnimatable> lindwormModel;
  
  @SideOnly(Side.CLIENT)
  private transient Resource thumbnailResource;
  
  @SideOnly(Side.CLIENT)
  private transient Resource itemResource;
  
  public void create(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    this.model = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/model.json");
    this.animation = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/animation.json");
    this.texture = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/texture.png");
    this.thumbnail = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/thumbnail.png");
    this.item = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/item.png");
    this.sound.create(assets);
    this.openingOverlay.create(assets);
    this.waitingOverlay.create(assets);
  }
  
  public void build() {
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      if (this.model == null)
        throw new IllegalStateException("Model must be set before building BoxData"); 
      if (this.texture == null)
        throw new IllegalStateException("Texture must be set before building BoxData"); 
      if (!MCResource.of(this.model).exists())
        throw new IllegalArgumentException("Model file does not exist: " + this.model); 
      if (!MCResource.of(this.texture).exists())
        throw new IllegalArgumentException("Texture file does not exist: " + this.texture); 
      if (this.animation != null && !MCResource.of(this.animation).exists())
        this.animation = null; 
      if (!MCResource.of(this.thumbnail).exists())
        throw new IllegalArgumentException("Thumbnail file does not exist: " + this.thumbnail); 
      if (this.item == null)
        throw new IllegalStateException("Item thumbnail must be set before building BoxData"); 
      if (!MCResource.of(this.item).exists())
        throw new IllegalArgumentException("Item thumbnail does not exist: " + this.item); 
      this.lindwormModel = new LindwormModel(LindwormLoader.loadModel(this.model), (this.animation != null) ? LindwormLoader.loadAnimation(this.animation) : null, Resource.of(this.texture).nearest(), (m, e) -> (new LindwormAnimatable(m, e, 0.0F, new LindwormAnimationType[0])).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
      this.thumbnailResource = Resource.of(this.thumbnail).nearest();
      this.itemResource = Resource.of(this.item).nearest();
    } 
    if (this.openingOverlay == null)
      throw new IllegalStateException("Opening overlay must be set before building BoxData"); 
    this.openingOverlay.build();
    if (this.waitingOverlay == null)
      throw new IllegalStateException("Waiting overlay must be set before building BoxData"); 
    this.waitingOverlay.build();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData$BoxResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */