package fr.paladium.palamod.modules.luckyblock.items.halloween;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSurvivingStone extends Item implements ITooltipWiki {
  public ItemSurvivingStone() {
    func_77656_e(1);
    func_77625_d(1);
    func_77655_b("survivingstone");
    func_111206_d("palamod:survivingstone");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K && 
      !item.func_77951_h()) {
      BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u, player.field_70161_v);
      EntityCustomFallingBlock fallingMain = new EntityCustomFallingBlock(player.func_130014_f_(), (Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.0D, player.field_70177_z + 90.0F, pos, Blocks.field_150403_cj, 0, EntityCustomFallingBlock.Type.SURVIVING, 0, 800, 0, false);
      BlockPos pos2 = new BlockPos(player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v);
      EntityCustomFallingBlock fallingTop = new EntityCustomFallingBlock(player.func_130014_f_(), (Entity)player, player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, 0.0D, player.field_70177_z + 90.0F, pos2, Blocks.field_150403_cj, 0, EntityCustomFallingBlock.Type.SURVIVING, 0, 800, 0, true);
      world.func_72838_d((Entity)fallingTop);
      world.func_72838_d((Entity)fallingMain);
      player.func_70690_d(new PotionEffect(PLuckyBlock.SURVIVING.field_76415_H, 800));
      item.func_77964_b(1);
    } 
    return item;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.1-luckystats-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemSurvivingStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */