package fr.paladium.palamod.modules.paladium.common.items.explosive;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDynamiteBig extends BaseItemDynamite {
  public ItemDynamiteBig(String unlocalizedName, String texture) {
    super(unlocalizedName, texture);
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (CommonModule.getInstance().getConfig().getServerType() == ServerType.FARMLAND || (!world.field_72995_K && WorldGuardUtils.isItemEffectBlocked(world, entityplayer.field_70165_t, entityplayer.field_70163_u, entityplayer.field_70161_v, Item.func_150891_b(itemstack.func_77973_b()))))
      return itemstack; 
    if (entityplayer.field_71071_by.func_146026_a((Item)this) && 
      !world.field_72995_K) {
      world.func_72838_d((Entity)new DynamiteEntity(world, (EntityLivingBase)entityplayer, 40 + Item.field_77697_d.nextInt(10), DynamiteEntity.BIG));
      StatsEep statsEep = StatsEep.get((Entity)entityplayer);
      statsEep.increaseDynamiteUsed();
    } 
    return itemstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\explosive\ItemDynamiteBig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */