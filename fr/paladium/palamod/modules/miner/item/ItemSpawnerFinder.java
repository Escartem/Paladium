package fr.paladium.palamod.modules.miner.item;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpawnerFinder extends ItemMiner implements ITooltipWiki {
  public ItemSpawnerFinder() {
    super("spawner_finder");
    func_77656_e(180000);
    func_77625_d(1);
  }
  
  public void func_77663_a(ItemStack item, World world, Entity player, int p_77663_4_, boolean p_77663_5_) {
    super.func_77663_a(item, world, player, p_77663_4_, p_77663_5_);
    if (!world.field_72995_K && p_77663_5_ && player instanceof EntityPlayer && 
      world.func_72820_D() % 20L == 0L) {
      item.func_77972_a(20, (EntityLivingBase)player);
      UseItemAchievement.performCheck((EntityPlayer)player, item, "SPAWNER_FINDER");
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#2.-le-spawner-finder";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemSpawnerFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */