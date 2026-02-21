package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEyePatch extends ItemArmor {
  public static final String NAME = "eye_patch";
  
  public ItemEyePatch() {
    super(PArmorMaterial.titane, 0, 0);
    func_77655_b("eye_patch");
    func_111206_d("palamod:eye_patch");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {}
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/eye_patch.png";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemEyePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */