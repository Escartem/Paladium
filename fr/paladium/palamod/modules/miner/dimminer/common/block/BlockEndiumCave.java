package fr.paladium.palamod.modules.miner.dimminer.common.block;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.miner.dimminer.client.render.BlockEndiumCaveRenderer;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityEndiumCaveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEndiumCave extends Block implements ITileEntityProvider, ITooltipWiki {
  public BlockEndiumCave() {
    super(Material.field_151592_s);
    func_149663_c("endium_cave_block");
    func_149658_d("palamod:caveblock/endium_cave_block");
    func_149711_c(4.0F);
    func_149752_b(4.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public void func_149714_e(World world, int x, int y, int z, int meta) {
    if (world.field_72995_K && FMLCommonHandler.instance().getSide() == Side.CLIENT)
      BlockEndiumCaveRenderer.lastChestUSPUpdate = 0L; 
  }
  
  public int func_149645_b() {
    return 0;
  }
  
  public boolean func_149662_c() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityEndiumCaveBlock();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#7.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\BlockEndiumCave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */