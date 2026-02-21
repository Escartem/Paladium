package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class BoxOverlayWaitingResource implements BoxData.IBoxResource {
  private ResourceLocation background;
  
  private ResourceLocation foreground;
  
  @SideOnly(Side.CLIENT)
  private transient Resource backgroundResource;
  
  @SideOnly(Side.CLIENT)
  private transient Resource foregroundResource;
  
  public ResourceLocation getBackground() {
    return this.background;
  }
  
  public ResourceLocation getForeground() {
    return this.foreground;
  }
  
  public Resource getBackgroundResource() {
    return this.backgroundResource;
  }
  
  public Resource getForegroundResource() {
    return this.foregroundResource;
  }
  
  public void create(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    this.background = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/waiting/background.png");
    this.foreground = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/waiting/foreground.png");
  }
  
  public void build() {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
      return; 
    if (this.background == null)
      throw new IllegalStateException("Background must be set before building BoxOverlayResource"); 
    if (this.foreground == null)
      throw new IllegalStateException("Foreground must be set before building BoxOverlayResource"); 
    if (!MCResource.of(this.background).exists())
      throw new IllegalArgumentException("Background file does not exist: " + this.background); 
    if (!MCResource.of(this.foreground).exists())
      throw new IllegalArgumentException("Foreground file does not exist: " + this.foreground); 
    this.backgroundResource = Resource.of(this.background).linear();
    this.foregroundResource = Resource.of(this.foreground).linear();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData$BoxOverlayWaitingResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */