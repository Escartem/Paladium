package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class BoxSoundFastResource implements BoxData.IBoxResource {
  private Map<ShopRarity, ResourceLocation> sounds;
  
  public void setSounds(Map<ShopRarity, ResourceLocation> sounds) {
    this.sounds = sounds;
  }
  
  public Map<ShopRarity, ResourceLocation> getSounds() {
    return this.sounds;
  }
  
  public void create(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    this.sounds = new HashMap<>();
    for (ShopRarity rarity : ShopRarity.values()) {
      ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/fast/" + rarity.name().toLowerCase() + ".ogg");
      if (MCResource.of(sound).exists())
        this.sounds.put(rarity, sound); 
    } 
  }
  
  public void build() {
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
      return; 
    if (this.sounds == null || this.sounds.isEmpty())
      throw new IllegalStateException("Sounds must be set before building BoxSoundIdleResource"); 
    if (!this.sounds.containsKey(ShopRarity.COMMON))
      throw new IllegalStateException("Common rarity sound must be set before building BoxSoundIdleResource"); 
    if (!MCResource.of(this.sounds.get(ShopRarity.COMMON)).exists())
      throw new IllegalArgumentException("Common rarity sound file does not exist: " + this.sounds.get(ShopRarity.COMMON)); 
  }
  
  public ResourceLocation getSound(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return this.sounds.getOrDefault(rarity, this.sounds.get(ShopRarity.COMMON));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData$BoxSoundFastResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */