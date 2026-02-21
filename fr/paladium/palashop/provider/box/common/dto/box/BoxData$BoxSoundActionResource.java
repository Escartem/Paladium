package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class BoxSoundActionResource implements BoxData.IBoxResource {
  private Map<ShopRarity, ResourceLocation> soundsByRarity;
  
  private Map<BoxReward.Type, ResourceLocation> soundsByType;
  
  public void setSoundsByRarity(Map<ShopRarity, ResourceLocation> soundsByRarity) {
    this.soundsByRarity = soundsByRarity;
  }
  
  public void setSoundsByType(Map<BoxReward.Type, ResourceLocation> soundsByType) {
    this.soundsByType = soundsByType;
  }
  
  public Map<ShopRarity, ResourceLocation> getSoundsByRarity() {
    return this.soundsByRarity;
  }
  
  public Map<BoxReward.Type, ResourceLocation> getSoundsByType() {
    return this.soundsByType;
  }
  
  public void create(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    this.soundsByRarity = new HashMap<>();
    for (ShopRarity rarity : ShopRarity.values()) {
      ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/action/item/" + rarity.name().toLowerCase() + ".ogg");
      if (MCResource.of(sound).exists())
        this.soundsByRarity.put(rarity, sound); 
    } 
    this.soundsByType = new HashMap<>();
    for (BoxReward.Type type : BoxReward.Type.values()) {
      ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/action/boost/" + type.name().toLowerCase() + ".ogg");
      if (MCResource.of(sound).exists())
        this.soundsByType.put(type, sound); 
    } 
  }
  
  public void build() {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
      return; 
    if (this.soundsByRarity == null || this.soundsByRarity.isEmpty())
      throw new IllegalStateException("Sounds must be set before building BoxSoundIdleResource"); 
    if (!this.soundsByRarity.containsKey(ShopRarity.COMMON))
      throw new IllegalStateException("Common rarity sound must be set before building BoxSoundIdleResource"); 
    if (this.soundsByType == null || this.soundsByType.isEmpty())
      throw new IllegalStateException("Sounds by type must be set before building BoxSoundActionResource"); 
    if (!MCResource.of(this.soundsByRarity.get(ShopRarity.COMMON)).exists())
      throw new IllegalArgumentException("Common rarity sound file does not exist: " + this.soundsByRarity.get(ShopRarity.COMMON)); 
  }
  
  public ResourceLocation getSound(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return this.soundsByRarity.getOrDefault(rarity, this.soundsByRarity.get(ShopRarity.COMMON));
  }
  
  public ResourceLocation getSound(@NonNull BoxReward.Type type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return this.soundsByType.get(type);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData$BoxSoundActionResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */