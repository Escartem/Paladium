package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemStarHelmet extends ItemArmor {
  private static final String NAME = "star_helmet";
  
  public ItemStarHelmet() {
    super(PArmorMaterial.paladium, 0, 0);
    func_77655_b("star_helmet");
    func_111206_d("palamod:star_helmet");
    func_77637_a(PLuckyBlock.TAB);
    this.field_77777_bU = 1;
  }
  
  @SideOnly(Side.CLIENT)
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/armors/star_helmet.png";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\ItemStarHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */