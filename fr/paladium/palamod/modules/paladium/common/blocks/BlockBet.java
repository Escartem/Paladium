package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Renderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBet extends Block {
  public BlockBet() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("bet");
    func_149658_d("palamod:bet");
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/bet"); 
    return true;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return PRegister_Renderer.renderBetBlock;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return new TileEntityBetBlock();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockBet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */