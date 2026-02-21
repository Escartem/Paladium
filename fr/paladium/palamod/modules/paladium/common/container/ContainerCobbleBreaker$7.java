package fr.paladium.palamod.modules.paladium.common.container;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palajobs.core.pojo.objectives.types.CobbleBreakerObjective;
import fr.paladium.palamod.modules.achievements.types.CobbleBreakerAchievement;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack item) {
    super.func_82870_a(player, item);
    if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
      CobbleBreakerObjective.performCheck(player, 
          (ContainerCobbleBreaker.access$000(ContainerCobbleBreaker.this) == null) ? item : ContainerCobbleBreaker.access$000(ContainerCobbleBreaker.this));
      CobbleBreakerAchievement.performCheck(player, (item.field_77994_a == 0) ? 1 : item.field_77994_a);
    } 
  }
  
  public boolean func_75214_a(@Nullable ItemStack stack) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ContainerCobbleBreaker$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */