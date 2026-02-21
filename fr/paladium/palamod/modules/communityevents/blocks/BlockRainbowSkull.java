package fr.paladium.palamod.modules.communityevents.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.communityevents.ClientProxy;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityRainbowSkull;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRainbowSkull extends Block {
  public BlockRainbowSkull() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("rainbowskull");
    func_149658_d("palamod:rainbowskull");
    func_149711_c(1.0F);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderRainbowSkull;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityRainbowSkull();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\blocks\BlockRainbowSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */