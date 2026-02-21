package fr.paladium.palamod.modules.luckyblock.blocks.christmas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChristmasTree extends Block {
  public BlockChristmasTree() {
    super(Material.field_151575_d);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("christmas_tree");
    func_149658_d("palamod:christmas_tree");
    func_149711_c(2.0F);
    func_149672_a(field_149766_f);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te != null) {
      TileEntityChristmasTree christmasTree = (TileEntityChristmasTree)te;
      if (player.func_70694_bm() != null && christmasTree.getState() < 3 && 
        player.func_70694_bm().func_77973_b() == ItemsRegister.PALADIUM_INGOT) {
        player.field_71071_by.func_146026_a(ItemsRegister.PALADIUM_INGOT);
        christmasTree.setState(christmasTree.getState() + 1);
        return true;
      } 
    } 
    return super.func_149727_a(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderChristmasTree;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityChristmasTree();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\christmas\BlockChristmasTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */