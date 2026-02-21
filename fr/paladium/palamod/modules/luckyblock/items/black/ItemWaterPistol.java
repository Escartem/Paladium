package fr.paladium.palamod.modules.luckyblock.items.black;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityWaterDrop;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWaterPistol extends Item implements ITooltipWiki {
  public ItemWaterPistol() {
    func_77656_e(100);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (stack.func_77960_j() < stack.func_77958_k()) {
      stack.func_77972_a(1, (EntityLivingBase)player);
      if (!world.field_72995_K)
        world.func_72838_d((Entity)new EntityWaterDrop(world, (EntityLivingBase)player)); 
    } 
    return stack;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#3.-lucky-block-noir";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\black\ItemWaterPistol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */