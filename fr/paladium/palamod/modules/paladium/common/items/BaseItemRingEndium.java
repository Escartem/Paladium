package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.palajobs.core.utils.IRepairable;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseItemRingEndium extends BaseItem {
  public BaseItemRingEndium(String texture, int durability) {
    super(texture);
    func_77625_d(1);
    func_77656_e(durability);
    func_77625_d(1);
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity;
      for (int i = 0; i < 4; i++) {
        ItemStack armor = player.func_82169_q(i);
        if (armor != null && armor.func_77973_b() instanceof IRepairable) {
          IRepairable repairable = (IRepairable)armor.func_77973_b();
          if (!repairable.isInChest() && repairable.isEndium())
            repairable.repair(armor, stack, (EntityLivingBase)player); 
        } 
        if (armor != null && armor.func_77973_b() instanceof IRepairable) {
          IRepairable repairable = (IRepairable)armor.func_77973_b();
          if (!repairable.isInChest() && repairable.isEndium())
            repairable.repair(armor, stack, (EntityLivingBase)player); 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\BaseItemRingEndium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */