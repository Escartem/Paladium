package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockKawaiiTNT extends BlockTNT implements ITooltipWiki {
  private int type;
  
  public BlockKawaiiTNT(int type) {
    this.type = type;
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c((type == 0) ? "kawaii_tnt" : "less_kawaii_tnt");
    func_149658_d("palamod:" + ((type == 0) ? "kawaii_tnt" : "less_kawaii_tnt"));
  }
  
  public void func_149723_a(World world, int x, int y, int z, Explosion explosion) {
    if (world.field_72995_K)
      return; 
    if (this.type == 0) {
      world.func_72876_a(explosion.field_77283_e, x, y, z, 1.0F, true);
    } else if (this.type == 1) {
      world.func_72876_a(explosion.field_77283_e, x, y, z, 5.0F, true);
    } 
  }
  
  public void func_150114_a(World world, int x, int y, int z, int meta, EntityLivingBase entity) {
    if (!world.field_72995_K && (
      meta & 0x1) == 1)
      if (this.type == 0) {
        world.func_72876_a((Entity)entity, x, y, z, 1.0F, true);
      } else if (this.type == 1) {
        world.func_72876_a((Entity)entity, x, y, z, 5.0F, true);
      }  
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockKawaiiTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */