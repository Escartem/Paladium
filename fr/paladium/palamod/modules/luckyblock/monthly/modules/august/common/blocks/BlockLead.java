package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLead extends BlockFalling implements ITooltipInformations {
  public static final String DESCRIPTION = "Certains alchimistes cherchaient à changer le plomb en or. Est-ce que les alchimistes de Paladium y arriveront ?";
  
  public static final String NAME = "lead";
  
  public BlockLead() {
    super(Material.field_151574_g);
    func_149663_c("lead");
    func_149658_d("palamod:lead");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public int func_149692_a(int damage) {
    return damage >> 2;
  }
  
  public void func_149828_a(World world, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_) {
    world.func_72926_e(1022, p_149828_2_, p_149828_3_, p_149828_4_, 0);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Certains alchimistes cherchaient à changer le plomb en or. Est-ce que les alchimistes de Paladium y arriveront ?");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\blocks\BlockLead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */