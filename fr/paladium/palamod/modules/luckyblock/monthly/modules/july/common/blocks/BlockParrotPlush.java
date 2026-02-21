package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityParrotPlush;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockParrotPlush extends Block {
  public static final String NAME = "parrot_plush";
  
  public BlockParrotPlush() {
    super(Material.field_151580_n);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("parrot_plush");
    func_149658_d("palamod:parrot_plush");
    func_149711_c(1.0F);
  }
  
  public boolean hasTileEntity(int metadata) {
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
    return ClientProxy.renderParrotPlush;
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      world.func_72921_c(x, y, z, 2, 2); 
    if (l == 1)
      world.func_72921_c(x, y, z, 5, 2); 
    if (l == 2)
      world.func_72921_c(x, y, z, 3, 2); 
    if (l == 3)
      world.func_72921_c(x, y, z, 4, 2); 
  }
  
  private void setDefaultDirection(World world, int x, int y, int z) {
    if (world.field_72995_K)
      return; 
    Block block = world.func_147439_a(x, y, z - 1);
    Block block1 = world.func_147439_a(x, y, z + 1);
    Block block2 = world.func_147439_a(x - 1, y, z);
    Block block3 = world.func_147439_a(x + 1, y, z);
    byte b0 = 3;
    if (block.func_149730_j() && !block1.func_149730_j())
      b0 = 3; 
    if (block1.func_149730_j() && !block.func_149730_j())
      b0 = 2; 
    if (block2.func_149730_j() && !block3.func_149730_j())
      b0 = 5; 
    if (block3.func_149730_j() && !block2.func_149730_j())
      b0 = 4; 
    world.func_72921_c(x, y, z, b0, 2);
  }
  
  public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityParrotPlush();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\blocks\BlockParrotPlush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */