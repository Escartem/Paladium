package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityStatue;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Renderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStatue extends Block {
  private int angle;
  
  public BlockStatue() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("statue");
    func_149711_c(5.0F);
  }
  
  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack itemStack) {
    if (!world.field_72995_K) {
      int angle = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      angle++;
      angle %= 4;
      this.angle = angle;
      String playerUUID = null;
      if (itemStack.func_77978_p() != null)
        playerUUID = itemStack.func_77978_p().func_74779_i("playerUUID"); 
      ((TileEntityStatue)world.func_147438_o(i, j, k)).setPlayerUUID(playerUUID);
      ((TileEntityStatue)world.func_147438_o(i, j, k)).setAngle(this.angle);
    } 
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    if (!world.field_72995_K) {
      TileEntityStatue statue = (TileEntityStatue)world.func_147438_o(x, y, z);
      ItemStack stack = new ItemStack(this, 1);
      NBTTagCompound tag = new NBTTagCompound();
      if (statue.getPlayerUUID() != null)
        tag.func_74778_a("playerUUID", statue.getPlayerUUID()); 
      stack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(world, x, y, z);
      entityitem.field_145804_b = 0;
      entityitem.func_92058_a(stack);
      world.func_72838_d((Entity)entityitem);
    } 
    if (hasTileEntity(meta))
      world.func_147475_p(x, y, z); 
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {}
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return PRegister_Renderer.renderStatue;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityStatue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockStatue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */