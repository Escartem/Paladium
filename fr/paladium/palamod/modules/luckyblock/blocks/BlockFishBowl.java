package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockFishBowl extends BlockGlass implements ITooltipWiki {
  public BlockFishBowl(Material material) {
    super(material, true);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("fishBowl");
    func_149658_d("minecraft:glass");
    func_149711_c(1.0F);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFishBowl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */