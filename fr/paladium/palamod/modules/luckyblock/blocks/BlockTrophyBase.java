package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.ILuckyStatsOwnedReward;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;

public class BlockTrophyBase extends Block implements ILuckyStatsOwnedReward {
  protected BlockTrophyBase() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public String getValue(EntityPlayer player) {
    return player.func_70005_c_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockTrophyBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */