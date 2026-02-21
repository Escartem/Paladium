package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityHappyNewYearFirework;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHappyNewYearFirework extends ItemFirework implements ITooltipWiki {
  public ItemHappyNewYearFirework() {
    func_77655_b("happy_new_year");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:happy_new_year_firework");
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    int yaw = (int)player.field_70177_z;
    yaw %= 360;
    int facing = Math.abs(yaw) / 45;
    if (yaw < 0 && (facing == 1 || facing == 2)) {
      facing = 6;
    } else if (yaw < 0 && (facing == 5 || facing == 6)) {
      facing = 1;
    } 
    if (item != null) {
      world.func_72838_d((Entity)new EntityHappyNewYearFirework(world, x, y, z, facing));
      item.field_77994_a--;
    } 
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemHappyNewYearFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */