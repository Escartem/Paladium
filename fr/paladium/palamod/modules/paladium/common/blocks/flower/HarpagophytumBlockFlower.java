package fr.paladium.palamod.modules.paladium.common.blocks.flower;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.blocks.BaseBlockFlower;
import fr.paladium.palamod.modules.paladium.common.logics.TileHarpagophytumFlower;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class HarpagophytumBlockFlower extends BaseBlockFlower implements ITileEntityProvider, ITooltipWiki {
  public HarpagophytumBlockFlower(String unlocalizedName) {
    super(unlocalizedName);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileHarpagophytumFlower();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\flower\HarpagophytumBlockFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */