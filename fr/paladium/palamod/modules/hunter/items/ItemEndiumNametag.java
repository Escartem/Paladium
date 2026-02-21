package fr.paladium.palamod.modules.hunter.items;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.hunter.networks.PlayerEndiumNameTagProperties;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;

public class ItemEndiumNametag extends ItemNameTag {
  public ItemEndiumNametag() {
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
    func_77655_b("endium_nametag");
    func_111206_d("palamod:endium_nametag");
  }
  
  public boolean func_111207_a(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
    if (!item.func_82837_s())
      return false; 
    if (entity instanceof EntityLiving) {
      if (!player.field_70170_p.field_72995_K && 
        PConfigs.server_minage_enabled)
        return super.func_111207_a(item, player, entity); 
      EntityLiving entityliving = (EntityLiving)entity;
      entityliving.getEntityData().func_74778_a("defaultName", entityliving.func_70005_c_());
      entityliving.func_94058_c("§b" + item.func_82833_r());
      entityliving.func_110163_bv();
      item.field_77994_a--;
      if (!player.field_70170_p.field_72995_K)
        PlayerEndiumNameTagProperties.get(player).addEntity((Entity)entity); 
      return true;
    } 
    return super.func_111207_a(item, player, entity);
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lore, boolean flag) {
    lore.add("§eVous ne pouvez avoir que 3 entités renommées en même temps");
    super.func_77624_a(item, player, lore, flag);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemEndiumNametag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */