package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class BoxSoundSpinResource implements BoxData.IBoxResource {
  private ResourceLocation click;
  
  private ResourceLocation riser;
  
  private Map<ShopRarity, ResourceLocation> sounds;
  
  public void setClick(ResourceLocation click) {
    this.click = click;
  }
  
  public void setRiser(ResourceLocation riser) {
    this.riser = riser;
  }
  
  public void setSounds(Map<ShopRarity, ResourceLocation> sounds) {
    this.sounds = sounds;
  }
  
  public ResourceLocation getClick() {
    return this.click;
  }
  
  public ResourceLocation getRiser() {
    return this.riser;
  }
  
  public Map<ShopRarity, ResourceLocation> getSounds() {
    return this.sounds;
  }
  
  public void create(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    this.click = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/spin/click.ogg");
    this.riser = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/spin/riser.ogg");
    this.sounds = new HashMap<>();
    for (ShopRarity rarity : ShopRarity.values()) {
      ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/spin/" + rarity.name().toLowerCase() + ".ogg");
      if (MCResource.of(sound).exists())
        this.sounds.put(rarity, sound); 
    } 
  }
  
  public void build() {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
      return; 
    if (this.click == null)
      throw new IllegalStateException("Click sound must be set before building BoxSoundSpinResource"); 
    if (this.riser == null)
      throw new IllegalStateException("Riser sound must be set before building BoxSoundSpinResource"); 
    if (this.sounds == null || this.sounds.isEmpty())
      throw new IllegalStateException("Sounds must be set before building BoxSoundIdleResource"); 
    if (!this.sounds.containsKey(ShopRarity.COMMON))
      throw new IllegalStateException("Common rarity sound must be set before building BoxSoundIdleResource"); 
    if (!MCResource.of(this.click).exists())
      throw new IllegalArgumentException("Click sound file does not exist: " + this.click); 
    if (!MCResource.of(this.riser).exists())
      throw new IllegalArgumentException("Riser sound file does not exist: " + this.riser); 
    if (!MCResource.of(this.sounds.get(ShopRarity.COMMON)).exists())
      throw new IllegalArgumentException("Common rarity sound file does not exist: " + this.sounds.get(ShopRarity.COMMON)); 
  }
  
  public ResourceLocation getSound(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return this.sounds.getOrDefault(rarity, this.sounds.get(ShopRarity.COMMON));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData$BoxSoundSpinResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */