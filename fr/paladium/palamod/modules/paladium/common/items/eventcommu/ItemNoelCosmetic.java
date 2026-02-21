package fr.paladium.palamod.modules.paladium.common.items.eventcommu;

import fr.welsymc.guardiangolem.common.items.ItemCosmetic;
import fr.welsymc.guardiangolem.common.items.cosmetics.skins.ICosmeticSkin;
import net.minecraft.util.ResourceLocation;

public class ItemNoelCosmetic extends ItemCosmetic implements ICosmeticSkin {
  private final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/golem/cosmetic/christmass_golem.png");
  
  public ItemNoelCosmetic(String name) {
    super(name);
  }
  
  public ResourceLocation texture() {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\eventcommu\ItemNoelCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */