package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palajobs.core.quest.types.ActionQuest;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.items.BaseItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseItemBerry extends BaseItem implements ITooltipWiki {
  public BaseItemBerry(String unlocalizedName, String texture) {
    super(texture);
    func_77655_b(unlocalizedName);
    func_77637_a((CreativeTabs)Registry.PLANTS_TAB);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (isXPBerry()) {
      boolean isCompressed = (this == ItemsRegister.COMPRESSED_XP_BERRY);
      int xpValue = 0;
      if (!isCompressed) {
        xpValue = field_77697_d.nextInt(2) + 2;
      } else {
        for (int i = 0; i < 9; i++)
          xpValue += field_77697_d.nextInt(2) + 2; 
      } 
      EntityXPOrb entity = new EntityXPOrb(world, player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, xpValue);
      spawnEntity(player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, (Entity)entity, world, player);
      if (!player.field_71075_bZ.field_75098_d)
        stack.field_77994_a--; 
      ActionQuest.performCheck(player, "USE_XPBERRY", 1);
    } 
    return stack;
  }
  
  private boolean isXPBerry() {
    return (this == ItemsRegister.XP_BERRY || this == ItemsRegister.COMPRESSED_XP_BERRY);
  }
  
  private static void spawnEntity(double x, double y, double z, Entity entity, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      world.func_72838_d(entity); 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\BaseItemBerry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */