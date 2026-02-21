package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palajobs.core.utils.IRepairable;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseItemRing extends BaseItem implements ITooltipWiki {
  public BaseItemRing(String texture, int durability) {
    super(texture);
    func_77625_d(1);
    func_77656_e(durability);
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity;
      for (int i = 0; i < 4; i++) {
        ItemStack armor = player.func_82169_q(i);
        if (armor != null && armor.func_77973_b() instanceof IRepairable) {
          IRepairable repairable = (IRepairable)armor.func_77973_b();
          if (!repairable.isInChest() && !repairable.isEndium())
            repairable.repair(armor, stack, (EntityLivingBase)player); 
        } 
        if (armor != null && armor.func_77973_b() instanceof IRepairable) {
          IRepairable repairable = (IRepairable)armor.func_77973_b();
          if (!repairable.isInChest() && !repairable.isEndium())
            repairable.repair(armor, stack, (EntityLivingBase)player); 
        } 
      } 
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#3.-les-rings";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\BaseItemRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */